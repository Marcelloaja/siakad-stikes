package com.inbis.siakad_stikes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CompleteData (
    val comCourseName: String,
    val comCourseCode: String,
    val comCourseSifat: String,
    val comCourseSks: String,
    val comCourseValue: String
) : Parcelable