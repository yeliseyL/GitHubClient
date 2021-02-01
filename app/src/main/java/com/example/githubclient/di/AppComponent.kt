package com.example.githubclient.di

import com.example.githubclient.di.modules.*
import com.example.githubclient.mvp.presenter.MainPresenter
import com.example.githubclient.mvp.presenter.UserPresenter
import com.example.githubclient.mvp.presenter.UsersPresenter
import com.example.githubclient.ui.MainActivity
import com.example.githubclient.ui.adapter.UsersRVAdapter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        DatabaseModule::class,
        CiceroneModule::class,
        RepoModule::class,
        ImageLoaderModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(userPresenter: UserPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)
}