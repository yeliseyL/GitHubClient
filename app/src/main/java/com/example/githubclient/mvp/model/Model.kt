package com.example.githubclient.mvp.model

class Model {
    private val counters = mutableListOf(0, 0, 0)

    /**
     * Increases given counters element by 1
     * @param index element index
     * @return increased value
     */
    fun next(index: Int): Int {
        val nextValue = counters[index] + 1
        counters[index] = nextValue
        return nextValue
    }
}