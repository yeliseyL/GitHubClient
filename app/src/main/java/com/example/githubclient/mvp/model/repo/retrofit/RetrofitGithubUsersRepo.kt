package com.example.githubclient.mvp.model.repo.retrofit

import com.example.githubclient.mvp.model.cache.IGithubUsersCache
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.network.INetworkStatus
import com.example.githubclient.mvp.model.repo.IGithubUsersRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(private val networkStatus: INetworkStatus, private val cache: IGithubUsersCache) : IGithubUsersRepo {
    override fun getUsers(): Single<List<GithubUser>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            cache.insertUsersToCache()
        } else {
            cache.getUsersFromCache()
        }
    }.subscribeOn(Schedulers.io())
}