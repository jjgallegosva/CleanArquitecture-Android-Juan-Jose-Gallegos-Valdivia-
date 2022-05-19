package com.example.moviesapp.login

import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    fun ingresar(loginAux: LoginDTO,loginComprobacion:LoginDTO) =loginAux.esIgual(loginComprobacion)

}