package com.sixtassginment.data.dto

data class CarDto(
    val id:String,
    val modelIdentifier:String?,
    val modelName:String?,
    val name:String?,
    val make:String?,
    val group:String?,
    val color:String?,
    val series:String?,
    val fuelType:String?,
    val fuelLevel:Double?,
    val transmission:String?,
    val licensePlate:String?,
    val latitude:Double?,
    val longitude:Double?,
    val innerCleanliness:String?,
    val carImageUrl:String?
    )
/*
* "id": "WMWSW31030T222518",
"modelIdentifier": "mini",
"modelName": "MINI",
"name": "Vanessa",
"make": "BMW",
"group": "MINI",
"color": "midnight_black",
"series": "MINI",
"fuelType": "D",
"fuelLevel": 0.7,
"transmission": "M",
"licensePlate": "M-VO0259",
"latitude": 48.134557,
"longitude": 11.576921,
"innerCleanliness": "REGULAR",
"carImageUrl": "https://cdn.sixt.io/codingtask/images/mini.png"
*
* */