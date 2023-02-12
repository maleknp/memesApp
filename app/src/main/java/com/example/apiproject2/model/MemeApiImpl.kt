package com.example.apiproject2.model

import android.util.Log
import com.example.apiproject2.model.Routes
import com.example.apiproject2.utils.Resource
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*

/**
 * Created by Taha Ben Ashur (https://github.com/tahaak67) on 08,Feb,2023
 */
class MemeApiImpl(private val client: HttpClient) : MemeApi {

    override suspend fun getMemes(): Resource<List<Meme>> {
        return try {
            val response = client.get {
                url("${Routes.GIMME}/${(1 .. 200).random()}")
            }
            Log.d("meme", response.toString())
            val memes :Memes = response.body()
            Resource.Success(memes.memes)
        } catch (e: RedirectResponseException) {
            Log.e("PostsApi", "3XX Error: ${e.response.status.description}")
            Resource.Error("${e.response}")
        } catch (e: ClientRequestException) {
            Log.e("PostsApi", "4XX Error: ${e.response.status.description}")
            Resource.Error("${e.response}")
        } catch (e: ServerResponseException) {
            Log.e("PostsApi", "5XX Error: ${e.response.status.description}")
            Resource.Error("${e.response}")
        } catch (e: Exception) {
            Log.e("PostsApi", "Error: ${e.message}")
            Resource.Error("${e.message}")
        }
    }

}