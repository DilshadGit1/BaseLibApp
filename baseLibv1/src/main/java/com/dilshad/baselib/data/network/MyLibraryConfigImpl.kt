package com.dilshad.baselib.data.network

import javax.inject.Inject

class MyLibraryConfigImpl @Inject constructor(override val baseUrl: String) : MyLibraryConfig