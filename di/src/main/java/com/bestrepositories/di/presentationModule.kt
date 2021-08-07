package com.bestrepositories.di

import com.bestrepositories.feature_like.presentation.FavoritesViewModel
import com.bestrepositories.feature_main.repositories.presentation.RepositoriesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.module

@OptIn(KoinApiExtension::class)
val presentationModule = module {

    viewModel { RepositoriesViewModel() }

    viewModel { FavoritesViewModel() }
}