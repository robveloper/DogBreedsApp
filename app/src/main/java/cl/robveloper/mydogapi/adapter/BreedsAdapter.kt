package cl.robveloper.mydogapi.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.robveloper.mydogapi.databinding.PatronlistaBinding
import cl.robveloper.mydogapi.model.ListMain
import cl.robveloper.mydogapi.ui.ActivityImages

class BreedsAdapter : RecyclerView.Adapter<BreedsAdapter.MyViewHolder>() {

    var Breeds : MutableList<ListMain> = ArrayList()
    lateinit var context : Context


    class MyViewHolder(val binding: PatronlistaBinding) : RecyclerView.ViewHolder(binding.root)

    fun BreedsAdapter(Breed: MutableList<ListMain>, context: Context){
        this.Breeds = Breed
        this.context = context
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = Breeds.get(position)
        holder.binding.tvBreed.text = item.breeds

        holder.binding.nameBreed.setOnClickListener{

            val showActivityImages = Intent(context.applicationContext, ActivityImages::class.java)
            showActivityImages.putExtra("breed", Breeds[position].breeds)
            showActivityImages.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.getApplicationContext().startActivity(showActivityImages)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(PatronlistaBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = Breeds.size

}