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

    @Query("UPDATE notas_table SET title=:title, body=:body WHERE id= :id ")
    suspend fun update(id:Int,title:String,body:String)

    @Query("DELETE FROM notas_table WHERE id=:id")
    suspend fun delete(id:Int)

    @Query("DELETE FROM notas_table")
    suspend fun deleteAll()

}