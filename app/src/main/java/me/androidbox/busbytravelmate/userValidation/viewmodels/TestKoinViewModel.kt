package me.androidbox.busbytravelmate.userValidation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class TestKoinViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private companion object {
        const val TEST_SAVED = "test_saved"
    }

    val state = savedStateHandle.getStateFlow(TEST_SAVED, "nothing")

    fun displayMessage(): String {
        return "This is the message from testKoinViewModel [${state}]"
    }
}
