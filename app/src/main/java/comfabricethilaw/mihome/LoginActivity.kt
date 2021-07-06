package comfabricethilaw.mihome

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import comfabricethilaw.mihome.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignIn.setOnClickListener {
            handleSignInClick()
        }
        binding.btnNewResident.setOnClickListener {
            handleSignUpClick()
        }
        binding.btnHelpPasswordForgot.setOnClickListener {
            handleForgotPasswordClick()
        }

    }

    private fun handleSignInClick() {
        val username = binding.inputUsername.text.toString()
        val password = binding.inputPassword.text.toString()

        validateInput(username, password) { validUsername, validPassword ->
            submit(username = validUsername, password = validPassword)
        }
    }

    private fun handleSignUpClick() {
        // add your logic
        Toast.makeText(this, "Todo : add the logic for it", Toast.LENGTH_SHORT).show()
    }

    private fun handleForgotPasswordClick() {
        // add your logic
        Toast.makeText(this, "Todo : add the logic for it", Toast.LENGTH_SHORT).show()
    }


    private fun validateInput(
        username: String,
        password: String,
        onValidationPassed: (validUsername :String, validPassword : String) -> Unit
    ) {
        // add your validation logic
        onValidationPassed.invoke(username, password)
    }

    private fun submit(username: String, password: String) {
        // add your logic
        startActivity(Intent(this, MainActivity::class.java))
    }

}