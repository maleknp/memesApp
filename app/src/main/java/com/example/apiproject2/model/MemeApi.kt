package com.example.apiproject2.model


import com.example.apiproject2.utils.Resource

/**
 * Created by Taha Ben Ashur (https://github.com/tahaak67) on 08,Feb,2023
 */
interface MemeApi {

    suspend fun getMemes(): Resource<List<Meme>>

}