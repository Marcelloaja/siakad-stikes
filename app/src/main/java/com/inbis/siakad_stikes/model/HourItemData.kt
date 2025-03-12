package com.inbis.siakad_stikes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class HourItemData (
    val hourName: String,
    var isSelected: Boolean = false
    ) : Parcelable