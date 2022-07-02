package io.github.leoallvez.take.data.source.mock

import com.haroldadmin.cnradapter.NetworkResponse
import com.haroldadmin.cnradapter.NetworkResponse.*
import io.github.leoallvez.take.data.api.response.ErrorResponse
import io.github.leoallvez.take.data.api.response.MediaDetailResponse
import okio.IOException

internal typealias Response = NetworkResponse<MediaDetailResponse, ErrorResponse>
internal typealias MediaDetailsSuccessResponse = Success<MediaDetailResponse>

abstract class MockResponseFactory {

    abstract fun makeResponse(): Response

    companion object {
        inline fun <reified T : Response> createFactory() = when(T::class) {
            Success::class      -> MockSuccessFactory
            NetworkError::class -> MockNetworkErrorFactory
            ServerError::class  -> MockServerErrorFactory
            UnknownError::class -> MockUnknownErrorFactory
            else -> throw IllegalArgumentException()
        }
        fun getDataResponse() = MediaDetailResponse()
    }
}

object MockSuccessFactory : MockResponseFactory() {
    override fun makeResponse(): Response {
        return Success(body = getDataResponse(), code = 200)
    }
}

object MockNetworkErrorFactory : MockResponseFactory() {
    override fun makeResponse(): Response {
        return NetworkError(error = IOException("network error"))
    }
}

object MockServerErrorFactory : MockResponseFactory() {

    override fun makeResponse(): Response {
        return ServerError(body = makeServeErrorBody(), code = 500)
    }

    private fun makeServeErrorBody() = ErrorResponse().apply {
        success = false
        statusCode = 500
        statusMessage = "error"
    }
}

object MockUnknownErrorFactory : MockResponseFactory() {
    override fun makeResponse() = UnknownError(Throwable("unknown error"))
}