package com.example.githubclient.mvp.model.repo.retrofit

import com.example.githubclient.mvp.model.api.IDataSource
import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.entity.room.Database
import com.example.githubclient.mvp.model.entity.room.RoomGithubRepository
import com.example.githubclient.mvp.model.network.INetworkStatus
import com.example.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositoriesRepo(private val api: IDataSource, private val networkStatus: INetworkStatus, private val db: Database) :
    IGithubRepositoriesRepo {
    override fun getRepositories(user: GithubUser): Single<List<GithubRepository>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            user.reposUrl?.let {url ->
                api.getRepositories(url).flatMap {repositories ->
                    Single.fromCallable{
                        val roomUser = user.login?.let { db.userDao.findByLogin(it) } ?: throw RuntimeException("No such user in cache")
                        val roomRepos = repositories.map { RoomGithubRepository(it.id ?: "", it.name ?: "", it.forksCount ?: 0, roomUser.id) }

                        db.repositoryDao.insert(roomRepos)

                        repositories
                    }
                }
            }
        } else {
            Single.fromCallable {
                val roomUser = user.login.let { db.userDao.findByLogin(it) } ?: throw RuntimeException("No such user in cache")
                db.repositoryDao.findForUser(roomUser.id).map { GithubRepository(it.id, it.name, it.forksCount) }
            }
        }
    }.subscribeOn(Schedulers.io())
}
