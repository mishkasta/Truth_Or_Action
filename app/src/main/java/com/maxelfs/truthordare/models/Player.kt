package com.maxelfs.truthordare.models

data class Player (
    var id: Long = 0L,
    var name: String = "",
    var gender: Gender = Gender.NOT_SET,
    var position: Int = 0
)