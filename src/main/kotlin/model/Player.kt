package model

data class Player(
    val id: Int,
    var name: String = "",
    private val kills: Int = 0,
) {
    fun changeName(newName: String) {
        name = newName
    }
}
