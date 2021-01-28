package com.example.githubclient.mvp.model.repo.retrofit

import com.example.githubclient.mvp.model.api.IDataSource
import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositoriesRepo(private val api: IDataSource) :
    IGithubRepositoriesRepo {
    override fun getRepositories(user: GithubUser): @NonNull Single<List<GithubRepository>>? =
        user.reposUrl?.let {url ->
            api.getRepositories(url).flatMap {repositories ->
                Single.fromCallable{
                    repositories
                }
            }
        }
    }

