package com.blueapp_compose.di

import android.content.Context
import com.blueapp_compose.repository.ProductsRepository
import com.blueapp_compose.utils.ProductDataSource
import com.blueapp_xml.utils.AndroidStringResourceProvider
import com.blueapp_xml.utils.StringResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideProductDataSource(@ApplicationContext context: Context): ProductDataSource {
        return ProductDataSource(context)
    }


    @Provides
    @Singleton
    fun provideStringResourceProvider(@ApplicationContext context: Context): StringResourceProvider {
        return AndroidStringResourceProvider(context)
    }

    @Provides
    @Singleton
    fun provideProductRepository(productDataSource: ProductDataSource, stringResourceProvider: StringResourceProvider): ProductsRepository = ProductsRepository(productDataSource, stringResourceProvider)

}