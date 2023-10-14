package model

data class Kill(
    val killer: Killer,
    val killed: Player,
    val weapon: Weapon,
) {
    override fun toString(): String {
        return "${killed.name} was killed with $weapon"
    }
}

interface Killer {
    fun kill(killed: Player, weapon: Weapon)
}
