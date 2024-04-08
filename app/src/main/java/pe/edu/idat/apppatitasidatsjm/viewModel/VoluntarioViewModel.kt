package pe.edu.idat.apppatitasidatsjm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pe.edu.idat.apppatitasidatsjm.repository.VoluntarioRepository
import pe.edu.idat.apppatitasidatsjm.retrofit.request.VoluntarioRequest
import pe.edu.idat.apppatitasidatsjm.retrofit.response.RegistroResponse

class VoluntarioViewModel: ViewModel() {
    private val repository = VoluntarioRepository()
    var response: LiveData<RegistroResponse> = repository.registroResponse

    fun registroVoluntario(idPersona: Int) {
        response = repository.registrarVoluntario(idPersona)
    }
}