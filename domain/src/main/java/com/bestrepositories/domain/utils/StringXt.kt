package com.bestrepositories.domain.utils

import java.text.Normalizer
import java.util.regex.Pattern

fun String.unAccent(): String {
    val nfdNormalizedString = Normalizer.normalize(this, Normalizer.Form.NFD)
    val pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
    return pattern.matcher(nfdNormalizedString).replaceAll("") ?: ""
}