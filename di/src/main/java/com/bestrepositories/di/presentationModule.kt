package com.bestrepositories.di

import com.bestrepositories.feature_main.repositories.presentation.RepositoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { RepositoriesViewModel() }
}