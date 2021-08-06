package com.bestrepositories.di.intent

import androidx.fragment.app.Fragment
import com.bestrepositories.feature_like.navigation.FavoritesNavigation
import com.bestrepositories.intent.navigation.favorites.FavoritesNavigationImpl
import org.koin.dsl.module

val intentFavoritesModule = module {

    factory<FavoritesNavigation> { (fragment: Fragment) -> FavoritesNavigationImpl(fragment) }
}