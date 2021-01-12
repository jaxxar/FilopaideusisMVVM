package com.example.filopaideusismvvm.di

import android.app.Application
import androidx.room.Room
import com.example.filopaideusismvvm.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        app: Application
    ) = Room.databaseBuilder(app, AppDatabase::class.java, "filopaideusis_db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideStudentClassDao(db: AppDatabase) = db.studentClassDao()

    @Provides
    fun provideSectionsDao(db: AppDatabase) = db.sectionsDao()

    @Provides
    fun provideQuestionDao(db: AppDatabase) = db.questionDao()

}