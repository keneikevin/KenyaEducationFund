package com.example.kenyaeducationfund.repositories

import android.net.Uri
import com.example.kenyaeducationfund.other.Resource

interface MainRepository {
    suspend fun createPost(imageuri: Uri, text:String): Resource<Any>
}