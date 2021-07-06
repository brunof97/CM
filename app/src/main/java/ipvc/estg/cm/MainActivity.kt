package ipvc.estg.cm

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import ipvc.estg.cm.api.Endpoints
import ipvc.estg.cm.api.OutputPost
import ipvc.estg.cm.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, Activity_Notas::class.java)
            startActivity(intent)
        }



        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener {


            val username = findViewById<EditText>(R.id.username).text.toString().trim()
            val password = findViewById<EditText>(R.id.password).text.toString().trim()

            val request = ServiceBuilder.buildService(Endpoints::class.java)
            val call = request.login(username, password)

            if(username.isEmpty() || password.isEmpty())
            {
                Toast.makeText(this@MainActivity, getString(R.string.emptyfield), Toast.LENGTH_SHORT).show()

            }
            else {

                call.enqueue(object : Callback<OutputPost> {
                    override fun onResponse(call: Call<OutputPost>, response: Response<OutputPost>) {

                        if (response.isSuccessful) {
                            if (response.body()?.estado == false) {
                                Toast.makeText(
                                        this@MainActivity,
                                        getString(R.string.incorrect),
                                        Toast.LENGTH_SHORT
                                ).show()
                            } else {

                                val check1: CheckBox = findViewById(R.id.checkBox)
                                val id = response.body()?.title.toString()
                                if (check1.isChecked) {


                                    var token = getSharedPreferences("utilizador", Context.MODE_PRIVATE)
                                    intent.putExtra("utilizador", username)
                                    intent.putExtra("utilizador", id)
                                    var editor = token.edit()
                                    editor.putString("loginutilizador", username)
                                    editor.putString("loginid", id)
                                    editor.commit()
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK


                                }


                                Toast.makeText(this@MainActivity, getString(R.string.welcome) + username, Toast.LENGTH_LONG).show()

                                val intent = Intent(this@MainActivity, MapsActivity::class.java)
                                intent.putExtra("id", id)
                                if (check1.isChecked) {
                                    finish()
                                }
                                startActivity(intent)


                            }
                        }
                    }
                    override fun onFailure(call: Call<OutputPost>, t: Throwable) {

                        Toast.makeText(
                                this@MainActivity,
                                getString(R.string.incorrect),
                                Toast.LENGTH_SHORT
                        ).show()
                    }
                })





            }




        }




    }

    override fun onResume() {
        super.onResume()
        var token = getSharedPreferences("utilizador", Context.MODE_PRIVATE)
        if (token.getString("loginutilizador", " ") != " ") {
            val intent = Intent(applicationContext, MapsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("id", token.getString("loginid", " "))

            finish()
            startActivity(intent)
        }
    }

}