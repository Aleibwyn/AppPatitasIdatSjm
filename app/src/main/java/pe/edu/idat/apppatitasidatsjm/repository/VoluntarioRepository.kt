package pe.edu.idat.apppatitasidatsjm.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import pe.edu.idat.apppatitasidatsjm.retrofit.PatitasCliente
import pe.edu.idat.apppatitasidatsjm.retrofit.request.VoluntarioRequest
import pe.edu.idat.apppatitasidatsjm.retrofit.response.MascotaResponse
import pe.edu.idat.apppatitasidatsjm.retrofit.response.RegistroResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VoluntarioRepository {
    var registroResponse = MutableLiveData<RegistroResponse>()
    fun registrarVoluntario(idPersona: Int): MutableLiveData<RegistroResponse> {
        val call: Call<RegistroResponse> = PatitasCliente.retrofitService.voluntario(VoluntarioRequest(idPersona))
        call.enqueue(object : Callback<RegistroResponse> {
            override fun onResponse(call: Call<RegistroResponse>, response: Response<RegistroResponse>) {
                registroResponse.value = response.body()
            }

            override fun onFailure(call: Call<RegistroResponse>, t: Throwable) {
                Log.e("ErrorMascotas", t.message.toString())
            }

        })
        return registroResponse
    }
}