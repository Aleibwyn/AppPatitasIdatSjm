package pe.edu.idat.apppatitasidatsjm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pe.edu.idat.apppatitasidatsjm.repository.AuthRepository
import pe.edu.idat.apppatitasidatsjm.retrofit.request.LoginRequest
import pe.edu.idat.apppatitasidatsjm.retrofit.request.RegistroRequest
import pe.edu.idat.apppatitasidatsjm.retrofit.response.LoginResponse
import pe.edu.idat.apppatitasidatsjm.retrofit.response.RegistroResponse

class AuthViewModel: ViewModel() {
    var loginResponse: LiveData<LoginResponse>
    var registroResponse: LiveData<RegistroResponse>

    private var repository = AuthRepository()

    init {
        loginResponse = repository.loginResponse
        registroResponse = repository.registroResponse
    }

    fun autenticarUsuario(usuario: String, password: String) {
        loginResponse = repository.autenticarUsuario(LoginRequest(usuario, password))
    }

    fun registrarUsuario(
        nombre: String,
        apellido: String,
        email: String,
        celular: String,
        usuario: String,
        password: String
    ) {
        registroResponse = repository.registrarUsuario(RegistroRequest( nombre, apellido, email, celular, usuario, password))
    }
}


