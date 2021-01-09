package app.valorant.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agente_table")
data class Agente(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val imagen: String,
    @ColumnInfo(name = "rol") val rol: String,
    @ColumnInfo(name = "rolIcon") val rol_icon: String,
    @ColumnInfo(name = "bio") val bio: String,
    @ColumnInfo(name = "iconQ") val icon_q: String,
    @ColumnInfo(name = "nombreQ") val nombre_q: String,
    @ColumnInfo(name = "descQ") val desc_q: String,
    @ColumnInfo(name = "iconE") val icon_e: String,
    @ColumnInfo(name = "nombreE") val nombre_e: String,
    @ColumnInfo(name = "descE") val desc_e: String,
    @ColumnInfo(name = "iconC") val icon_c: String,
    @ColumnInfo(name = "nombreC") val nombre_c: String,
    @ColumnInfo(name = "descC") val desc_c: String,
    @ColumnInfo(name = "iconX") val icon_x: String,
    @ColumnInfo(name = "nombreX") val nombre_x: String,
    @ColumnInfo(name = "descX") val desc_x: String
)