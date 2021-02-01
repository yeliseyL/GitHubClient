package com.example.githubclient.navigation

import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.ui.fragments.RepositoryFragment
import com.example.githubclient.ui.fragments.UserFragment
import com.example.githubclient.ui.fragments.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen() : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }

    class UserScreen(val user: GithubUser) : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(user)
    }

    class RepositoryScreen(val user: GithubRepository) : SupportAppScreen() {
        override fun getFragment() = RepositoryFragment.newInstance(user)
    }
}