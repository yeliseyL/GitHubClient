package com.example.githubclient.mvp.presenter

import com.example.githubclient.R
import com.example.githubclient.mvp.model.Model
import com.example.githubclient.mvp.view.MainView

class Presenter(private val view: MainView) {
    private val model: Model = Model()

    fun counterClick(id: Int) {
        when (id) {
            R.id.btn_counter1 -> view.setButtonText(0, model.next(0).toString())
            R.id.btn_counter2 -> view.setButtonText(1, model.next(1).toString())
            R.id.btn_counter3 -> view.setButtonText(2, model.next(2).toString())
        }
    }

}