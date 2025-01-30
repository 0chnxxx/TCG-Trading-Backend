package com.trading.tcg.application.card.domain

enum class PokemonType(
    val type: String,
    val image: String
) {
    ZERO_COST("0코스트", "https://cards.image.pokemonkorea.co.kr/data/images/symbol/0cost.png"),
    PLUS("플러스", "https://cards.image.pokemonkorea.co.kr/data/images/symbol/plus.png"),
    NONE("무색", "https://cards.image.pokemonkorea.co.kr/data/images/symbol/type9.png"),
    FIRE("불꽃", "https://cards.image.pokemonkorea.co.kr/data/images/symbol/type2.png"),
    WATER("물", "https://cards.image.pokemonkorea.co.kr/data/images/symbol/type3.png"),
    GRASS("풀", "https://cards.image.pokemonkorea.co.kr/data/images/symbol/type1.png"),
    THUNDER("번개", "https://cards.image.pokemonkorea.co.kr/data/images/symbol/type4.png"),
    FIGHTER("격투", "https://cards.image.pokemonkorea.co.kr/data/images/symbol/type6.png"),
    STEEL("강철", "https://cards.image.pokemonkorea.co.kr/data/images/symbol/type8.png"),
    DEVIL("악", "https://cards.image.pokemonkorea.co.kr/data/images/symbol/type7.png"),
    FAIRY("페어리", "https://cards.image.pokemonkorea.co.kr/data/images/symbol/type11.png"),
    DRAGON("드래곤", "https://cards.image.pokemonkorea.co.kr/data/images/symbol/type10.png"),
    SUPER("초", "https://cards.image.pokemonkorea.co.kr/data/images/symbol/type5.png");
}
