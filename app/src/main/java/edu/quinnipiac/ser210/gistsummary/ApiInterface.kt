package edu.quinnipiac.ser210.gistsummary

import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.*

interface ApiInterface
{
    /*
    @POST("extractive/summarize-url/")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "X-RapidAPI-Key:d859bd9d8emsh7665e099afce6e3p13227djsnf07373f301cb",
        "X-RapidAPI-Host:tldrthis.p.rapidapi.com")
    fun generateUrlSummary(@Body params: SummarySpecs) : Call<TextSummary>

    @POST("abstractive/summarize-url/")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "X-RapidAPI-Key:d859bd9d8emsh7665e099afce6e3p13227djsnf07373f301cb",
        "X-RapidAPI-Host:tldrthis.p.rapidapi.com")
    fun generateHumanUrlSummary(@Body params: SummarySpecs) : Call<TextSummary>

    @POST("extractive/summarize-text/")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "X-RapidAPI-Key:d859bd9d8emsh7665e099afce6e3p13227djsnf07373f301cb",
        "X-RapidAPI-Host:tldrthis.p.rapidapi.com")
    fun generateTextSummary(@Body params: SummarySpecs) : Call<TextSummary>

    @POST("abstractive/summarize-text/")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "X-RapidAPI-Key:d859bd9d8emsh7665e099afce6e3p13227djsnf07373f301cb",
        "X-RapidAPI-Host:tldrthis.p.rapidapi.com")
    fun generateHumanTextSummary(@Body params: SummarySpecs) : Call<TextSummary>

*/
    @Headers("Accept:application/json", "Content-Type:application/json",
        "X-RapidAPI-Key:d859bd9d8emsh7665e099afce6e3p13227djsnf07373f301cb",
        "X-RapidAPI-Host:tldr-text-analysis.p.rapidapi.com")
    @GET("summarize/")
    fun generateSummary(@Query("text") text: String, @Query("max_sentences") max_sentences: Int) : Call<TextSummary>

    companion object
    {
        var BASE_URL = "https://tldr-text-analysis.p.rapidapi.com/"

        fun create(): ApiInterface
        {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
            client.addInterceptor(logging)

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client.build())
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}