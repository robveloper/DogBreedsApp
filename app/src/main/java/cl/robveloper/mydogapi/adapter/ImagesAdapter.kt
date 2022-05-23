package cl.robveloper.mydogapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.robveloper.mydogapi.databinding.PatronimgsBinding
import cl.robveloper.mydogapi.model.ListImages
import com.bumptech.glide.Glide

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.MyViewHolder>() {

    var ImagesDogs: MutableList<ListImages> = ArrayList()
    lateinit var context: Context

    class MyViewHolder(val binding: PatronimgsBinding) : RecyclerView.ViewHolder(binding.root)

    fun ImagesAdapter(Breed: MutableList<ListImages>, context: Context) {
        this.ImagesDogs = Breed
        this.context = context
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = ImagesDogs.get(position)
        holder.binding.tvName.text = item.idImag
        Glide.with(context).load(item.route).into(holder.binding.imgDog)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            PatronimgsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = ImagesDogs.size


}