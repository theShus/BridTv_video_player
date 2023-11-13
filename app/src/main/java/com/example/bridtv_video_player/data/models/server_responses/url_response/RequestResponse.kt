package com.example.bridtv_video_player.data.models.server_responses.url_response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RequestResponse(
    @SerializedName("files") val files: FilesResponse,
    @SerializedName("file_codecs") val file_codecs: Any,
    @SerializedName("lang") val lang: Any,
    @SerializedName("referrer") val referrer: Any,
    @SerializedName("cookie_domain") val cookie_domain: Any,
    @SerializedName("signature") val signature: Any,
    @SerializedName("timestamp") val timestamp: Any,
    @SerializedName("expires") val expires: Any,
    @SerializedName("gc_debug") val gc_debug: Any,
    @SerializedName("thumb_preview") val thumb_preview: Any,
    @SerializedName("currency") val currency: Any,
    @SerializedName("session") val session: Any,
    @SerializedName("cookie") val cookie: Any,
    @SerializedName("build") val build: Any,
    @SerializedName("urls") val urls: Any,
    @SerializedName("flags") val flags: Any,
    @SerializedName("country") val country: Any,
    @SerializedName("client") val client: Any,
    @SerializedName("ab_tests") val ab_tests: Any,
) : Serializable