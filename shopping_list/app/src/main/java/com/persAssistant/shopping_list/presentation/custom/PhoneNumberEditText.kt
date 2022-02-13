package com.persAssistant.shopping_list.presentation.custom

import android.content.Context
import android.util.AttributeSet
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

/**
 * Implementing Decoro (Input Mask)
 */

class PhoneNumberEditText constructor(
    context: Context,
    attributeSet: AttributeSet? = null
): BaseCustomEditText(context, attributeSet) {

    override fun createMaskWatcher(focused: Boolean) {
        MaskImpl.createTerminated(RUS_PHONE_NUMBER).apply {
            isHideHardcodedHead = false
        }.also {
            onFocusChangedWatcher(MaskFormatWatcher(it), focused, this) {
                text?.clear()
            }
        }
    }

    override fun onFocusChangedWatcher(watcher: MaskFormatWatcher,
                                      focused: Boolean,
                                      editText: PhoneNumberEditText,
                                      onWatcherRemoved: ((Boolean) -> Unit)?
    ) {
        if (focused && !watcher.isInstalled) {
            watcher.installOnAndFill(editText)
        } else {
            if (!watcher.mask.hasUserInput()) {
                watcher.removeFromTextView()
                onWatcherRemoved?.invoke(focused)
            }
        }
    }

}