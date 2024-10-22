package com.acoding.cryptotrackerapp.core.presentation.util

import android.content.Context
import com.acoding.cryptotrackerapp.R
import com.acoding.cryptotrackerapp.core.domain.util.NetworkError

fun NetworkError.toString(context: Context): String{
    val resId = when(this){
        NetworkError.REQUEST_TIMEOUT -> R.string.request_timeout
        NetworkError.TOO_MANY_REQUESTS -> R.string.too_many_requests
        NetworkError.NO_INTERNET -> R.string.no_internet
        NetworkError.SERVER_ERROR -> R.string.server_error
        NetworkError.SERIALIZATION -> R.string.serialization
        NetworkError.UNKNOWN -> R.string.unknown_error
    }

    return context.getString(resId)
}