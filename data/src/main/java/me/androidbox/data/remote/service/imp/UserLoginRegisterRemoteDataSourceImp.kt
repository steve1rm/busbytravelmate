package me.androidbox.data.remote.service.imp

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import me.androidbox.APIResponse
import me.androidbox.data.remote.service.UserLoginRegisterRemoteDataSource
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UserLoginRegisterRemoteDataSourceImp : UserLoginRegisterRemoteDataSource {
    private var firebaseAuth: FirebaseAuth = Firebase.auth

    override suspend fun loginUser(email: String, password: String): APIResponse<String?> {
        firebaseAuth.currentUser?.let {
            return APIResponse.Success(it.uid)
        }

        return suspendCoroutine { continuation ->
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resume(APIResponse.Success(firebaseAuth.currentUser?.uid))
                    }
                    else {
                        APIResponse.Failure(task.exception ?: Exception("Unknown error - when logging in"))
                    }
                }
        }
    }

    override suspend fun registerUser(email: String, password: String): APIResponse<String?> {
        firebaseAuth.currentUser?.let {
            return APIResponse.Success(it.uid)
        }

        return suspendCoroutine { continuation ->
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resume(APIResponse.Success(firebaseAuth.currentUser?.uid))
                    }
                    else {
                        APIResponse.Failure(task.exception ?: Exception("Unknown error - when registering"))
                    }
                }
        }
    }
}