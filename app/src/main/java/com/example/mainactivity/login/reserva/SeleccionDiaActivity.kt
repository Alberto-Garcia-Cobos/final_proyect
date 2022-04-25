package com.example.mainactivity.login.reserva

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.mainactivity.R
import com.example.mainactivity.login.MainActivity
import com.google.firebase.firestore.FirebaseFirestore

class SeleccionDiaActivity : AppCompatActivity() {

    lateinit var  botonSeleccionDia: ImageView
    lateinit var  botonCambioDePagina: Button
    private var fecha = ""
    lateinit var muestraDia: TextView
    private val db = FirebaseFirestore.getInstance()
    private lateinit var volver:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccion_dia)

        muestraDia = findViewById(R.id.muestraDia)
        botonSeleccionDia = findViewById(R.id.botonSeleccionDia)
        botonCambioDePagina = findViewById(R.id.botonCambioDePagina)
        volver = findViewById(R.id.btnVolver)
        elegirDia()
        cambioDeActivity()
        botonCambioDePagina.isClickable = false


        //Boton volver a la activity principal
        volver.setOnClickListener(){
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }

    //Funcion para la seleccion del dia de la reserva
    fun elegirDia(){
        botonSeleccionDia.setOnClickListener(){
            showDatePickerDialog()

        }
    }

    //Función donde muestra el dia de la reserva
    fun seleccionDia(day: Int, month: Int, year: Int) {
        fecha = "$day-$month-$year"
        muestraDia.setText(fecha)

        //Guardamos el dia en la base de datos
        val docRef = db.collection("reservas").document(fecha)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document ["horaA"]== true ||
                    document ["horaB"] == true ||
                    document ["horaC"]== true ||
                    document["horaD"] == true ||
                    document["pista1"] == true ||
                    document["pista2"] == true) {
                    document.exists()
                } else
                {
                    val reservaUnDia = Reserva(false, false, false, false,
                        "", "", "", "",
                        fecha, false,false)
                    db.collection("reservas").document(fecha).set(
                        reservaUnDia
                    ).addOnSuccessListener {
                        Toast.makeText(
                            this, "Día seleccionado $fecha",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
        botonCambioDePagina.isClickable = true
    }

    //Funcion DatePecker que muestra el calendario
    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> seleccionDia(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    //Funcion donde acepta el dia y pasa a la eleccion de la pista
    private fun cambioDeActivity(){
        botonCambioDePagina.setOnClickListener(){
            val i = Intent(this,PistaActivity::class.java)
            i.putExtra("dia", fecha)
            startActivity(i)


        }
    }





}