package pe.edu.idat.apppatitasidatsjm.viewModel

import androidx.lifecycle.LiveData
import pe.edu.idat.apppatitasidatsjm.repository.MascotaRepository
import pe.edu.idat.apppatitasidatsjm.retrofit.response.MascotaResponse

class MascotaViewModel {
    var mascotaResponse: LiveData<List<MascotaResponse>>

    var repository = MascotaRepository()

    init {
        mascotaResponse = repository.mascotaResponse
    }

    fun listarMascotas() {
        mascotaResponse = repository.listarMascotas()
    }
}