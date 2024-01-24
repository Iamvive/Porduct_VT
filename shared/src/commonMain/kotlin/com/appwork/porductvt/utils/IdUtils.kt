package com.appwork.porductvt.utils

import com.appwork.porductvt.utils.models.RandomIntId
import kotlin.random.Random

object IdUtils {
    fun getRandomIntID() = RandomIntId(
        id = Random.nextInt(1, 1000)
    )
}

fun RandomIntId.asString() = this.id.toString()