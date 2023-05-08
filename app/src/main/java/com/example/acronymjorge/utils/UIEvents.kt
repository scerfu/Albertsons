package com.example.acronymjorge.utils

import com.example.acronymjorge.model.AcronymResponseItem

sealed class UIEvents {
    object LOADING : UIEvents()

    data class SUCCESS(val response: List<AcronymResponseItem>) : UIEvents()

    data class ERROR(val error: Exception) : UIEvents()
}
