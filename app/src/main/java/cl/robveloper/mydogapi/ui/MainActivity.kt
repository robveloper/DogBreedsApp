package cl.robveloper.mydogapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import cl.robveloper.mydogapi.adapter.BreedsAdapter
import cl.robveloper.mydogapi.databinding.ActivityMainBinding
import cl.robveloper.mydogapi.model.ListMain
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val BreedsAdapter : BreedsAdapter = BreedsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        charge_list()
    }

    fun charge_list(){
        val url = "https://dog.ceo/api/breeds/list/all"
        val call = JsonObjectRequest(Request.Method.GET, url, null,
            { response->
                try {

                    if(response.getString("status") == "success"){

                        val arrayData = response.getJSONObject("message")
                        val items : MutableList<ListMain> = ArrayList()

                        val key = arrayData.names()
                        for (i in 0 until key.length()){
                            val value = key.getString(i)
                            items.add(ListMain(value))
                        }
                        binding.tvTitleMain.text = "Mostrando " + key.length().toString() + " Razas de perros"
                        binding.rvBreeds.setHasFixedSize(true)
                        binding.rvBreeds.layoutManager = LinearLayoutManager(this)
                        BreedsAdapter.BreedsAdapter(items, this)
                        binding.rvBreeds.adapter = BreedsAdapter



                    }else{
                        Toast.makeText(this, "Error descarga", Toast.LENGTH_LONG).show()
                    }

                } catch (e:Exception){
                    Toast.makeText(this, "$e", Toast.LENGTH_LONG).show()
                }

            }, {
                Toast.makeText(this, "$it", Toast.LENGTH_LONG).show()
            })

        val stack = Volley.newRequestQueue(this)
        stack.add(call)
    }

}