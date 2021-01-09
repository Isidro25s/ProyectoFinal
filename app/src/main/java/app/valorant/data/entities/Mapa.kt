package app.valorant.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mapas_table")
data class Mapa(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val imagen: String,
    @ColumnInfo(name = "bio") val bio: String,
    @ColumnInfo(name = "minimap") val minimap: String,
    @ColumnInfo(name = "map1") val map1: String,
    @ColumnInfo(name = "map2") val map2: String,
    @ColumnInfo(name = "map3") val map3: String,
    @ColumnInfo(name = "map4") val map4: String
)