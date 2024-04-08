package pe.edu.idat.apppatitasidatsjm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pe.edu.idat.apppatitasidatsjm.repository.MascotaRepository
import pe.edu.idat.apppatitasidatsjm.retrofit.response.MascotaResponse

class MascotaViewModel : ViewModel() {
    var mascotaResponse: LiveData<List<MascotaResponse>>

    var repository = MascotaRepository()

    init {
        mascotaResponse = repository.mascotaResponse
    }

    fun listarMascotas() {
        mascotaResponse = repository.listarMascotas()
    }
}