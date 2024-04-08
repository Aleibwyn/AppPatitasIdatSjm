package pe.edu.idat.apppatitasidatsjm.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.idat.apppatitasidatsjm.MascotaAdapter
import pe.edu.idat.apppatitasidatsjm.databinding.FragmentMascotaBinding
import pe.edu.idat.apppatitasidatsjm.viewModel.MascotaViewModel

class MascotaFragment : Fragment() {
    private var _binding: FragmentMascotaBinding? = null
    private val binding = _binding!!
    private lateinit var viewModel: MascotaViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMascotaBinding.inflate(inflater, container, false)
        val view =  binding.root

        binding.mascotasView.layoutManager = LinearLayoutManager(requireActivity())


        viewModel =  ViewModelProvider(this).get(MascotaViewModel::class.java)

        listarMascotas()
        return view
    }

    private fun listarMascotas() {
        viewModel.listarMascotas().observe(viewLifecycleOwner, Observer {
            binding.mascotasView.adapter = MascotaAdapter(it)
        })
    }

}