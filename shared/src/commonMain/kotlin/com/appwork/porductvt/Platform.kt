package com.appwork.porductvt

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform