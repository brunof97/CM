package ipvc.estg.cm.Notas

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notas_table")
data class Notas(

        @PrimaryKey(autoGenerate=true) val id:Int?=null,
        @ColumnInfo(name = "title") val title: String
)

