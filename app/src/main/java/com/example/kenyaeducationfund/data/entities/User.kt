package com.example.kenyaeducationfund.data.entities

import com.example.kenyaeducationfund.other.Constants.DEFAULT_PROFILE_PICTURE
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
        val uid: String = "",
        val username: String = "",
        val profilePictureUrl: String = DEFAULT_PROFILE_PICTURE,
        val description: String = "",
        var follows: List<String> = listOf(),
        @get:Exclude
        var isFollowing: Boolean = false
)