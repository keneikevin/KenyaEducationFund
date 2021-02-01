package com.example.kenyaeducationfund.data.entities

import com.example.kenyaeducationfund.other.Constants.DEFAULT_PROFILE_PICTURE
import com.google.firebase.firestore.Exclude

data class User(
        val uid:String ="",
        val username:String ="",
        val profilePicture :String = DEFAULT_PROFILE_PICTURE,
        val description:String ="",
        val follows:List<String> = listOf(),
        @Exclude
        var isFollowing:String ="",
)
