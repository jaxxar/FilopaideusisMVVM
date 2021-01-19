package com.example.filopaideusismvvm.di

import android.app.Application
import androidx.room.Room
import com.example.filopaideusismvvm.data.AppDatabase
import com.example.filopaideusismvvm.utilities.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        app: Application,
        callback: AppDatabase.Callback
    ) = Room.databaseBuilder(app, AppDatabase::class.java, DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()

    @Provides
    fun provideStudentClassDao(db: AppDatabase) = db.studentClassDao()

    @Provides
    fun provideSectionsDao(db: AppDatabase) = db.sectionsDao()

    @Provides
    fun provideQuestionDao(db: AppDatabase) = db.questionDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope