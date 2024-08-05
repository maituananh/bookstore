package com.example.bookstore

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.bookstore.databinding.ActivityMainBinding
import com.example.bookstore.ui.login.view_model.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val loginViewModel: LoginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.text = "Login Book App"
        binding.btnLogin.setOnClickListener(handleLogin())

        loginViewModel.loginResult.observe(this) {
            run {
                val intentActivity = Intent(this, HomeActivity::class.java)
                intentActivity.putExtra("username", binding.txtUsername.text.toString())
                startActivity(intentActivity)
            }
        }
    }

    private fun handleLogin(): View.OnClickListener {
        return View.OnClickListener {
            loginViewModel.login(
                binding.txtUsername.text.toString(),
                binding.txtPassword.text.toString()
            )
        }
    }
}