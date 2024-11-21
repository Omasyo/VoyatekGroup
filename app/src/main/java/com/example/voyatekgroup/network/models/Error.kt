package com.example.voyatekgroup.network.models

data class Error(
    val code: String,
    override val message: String
) : Exception()