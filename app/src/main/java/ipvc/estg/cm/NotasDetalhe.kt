package ipvc.estg.cm

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import ipvc.estg.cm.viewModel.NotasViewModel

class NotasDetalhe : AppCompatActivity() {
    private lateinit var notasViewModel: NotasViewModel

    lateinit var title: EditText
    lateinit var body: EditText
    var id: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas_detalhe)
        notasViewModel = ViewModelProvider(this).get(NotasViewModel::class.java)

        val bundle: Bundle? = intent.extras

        title = findViewById(R.id.edit_nota_title)
        body = findViewById(R.id.edit_nota_body)

        var ntitle: String = " "
        var nbody: String = " "

        if (bundle != null) {
            ntitle = bundle.getString("title").toString()
            nbody = bundle.getString("body").toString()
            id = bundle.getInt("id")
            title.setText(ntitle)
            body.setText(nbody)
        }


        val button_update: Button = findViewById(R.id.button_editar)
        val button_delete: Button = findViewById(R.id.button_apagar)

        button_update.setOnClickListener {
            if (TextUtils.isEmpty(title.text) || TextUtils.isEmpty(body.text)) {
                Toast.makeText(this, "Todos os campos devem estar preenchidos", Toast.LENGTH_SHORT).show();
            } else {
                notasViewModel.update(id = id, title = title.text.toString(), body = body.text.toString())
                Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show();
            }
            finish()
        }

        button_delete.setOnClickListener {

            notasViewModel.delete(id = id)

            finish()
        }


    }
}