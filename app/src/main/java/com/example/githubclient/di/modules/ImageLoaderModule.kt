package com.example.githubclient.di.modules

import android.widget.ImageView
import com.example.githubclient.mvp.model.image.IImageLoader
import com.example.githubclient.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides

@Module
class ImageLoaderModule {
    @Provides
    fun imageLoader() : IImageLoader<ImageView> = GlideImageLoader()
}