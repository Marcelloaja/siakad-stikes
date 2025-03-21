package com.inbis.siakad_stikes.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsData(
    val newsWriter : String,
    val newsTitle : String,
    val newsDate : String,
) : Parcelable
