package com.example.mainactivity.login.gestion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainactivity.R
import com.example.mainactivity.login.reserva.Reserva
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class GestionReservaActivity : AppCompatActivity() {


    private val db = FirebaseFirestore.getInstance()
    lateinit var mRecyclerView :RecyclerView
    private val mAdapter : RecyclerAdapter = RecyclerAdapter()
    private var auth = FirebaseAuth.getInstance().currentUser?.email
    var reservas: MutableList<Reserva> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_reserva)

        getReservas()

    }


    fun setUpRecyclerView(reservas:MutableList<Reserva>){

        mRecyclerView = findViewById(R.id.recyclerView2)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter.RecyclerAdapter(reservas,this)
        mRecyclerView.adapter = mAdapter

       val item = object : SwipeToDelete(this, 0, ItemTouchHelper.LEFT){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                mAdapter.deleteItem(viewHolder.adapterPosition)
                alertaError()
            }
        }

        val itemTouchHelper = ItemTouchHelper(item)

        itemTouchHelper.attachToRecyclerView(mRecyclerView)


    }

    private fun alertaError(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar")
        builder.setMessage("Vas a eliminar esta reserva")
        builder.setPositiveButton("Aceptar", null)
        var dialog: AlertDialog = builder.create()
        dialog.show()
        Toast.makeText(this, "Reserva eliminada", Toast.LENGTH_SHORT)
            .show()
    }

    fun getReservas(){

        db.collection("reservas").whereEqualTo("refUserA" ,auth).get()
            .addOnSuccessListener {
                for(doc in it){
                    reservas.add(doc.toObject())
                }
                db.collection("reservas").whereEqualTo("refUserB" ,auth).get()
                    .addOnSuccessListener {
                        for (doc in it) {
                            reservas.add(doc.toObject())
                        }
                        db.collection("reservas").whereEqualTo("refUserC", auth).get()
                            .addOnSuccessListener {
                                for (doc in it) {
                                    reservas.add(doc.toObject())
                                }
                                db.collection("reservas").whereEqualTo("refUserD", auth).get()
                                    .addOnSuccessListener {
                                        for (doc in it) {
                                            reservas.add(doc.toObject())
                                        }
                                        setUpRecyclerView(reservas)
                                    }
                            }

                    }

            }

    }

}







