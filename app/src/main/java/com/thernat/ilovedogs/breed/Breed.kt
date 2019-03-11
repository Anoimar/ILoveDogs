package com.thernat.ilovedogs.breed

data class Breed(
    val bred_for: String,
    val breed_group: String,
    val country_code: String,
    val description: String,
    val height: Height,
    val history: String,
    val id: Int,
    val life_span: String,
    val name: String,
    val origin: String,
    val temperament: String,
    val weight: Weight
)