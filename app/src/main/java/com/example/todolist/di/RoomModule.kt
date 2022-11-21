package com.example.todolist.di

import android.content.Context
import androidx.room.Room
import com.example.todolist.data.local.ToDoDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideRoomDB(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        ToDoDB::class.java, "ToDoDB"
    ).allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun providesDao(database: ToDoDB) = database.toDoDAO()
}