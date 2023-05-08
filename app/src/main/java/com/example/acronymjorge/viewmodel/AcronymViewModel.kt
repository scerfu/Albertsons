package com.example.acronymjorge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acronymjorge.remote.Repository
import com.example.acronymjorge.utils.UIEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AcronymViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _meaning: MutableStateFlow<UIEvents> = MutableStateFlow(UIEvents.LOADING)
    val meaning: StateFlow<UIEvents> = _meaning

    val acronym: String = "HMM"

    init {
        getMeanings(acronym)
    }

    fun getMeanings(acronym: String) {
        viewModelScope.launch {
           repository.getAcronym(acronym){
               _meaning.value = it
           }
        }
    }
}