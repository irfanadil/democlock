package com.berlin.democlock.model

import com.berlin.democlock.utils.LampColor

data class Minutes(
    val topColors: List<LampColor> = defaultTop(),
    val bottomColors: List<LampColor> = defaultBottom()
) {
    companion object {
        fun defaultBottom() = MutableList(4) { LampColor.OFF }
        fun defaultTop() = MutableList(11) { LampColor.OFF }
    }
}