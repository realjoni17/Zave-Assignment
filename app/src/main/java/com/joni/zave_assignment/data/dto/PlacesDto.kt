package com.joni.zave_assignment.data.dto

data class PlacesDto(
    val html_attributions: List<Any>,
    val next_page_token: String,
    val results: List<Result>,
    val status: String
)