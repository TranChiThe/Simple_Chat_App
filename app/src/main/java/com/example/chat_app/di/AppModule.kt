package com.example.chat_app.di

import android.content.Context
import com.example.chat_app.data.repositories.UserRepositoryImpl
import com.example.chat_app.domain.model.MyObjectBox
import com.example.chat_app.domain.model.User
import com.example.chat_app.domain.repositories.UserRepository
import com.example.chat_app.domain.use_cases.User.GetAllUser
import com.example.chat_app.domain.use_cases.User.RegisterAccount
import com.example.chat_app.domain.use_cases.User.UserUserCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.objectbox.Box
import io.objectbox.BoxStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideBoxStore(@ApplicationContext context: Context): BoxStore {
        return MyObjectBox.builder()
            .androidContext(context)
            .build()
    }

    @Provides
    @Singleton
    fun provideUserBox (boxStore: BoxStore): Box<User>{
        return boxStore.boxFor(User::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userBox: Box<User>): UserRepository {
        return UserRepositoryImpl(userBox)
    }

    @Provides
    @Singleton
    fun provideUserUserCase(userRepository: UserRepository):UserUserCase = UserUserCase(
        registerAccount = RegisterAccount(userRepository),
        getAllUser = GetAllUser(userRepository)
    )
}