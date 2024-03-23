package me.androidbox.busbytravelmate.userValidation.viewstate

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import me.androidbox.APIResponse

@Parcelize
data class UserValidationState<T>(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val username: String = "",
    val isValidEmail: Boolean = false,
    val isValidPassword: Boolean = false,
    val isLoading: Boolean = false,
    val userToken: String = "",
    /** Raw value needed for parcelize to work on a complex object */
    val apiResponse: @RawValue APIResponse<T>? = null
) : Parcelable
