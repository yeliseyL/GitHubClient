package com.example.githubclient.navigation

import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.ui.fragments.UserFragment
import com.example.githubclient.ui.fragments.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen() : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }

    class UserScreen(private val pos: Int) : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(pos)
    }
}