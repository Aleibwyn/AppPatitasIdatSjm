package pe.edu.idat.apppatitasidatsjm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.idat.apppatitasidatsjm.databinding.ItemMascotaBinding
import pe.edu.idat.apppatitasidatsjm.retrofit.response.MascotaResponse

class MascotaAdapter(val mascotas: List<MascotaResponse>) : RecyclerView.Adapter<MascotaAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemMascotaBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MascotaAdapter.ViewHolder {
        val binding =  ItemMascotaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MascotaAdapter.ViewHolder, position: Int) {
        with(holder.binding) {
            with(mascotas[position]){
                tvNomMascota.text = nommascota
                tvUbicacionMascota.text = lugar
                tvContactoMascota.text = contacto

                Glide.with(holder.binding.root.context)
                    .load(urlimagem).centerCrop().into(ivMascota)
            }
        }
    }

    override fun getItemCount(): Int = mascotas.size
}