package com.example.githubclient.mvp.model.repo.retrofit

import com.example.githubclient.mvp.model.cache.IGithubRepositoriesCache
import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.network.INetworkStatus
import com.example.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositoriesRepo(private val networkStatus: INetworkStatus, private val cache: IGithubRepositoriesCache) :
    IGithubRepositoriesRepo {
    override fun getRepositories(user: GithubUser): Single<List<GithubRepository>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            cache.insertReposToCache(user)
        } else {
            cache.getReposFromCache(user)
        }
    }.subscribeOn(Schedulers.io())
}
