package edu.quinnipiac.ser210.gistsummary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SummaryViewModel: ViewModel() {
    var createSummarySpecsLiveData: MutableLiveData<TextSummary?> = MutableLiveData()
    var textSummary: TextSummary? = null

    fun getCreateTextSummaryObserver(): MutableLiveData<TextSummary?> {
        return createSummarySpecsLiveData
    }

    fun createSummary(specs: SummarySpecs, isUrl: Boolean) {
        val retroService = ApiInterface.create()

        val call = if (isUrl) {
            retroService.generateUrlSummary(specs)
        } else {
            retroService.generateTextSummary(specs)
        }

        call.enqueue(object : Callback<TextSummary> {
            override fun onFailure(call: Call<TextSummary>, t: Throwable) {
                createSummarySpecsLiveData.postValue(null)
            }

            override fun onResponse(call: Call<TextSummary>, response: Response<TextSummary>) {
                if (response.isSuccessful) {
                    textSummary = response.body() as TextSummary
                    createSummarySpecsLiveData.postValue(textSummary)
                } else {
                    createSummarySpecsLiveData.postValue(null)
                }
            }
        })
    }
}
