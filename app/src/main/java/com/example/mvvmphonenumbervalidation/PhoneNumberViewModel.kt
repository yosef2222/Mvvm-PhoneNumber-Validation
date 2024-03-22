package com.example.mvvmphonenumbervalidation;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PhoneNumberViewModel : ViewModel() {

    private val _formattedPhoneNumber = MutableLiveData<String>()
    val formattedPhoneNumber: LiveData<String> get() = _formattedPhoneNumber

    init {
        _formattedPhoneNumber.value = "" // Initialize with an empty string
    }

    fun formatPhoneNumber(phoneNumber: String) {
        if (isValidPhoneNumber(phoneNumber)) {
            _formattedPhoneNumber.value = format(phoneNumber)
        } else {
            _formattedPhoneNumber.value = "Invalid phone number"
        }
    }

    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        return phoneNumber.matches(Regex("^(\\+7|8)?\\d{10}$"))
    }

    private fun format(phoneNumber: String): String {
        val cleanedPhoneNumber = phoneNumber.replace(Regex("\\D"), "") // Remove all non-digit characters
        val areaCode = cleanedPhoneNumber.takeLast(10).substring(0, 3)
        val firstPart = cleanedPhoneNumber.takeLast(7).substring(0, 3)
        val secondPart = cleanedPhoneNumber.takeLast(4).substring(0, 2)
        val thirdPart = cleanedPhoneNumber.takeLast(2)
        return "+7 ($areaCode) $firstPart $secondPart $thirdPart"
    }
}
