package com.example.githubclient.mvp.presenter

import com.example.githubclient.Constants.INDEX_1
import com.example.githubclient.Constants.INDEX_2
import com.example.githubclient.Constants.INDEX_3
import com.example.githubclient.mvp.model.Model
import com.example.githubclient.mvp.view.MainView

class Presenter(private val view: MainView) {
    private val model: Model = Model()

    fun counterClick(id: Int) {
        when (id) {
            INDEX_1 -> view.setButtonText(INDEX_1, model.next(INDEX_1).toString())
            INDEX_2 -> view.setButtonText(INDEX_2, model.next(INDEX_2).toString())
            INDEX_3 -> view.setButtonText(INDEX_3, model.next(INDEX_3).toString())
        }
    }

}