package com.example.dailypulse.android.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dailypulse.articles.ArticlesViewModel
import org.koin.androidx.compose.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel{
        ArticlesViewModel(get())
    }
}