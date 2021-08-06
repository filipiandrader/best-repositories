package com.bestrepositories.di.intent

import androidx.fragment.app.Fragment
import com.bestrepositories.feature_main.repositories.navigation.RepositoriesNavigation
import com.bestrepositories.intent.navigation.repositories.RepositoriesNavigationImpl
import org.koin.dsl.module

val intentRepositoriesModule = module {

    factory<RepositoriesNavigation> { (fragment: Fragment) -> RepositoriesNavigationImpl(fragment) }
}