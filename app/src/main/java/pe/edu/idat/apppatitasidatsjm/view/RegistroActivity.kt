package pe.edu.idat.apppatitasidatsjm.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import pe.edu.idat.apppatitasidatsjm.R
import pe.edu.idat.apppatitasidatsjm.databinding.ActivityRegistroBinding
import pe.edu.idat.apppatitasidatsjm.retrofit.response.RegistroResponse
import pe.edu.idat.apppatitasidatsjm.util.AppMensaje
import pe.edu.idat.apppatitasidatsjm.util.TipoMensaje
import pe.edu.idat.apppatitasidatsjm.viewModel.AuthViewModel

class RegistroActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityRegistroBinding
    private lateinit var authViewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        authViewModel = ViewModelProvider(this)
            .get(AuthViewModel::class.java)
        authViewModel.registroResponse.observe(this) { response ->
            obtenerDatosRegistro(response)
        }

        binding.btnGuardarUsuario.setOnClickListener(this)
        binding.btnIrLogin.setOnClickListener(this)

    }

    private fun obtenerDatosRegistro(response: RegistroResponse) {
        if (response.rpta) {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        } else {
            AppMensaje.enviarMensaje(binding.root, response.mensaje, TipoMensaje.ERROR)
        }
        binding.btnGuardarUsuario.isEnabled = true
    }

    private fun registraUsuario() {
        binding.btnGuardarUsuario.isEnabled = false

        authViewModel.registrarUsuario(
            binding.etNombreUser.editText?.text.toString(),
            binding.etApellidoUser.editText?.text.toString(),
            binding.etEmailUser.editText?.text.toString(),
            binding.etCelular.editText?.text.toString(),
            binding.etUsuarioUser.editText?.text.toString(),
            binding.etPasswordUser.editText?.text.toString()
        )
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.btnGuardarUsuario -> registraUsuario()
            R.id.btnIrLogin -> startActivity(Intent(application, MainActivity::class.java))
        }
    }
}