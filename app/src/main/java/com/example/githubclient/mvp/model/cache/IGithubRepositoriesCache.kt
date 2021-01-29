package com.example.githubclient.mvp.model.cache

import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesCache {
    fun insertReposToCache(user: GithubUser): @NonNull Single<List<GithubRepository>>?
    fun getReposFromCache(user: GithubUser): Single<List<GithubRepository>>
}