package com.example.mainactivity.login.informacion

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.mainactivity.R
import com.example.mainactivity.login.MainActivity
import com.example.mainactivity.login.reserva.SeleccionDiaActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.net.URI
import java.net.URL

class InformacionActivity : AppCompatActivity() {

    private var auth = FirebaseAuth.getInstance().currentUser?.email
    private val db = FirebaseFirestore.getInstance()
    private val URL_NOTICAS = "https://www.analistaspadel.com"
    private val URL_COMPETICIONES = "https://www.worldpadeltour.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion)

        val noticias = findViewById<ImageView>(R.id.imageViewNoticias)
        val competiciones = findViewById<ImageView>(R.id.imageViewCompeticiones)
        val volver = findViewById<ImageView>(R.id.btnVolver)

        //Boton volver al menu main
        volver.setOnClickListener() {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        //Boton ir a la seccion noticias
        noticias.setOnClickListener() {
            var intent = Intent(Intent.ACTION_VIEW)
            var noticias_URL = db.collection("links").document("1").get().addOnCompleteListener() {
                intent.setData(Uri.parse(URL_NOTICAS))
                startActivity(intent)
            }
        }

        //Boton ir a la seccion competiciones
        competiciones.setOnClickListener() {
            var intent = Intent(Intent.ACTION_VIEW)
            var noticias_URL = db.collection("links").document("1").get().addOnCompleteListener() {
                intent.setData(Uri.parse(URL_COMPETICIONES))
                startActivity(intent)
            }
        }
    }
}