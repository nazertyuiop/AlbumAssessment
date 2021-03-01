package com.leboncoin.assessment.di

import com.leboncoin.data.AlbumRepositoryImp
import com.leboncoin.domain.AlbumRepository
import com.leboncoin.domain.usecase.GetAllAlbumUsecase
import com.leboncoin.domain.usecase.GetAlbumDetailUsecase
import org.koin.dsl.module.module

val repositoryModule = module {
    factory<AlbumRepository> { AlbumRepositoryImp(get(), get()) }
    factory { GetAllAlbumUsecase(get()) }
    factory { GetAlbumDetailUsecase(get()) }
}