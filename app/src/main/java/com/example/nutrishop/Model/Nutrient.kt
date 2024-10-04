package com.example.nutrishop.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Nutrient(
    var nutrient: String = "",
    var amount: String = ""
) : Parcelable
