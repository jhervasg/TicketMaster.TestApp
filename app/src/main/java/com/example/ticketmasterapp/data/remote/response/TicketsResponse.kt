package com.example.ticketmasterapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class TicketsResponse(
    @SerializedName("_embedded")
    val embedded: EmbeddedResponse
)

data class EmbeddedResponse(
    @SerializedName("events")
    val events: List<ResponseTk>,
)

data class ResponseTk(
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("images")
    val images: List<ImagesResponse>,
    @SerializedName("dates")
    val dates: DatesResponse,
    @SerializedName("_embedded")
    val placeInfo: PlaceInfo,
)

data class ImagesResponse(
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: String,
    @SerializedName("height")
    val height: String
)

data class DatesResponse(
    @SerializedName("start")
    val start: StartDates
)

data class StartDates(
    @SerializedName("localDate")
    val localDate: String
)

data class PlaceInfo(
    @SerializedName("venues")
    val placeInfo: List<Venues>
)

data class Venues(
    @SerializedName("name")
    val placeName: String,
    @SerializedName("state")
    val state: StateResponse,
)

data class StateResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("stateCode")
    val stateCode: String,
)