package com.example.mainactivity.login.reserva

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mainactivity.R
import com.example.mainactivity.login.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class ElegirHoraActivity : AppCompatActivity() {

    lateinit var  muestraDia: TextView
    lateinit var muestraHora : TextView
    lateinit var  botonElegirHoraA: Button
    lateinit var  botonElegirHoraB: Button
    lateinit var  botonElegirHoraC: Button
    lateinit var  botonElegirHoraD: Button
    lateinit var reserva: Reserva


    private var auth = FirebaseAuth.getInstance().currentUser?.email
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_elegir_hora)


        muestraDia = findViewById(R.id.textViewDia)
        muestraHora = findViewById(R.id.textViewHora)
        botonElegirHoraA = findViewById(R.id.confirmaHora)
        botonElegirHoraB = findViewById(R.id.confirmaHoraB)
        botonElegirHoraC = findViewById(R.id.confirmaHoraC)
        botonElegirHoraD = findViewById(R.id.confirmaHoraD)


        /*
        Función donde recojo el documento para gestionar la BBDD
         */
        fun recogerDatos() {
            val bundle = intent.extras
            val dato = bundle?.getString("dia").toString()
            muestraDia.setText(dato).toString()
            val docRef = db.collection("reservas").document(muestraDia.text.toString())
            docRef.get().addOnSuccessListener { documentSnapshot ->
                reserva = documentSnapshot.toObject()!!
                bloqueoBoton()
            }
        }
        recogerDatos()

        eligeHoraA()
        eligeHoraB()
        eligeHoraC()
        eligeHoraD()
    }

    /*
    Función a los botones para guardar la hora
     */

    fun eligeHoraA(){
        botonElegirHoraA.setOnClickListener(){
                muestraHora.setText("10:00-11:30")

                val builder = AlertDialog.Builder(this)
                builder.setTitle("RESERVA")
                builder.setMessage("Vas a reservar para el dia:" + muestraDia.text.toString() + "\n Hora: " + muestraHora.text.toString())
                builder.setPositiveButton("Aceptar") { _: DialogInterface, _: Int ->
                    guardarDatosHoraA()
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                }
                builder.setNegativeButton("Cancelar", null)
                var dialog: AlertDialog = builder.create()
                dialog.show()
            }

    }

    fun eligeHoraB(){
        botonElegirHoraB.setOnClickListener(){
            muestraHora.setText("11:30-13:00")
            val builder = AlertDialog.Builder(this)
            builder.setTitle("RESERVA")
            builder.setMessage("Vas a reservar para el dia:" + muestraDia.text.toString() + "\n Hora: " + muestraHora.text.toString())
            builder.setPositiveButton("Aceptar") { _: DialogInterface, _: Int ->
                guardarDatosHoraB()
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            }
            builder.setNegativeButton("Cancelar", null)
            var dialog: AlertDialog = builder.create()
            dialog.show()

        }
    }

    fun eligeHoraC(){
        botonElegirHoraC.setOnClickListener(){
            muestraHora.setText("13:00-14:30")
            val builder = AlertDialog.Builder(this)
            builder.setTitle("RESERVA")
            builder.setMessage("Vas a reservar para el dia:" + muestraDia.text.toString() + "\n Hora: " + muestraHora.text.toString())
            builder.setPositiveButton("Aceptar") { _: DialogInterface, _: Int ->
                guardarDatosHoraC()
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            }
            builder.setNegativeButton("Cancelar", null)
            var dialog: AlertDialog = builder.create()
            dialog.show()

        }
    }

    fun eligeHoraD(){
        botonElegirHoraD.setOnClickListener(){
            muestraHora.setText("16:00-17:30")
            val builder = AlertDialog.Builder(this)
            builder.setTitle("RESERVA")
            builder.setMessage("Vas a reservar para el dia:" + muestraDia.text.toString() + "\n Hora: " + muestraHora.text.toString())
            builder.setPositiveButton("Aceptar") { _: DialogInterface, _: Int ->
                guardarDatosHoraD()
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            }
            builder.setNegativeButton("Cancelar", null)
            var dialog: AlertDialog = builder.create()
            dialog.show()

        }
    }

    /*
    Funciones para guardar los datos en la BBDD con cada hora seleccionada
     */
    fun guardarDatosHoraA() {
        db.collection("reservas").document(muestraDia.text.toString()).update(
            (mapOf(
                "horaA" to true,
                "refUserA" to auth
            ))
        )
            .addOnSuccessListener {

                Toast.makeText(
                    this, "Reserva realizada correctamente",
                    Toast.LENGTH_LONG
                ).show()
            }
    }

    fun guardarDatosHoraB() {
        db.collection("reservas").document(muestraDia.text.toString()).update(
            (mapOf(
                "horaB" to true,
                "refUserB" to auth
            ))
        )
            .addOnSuccessListener {

                Toast.makeText(
                    this, "Reserva realizada correctamente",
                    Toast.LENGTH_LONG
                ).show()
            }
    }

    fun guardarDatosHoraC() {
        db.collection("reservas").document(muestraDia.text.toString()).update(
            (mapOf(
                "horaC" to true,
                "refUserC" to auth
            ))
        )
            .addOnSuccessListener {

                Toast.makeText(
                    this, "Reserva realizada correctamente",
                    Toast.LENGTH_LONG
                ).show()
            }
    }

    fun guardarDatosHoraD() {
        db.collection("reservas").document(muestraDia.text.toString()).update(
            (mapOf(
                "horaD" to true,
                "refUserD" to auth
            ))
        )
            .addOnSuccessListener {

                Toast.makeText(
                    this, "Reserva realizada correctamente",
                    Toast.LENGTH_LONG
                ).show()
            }
    }

    /*
    Bloqueo del boton en el caso de estar la hora seleccionada
     */

    fun bloqueoBoton(){
        if (reserva.horaA == true && (reserva.pista1 && reserva.pista2)){
            botonElegirHoraA.visibility = View.GONE
        }
        if (reserva.horaB == true && (reserva.pista1 && reserva.pista2)){
            botonElegirHoraB.visibility = View.GONE
        }
        if (reserva.horaC == true && (reserva.pista1 && reserva.pista2)){
            botonElegirHoraC.visibility = View.GONE
        }
        if (reserva.horaD == true && (reserva.pista1 && reserva.pista2)){
            botonElegirHoraD.visibility = View.GONE
        }
    }

}