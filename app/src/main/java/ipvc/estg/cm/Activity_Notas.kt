package ipvc.estg.cm

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ipvc.estg.cm.Notas.Notas
import ipvc.estg.cm.adapters.NotasAdapter
import ipvc.estg.cm.viewModel.NotasViewModel

class Activity_Notas : AppCompatActivity() {

    private lateinit var notasViewModel:NotasViewModel
    private val newNotasActivityRequestCode=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__notas)

        val recyclerView=findViewById<RecyclerView>(R.id.recyclerview)
        val adapter=NotasAdapter(this)
        recyclerView.adapter=adapter
        recyclerView.layoutManager=LinearLayoutManager(this)

        notasViewModel=ViewModelProvider(this).get(NotasViewModel::class.java)
        notasViewModel.allNotas.observe(this, Observer { nota->
            nota?.let{adapter.setNotas(it)}
        })

        val fab=findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{
         val intent = Intent(this@Activity_Notas, AddNota::class.java)
          startActivityForResult(intent,newNotasActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==newNotasActivityRequestCode&&resultCode==Activity.RESULT_OK){
            data?.getStringExtra(AddNota.EXTRA_REPLY)?.let {
                val titulo = Notas(title = it)
                notasViewModel.insert(titulo)
            }
            }else {
                Toast.makeText(
                    applicationContext,
                    "nota vazia",
                    Toast.LENGTH_LONG).show()
            }


    }

}