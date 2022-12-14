package ru.miet.dormitory.domain

import com.auth0.android.jwt.JWT

class JWTDecodeUseCase {
    fun decodeClaim(token: String, claimName: String): String {
        val jwt = JWT(token)
        return jwt.getClaim(claimName).asString().toString()
    }
}
