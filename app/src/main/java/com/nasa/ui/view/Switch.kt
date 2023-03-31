package com.nasa.ui.view

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.switchmaterial.SwitchMaterial
import com.nasa.extension.dp
import ru.kaycom.o2g.extension.setOnClickWithDelay

/**
 * @author Fedotov Yakov
 */

class Switch constructor(
    context: Context,
    attrs: AttributeSet?
) : SwitchMaterial(context, attrs) {

    var onCheckedUserListener: ((isChecked: Boolean) -> (Unit))? = null

    init {

        setPadding(START_PADDING.dp, TOP_PADDING.dp, END_PADDING.dp, BOTTOM_PADDING.dp)
        setOnClickWithDelay {
            super.setChecked(!isChecked)
            onCheckedUserListener?.invoke(isChecked)
        }
    }

    override fun setChecked(checked: Boolean) {
        /* no-op */
    }

    fun setCheckedWithoutListener(checked: Boolean) {
        super.setChecked(checked)
    }

    private companion object {
        const val START_PADDING = 0f
        const val TOP_PADDING = 0f
        const val END_PADDING = 0f
        const val BOTTOM_PADDING = 0f

    }

}