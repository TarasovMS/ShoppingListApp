package com.persAssistant.shopping_list.presentation.custom

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

abstract class BaseCustomEditText constructor(
    context: Context,
    attributeSet: AttributeSet? = null
): TextInputEditText(context, attributeSet) {

    companion object{
        val RUS_PHONE_NUMBER = arrayOf(
            PredefinedSlots.hardcodedSlot('+'),
            PredefinedSlots.hardcodedSlot('7'),
            PredefinedSlots.hardcodedSlot('('),
            PredefinedSlots.digit(),
            PredefinedSlots.digit(),
            PredefinedSlots.digit(),
            PredefinedSlots.hardcodedSlot(')'),
            PredefinedSlots.digit(),
            PredefinedSlots.digit(),
            PredefinedSlots.digit(),
            PredefinedSlots.hardcodedSlot('-'),
            PredefinedSlots.digit(),
            PredefinedSlots.digit(),
            PredefinedSlots.hardcodedSlot('-'),
            PredefinedSlots.digit(),
            PredefinedSlots.digit()
        )
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        createMaskWatcher(focused)
    }

    open fun createMaskWatcher(focused: Boolean) {
    }

    open fun onFocusChangedWatcher(watcher: MaskFormatWatcher, focused: Boolean, editText:
    PhoneNumberEditText, onWatcherRemoved: ((Boolean) -> Unit)? = null) {
    }
}