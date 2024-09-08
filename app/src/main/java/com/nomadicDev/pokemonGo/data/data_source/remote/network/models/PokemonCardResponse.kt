package com.nomadicDev.pokemonGo.data.data_source.remote.network.models

import com.google.gson.annotations.SerializedName

data class PokemonCardResponse(

	@field:SerializedName("data")
	val data: List<PokemonNetworkItem>,

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("pageSize")
	val pageSize: Int,

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("totalCount")
	val totalCount: Int
)

data class Normal(

	@field:SerializedName("market")
	val market: Any,

	@field:SerializedName("high")
	val high: Any,

	@field:SerializedName("directLow")
	val directLow: Any,

	@field:SerializedName("low")
	val low: Any,

	@field:SerializedName("mid")
	val mid: Any
)

data class UnlimitedHolofoil(

	@field:SerializedName("market")
	val market: Any,

	@field:SerializedName("high")
	val high: Any,

	@field:SerializedName("directLow")
	val directLow: Any,

	@field:SerializedName("low")
	val low: Any,

	@field:SerializedName("mid")
	val mid: Any
)

data class ResistancesItem(

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("value")
	val value: String
)

data class WeaknessesItem(

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("value")
	val value: String
)

data class JsonMember1stEditionHolofoil(

	@field:SerializedName("market")
	val market: Any,

	@field:SerializedName("high")
	val high: Any,

	@field:SerializedName("directLow")
	val directLow: Any,

	@field:SerializedName("low")
	val low: Any,

	@field:SerializedName("mid")
	val mid: Any
)

data class PokemonNetworkItem(

	@field:SerializedName("supertype")
	val supertype: String,

	@field:SerializedName("types")
	val types: List<String>?,

	@field:SerializedName("images")
	val images: Images,

	@field:SerializedName("retreatCost")
	val retreatCost: List<String>,

	@field:SerializedName("set")
	val set: Set,

	@field:SerializedName("artist")
	val artist: String,

	@field:SerializedName("hp")
	val hp: String?,

	@field:SerializedName("convertedRetreatCost")
	val convertedRetreatCost: Int,

	@field:SerializedName("legalities")
	val legalities: Legalities,

	@field:SerializedName("evolvesFrom")
	val evolvesFrom: String,

	@field:SerializedName("tcgplayer")
	val tcgplayer: Tcgplayer,

	@field:SerializedName("subtypes")
	val subtypes: List<String>,

	@field:SerializedName("number")
	val number: String,

	@field:SerializedName("attacks")
	val attacks: List<AttacksItem>?,

	@field:SerializedName("nationalPokedexNumbers")
	val nationalPokedexNumbers: List<Int>,

	@field:SerializedName("weaknesses")
	val weaknesses: List<WeaknessesItem>?,

	@field:SerializedName("name")
	val name: String?,

	@field:SerializedName("cardmarket")
	val cardmarket: Cardmarket,

	@field:SerializedName("id")
	val id: String?,

	@field:SerializedName("abilities")
	val abilities: List<AbilitiesItem>?,

	@field:SerializedName("rarity")
	val rarity: String,

	@field:SerializedName("flavorText")
	val flavorText: String,

	@field:SerializedName("evolvesTo")
	val evolvesTo: List<String>,

	@field:SerializedName("resistances")
	val resistances: List<ResistancesItem>?,

	@field:SerializedName("level")
	val level: String?,

	@field:SerializedName("rules")
	val rules: List<String>
)

data class Images(

	@field:SerializedName("symbol")
	val symbol: String,

	@field:SerializedName("logo")
	val logo: String,

	@field:SerializedName("small")
	val small: String,

	@field:SerializedName("large")
	val large: String
)

data class Holofoil(

	@field:SerializedName("market")
	val market: Any,

	@field:SerializedName("high")
	val high: Any,

	@field:SerializedName("directLow")
	val directLow: Any,

	@field:SerializedName("low")
	val low: Any,

	@field:SerializedName("mid")
	val mid: Any
)

data class AttacksItem(

	@field:SerializedName("damage")
	val damage: String,

	@field:SerializedName("cost")
	val cost: List<String>,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("text")
	val text: String,

	@field:SerializedName("convertedEnergyCost")
	val convertedEnergyCost: Int
)

data class Prices(

	@field:SerializedName("avg30")
	val avg30: Any,

	@field:SerializedName("reverseHoloSell")
	val reverseHoloSell: Any,

	@field:SerializedName("reverseHoloLow")
	val reverseHoloLow: Any,

	@field:SerializedName("averageSellPrice")
	val averageSellPrice: Any,

	@field:SerializedName("reverseHoloAvg7")
	val reverseHoloAvg7: Any,

	@field:SerializedName("germanProLow")
	val germanProLow: Any,

	@field:SerializedName("avg7")
	val avg7: Any,

	@field:SerializedName("trendPrice")
	val trendPrice: Any,

	@field:SerializedName("suggestedPrice")
	val suggestedPrice: Any,

	@field:SerializedName("avg1")
	val avg1: Any,

	@field:SerializedName("reverseHoloTrend")
	val reverseHoloTrend: Any,

	@field:SerializedName("lowPrice")
	val lowPrice: Any,

	@field:SerializedName("reverseHoloAvg30")
	val reverseHoloAvg30: Any,

	@field:SerializedName("lowPriceExPlus")
	val lowPriceExPlus: Any,

	@field:SerializedName("reverseHoloAvg1")
	val reverseHoloAvg1: Any,

	@field:SerializedName("normal")
	val normal: Normal,

	@field:SerializedName("holofoil")
	val holofoil: Holofoil,

	@field:SerializedName("reverseHolofoil")
	val reverseHolofoil: ReverseHolofoil,

	@field:SerializedName("1stEditionHolofoil")
	val jsonMember1stEditionHolofoil: JsonMember1stEditionHolofoil,

	@field:SerializedName("unlimitedHolofoil")
	val unlimitedHolofoil: UnlimitedHolofoil
)

data class AbilitiesItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("text")
	val text: String,

	@field:SerializedName("type")
	val type: String
)

data class Legalities(

	@field:SerializedName("unlimited")
	val unlimited: String,

	@field:SerializedName("expanded")
	val expanded: String
)

data class Cardmarket(

	@field:SerializedName("prices")
	val prices: Prices,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

data class ReverseHolofoil(

	@field:SerializedName("market")
	val market: Any,

	@field:SerializedName("high")
	val high: Any,

	@field:SerializedName("directLow")
	val directLow: Any,

	@field:SerializedName("low")
	val low: Any,

	@field:SerializedName("mid")
	val mid: Any
)

data class Tcgplayer(

	@field:SerializedName("prices")
	val prices: Prices,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

data class Set(

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("images")
	val images: Images,

	@field:SerializedName("printedTotal")
	val printedTotal: Int,

	@field:SerializedName("releaseDate")
	val releaseDate: String,

	@field:SerializedName("series")
	val series: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("legalities")
	val legalities: Legalities,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String,

	@field:SerializedName("ptcgoCode")
	val ptcgoCode: String
)
