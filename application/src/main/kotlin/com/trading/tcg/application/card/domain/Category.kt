package com.trading.tcg.application.card.domain

enum class Category(val category: String) {
    NORMAL("기본"),
    FIRST_EVOLUTION("1진화"),
    SECOND_EVOLUTION("2진화"),
    POKEMON_ex("포켓몬 ex"),
    ANCIENT("고대"),
    FUTURE("미래"),
    POKEMON_V("포켓몬 V"),
    POKEMON_VMAX("포켓몬 VMAX"),
    POKEMON_VSTAR("포켓몬 VSTAR"),
    POKEMON_GX("포켓몬 GX"),
    BLOW("일격"),
    COMBO("연격"),
    FUSION("퓨전"),
    RECOVERY("복원"),
    LEVELUP("레벨업"),
    PRISMSTAR("프리즘스타"),
    POKEMON_EX("포켓몬 EX"),
    M_EVOLUTION("M진화"),
    BREAK_EVOLUTION("BREAK진화"),
    V_EVOLUTION("V진화"),
    V_UNION("V-UNION"),
    TAG_TEAM("TAG TEAM"),
    TAG_TEAM_GX("TAG TEAM GX"),
    ITEM("아이템"),
    SUPPORT("서포트"),
    TOOL_OF_("포켓몬의 도구"),
    STARDIUM("스타디움"),
    NORMAL_ENERGY("기본 에너지"),
    SPECIAL_ENERGY("특수 에너지");
}
