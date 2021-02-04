package com.example.kenyaeducationfund.ui.Main.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kenyaeducationfund.data.entities.User
import com.example.kenyaeducationfund.other.Event
import com.example.kenyaeducationfund.other.Resource
import com.example.kenyaeducationfund.repositories.MainRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject constructor(
    private val repository: MainRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
): ViewModel() {
    private val _searchResult = MutableLiveData<Event<Resource<List<User>>>>()
    val searchResult: LiveData<Event<Resource<List<User>>>> = _searchResult

    fun searchUser(query: String){
if (query.isEmpty()) return
    _searchResult.postValue(Event(Resource.Loading()))
        viewModelScope.launch(dispatcher) {
    val result = repository.searchUser(query)
    _searchResult.postValue(Event(result))
        }
    }

}






















