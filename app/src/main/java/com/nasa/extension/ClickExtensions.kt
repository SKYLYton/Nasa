package ru.kaycom.o2g.extension

import android.os.SystemClock
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible

/**
 *
 * Время последнего клика
 */
var lastClickTime = 0L

/**
 *
 * Задержка с которым срабатывает клик
 */
const val CLICK_DELAY = 1000

/**
 *
 *  Добавляет слушатель для клика, который срабатывает только по истечении CLICK_DELAY
 *  @author Petr Nikitin {@kiloqky}
 */
inline fun View.setOnClickWithDelay(crossinline action: (view: View) -> Unit) {
    setOnClickListener {
        if (SystemClock.elapsedRealtime() - lastClickTime > CLICK_DELAY) {
            lastClickTime = SystemClock.elapsedRealtime()
            action.invoke(it)
        }
    }
}

/**
 *
 *  Добавляет слушатель для клика на меню Toolbar, который срабатывает только по истечении CLICK_DELAY
 *  @author Petr Nikitin {@kiloqky}
 */
inline fun Toolbar.setOnMenuClickWithDelay(crossinline action: (menuItem: MenuItem) -> Boolean) {
    setOnMenuItemClickListener {
        if (SystemClock.elapsedRealtime() - lastClickTime > CLICK_DELAY) {
            lastClickTime = SystemClock.elapsedRealtime()
            return@setOnMenuItemClickListener action.invoke(it)
        }
        return@setOnMenuItemClickListener false
    }
}

var View.visible: Boolean
    get() {
        return isVisible
    }
    set(value) {
        if (isVisible != value) {
            isVisible = value
        }
    }
