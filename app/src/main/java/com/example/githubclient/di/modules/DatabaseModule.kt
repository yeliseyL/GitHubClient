package com.example.githubclient.di.modules

import androidx.room.Room
import com.example.githubclient.App
import com.example.githubclient.mvp.model.cache.IGithubRepositoriesCache
import com.example.githubclient.mvp.model.cache.IGithubUsersCache
import com.example.githubclient.mvp.model.cache.room.RoomGithubRepositoriesCache
import com.example.githubclient.mvp.model.cache.room.RoomGithubUsersCache
import com.example.githubclient.mvp.model.entity.room.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun database(app: App) = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

    @Singleton
    @Provides
    fun usersCache(database: Database): IGithubUsersCache {
        return RoomGithubUsersCache(database)
    }

    @Singleton
    @Provides
    fun repositoriesCache(database: Database): IGithubRepositoriesCache {
        return RoomGithubRepositoriesCache(database)
    }
}