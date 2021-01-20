package com.example.githubclient.mvp.presenter

import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.entity.GithubUsersRepo
import com.example.githubclient.mvp.presenter.list.IUserListPresenter
import com.example.githubclient.mvp.view.UserView
import com.example.githubclient.mvp.view.UsersView
import com.example.githubclient.mvp.view.list.UserItemView
import com.example.githubclient.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(
    private val usersRepo: GithubUsersRepo,
    private val pos: Int,
    private val router: Router
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        users?.elementAt(pos.toLong())?.subscribe(
            { s -> viewState.init(s) },
            { println("onError: ${it.message}") })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}