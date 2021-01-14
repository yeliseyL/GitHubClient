package com.example.githubclient.mvp.presenter

import com.example.githubclient.mvp.view.MainView
import com.example.githubclient.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClicked() {
        router.exit()
    }

}