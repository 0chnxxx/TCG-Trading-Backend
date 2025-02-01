package com.trading.tcg.application.pokemon.domain

enum class PokemonCategory(
    val category: String
) {
    NORMAL("기본"),
    FIRST_EVOLUTION("1진화"),
    SECOND_EVOLUTION("2진화"),
    M_EVOLUTION("M진화"),
    BREAK_EVOLUTION("BREAK진화"),
    V_EVOLUTION("V진화"),
    V("V"),
    VMAX("VMAX"),
    VSTAR("VSTAR"),
    V_UNION("V-UNION"),
    ex("ex"),
    EX("EX"),
    GX("GX"),
    BRILLIANT("찬란한"),
    TEAM_PLASMA("플리스마단"),
    ANCIENT("고대"),
    FUTURE("미래"),
    FUSION("퓨전"),
    BLOW("일격"),
    COMBO("연격"),
    PRISMSTAR("프리즘스타"),
    LEVELUP("레벨업"),
    RECOVERY("복원"),
    TAG_TEAM("TAG TEAM"),
    ITEM("아이템"),
    TOOL_OF_("포켓몬의 도구"),
    SUPPORT("서포트"),
    STARDIUM("스타디움"),
    NORMAL_ENERGY("기본 에너지"),
    SPECIAL_ENERGY("특수 에너지");
}
