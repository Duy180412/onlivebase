package vn.vplay.vlive.myapplication.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vn.vplay.vlive.myapplication.data.repository.ContentRepositoryImpl
import vn.vplay.vlive.myapplication.domain.repository.ContentRepo

@Module
@InstallIn(SingletonComponent::class)
abstract class ContentRepoModule {

    @Binds
    abstract fun bindContentRepo(
        impl: ContentRepositoryImpl
    ): ContentRepo
}