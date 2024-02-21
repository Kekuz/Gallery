package com.gallery.database.room

import android.provider.SyncStateContract.Constants
import androidx.room.TypeConverter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class DateTypeConverter {

    private var df: DateFormat = SimpleDateFormat("dd.mm.yyyy", Locale.getDefault())

    @TypeConverter
    fun fromTimestamp(value: String?): Date? =
        if (value == null) null
        else df.parse(value)


    @TypeConverter
    fun toTimestamp(date: Date?): String {
        return date.toString()
    }
}