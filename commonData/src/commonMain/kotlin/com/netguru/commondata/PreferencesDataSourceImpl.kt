package com.netguru.commondata

import com.netguru.commondomain.data.PreferencesDataSource
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

internal class PreferencesDataSourceImpl(
    private val settings: Settings
) : PreferencesDataSource {
    override suspend fun getBoolValue(key: String, defaultValue: Boolean): Boolean {
        return settings.getBoolean(key, defaultValue)
    }

    override suspend fun saveBoolValue(key: String, newValue: Boolean) {
        settings[key] = newValue
    }

    override suspend fun clearData() {
        settings.clear()
    }
}
