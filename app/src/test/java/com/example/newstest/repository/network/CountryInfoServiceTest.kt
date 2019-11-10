package com.example.newstest.repository.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.countryinfo.repository.network.CountryInfoService
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class CountryInfoServiceTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: CountryInfoService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountryInfoService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun getCountryInfo() {
        enqueueResponse("country_info.json")
        val data = service.getCountryInfoService().execute()
        val request = mockWebServer.takeRequest()
        assertThat(request.path, `is`("/s/2iodh4vg0eortkl/facts.js"))

        assertThat(data, notNullValue())
        assertThat(data.body().title, notNullValue())
        assertThat(data.body().rows, `not`(emptyList()))
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader
            .getResourceAsStream("$fileName")
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(source.readString(Charsets.UTF_8))
        )
    }
}