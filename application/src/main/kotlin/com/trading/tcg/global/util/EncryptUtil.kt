package com.trading.tcg.global.util

import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.*

object EncryptUtil {
    fun encodeToSha512(text: String): String {
        val messageDigest = MessageDigest.getInstance("SHA-512")
        val digest = messageDigest.digest(text.toByteArray(StandardCharsets.UTF_8))
        val encodedText = StringBuilder()

        Formatter(encodedText).use { formatter ->
            for (d in digest) {
                formatter.format("%02x", d)
            }
        }

        return encodedText.toString()
    }
}
