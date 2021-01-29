package com.example.githubclient.mvp.model.cache.room

import com.example.githubclient.mvp.model.api.IDataSource
import com.example.githubclient.mvp.model.cache.IGithubUsersCache
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.entity.room.Database
import com.example.githubclient.mvp.model.entity.room.RoomGithubUser
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGithubUsersCache(private val api: IDataSource, private val db: Database) : IGithubUsersCache {
    override fun insertUsersToCache(): Single<List<GithubUser>> =
        api.getUsers().flatMap { users ->
            Single.fromCallable {
                val roomUsers = users.map { user ->
                    RoomGithubUser(
                        user.id ?: "",
                        user.login ?: "",
                        user.avatarUrl ?: "",
                        user.reposUrl ?: ""
                    )
                }
                db.userDao.insert(roomUsers)
                users
            }
        }.subscribeOn(Schedulers.io())

    override fun getUsersFromCache(): Single<List<GithubUser>> =
        Single.fromCallable {
            db.userDao.getAll().map {roomUser ->
                GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
            }
        }.subscribeOn(Schedulers.io())

}