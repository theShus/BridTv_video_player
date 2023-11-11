package com.example.bridtv_video_player.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

//import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
data class CarResponse (
    @SerializedName("id") var id: Long,
    @SerializedName("car") var car: String,
    @SerializedName("car_model") var car_model: String,
    @SerializedName("car_color") var car_color: String,
    @SerializedName("car_model_year") var car_model_year: Int,
    @SerializedName("car_vin") var car_vin: String,
    @SerializedName("price") var price: String,
    @SerializedName("availability") var availability: Boolean,
): Serializable
