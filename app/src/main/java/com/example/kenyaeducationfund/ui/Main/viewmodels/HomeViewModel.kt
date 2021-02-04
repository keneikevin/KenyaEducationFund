package com.example.kenyaeducationfund.ui.Main.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kenyaeducationfund.data.entities.Post
import com.example.kenyaeducationfund.other.Event
import com.example.kenyaeducationfund.other.Resource
import com.example.kenyaeducationfund.repositories.MainRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel@ViewModelInject constructor(
        private val repository: MainRepository,
        private val dispatcher:CoroutineDispatcher = Dispatchers.Main
):BasePostViewModel(repository,dispatcher) {



        private val _posts = MutableLiveData<Event<Resource<List<Post>>>>()
            override val posts: LiveData<Event<Resource<List<Post>>>>
            get() = _posts


    init {
        getPosts()
    }
    override fun getPosts(uid: String) {
        _posts.postValue(Event(Resource.Loading()))
        viewModelScope.launch(dispatcher){
           val result = repository.getPostsForFollows()
            _posts.postValue(Event(result))
        }
    }

}