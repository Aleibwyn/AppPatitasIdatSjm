package pe.edu.idat.apppatitasidatsjm.retrofit.request

data class RegistroRequest(
    var nombres: String,
    var apellidos: String,
    var email: String,
    var celular: String,
    var usuario: String,
    var password: String
)