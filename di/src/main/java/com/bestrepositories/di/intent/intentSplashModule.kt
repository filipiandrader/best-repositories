package com.bestrepositories.di.intent

import androidx.fragment.app.Fragment
import com.bestrepositories.feature_main.splash.navigation.SplashScreenNavigation
import com.bestrepositories.intent.navigation.SplashScreenNavigationImpl
import org.koin.dsl.module

val intentSplashModule = module {

    factory<SplashScreenNavigation> { (fragment: Fragment) ->
        SplashScreenNavigationImpl(fragment)
    }
}