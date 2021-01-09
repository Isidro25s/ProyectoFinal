package app.valorant.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "armas_table")
data class Arma(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val imagen: String,
    @ColumnInfo(name = "bio") val bio: String,
    @ColumnInfo(name = "tipo") val tipo: String,
    @ColumnInfo(name = "creditos") val creditos: String,
    @ColumnInfo(name = "capacidad") val capacidad: String,
    @ColumnInfo(name = "damageH") val damageH: String,
    @ColumnInfo(name = "damageB") val damageB: String,
    @ColumnInfo(name = "damageL") val damageL: String
)