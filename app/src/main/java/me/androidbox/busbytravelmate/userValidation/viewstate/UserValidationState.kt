package me.androidbox.busbytravelmate.userValidation.viewstate

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import me.androidbox.APIResponse

@Parcelize
data class UserValidationState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val username: String = "",
    val isValidEmail: Boolean = false,
    val isValidPassword: Boolean = false,
    val isLoading: Boolean = false,
    val userToken: String = "",
    val apiResponse: @RawValue APIResponse<Any>? = null
) : Parcelable
