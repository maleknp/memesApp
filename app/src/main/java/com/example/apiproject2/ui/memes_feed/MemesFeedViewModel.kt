package com.example.apiproject2.ui.memes_feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiproject2.model.Meme
import com.example.apiproject2.model.MemeApi
import com.example.apiproject2.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Taha Ben Ashur (https://github.com/tahaak67) on 08,Feb,2023
 */
@HiltViewModel
class MemesFeedViewModel @Inject constructor(private val memeApi: MemeApi) : ViewModel() {

    private val _memes: MutableLiveData<Resource<List<Meme>>> =
        MutableLiveData(Resource.Loading())
    val memes: LiveData<Resource<List<Meme>>> get() = _memes

    init {
        getMemes()
    }

    fun getMemes() {
        viewModelScope.launch {
            _memes.value = Resource.Loading()
            _memes.value = memeApi.getMemes()
        }
    }

}
