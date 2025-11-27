package com.example.naresbob.ui.register

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.naresbob.R
import com.example.naresbob.databinding.ActivityRegisterBinding
import com.example.naresbob.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        window.statusBarColor = getColor(R.color.coklat_utama)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                0, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        }

        else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility =
                window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.tvSignin.setOnClickListener {
            finish()
        }

        binding.btnSignup.setOnClickListener {
            val username = binding.textInputUsername.editText?.text.toString().trim()
            val fullname = binding.textInputFullname.editText?.text.toString().trim()
            val dateOfBirth = binding.textInputDateOfBirth.editText?.text.toString().trim()
            val password = binding.textInputPassword.editText?.text.toString().trim()
            val confirmPassword = binding.textInputConfirmPassword.editText?.text.toString().trim()


            if (username.isEmpty() || password.isEmpty() || fullname.isEmpty() || dateOfBirth.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Field tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.register(username, fullname, dateOfBirth, password)
            }
        }

        lifecycleScope.launch {
            viewModel.state.collect { ui ->
                if (ui.isLoading) {
                    Toast.makeText(this@RegisterActivity, "Loading...", Toast.LENGTH_SHORT).show()
                }

                ui.message?.let {
                    Toast.makeText(this@RegisterActivity, it, Toast.LENGTH_SHORT).show()
                }

                if (ui.success) {
                    Toast.makeText(this@RegisterActivity, "Login berhasil", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}