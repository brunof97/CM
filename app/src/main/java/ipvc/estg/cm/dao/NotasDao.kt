package ipvc.estg.cm.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ipvc.estg.cm.Notas.Notas


@Dao
interface NotasDao {

    @Query("SELECT * FROM notas_table ORDER BY title ASC")
    fun getNotas(): LiveData<List<Notas>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note:Notas)

    @Query("DELETE FROM notas_table")
    suspend fun deleteAll()

}