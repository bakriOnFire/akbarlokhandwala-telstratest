package com.example.newstest.repository.network

import com.example.countryinfo.common.Status
import com.example.countryinfo.model.CountryInfoBaseResponse
import com.example.countryinfo.model.ServerResponse
import com.example.countryinfo.repository.network.ServerResponseBuilder
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response


@RunWith(JUnit4::class)
class ServerResponseBuilderTest {
    @Test
    fun failure() {
        val exception = Exception("Request is null")

        val errorResponse = ServerResponseBuilder.create(exception)

        val status = errorResponse.status.ordinal
        assertThat<Int>(status, `is`(Status.ERROR.ordinal))

        val errorMessage = errorResponse.message
        assertThat<String>(errorMessage, `is`("Request is null"))
    }

    @Test
    fun success() {
        val successResponse: ServerResponse = ServerResponseBuilder
            .create(Response.success(CountryInfoBaseResponse("About Canada", emptyList())))


        val status = successResponse.status.ordinal
        assertThat<Int>(status, `is`(Status.SUCCESS.ordinal))

        val successMessage = successResponse.message
        assertThat<String>(successMessage, `is`("success"))

        val title = successResponse.successData?.title
        assertThat<String>(title, `is`("About Canada"))
    }
}