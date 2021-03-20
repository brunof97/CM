package ipvc.estg.cm

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class AddNota : AppCompatActivity() {

    private lateinit var editTitleView:EditText
    private lateinit var editBodyView:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_nota)

        editTitleView=findViewById(R.id.edit_title)
        editBodyView=findViewById(R.id.edit_body)

        val button=findViewById<Button>(R.id.button_save)
        button.setOnClickListener{
            val replyIntent= Intent()
            if(TextUtils.isEmpty(editTitleView.text)){
                setResult(Activity.RESULT_CANCELED,replyIntent)
            } else if(TextUtils.isEmpty(editBodyView.text)){
                setResult(Activity.RESULT_CANCELED,replyIntent)
            }else{
                val title=editTitleView.text.toString()
                val body=editBodyView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY1, title)
                replyIntent.putExtra(EXTRA_REPLY2, body)
                setResult(Activity.RESULT_OK,replyIntent)
            }
            finish()
        }
    }

    companion object{
        const val EXTRA_REPLY1="title"
        const val EXTRA_REPLY2="body"

    }
}