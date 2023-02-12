package com.example.apiproject2.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Memes(
    @SerialName("count")
    val count: Int,
    @SerialName("memes")
    val memes: List<Meme>
)