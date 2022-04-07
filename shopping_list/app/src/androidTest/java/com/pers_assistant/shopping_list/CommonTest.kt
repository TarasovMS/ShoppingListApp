package com.pers_assistant.shopping_list

import androidx.test.platform.app.InstrumentationRegistry
import com.pers_assistant.shopping_list.data.database.RoomDataBaseHelper

abstract class CommonTest {

    init {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        appContext.deleteDatabase(RoomDataBaseHelper.DATABASE_NAME)
        Thread.sleep(100)
    }
}