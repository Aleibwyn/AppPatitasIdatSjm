package pe.edu.idat.apppatitasidatsjm.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.idat.apppatitasidatsjm.databinding.ItemMascotaBinding
import pe.edu.idat.apppatitasidatsjm.retrofit.response.MascotaResponse

class MascotaAdapter(private var listaMascota: List<MascotaResponse>) : RecyclerView.Adapter<MascotaAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemMascotaBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =  ItemMascotaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listaMascota[position]){
                binding.tvNomMascota.text = nommascota
                binding.tvUbicacionMascota.text = lugar
                binding.tvContactoMascota.text = contacto
                Glide.with(itemView.context)
                    .load(urlimagen)
                    .into(binding.ivMascota)
            }
        }
    }

    override fun getItemCount(): Int = listaMascota.size
}