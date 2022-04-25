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
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {


    private val db = FirebaseFirestore.getInstance()
    lateinit var txtSurName: TextView
    lateinit var txtName: TextView
    lateinit var txtEmail: TextView
    lateinit var txtPassword: TextView
    lateinit var btnRegistrar: Button
    lateinit var usuario: Usuarios


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        txtPassword = findViewById(R.id.txtPassword)
        txtEmail = findViewById(R.id.txtEmail)
        txtName = findViewById(R.id.txtName)
        txtSurName = findViewById(R.id.txtLastName)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        txtEmail.text.toString()
        txtPassword.text.toString()
        txtName.text.toString()
        txtEmail.text.toString()
        usuario = Usuarios(txtEmail,txtPassword,txtName,txtSurName)

        registroUsuario()
        btnVolver()

    }

    private fun registroUsuario() {
            btnRegistrar.setOnClickListener {
                if (valicacionFormulario() == true) {
                if (usuario.email.text.isNotEmpty() && usuario.contrasenya.text.isNotEmpty()) {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        usuario.email.text.toString(),
                        usuario.contrasenya.text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            guardarUsuario()
                            Toast.makeText(this, "Usuario creado correctamente", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            alertaError()
                        }
                    }
                }
            }
        }
    }


    private fun guardarUsuario() {

            db.collection("users").document(txtEmail.text.toString()).set(
                hashMapOf(
                    "name" to usuario.nombre.text.toString(),
                    "surname" to usuario.apellido.text.toString(),
                    "email" to usuario.email.text.toString()
                )
            )
            val i = Intent(this, MainActivity::class.java)
            i.putExtra("Email", txtEmail.text.toString())
            startActivity(i)
        }



    private fun alertaError(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Email incorrecto")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    private fun btnVolver(){
        val btnNuevo = findViewById<Button>(R.id.btnVolver)
        btnNuevo.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun valicacionFormulario(): Boolean{
        var isValid = true
        if(txtName.text.toString().isBlank()){
            isValid = false
            txtName.setError("Campo requerido")
        }
        if(txtSurName.text.toString().isBlank()){
            isValid = false
            txtSurName.setError("Campo requerido")
        }
        if(txtEmail.text.toString().isBlank()){
            isValid = false
            txtEmail.setError("Campo requerido")
        }


        if(txtPassword.text.toString().length <6){
            isValid = false
            txtPassword.setError("Minimo 6 caracteres")
        }
        return isValid
    }
}


