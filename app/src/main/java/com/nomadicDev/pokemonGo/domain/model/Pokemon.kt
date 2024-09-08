package com.nomadicDev.pokemonGo.domain.model

import com.nomadicDev.pokemonGo.data.data_source.local.db.Ability
import com.nomadicDev.pokemonGo.data.data_source.local.db.Attack
import com.nomadicDev.pokemonGo.data.data_source.local.db.ImageUrls
import com.nomadicDev.pokemonGo.data.data_source.local.db.Resistance
import com.nomadicDev.pokemonGo.data.data_source.local.db.Weakness

data class Pokemon(
    val id: String,
    val name: String,
    val level: Int?,
    val hp: Int,
    val types: List<String>? = emptyList(),
    val images: ImageUrls,
    val attacks: List<Attack> = emptyList(),
    val weaknesses: List<Weakness>? = null,
    val resistances: List<Resistance>? = null,
    val abilities: List<Ability>? = null,
    val subtypes: List<String>? = null
)