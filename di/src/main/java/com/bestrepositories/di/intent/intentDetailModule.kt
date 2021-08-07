package com.bestrepositories.di.intent

import androidx.fragment.app.Fragment
import com.bestrepositories.feature_main.repositories.navigation.DetailNavigation
import com.bestrepositories.intent.navigation.detail.DetailNavigationImpl
import org.koin.dsl.module

val intentDetailModule = module {

    factory<DetailNavigation> { (fragment: Fragment) -> DetailNavigationImpl(fragment) }
}