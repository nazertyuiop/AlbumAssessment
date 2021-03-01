package com.leboncoin.assessment.di

import com.bumptech.glide.Glide
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val applicationModule = module {


    single { Glide.get(androidContext()) }

}

val applicationInjectionsModules = listOf(
    applicationModule,
    dataModule,
    repositoryModule
)