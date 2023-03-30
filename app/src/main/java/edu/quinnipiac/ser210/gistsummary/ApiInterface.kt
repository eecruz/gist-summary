package edu.quinnipiac.ser210.gistsummary

import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface
{
    @POST("extractive/summarize-url/")
    @Headers("Accept:application/json", "Content-Type:application/json",
             "Authorization: Bearer d859bd9d8emsh7665e099afce6e3p13227djsnf07373f301cb")
    fun generateUrlSummary(@Body params: SummarySpecs) : Call<TextSummary>

    @POST("abstractive/summarize-url/")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer d859bd9d8emsh7665e099afce6e3p13227djsnf07373f301cb")
    fun generateHumanUrlSummary(@Body params: SummarySpecs) : Call<TextSummary>

    @POST("extractive/summarize-text/")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer d859bd9d8emsh7665e099afce6e3p13227djsnf07373f301cb")
    fun generateTextSummary(@Body params: SummarySpecs) : Call<TextSummary>

    @POST("abstractive/summarize-text/")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer d859bd9d8emsh7665e099afce6e3p13227djsnf07373f301cb")
    fun generateHumanTextSummary(@Body params: SummarySpecs) : Call<TextSummary>



    companion object
    {
        var BASE_URL = "https://tldrthis.p.rapidapi.com/v1/model/"

        fun create(): ApiInterface
        {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}