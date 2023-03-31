package com.nasa.extension

import android.content.res.Resources
import android.util.TypedValue
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.math.ceil

/**
 * Расширения для чисел
 *
 * @author Onanov Aleksey (@onanov)
 */
val Number.dp: Int
    get() = ceil(
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        )
    ).toInt()

val Number.sp: Float
    get() = ceil(
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        )
    )

val Number.millis: Long
    get() = this.toLong().times(1000)

val Number.millisToSec: Long
    get() = this.toLong().div(1000)

val Number.secToMills: Long
    get() = this.toLong().times(1000)

val Number.minutesToMills: Long
    get() = this.toLong().times(60000)

val Number.hoursToMills: Long
    get() = this.toLong().times(3600000)

val Number.roundedToHundredths: String
    get() = DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.ROOT)).format(this)

const val MILLISECONDS = 1

const val SECONDS = 1000

const val MINUTES = 60000

const val HOURS = 3600000