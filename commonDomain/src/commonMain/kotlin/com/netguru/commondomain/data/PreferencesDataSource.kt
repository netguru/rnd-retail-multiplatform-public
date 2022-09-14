package com.netguru.commondomain.data

interface PreferencesDataSource {
    suspend fun getBoolValue(key: String, defaultValue: Boolean): Boolean
    suspend fun saveBoolValue(key: String, newValue: Boolean)
    suspend fun clearData()
}
