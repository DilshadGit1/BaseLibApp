package com.example.municipal.base

import android.location.Location

interface OnLocationChangeListener {
    fun onLocation(location: Location)
}