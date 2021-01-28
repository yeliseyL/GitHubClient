package com.example.githubclient.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
class GithubUser(
    @Expose val id: String,
    @Expose val login : String,
    @Expose val avatarUrl: String? = null,
    @Expose val reposUrl: String? = null
): Parcelable