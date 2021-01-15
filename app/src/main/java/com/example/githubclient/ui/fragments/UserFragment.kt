package com.example.githubclient.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubclient.App
import com.example.githubclient.R
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.entity.GithubUsersRepo
import com.example.githubclient.mvp.presenter.UserPresenter
import com.example.githubclient.mvp.presenter.UsersPresenter
import com.example.githubclient.mvp.view.UserView
import com.example.githubclient.mvp.view.UsersView
import com.example.githubclient.ui.BackButtonListener
import com.example.githubclient.ui.adapter.UsersRVAdapter
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment(private val pos: Int) : MvpAppCompatFragment(), UserView, BackButtonListener {
    companion object {
        fun newInstance(pos: Int) = UserFragment(pos)
    }

    val presenter: UserPresenter by moxyPresenter { UserPresenter(GithubUsersRepo(), pos, App.instance.router) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        View.inflate(context, R.layout.fragment_user, null)

    override fun init(user: GithubUser) {
        user_login.text = user.login
    }
    override fun backPressed() = presenter.backPressed()
}