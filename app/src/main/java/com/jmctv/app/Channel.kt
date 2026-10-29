package com.jmctv.app

data class Channel(
    val id: String,
    val name: String,
    val url: String,
    val description: String = ""
)
