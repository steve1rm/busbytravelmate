package me.androidbox.data.remote.service.imp

import com.google.firebase.auth.FirebaseAuth
import me.androidbox.APIResponse
import me.androidbox.data.remote.service.UserLoginRegisterRemoteDataSource
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UserLoginRegisterRemoteDataSourceImp(private val firebaseAuth: FirebaseAuth) : UserLoginRegisterRemoteDataSource {

    override suspend fun registerUser(email: String, password: String): APIResponse<String?> {
        firebaseAuth.currentUser?.let {
            Timber.d("User is already registered ${firebaseAuth.currentUser?.uid}")
            return APIResponse.Success(it.uid)
        }

        return suspendCoroutine { continuation ->
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Timber.d("User has been created ${firebaseAuth.currentUser?.uid}")
                        continuation.resume(APIResponse.Success(firebaseAuth.currentUser?.uid))
                    }
                    else {
                        Timber.d("Error when creating ${firebaseAuth.currentUser?.uid}")
                        APIResponse.Failure(task.exception ?: Exception("Unknown error - when registering"))
                    }
                }
        }
    }
    override suspend fun loginUser(email: String, password: String): APIResponse<String?> {
        firebaseAuth.currentUser?.let {
            Timber.d("User is already logged in ${firebaseAuth.currentUser?.uid}")
            return APIResponse.Success(it.uid)
        }

        return suspendCoroutine { continuation ->
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Timber.d("User has been logged in [$email, $password] ${firebaseAuth.currentUser?.uid}")
                        continuation.resume(APIResponse.Success(firebaseAuth.currentUser?.uid))
                    }
                    else {
                        Timber.e(task.exception, "Error when logging in ${task.exception?.message}")
                        continuation.resume(APIResponse.Failure(task.exception ?: Exception("Unknown error - when logging in")))
                    }
                }
        }
    }

    override suspend fun logout(): APIResponse<Unit> {
        if(firebaseAuth.currentUser == null) {
            return APIResponse.Success(Unit)
        }

        return suspendCoroutine { continuation ->
            firebaseAuth.signOut()
            continuation.resume(APIResponse.Success(Unit))
        }
    }
}