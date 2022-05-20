package com.example.moviesapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.moviesapp.MainActivity
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    val loginViewModel:LoginViewModel by viewModel()
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.lifecycleOwner = this
        binding.viewmodel = loginViewModel
        

        binding.btnIngresar.setOnClickListener {
            val user = binding.usuario.text.toString()
            val password = binding.usuContra.text.toString()
            val loginPermiso = LoginDTO(getString(R.string.user),getString(R.string.password))
            if(loginViewModel.ingresar(LoginDTO(user,password),loginPermiso)){
                startActivity(Intent(this, MainActivity::class.java))
            }
            else{
                Toast.makeText(this,getString(R.string.error_login), Toast.LENGTH_SHORT).show()
            }
        }
    }
}