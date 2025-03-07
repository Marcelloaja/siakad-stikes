package com.inbis.siakad_stikes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DayItemData(
    val dayName: String,
    var isSelected: Boolean = false
) : Parcelable
