package com.inbis.siakad_stikes.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResumeData(
    val resumeLesson : String,
    val resumeLecture : String,
    val resumeAttend : Int,
    val resumeAlpha : Int,
    val resumeSick : Int,
    val resumeTotal : Int,
) : Parcelable
