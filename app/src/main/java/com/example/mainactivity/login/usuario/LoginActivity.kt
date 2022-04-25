package com.example.mainactivity.login.usuario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mainactivity.R
import com.example.mainactivity.login.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var perderContrasenia: TextView
    lateinit var txtUsuario : TextView
    lateinit var txtPassword : TextView
    lateinit var btnEntrar : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        setTheme(R.style.Theme_MainActivity)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        txtUsuario = findViewById(R.id.txtuser)
        txtPassword = findViewById(R.id.txtPassword)
        btnEntrar = findViewById(R.id.btnLogin)
        onClick()
        entrarUsuario()
        olvidarContrasenia()

    }

    private fun olvidarContrasenia(){
        perderContrasenia = findViewById(R.id.txtLoser)
        perderContrasenia.setOnClickListener(){
            startActivity(Intent(this, PerderContraseniaActivity::class.java))

        }
    }

    private fun onClick(){
        val btnNuevo = findViewById<Button>(R.id.btnNuevoUsuario)
        btnNuevo.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun entrarUsuario() {

        btnEntrar.setOnClickListener {

            if (valicacionFormulario()) {
                if (txtUsuario.text.isNotEmpty() && txtPassword.text.isNotEmpty()) {

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(
                        txtUsuario.text.toString(),
                        txtPassword.text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val i = Intent(this, MainActivity::class.java)
                            i.putExtra("Email", txtUsuario.text.toString())
                            startActivity(i)
                        } else {
                            alertaError()
                        }
                    }
                }
            }
        }
    }


    private fun alertaError(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Email incorrecto")
        builder.setPositiveButton("Aceptar", null)
        var dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun valicacionFormulario(): Boolean {
        var isValid = true

        if (txtUsuario.text.toString().isEmpty()) {
            isValid = false
            txtUsuario.setError("Campo requerido")
        }

        if (txtPassword.text.toString().length < 6) {
            isValid = false
            txtPassword.setError("Minimo 6 caracteres")
        }
        return isValid
    }

}