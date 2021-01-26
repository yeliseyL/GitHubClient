package com.example.githubclient.mvp.presenter

import com.example.githubclient.mvp.view.UserView
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