package com.bestrepositories.base_feature.utils.delegateproperties

import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

inline fun <F : Fragment, reified V : Any> F.navDirections() = inject<V> {
    parametersOf(this)
}
