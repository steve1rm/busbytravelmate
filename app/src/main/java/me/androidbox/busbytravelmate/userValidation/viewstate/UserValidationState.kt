package me.androidbox.busbytravelmate.userValidation.viewstate

import android.os.Parcelable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import me.androidbox.APIResponse

@Parcelize
data class UserValidationState<T>(
    val email: String = "",
    val password: String = "",
    val username: String = "",
    val isValidEmail: Boolean = false,
    val isValidPassword: Boolean = false,
    val isLoading: Boolean? = null,
    val userToken: String = "",
    val isLoginSuccess: Boolean = false,
    val errorMessage: String = "",
    /** Raw value needed for parcelize to work on a complex object */
    val apiResponse: @RawValue APIResponse<T>? = null
) : Parcelable
