package com.inbis.siakad_stikes.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OnGoingData(
    val courseName : String,
    val courseLecture : String,
    val courseRoom : String,
    val courseDay : String,
    val courseHour : String,
) : Parcelable
