package pe.edu.idat.apppatitasidatsjm.retrofit

import pe.edu.idat.apppatitasidatsjm.retrofit.request.LoginRequest
import pe.edu.idat.apppatitasidatsjm.retrofit.request.RegistroRequest
import pe.edu.idat.apppatitasidatsjm.retrofit.request.VoluntarioRequest
import pe.edu.idat.apppatitasidatsjm.retrofit.response.LoginResponse
import pe.edu.idat.apppatitasidatsjm.retrofit.response.MascotaResponse
import pe.edu.idat.apppatitasidatsjm.retrofit.response.RegistroResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface PatitasServicio {
    @POST("login.php")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
    @PUT("persona.php")
    fun registro(@Body registroRequest: RegistroRequest): Call<RegistroResponse>
    @POST("personavoluntaria.php")
    fun voluntario(@Body voluntarioRequest: VoluntarioRequest): Call<RegistroResponse>
    @GET("mascotaperdida.php")
    fun mascotas(): Call<List<MascotaResponse>>

}