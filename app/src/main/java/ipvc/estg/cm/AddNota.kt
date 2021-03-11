package ipvc.estg.cm

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class AddNota : AppCompatActivity() {

    private lateinit var editNotasView:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_nota)

        editNotasView=findViewById(R.id.edit_nota)

        val button=findViewById<Button>(R.id.button_save)
        button.setOnClickListener{
            val replyIntent= Intent()
            if(TextUtils.isEmpty(editNotasView.text)){
                setResult(Activity.RESULT_CANCELED,replyIntent)
            } else{
                val titulo=editNotasView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, titulo)
                setResult(Activity.RESULT_OK,replyIntent)
            }
            finish()
        }
    }

    companion object{
        const val EXTRA_REPLY="com.android.wordlistsql.Reply"
    }
}