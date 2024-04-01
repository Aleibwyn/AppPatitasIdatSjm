package pe.edu.idat.apppatitasidatsjm

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import pe.edu.idat.apppatitasidatsjm.databinding.ActivityMainBinding
import pe.edu.idat.apppatitasidatsjm.retrofit.response.LoginResponse
import pe.edu.idat.apppatitasidatsjm.util.AppMensaje
import pe.edu.idat.apppatitasidatsjm.util.TipoMensaje
import pe.edu.idat.apppatitasidatsjm.viewModel.AuthViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var authViewModel: AuthViewModel

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



        binding.btnLogIn.setOnClickListener(this)
        binding.btnRegister.setOnClickListener(this)

    }

    private fun obtenerDatosLogin(response: LoginResponse) {
        if (response.rpta) {
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

}