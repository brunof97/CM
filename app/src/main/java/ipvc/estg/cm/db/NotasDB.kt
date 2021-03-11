package ipvc.estg.cm.db

import android.content.Context
import android.provider.SyncStateContract.Helpers.insert
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ipvc.estg.cm.Notas.Notas
import ipvc.estg.cm.dao.NotasDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = arrayOf(Notas::class), version = 1, exportSchema = false)
public abstract class NotasDB : RoomDatabase() {

    abstract fun NotasDao(): NotasDao
    private class NotasDatabaseCallBack(
        private val scope: CoroutineScope
    ):RoomDatabase.Callback(){
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let{database->
                scope.launch {
                    var notaDao=database.NotasDao()

                    //delete all content
                    notaDao.deleteAll()

                    //add nota
                    var titulo=Notas(1,"Nota1")
                    notaDao.insert(titulo)
                    titulo=Notas(2,"Nota2")
                    notaDao.insert(titulo)



                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NotasDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NotasDB {
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotasDB::class.java,
                    "notas_database"

                )
                    .addCallback(NotasDatabaseCallBack(scope))
                    .build()
                INSTANCE=instance
                return instance
            }
        }
    }
}