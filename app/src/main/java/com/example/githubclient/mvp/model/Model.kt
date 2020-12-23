package com.example.githubclient.mvp.model

class Model {
    private val counters = mutableListOf(0, 0, 0)

    /**
     * Эта функция бла бла бла
     * @param index
     * @return
     */
    fun next(index: Int): Int {
        val nextValue = counters[index] + 1
        counters[index] = nextValue
        return nextValue
    }
}