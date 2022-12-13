package ru.miet.dormitory.ui.stateholders.requests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RequestsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is requests Fragment"
    }
    val text: LiveData<String> = _text
}