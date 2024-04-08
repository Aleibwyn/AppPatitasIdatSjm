package pe.edu.idat.apppatitasidatsjm.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import pe.edu.idat.apppatitasidatsjm.retrofit.PatitasCliente
import pe.edu.idat.apppatitasidatsjm.retrofit.response.MascotaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MascotaRepository {
    var mascotaResponse = MutableLiveData<List<MascotaResponse>>()
    fun listarMascotas(): MutableLiveData<List<MascotaResponse>> {
        val call: Call<List<MascotaResponse>> = PatitasCliente.retrofitService.mascotas()
        call.enqueue(object : Callback<List<MascotaResponse>> {
            override fun onResponse(
                call: Call<List<MascotaResponse>>,
                response: Response<List<MascotaResponse>>
            ) {
                mascotaResponse.value = response.body()
            }

            override fun onFailure(call: Call<List<MascotaResponse>>, t: Throwable) {
                Log.e("ErrorMascotas", t.message.toString())
            }
        })
        return mascotaResponse
    }

}
