package com.bestrepositories.intent.navigation

import androidx.fragment.app.Fragment
import com.bestrepositories.feature_main.SplashScreenFragmentDirections
import com.bestrepositories.feature_main.splash.navigation.SplashScreenNavigation
import com.bestrepositories.intent.utils.navigate

class SplashScreenNavigationImpl(private val fragment: Fragment) : SplashScreenNavigation {

    override fun navigateToRepositories() = fragment.navigate(
        SplashScreenFragmentDirections.actionSplashScreenFragmentToRepositoriesNavigation()
    )
}