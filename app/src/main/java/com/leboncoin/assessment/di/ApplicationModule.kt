package com.leboncoin.assessment.di

import com.bumptech.glide.Glide
import com.leboncoin.assessment.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module {


    single { Glide.get(androidContext()) }
    viewModel { HomeViewModel(get()) }

}

val applicationInjectionsModules = listOf(
    applicationModule,
    dataModule,
    repositoryModule
)