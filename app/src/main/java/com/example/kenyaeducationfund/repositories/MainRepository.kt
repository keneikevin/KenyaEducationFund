package com.example.kenyaeducationfund.repositories

import android.net.Uri
import com.example.kenyaeducationfund.data.entities.Comment
import com.example.kenyaeducationfund.data.entities.Post
import com.example.kenyaeducationfund.data.entities.ProfileUpdate
import com.example.kenyaeducationfund.data.entities.User
import com.example.kenyaeducationfund.other.Resource

interface MainRepository {

    suspend fun createPost(imageUri: Uri, text: String): Resource<Any>

    suspend fun getUsers(uids: List<String>): Resource<List<User>>

    suspend fun getUser(uid: String): Resource<User>

    suspend fun getPostsForFollows(): Resource<List<Post>>

    suspend fun toggleLikeForPost(post: Post): Resource<Boolean>

    suspend fun deletePost(post: Post): Resource<Post>

    suspend fun getPostsForProfile(uid: String):Resource<List<Post>>

    suspend fun toggleFollowForUser(uid:String):Resource<Boolean>

    suspend fun searchUser(query:String):Resource<List<User>>
    suspend fun createComment(commentText:String, postId:String):Resource<Comment>
    suspend fun deleteComment(comment: Comment):Resource<Comment>
    suspend fun getCommentForPost(PostId:String):Resource<List<Comment>>
    suspend fun updateProfile(profileUpdate:ProfileUpdate):Resource<Any>
    suspend fun updateProfilePicture(uid:String, imageUri: Uri):Uri?
}













