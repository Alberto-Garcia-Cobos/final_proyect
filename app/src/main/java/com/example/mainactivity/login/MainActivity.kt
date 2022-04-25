package com.example.mainactivity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.mainactivity.R
import com.example.mainactivity.login.gestion.GestionReservaActivity
import com.example.mainactivity.login.informacion.InformacionActivity
import com.example.mainactivity.login.reserva.SeleccionDiaActivity
import com.example.mainactivity.login.usuario.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val reserva = findViewById<ImageView>(R.id.imageViewReservas)
        val gestionDatos = findViewById<ImageView>(R.id.imageViewGestion)
        val link = findViewById<ImageView>(R.id.imageViewInformacion)
        val logout = findViewById<ImageView>(R.id.imageViewSalir)


        reserva.setOnClickListener(){
            val i = Intent(this, SeleccionDiaActivity::class.java)
            startActivity(i)
        }

        gestionDatos.setOnClickListener(){
            val i = Intent(this, GestionReservaActivity::class.java)
            startActivity(i)
        }

        link.setOnClickListener(){
            val i = Intent(this, InformacionActivity::class.java)
            startActivity(i)
        }

        logout.setOnClickListener(){
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            auth.signOut()
        }
    }
}