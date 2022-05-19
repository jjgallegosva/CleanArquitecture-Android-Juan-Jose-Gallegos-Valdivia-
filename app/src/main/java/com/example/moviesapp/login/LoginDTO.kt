package com.example.moviesapp.login

data class LoginDTO(
    val user : String,
    val password : String
){
    fun esIgual(loginAux:LoginDTO) = user==loginAux.user && password ==loginAux.password
}
