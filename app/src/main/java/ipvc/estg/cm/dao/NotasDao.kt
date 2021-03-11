package ipvc.estg.cm.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ipvc.estg.cm.Notas.Notas


@Dao
interface NotasDao {

    @Query("SELECT * FROM notas_table ORDER BY title ASC")
    fun getNotas(): LiveData<List<Notas>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(title: Notas)

    @Query("DELETE FROM notas_table")
    suspend fun deleteAll()

}