package com.appwork.porductvt.utils

import com.appwork.porductvt.utils.models.RandomIntId
import kotlin.random.Random

object IdUtils {
    fun getRandomIntID() = RandomIntId(
        id = Random.nextInt()
    )
}

fun RandomIntId.asString() = this.id.toString()