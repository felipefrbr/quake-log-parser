package model

data class Player(
    val id: Int,
    var name: String = "",
    private val kills: MutableList<Kill> = mutableListOf(),
) : Killer {
    fun changeName(newName: String) {
        name = newName
    }

    override fun kill(killed: Player, weapon: Weapon) {
        kills.add(Kill(this, killed, weapon))
    }

    fun getKills(): List<Kill> {
        return kills
    }
}
