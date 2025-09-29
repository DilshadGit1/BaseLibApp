package com.dilshad


import com.dilshad.backupdashboard.repository.ProductConfigRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface LibEntryPoint {
    fun libRepository(): ProductConfigRepository
}
