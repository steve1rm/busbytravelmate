package me.androidbox.repository.userValidationRepository.usecases

import me.androidbox.APIResponse

fun interface IsLoggedInUseCase {
    suspend fun execute(): APIResponse<Boolean>
}