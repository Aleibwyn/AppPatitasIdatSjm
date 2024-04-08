package pe.edu.idat.apppatitasidatsjm.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pe.edu.idat.apppatitasidatsjm.databinding.FragmentVoluntarioBinding
import pe.edu.idat.apppatitasidatsjm.model.entity.PersonaEntity
import pe.edu.idat.apppatitasidatsjm.retrofit.response.RegistroResponse
import pe.edu.idat.apppatitasidatsjm.util.AppMensaje
import pe.edu.idat.apppatitasidatsjm.util.TipoMensaje
import pe.edu.idat.apppatitasidatsjm.viewModel.PersonaViewModel
import pe.edu.idat.apppatitasidatsjm.viewModel.VoluntarioViewModel

class VoluntarioFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentVoluntarioBinding? = null
    private val binding = _binding!!

    private lateinit var viewModel: VoluntarioViewModel
    private  lateinit var personaViewModel: PersonaViewModel

    private lateinit var personaEntity: PersonaEntity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVoluntarioBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(VoluntarioViewModel::class.java)
        personaViewModel = ViewModelProvider(this).get(PersonaViewModel::class.java)

        binding.btnregistrarvoluntario.setOnClickListener(this)

        personaViewModel.obtener().observe(viewLifecycleOwner, Observer {
            persona -> persona?.let {
            if (persona.esvoluntario == "1") {
                formVoluntario()
            } else {
                personaEntity = persona
            }
        }
        })

        return binding.root
    }

    override fun onClick(p0: View) {
        if (binding.cbaceptarterminos.isChecked) {
            binding.btnregistrarvoluntario.isEnabled = false

            viewModel.registroVoluntario(personaEntity.id)
        } else {
            AppMensaje.enviarMensaje(
                binding.root,
                "Acepte los términos y condiciones para ser voluntario.",
                TipoMensaje.ERROR
            )
        }
        viewModel.response.observe(viewLifecycleOwner, Observer {
            respuestaRegistroVoluntario(it)
        })
    }

    private fun respuestaRegistroVoluntario(it: RegistroResponse?) {
        if (it!!.rpta) {
            val nuevaPersonaEntity = PersonaEntity(
                personaEntity.id,
                personaEntity.nombres,
                personaEntity.apellidos,
                personaEntity.email,
                personaEntity.celular,
                personaEntity.usuario,
                personaEntity.password,
                "1"
            )
            personaViewModel.actualizar(nuevaPersonaEntity)
            formVoluntario()
        }
        AppMensaje.enviarMensaje(binding.root, it.mensaje, TipoMensaje.SUCCESSFULL)
    }

    private fun formVoluntario() {
        binding.cbaceptarterminos.visibility = View.GONE
        binding.btnregistrarvoluntario.visibility = View.GONE
        binding.textView4.visibility = View.GONE
        binding.textView3.text = "Gracias por su compromiso como voluntario"

    }
}