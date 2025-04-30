package com.blueapp_xml.di

import android.content.Context
import com.blueapp_xml.repository.ProductDataSource
import com.blueapp_xml.repository.ProductsRepository
import com.blueapp_xml.utils.AndroidStringResourceProvider
import com.blueapp_xml.utils.StringResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 23/04/25
 * Description: AppModule class.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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