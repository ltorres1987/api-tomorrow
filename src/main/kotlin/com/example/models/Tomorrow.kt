package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Data1(val data: Data2)

@Serializable
data class Data2(val timelines: List<Data3>)

@Serializable
data class Data3(val timestep: String, val endTime: String, val startTime: String, val intervals: List<Data4>)

@Serializable
data class Data4(val startTime: String, val values: Data5)

@Serializable
data class Data5(val temperature: Double)