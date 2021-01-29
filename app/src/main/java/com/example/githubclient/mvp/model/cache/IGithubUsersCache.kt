package com.example.githubclient.mvp.model.cache

import com.example.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUsersCache {
    fun insertUsersToCache(): Single<List<GithubUser>>
    fun getUsersFromCache(): Single<List<GithubUser>>
}