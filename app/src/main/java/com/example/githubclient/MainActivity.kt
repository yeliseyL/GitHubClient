package com.example.githubclient

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.githubclient.mvp.presenter.Presenter
import com.example.githubclient.mvp.view.MainView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainView {
    private lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = Presenter(this)

        val listener = View.OnClickListener {
            presenter.counterClick(it.id)
        }

        btn_counter1.setOnClickListener(listener)
        btn_counter2.setOnClickListener(listener)
        btn_counter3.setOnClickListener(listener)
    }

    override fun setButtonText(index: Int, text: String?) {
        when (index) {
            0 -> btn_counter1.text = text
            1 -> btn_counter2.text = text
            2 -> btn_counter3.text = text
        }
    }
}