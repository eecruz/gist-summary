package edu.quinnipiac.ser210.gistsummary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SummaryViewModel: ViewModel() {
    var createSummaryLiveData: MutableLiveData<TextSummary?> = MutableLiveData()
    var textSummary: TextSummary = TextSummary("(generating summary...)")

    fun getCreateTextSummaryObserver(): MutableLiveData<TextSummary?> {
        return createSummaryLiveData
    }

    fun setLiveData()
    {
        createSummaryLiveData.value = textSummary
    }

    fun getSummary(): String
    {
        return textSummary.summary.toString()
    }

    fun createSummary(text: String) {
        val retroService = ApiInterface.create()

        val call = retroService.generateSummary(text, 6)

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
