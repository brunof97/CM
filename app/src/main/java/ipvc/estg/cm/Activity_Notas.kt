package ipvc.estg.cm

import android.app.Activity
import android.content.Intent
import android.icu.text.CaseMap
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
import ipvc.estg.cm.dao.NotasDao
import ipvc.estg.cm.viewModel.NotasViewModel

class Activity_Notas : AppCompatActivity(),NotasAdapter.CellClickListener {


   // NotasAdapter.onItemClickListener

    private lateinit var notasViewModel:NotasViewModel
    private val newNotasActivityRequestCode=1
    private lateinit var adapter:   NotasAdapter;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__notas)

        val recyclerView=findViewById<RecyclerView>(R.id.recyclerview)
        adapter=NotasAdapter(this,this)
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
            val ntitle=data?.getStringExtra(AddNota.EXTRA_REPLY1)
            val nbody=data?.getStringExtra(AddNota.EXTRA_REPLY2)

            val nota=Notas(title=ntitle.toString(),body=nbody.toString())

            notasViewModel.insert(nota)

            }else {
                Toast.makeText(
                    applicationContext,
                        getString(R.string.emptynote),
                    Toast.LENGTH_LONG).show()
            }


    }

    override fun onCellClickListener(position:Int){


    val intent=Intent(this,NotasDetalhe::class.java)
        intent.putExtra("id",adapter.getNotaPosition(position).id)
        intent.putExtra("title",adapter.getNotaPosition(position).title)
        intent.putExtra("body",adapter.getNotaPosition(position).body)
        startActivity(intent)


 }
}