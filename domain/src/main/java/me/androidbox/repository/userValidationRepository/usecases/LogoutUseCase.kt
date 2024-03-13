package me.androidbox.repository.userValidationRepository.usecases

import me.androidbox.APIResponse

fun interface LogoutUseCase {
    suspend fun execute(): APIResponse<Unit>
}
