/** Assignment: Assignment 3
 *  @author: Emilio Cruz and Glenn Buyce
 *  @date: 4/4/23
 */

package edu.quinnipiac.ser210.gistsummary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SummaryViewModel: ViewModel() {
    var createSummaryLiveData: MutableLiveData<TextSummary?> = MutableLiveData()
    var textSummary: TextSummary = TextSummary("(generating summary...)")

    // live data representation for summary value
    fun getCreateTextSummaryObserver(): MutableLiveData<TextSummary?> {
        return createSummaryLiveData
    }

    fun setLiveData()
    {
        createSummaryLiveData.value = textSummary
    }

    // gets summary from data class
    fun getSummary(): String
    {
        return textSummary.summary.toString()
    }

    // calls API to generate summary from user's input text
    fun createSummary(text: String) {
        val retroService = ApiInterface.create()

        val call = retroService.generateSummary(text, 6)

        // handles API call on concurrent thread so as not to slow down UI
        call.enqueue(object : Callback<TextSummary> {
            override fun onFailure(call: Call<TextSummary>, t: Throwable) {
                createSummaryLiveData.postValue(null)
            }

            override fun onResponse(call: Call<TextSummary>, response: Response<TextSummary>) {
                if (response.isSuccessful) {
                    textSummary = response.body() as TextSummary
                    createSummaryLiveData.postValue(textSummary)

                } else {
                    createSummaryLiveData.postValue(null)
                }
            }
        })
    }
}
