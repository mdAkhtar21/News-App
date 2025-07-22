package com.example.newsapp.di

import android.app.Application
import androidx.room.Room
import com.example.newsapp.Data.local.NewsDao
import com.example.newsapp.Data.local.NewsDatabase
import com.example.newsapp.Data.local.NewsTypeConvertor
import com.example.newsapp.Data.manager.LocalUserManagerImp
import com.example.newsapp.Data.remote.dto.NewsApi
import com.example.newsapp.Data.remote.repository.NewsRepositoryImpl
import com.example.newsapp.Domain.manager.LocalUserManager
import com.example.newsapp.Domain.repository.NewsRepository
import com.example.newsapp.Domain.usecases.app_entry.AppEntryUsecases
import com.example.newsapp.Domain.usecases.app_entry.ReadAppEntry
import com.example.newsapp.Domain.usecases.app_entry.SaveAppEntry
import com.example.newsapp.Domain.usecases.news.DeleteArticle
import com.example.newsapp.Domain.usecases.news.GetNews
import com.example.newsapp.Domain.usecases.news.NewsUseCases
import com.example.newsapp.Domain.usecases.news.SearchNews
import com.example.newsapp.Domain.usecases.news.SelectArticle
import com.example.newsapp.Domain.usecases.news.SelectArticles
import com.example.newsapp.Domain.usecases.news.UpsertArticle
import com.example.newsapp.presentation.search.SearchEvent
import com.example.newsapp.utill.Constants.BASE_URL
import com.example.newsapp.utill.Constants.NEWS_DATABASE_NAME
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application):LocalUserManager=LocalUserManagerImp(application)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    )= AppEntryUsecases(readAppEntry=ReadAppEntry(localUserManager),
        saveAppEntry=SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi():NewsApi{
        return Retrofit.Builder().
        baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao:NewsDao
    ): NewsRepository = NewsRepositoryImpl(newsApi, newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepository,newsDao: NewsDao):NewsUseCases{
        return NewsUseCases(
            getNews= GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(application: Application):NewsDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao =newsDatabase.newsDao
}