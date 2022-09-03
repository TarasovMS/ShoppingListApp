package com.persAssistant.shopping_list.di.module

import android.content.Context
import com.persAssistant.shopping_list.data.database.RoomDataBaseHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule  {

    @[Provides Singleton]
    fun provideRoomDataBaseHelper (context: Context): RoomDataBaseHelper {
        return RoomDataBaseHelper.getInstance(context)
    }

}
