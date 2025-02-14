package com.inbis.siakad_stikes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CompletedData(
    val courseComName: String,
    val courseComKode: String,
    val courseComSifat: String,
    val courseComSks: String,
    val courseComNilai: String,
) : Parcelable
