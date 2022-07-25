package com.persAssistant.shopping_list.di.handler

import com.persAssistant.shopping_list.data.handler.ValidateHandler
import com.persAssistant.shopping_list.data.handler.ValidateHandlerImpl
import com.persAssistant.shopping_list.util.SUPPRESS_UNUSED
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@[Module Suppress(SUPPRESS_UNUSED)]
class HandlerModule {

    @[Provides Singleton]
    fun bindHandlerModule2(): ValidateHandler = ValidateHandlerImpl()

}


