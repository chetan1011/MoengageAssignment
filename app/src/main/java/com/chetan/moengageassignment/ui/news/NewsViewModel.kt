package com.chetan.moengageassignment.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetan.moengageassignment.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val noteRepository: NewsRepository) : ViewModel() {

    val newsListLiveData get() = noteRepository.newsLiveData


    fun getAllNews() {
        viewModelScope.launch {
            noteRepository.getNewsList()
        }
    }






}