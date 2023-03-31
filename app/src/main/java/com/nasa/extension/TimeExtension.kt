package com.nasa.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Fedotov Yakov
 */
fun Date.getTimeToString(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(this)
}