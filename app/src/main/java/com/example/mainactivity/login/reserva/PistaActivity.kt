package com.example.mainactivity.login.reserva

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.mainactivity.R
import com.example.mainactivity.login.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class PistaActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    lateinit var reserva: Reserva
    lateinit var  muestraDia: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pista)
        muestraDia = findViewById(R.id.muestraDatosDia)
        val bundle = intent.extras
        val dato = bundle?.getString("dia").toString()
        muestraDia.setText(dato).toString()


        //Boton volver a la clase elige Dia
        var volver = findViewById<ImageView>(R.id.btnVolver)
        volver.setOnClickListener(){
            val i = Intent(this, SeleccionDiaActivity::class.java)
            startActivity(i)
        }


        fun recogerDatos() {
            val bundle = intent.extras
            val dato = bundle?.getString("dia").toString()
            muestraDia.setText(dato).toString()
            val docRef = db.collection("reservas").document(muestraDia.text.toString())
            docRef.get().addOnSuccessListener { documentSnapshot ->
                reserva = documentSnapshot.toObject()!!

            }
        }

        recogerDatos()

        botonPista1()
        botonPista2()

    }



    //Boton eleccion pista 1
    fun botonPista1(){
        var pista1 = findViewById<ImageView>(R.id.pista2)
        pista1.setOnClickListener(){
            db.collection("reservas").document(muestraDia.text.toString()).update(
                (mapOf(
                    "pista1" to true
                ))
            )
                .addOnSuccessListener {

                    Toast.makeText(
                        this, "Pista 1 seleccionada correctamente",
                        Toast.LENGTH_LONG
                    ).show()
                }
            val e = Intent(this, ElegirHoraActivity::class.java)
            e.putExtra("dia", muestraDia.text.toString())
            startActivity(e)

        }
    }



    //Boton eleccion pista 2
    fun botonPista2(){
        var pista2 = findViewById<ImageView>(R.id.pista1)
        pista2.setOnClickListener(){
            db.collection("reservas").document(muestraDia.text.toString()).update(
                (mapOf(
                    "pista2" to true
                ))
            )
                .addOnSuccessListener {
                    Toast.makeText(
                        this, "Pista 2 seleccionada correctamente",
                        Toast.LENGTH_LONG
                    ).show()
                }
            val e = Intent(this, ElegirHoraActivity::class.java)
            e.putExtra("dia", muestraDia.text.toString())
            startActivity(e)

        }
    }
}