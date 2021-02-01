package com.example.githubclient.di

import com.example.githubclient.di.modules.*
import com.example.githubclient.mvp.presenter.MainPresenter
import com.example.githubclient.mvp.presenter.UsersPresenter
import com.example.githubclient.ui.MainActivity
import com.example.githubclient.ui.fragments.RepositoryFragment
import com.example.githubclient.ui.fragments.UserFragment
import com.example.githubclient.ui.fragments.UsersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        DatabaseModule::class,
        CiceroneModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)

    // ДЗ - избавиться от зависимостей ниже
    fun inject(usersFragment: UsersFragment)
    fun inject(userFragment: UserFragment)
    fun inject(repositoryFragment: RepositoryFragment)
}