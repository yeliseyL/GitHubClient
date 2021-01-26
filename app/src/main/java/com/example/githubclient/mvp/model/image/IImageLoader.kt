package com.example.githubclient.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}