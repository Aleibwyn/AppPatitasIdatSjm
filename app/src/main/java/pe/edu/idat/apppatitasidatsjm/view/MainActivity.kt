package pe.edu.idat.apppatitasidatsjm.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pe.edu.idat.apppatitasidatsjm.R
import pe.edu.idat.apppatitasidatsjm.databinding.ActivityMainBinding
import pe.edu.idat.apppatitasidatsjm.model.entity.PersonaEntity
import pe.edu.idat.apppatitasidatsjm.retrofit.response.LoginResponse
import pe.edu.idat.apppatitasidatsjm.util.AppMensaje
import pe.edu.idat.apppatitasidatsjm.util.SharedPreferencesManager
import pe.edu.idat.apppatitasidatsjm.util.TipoMensaje
import pe.edu.idat.apppatitasidatsjm.viewModel.AuthViewModel
import pe.edu.idat.apppatitasidatsjm.viewModel.PersonaViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var authViewModel: AuthViewModel
    private lateinit var personaViewModel: PersonaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        authViewModel = ViewModelProvider(this)
            .get(AuthViewModel::class.java)
        authViewModel.loginResponse.observe(this) { response ->
            obtenerDatosLogin(response)
        }
        if (recordarDatosLogin()) {
            SharedPreferencesManager().deletePreferences("PREF_RECORDAR")
            binding.cbRecordar.isChecked = true
            binding.etUsername.isEnabled = false
            binding.etPassword.isEnabled = false
            binding.cbRecordar.text = "Quitar check para ingresar con otro usuario."
            personaViewModel.obtener().observe(this, Observer {
                it?.let {
                    binding.etUsername.editText?.setText(it.usuario)
                    binding.etPassword.editText?.setText(it.password)

                }
            })
        } else {
            personaViewModel=  ViewModelProvider(this).get(PersonaViewModel::class.java)
        }

        binding.btnLogIn.setOnClickListener(this)
        binding.btnRegister.setOnClickListener(this)
        binding.cbRecordar.setOnClickListener(this)
    }

    private fun obtenerDatosLogin(response: LoginResponse) {
        if (response.rpta) {
            val personaEntity = PersonaEntity(response.idpersona.toInt(), response.nombres, response.apellidos, response.email, response.celular, response.usuario, response.password, response.esvoluntario.toString())
            if (recordarDatosLogin()) {
                personaViewModel.actualizar(personaEntity)
            } else {
                personaViewModel.insertar(personaEntity)
                if (binding.cbRecordar.isChecked) {
                    SharedPreferencesManager()
                        .setSomeBooleanValue("PREF_RECORDAR", true)
                }
            }
            startActivity(Intent(applicationContext, HomeActivity::class.java))
        } else {
            AppMensaje.enviarMensaje(binding.root, response.mensaje, TipoMensaje.ERROR)
        }
        binding.btnRegister.isEnabled = true
        binding.btnLogIn.isEnabled = true
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.btnLogIn -> autenticarUsuario()
            R.id.btnRegister -> startActivity(Intent(application, RegistroActivity::class.java))
            R.id.cbRecordar -> setearValoresRecordar(p0)
        }
    }

    private fun setearValoresRecordar(vista: View) {
        if (vista is CheckBox) {
            val checkeo = vista.isChecked
            if (!checkeo) {
                if (recordarDatosLogin()) {
                    personaViewModel.eliminar()
                    binding.etUsername.isEnabled = true
                    binding.etPassword.isEnabled = true
                    binding.cbRecordar.text = getString(R.string.valcbaceptarterminos)
                }
            }
        }
    }

    private fun autenticarUsuario() {
        binding.btnLogIn.isEnabled = false
        binding.btnRegister.isEnabled = false
        authViewModel.autenticarUsuario(
            binding.etUsername.editText?.text.toString(),
            binding.etPassword.editText?.text.toString()
        )

    }

    private fun recordarDatosLogin(): Boolean {
        return SharedPreferencesManager()
            .getSomeBooleanValue("PREF_RECORDAR")
    }

}