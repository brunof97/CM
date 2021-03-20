package ipvc.estg.cm.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ipvc.estg.cm.Notas.Notas
import ipvc.estg.cm.db.NotasDB
import ipvc.estg.cm.db.NotasRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NotasViewModel(application: Application) : AndroidViewModel(application) {

    private val repository:NotasRepository

    val allNotas: LiveData<List<Notas>>

    init{
        val notaDao= NotasDB.getDatabase(application, viewModelScope).NotasDao()
        repository=NotasRepository(notaDao)
        allNotas=repository.allNotas

    }

    fun insert(note:Notas)=viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
    }
}

