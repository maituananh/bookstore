package com.example.bookstore

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.bookstore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.text = "Login Book App"
        binding.btnLogin.setOnClickListener(handleLogin())
    }

    private fun handleLogin(): View.OnClickListener {
        return View.OnClickListener {
            if (binding.txtUsername.text.toString() == "admin") {
                val intentActivity = Intent(this, HomeActivity::class.java)
                intentActivity.putExtra("username", binding.txtUsername.text.toString())
                startActivity(intentActivity)
            }
        }
    }
}