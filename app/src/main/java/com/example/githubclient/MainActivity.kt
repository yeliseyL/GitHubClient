package com.example.githubclient

import android.os.Bundle
import android.view.View
import com.example.githubclient.Constants.INDEX_1
import com.example.githubclient.Constants.INDEX_2
import com.example.githubclient.Constants.INDEX_3
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
            when (it.id) {
                R.id.btn_counter1 -> presenter.counterClick(INDEX_1)
                R.id.btn_counter2 -> presenter.counterClick(INDEX_2)
                R.id.btn_counter3 -> presenter.counterClick(INDEX_3)
            }
        }

        btn_counter1.setOnClickListener(listener)
        btn_counter2.setOnClickListener(listener)
        btn_counter3.setOnClickListener(listener)
    }

    override fun setButtonText(index: Int, text: String?) {
        when (index) {
            INDEX_1 -> btn_counter1.text = text
            INDEX_2 -> btn_counter2.text = text
            INDEX_3 -> btn_counter3.text = text
        }
    }
}