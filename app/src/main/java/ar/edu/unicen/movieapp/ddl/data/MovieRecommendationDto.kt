package ar.edu.unicen.movieapp.ddl.data

import ar.edu.unicen.movieapp.ddl.models.MovieRecommendation
import ar.edu.unicen.movieapp.ddl.models.MovieRecommendationInfo
import com.google.gson.annotations.SerializedName

class MovieRecommendationDto (
    @SerializedName("activity")
    val activity: String,
    @SerializedName("accessibility")
    val accessibility: Double,
    @SerializedName("type")
    val type:String,
    @SerializedName("participants")
    val participants: Int,
    @SerializedName("price")
    val price: Double,
    @SerializedName("link")
    val link:String,
    @SerializedName("key")
    val key: String,
    ){

    fun toMovieRecommendation(): MovieRecommendation{
        return  MovieRecommendation(
            id = key,
            activity = activity,
            info =  MovieRecommendationInfo(
                type = type,
                participants = participants,
                price = price,
                accessibility = accessibility,
                link = link
            )
        )
    }
}