package com.example.hackerranktestpreparation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackerranktestpreparation.data.ResponseResult
import com.example.hackerranktestpreparation.network.RemoteRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class MainActivityViewModel : ViewModel() {

    val apiRequestLiveData = MutableLiveData<ResponseResult>()
    val loadingViewLiveData = MutableLiveData<Boolean>()

    fun getResponseFromApi() {
        viewModelScope.launch(Dispatchers.Main) {
            loadingViewLiveData.postValue(true)

            val response = getApiResponse()
            loadingViewLiveData.postValue(false)

            apiRequestLiveData.postValue(response)
        }
    }

    private suspend fun getApiResponse(): ResponseResult {

        val res = withContext(Dispatchers.IO) {
            callAPI()
        }
        return res
    }

    private fun callAPI(): ResponseResult {

        val response = URL(AppConstants.API_URL).readText()
        return RemoteRepository.getResponseFromGson(response, ResponseResult::class.java)
    }

    /*
 * Complete the 'getArticleTitles' function below.
 *
 * The function is expected to return a STRING_ARRAY.
 * The function accepts STRING author as parameter.
 * API URL: https://jsonmock.hackerrank.com/api/articles?author=<authorName>&page=<num>
 */

    fun getArticleTitles(author: String): Array<String> {
        // Write your code here
        var json =
            URL("https://jsonmock.hackerrank.com/api/articles?author=$author&page=1").readText()

        var titlesList = ArrayList<String>()
        val article = fromJson<ArticlesDataClass>(json)
        val count = article.total_pages?.toInt() ?: 1

        article.data?.forEach {
            if (it.title != null) {
                titlesList.add(it.title)
            } else if (it.story_title != null) {
                titlesList.add(it.story_title)
            }
        }

        for (index in 2..count) {
            json =
                URL("https://jsonmock.hackerrank.com/api/articles?author=$author&page=$index").readText()
            val articlePaging = fromJson<ArticlesDataClass>(json)
            val count = articlePaging.total_pages?.toInt() ?: 1

            articlePaging.data?.forEach {
                if (it.title != null) {
                    titlesList.add(it.title)
                } else if (it.story_title != null) {
                    titlesList.add(it.story_title)
                }
            }

        }

        return titlesList.toTypedArray()
    }

    data class ArticlesDataClass(
        val page: String?, val per_page: String?,
        val total: String?, val total_pages: String?, val data: List<Articles>?
    )

    data class Articles(val title: String?, val story_title: String?)

    /*
     * Helper function that converts an object of type T -> JSON string.
     */
    private inline fun <T> toJson(model: T): String = Gson().toJson(model)

    /*
    * Helper function that converts a JSON string -> to an object of type T.
    */
    private inline fun <reified T> fromJson(json: String): T = Gson().fromJson(json, T::class.java)
}