package model

data class World(
    val id: Int,
    val name: String = "",
    private val kills: MutableList<Kill> = mutableListOf(),
) : Killer {

    override fun kill(killed: Player, weapon: Weapon) {
        kills.add(Kill(this, killed, weapon))
    }

    override fun getKills(): List<Kill> {
        return kills
    }
}
