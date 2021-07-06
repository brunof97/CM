package ipvc.estg.cm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ipvc.estg.cm.adapters.UserAdapter
import ipvc.estg.cm.api.Endpoints
import ipvc.estg.cm.api.ServiceBuilder
import ipvc.estg.cm.api.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        /*val request = ServiceBuilder.buildService(Endpoints::class.java)
        val call = request.getUsers()
        call.enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                if (response.isSuccessful){
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@MainActivity2)
                        adapter = UserAdapter(response.body()!!)
                    }
                }
            }
            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                Toast.makeText(this@MainActivity2, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })*/
    }


    fun getSingle(view: View){

        val request = ServiceBuilder.buildService(Endpoints::class.java)
        val call = request.getUserById(2)
        call.enqueue(object : Callback<Users>{
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                if (response.isSuccessful){
                    val c: Users = response.body()!!
                    Toast.makeText(this@MainActivity2, c.id, Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Users>, t: Throwable) {
                Toast.makeText(this@MainActivity2, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }
}