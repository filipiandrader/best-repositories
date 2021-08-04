package com.filipiandrade.dietapp.data_remote.utils.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/*
 * Created by Filipi Andrade Rocha on 09/04/2021.
 */

inline fun <reified T> String.fromJson(): T? = try {
    Gson().getAdapter(TypeToken.get(T::class.java)).fromJson(this)
} catch (e: Exception) {
    null
}