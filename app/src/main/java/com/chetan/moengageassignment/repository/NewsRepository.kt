package com.chetan.moengageassignment.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.chetan.moengageassignment.api.NewsAPI
import com.chetan.moengageassignment.models.NewsListResponse
import com.chetan.moengageassignment.models.NewsResponse
import com.chetan.moengageassignment.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsAPI: NewsAPI) {

    private val _newsListLiveData = MutableLiveData<NetworkResult<NewsListResponse>>()
    val newsLiveData get() = _newsListLiveData



    suspend fun getNewsList() {
        _newsListLiveData.postValue(NetworkResult.Loading())
        val response = newsAPI.getNewsList()

        Log.d( "getNewsList: ","response==>"+response)
        if (response.isSuccessful && response.body() != null) {
            _newsListLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _newsListLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _newsListLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }
}