package com.joni.zave_assignment.domain.models

data class SearchQuery(
    val id: Long = 0,
    val query: String,
    val timestamp: Long = System.currentTimeMillis()
)
