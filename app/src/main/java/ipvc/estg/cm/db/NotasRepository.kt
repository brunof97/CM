package ipvc.estg.cm.db

import androidx.lifecycle.LiveData
import ipvc.estg.cm.Notas.Notas
import ipvc.estg.cm.dao.NotasDao

class NotasRepository(private val notasDao: NotasDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allNotas: LiveData<List<Notas>> = notasDao.getNotas()


    suspend fun insert(titulo: Notas) {
        notasDao.insert(titulo)
    }
}