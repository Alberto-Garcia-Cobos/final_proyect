package com.example.mainactivity.login.usuario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.mainactivity.R
import com.example.mainactivity.login.MainActivity
import com.example.mainactivity.login.reserva.SeleccionDiaActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.regex.Pattern

class PerderContraseniaActivity : AppCompatActivity() {

    lateinit var recuperar: Button
    lateinit var textoEmail: TextView
    lateinit var btnVolver: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perder_contrasenia)

        recuperar = findViewById(R.id.btnRecuperar)
        textoEmail = findViewById(R.id.txtuser)
        btnVolver = findViewById(R.id.btnVolver)

        recuperar.setOnClickListener(){
            validate()
        }

        btnVolver.setOnClickListener{
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
    }

    fun validate(){
        val email = textoEmail.text.toString().trim()


        if (email.isEmpty() ){
            textoEmail.setError("Correo invalido")
            return
        }

        sendEmail(email)
    }

    //Gestionamos el boton de volver del movil en caso de ser pulsado por error
    override fun onBackPressed() {
        super.onBackPressed()

        val i = Intent(this, LoginActivity::class.java)
        startActivity(i)
        finish()
    }

    fun sendEmail(email:String){
         var auth = FirebaseAuth.getInstance()
        auth.sendPasswordResetEmail(email).addOnCompleteListener(){
            if (it.isSuccessful){
                Toast.makeText(
                    this, "Se ha enviado un email a su correo electronico",
                    Toast.LENGTH_LONG
                ).show()
                val i = Intent(this, LoginActivity::class.java)
                startActivity(i)
                finish()
            }else{
                Toast.makeText(
                    this, "Email erroneo",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}