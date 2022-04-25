package com.example.mainactivity.login.gestion

import android.content.ContentValues
import android.content.Context
import android.icu.text.Transliterator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mainactivity.R

import com.example.mainactivity.login.reserva.Reserva
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class RecyclerAdapter(): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    var reservas:MutableList<Reserva> = ArrayList()
    lateinit var context:Context
    private var auth = FirebaseAuth.getInstance().currentUser?.email



    fun RecyclerAdapter(reservas: MutableList<Reserva>,context: Context){
        this.context = context
        this.reservas = reservas
    }

    fun deleteItem(i: Int){
        reservas.removeAt(i)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        return ViewHolder(layoutInflater.inflate(R.layout.card_layout, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {

        val item = reservas.get(i)
        viewHolder.bind(item)


    }

    override fun getItemCount(): Int {
        return reservas.size
    }



   class ViewHolder(view: View):RecyclerView.ViewHolder(view){

       private val db = FirebaseFirestore.getInstance()
        var itemHoraReserva = view.findViewById(R.id.txtMuestraHoraReserva) as TextView
        var itemDiaReserva =  view.findViewById(R.id.txtMuestrDiaReserva) as TextView




       fun bind(reserva: Reserva){
           if (reserva.horaA == true){
               itemHoraReserva.text = "10:00 - 11:30"
           }
           if (reserva.horaB == true){
               itemHoraReserva.text = "11:30 - 13:00"
           }
           if (reserva.horaC == true){
               itemHoraReserva.text = "13:00 - 14:30"
           }
           if (reserva.horaD == true){
               itemHoraReserva.text = "16:00 - 17:30"
           }

           itemDiaReserva.text = reserva.date.toString()

           }

       }


   }





