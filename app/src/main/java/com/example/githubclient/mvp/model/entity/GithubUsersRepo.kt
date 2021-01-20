package com.example.githubclient.mvp.model.entity

import io.reactivex.rxjava3.core.Observable

class GithubUsersRepo {
    private val repositories = listOf (
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    fun getUsers(): Observable<GithubUser>? = Observable.fromIterable(repositories)
}