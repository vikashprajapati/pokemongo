package com.nomadicDev.pokemonGo.domain

import android.util.Log
import com.google.gson.Gson
import com.nomadicDev.pokemonGo.data.data_source.local.db.Ability
import com.nomadicDev.pokemonGo.data.data_source.local.db.Attack
import com.nomadicDev.pokemonGo.data.data_source.local.db.ImageUrls
import com.nomadicDev.pokemonGo.data.data_source.local.db.PokemonEntity
import com.nomadicDev.pokemonGo.data.data_source.local.db.Resistance
import com.nomadicDev.pokemonGo.data.data_source.local.db.Weakness
import com.nomadicDev.pokemonGo.data.data_source.remote.network.models.PokemonNetworkItem
import com.nomadicDev.pokemonGo.domain.model.Pokemon
import java.util.UUID


fun List<PokemonNetworkItem>?.toPokemonCardEntities(): List<PokemonEntity>? {
    return this?.map {
        Log.d("Mappers", "toPokemonCardEntities: ${Gson().toJson(it)}")
        PokemonEntity(
            id = (it.id?:UUID.randomUUID()).toString(),
            imageUrl = ImageUrls(
                small = it.images.small,
                large = it.images.large
            ),
            name = it.name?: "",
            types = it.types,
            subtypes = it.subtypes,
            level = it.level?.toInt(),
            hp = it.hp?.toInt(),
            attacks = it.attacks?.map { attack ->
                Attack(
                    name = attack.name,
                    cost = attack.cost,
                    damage = attack.damage,
                    text = attack.text
                )
            },
            weaknesses = it.weaknesses?.map { wk ->
                Weakness(
                    type = wk.type,
                    value = wk.value
                )
            },
            resistances = it.resistances?.map { rs ->
                Resistance(
                    type = rs.type,
                    value = rs.value
                )
            },
            abilities = it.abilities?.map { ab ->
                Ability(
                    name = ab.name,
                    text = ab.text
                )
            }
        )
    }
}

fun List<PokemonEntity>?.toDomainModel(): List<Pokemon> {
    return this?.map {
        Pokemon(
            id = it.id,
            images = it.imageUrl,
            name = it.name,
            types = it.types,
            hp = it.hp?: 0,
            level = it.level?: 0
        )
    } ?: emptyList()
}

fun PokemonEntity.toDomainModel(): Pokemon {
    return Pokemon(
        id = this.id,
        images = this.imageUrl,
        name = this.name,
        types = this.types,
        hp = this.hp?: 0,
        level = this.level
    )
}

fun Attack.toFormattedString(): String {
    return "${this.name} (${this.cost.joinToString(" ")}): ${this.damage} ${this.text}"
}

fun List<Attack>.toAttackFormattedString(): String {
    return this.joinToString("\n") { it.toFormattedString() }
}

fun Weakness.toFormattedString(): String {
    return "${this.type} (${this.value})"
}

fun List<Weakness>.toWeaknessFormattedString(): String {
    return this.joinToString("\n") { it.toFormattedString() }
}

fun Resistance.toFormattedString(): String {
    return "${this.type} (${this.value})"
}

fun List<Resistance>.toResistanceFormattedString(): String {
    return this.joinToString("\n") { it.toFormattedString() }
}

fun Ability.toFormattedString(): String {
    return "${this.name}: ${this.text}"
}

fun List<Ability>.toAbilityFormattedString(): String {
    return this.joinToString("\n") { it.toFormattedString() }
}