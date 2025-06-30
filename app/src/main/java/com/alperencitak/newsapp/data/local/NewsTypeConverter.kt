package com.alperencitak.newsapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.alperencitak.newsapp.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConverter {

    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(sourceString: String): Source {
        return sourceString.split(",").let { sourceArray ->
            Source(
                id = sourceArray[0],
                name = sourceArray[1]
            )
        }
    }

}