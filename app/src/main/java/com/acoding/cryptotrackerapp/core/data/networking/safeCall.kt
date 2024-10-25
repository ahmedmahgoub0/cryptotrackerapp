package com.acoding.cryptotrackerapp.core.data.networking

import com.acoding.cryptotrackerapp.core.domain.util.NetworkError
import com.acoding.cryptotrackerapp.core.domain.util.Result
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import java.io.IOException
import java.nio.channels.UnresolvedAddressException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, NetworkError> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        return Result.Error(NetworkError.NO_INTERNET)
    } catch (e: SerializationException) {
        return Result.Error(NetworkError.SERIALIZATION)
    } catch (e: IOException) {
        return Result.Error(NetworkError.REQUEST_TIMEOUT)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(NetworkError.UNKNOWN)
    }

    return responseToResult(response)
}