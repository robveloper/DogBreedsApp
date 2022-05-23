package cl.robveloper.mydogapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import cl.robveloper.mydogapi.adapter.ImagesAdapter
import cl.robveloper.mydogapi.databinding.ActivityImagesBinding
import cl.robveloper.mydogapi.model.ListImages
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import java.lang.Exception

class ActivityImages : AppCompatActivity() {

    lateinit var binding: ActivityImagesBinding
    var ImagesAdapter : ImagesAdapter = ImagesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = this.intent.extras
        var breed = data!!.getString("breed").toString()

        binding.tvTitle.text = "Imagenes de " +breed.replaceFirstChar { it.uppercase() }
        donwload_images(breed)

    }

    fun donwload_images( breed : String){

        var url = "https://dog.ceo/api/breed/"+breed+"/images"

        val call = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response->
                try {

                    if(response.getString("status") == "success"){

                        val arrayData = response.getJSONArray("message")
                        val items : MutableList<ListImages> = ArrayList()

                        for (i in 0 until arrayData.length()) {
                            var item = arrayData.get(i)
                            val aux = i + 1
                            var name = breed + " " + aux.toString()
                            items.add(ListImages(name, item.toString()))
                        }

                        binding.rvImages.setHasFixedSize(true)
                        binding.rvImages.layoutManager = LinearLayoutManager(this)
                        ImagesAdapter.ImagesAdapter(items, this)
                        binding.rvImages.adapter = ImagesAdapter

                    }else{
                        Toast.makeText(this, "Error descarga", Toast.LENGTH_LONG).show()
                    }

                } catch (e: Exception){
                    Toast.makeText(this, "$e", Toast.LENGTH_LONG).show()
                }

            }, {
                Toast.makeText(this, "$it", Toast.LENGTH_LONG).show()
            })

        val stack = Volley.newRequestQueue(this)
        stack.add(call)
    }
}