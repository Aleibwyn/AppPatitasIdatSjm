package pe.edu.idat.apppatitasidatsjm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pe.edu.idat.apppatitasidatsjm.repository.VoluntarioRepository
import pe.edu.idat.apppatitasidatsjm.retrofit.request.VoluntarioRequest
import pe.edu.idat.apppatitasidatsjm.retrofit.response.RegistroResponse

class VoluntarioViewModel: ViewModel() {
    private var response: LiveData<RegistroResponse>

    private val repository = VoluntarioRepository()

    init {
        response = repository.registroResponse
    }

    fun registroVoluntario(idPersona: Int) {
        response = repository.registrarVoluntario(VoluntarioRequest(idPersona))
    }
}