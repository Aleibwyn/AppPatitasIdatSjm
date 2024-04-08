package pe.edu.idat.apppatitasidatsjm.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import pe.edu.idat.apppatitasidatsjm.R
import pe.edu.idat.apppatitasidatsjm.databinding.FragmentVoluntarioBinding
import pe.edu.idat.apppatitasidatsjm.util.AppMensaje
import pe.edu.idat.apppatitasidatsjm.util.TipoMensaje
import pe.edu.idat.apppatitasidatsjm.viewModel.VoluntarioViewModel

class VoluntarioFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentVoluntarioBinding? = null
    private val binding = _binding!!

    private lateinit var viewModel: VoluntarioViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVoluntarioBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(VoluntarioViewModel::class.java)

        binding.btnregistrarvoluntario.setOnClickListener(this)


        return binding.root
    }

    override fun onClick(p0: View) {
        if (binding.cbaceptarterminos.isChecked) {
            binding.btnregistrarvoluntario.isEnabled = false

            //viewModel.registroVoluntario()
        } else {
            AppMensaje.enviarMensaje(
                binding.root,
                "Acepte los términos y condiciones para ser voluntario.",
                TipoMensaje.ERROR
            )
        }
    }
}