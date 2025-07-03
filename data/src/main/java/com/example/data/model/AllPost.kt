package com.example.data.model

import kotlinx.serialization.Serializable


@Serializable
data class AllPost(
    val thought:String,
    val thoughtMedia:List<String>,
    val username:String
){
    fun toAllPost(): com.example.business.model.AllPost {
        return com.example.business.model.AllPost(
            thought = thought,
            thoughtMedia = thoughtMedia,
            username = username

        )

    }
}