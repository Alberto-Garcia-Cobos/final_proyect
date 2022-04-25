package com.example.mainactivity.login.reserva

data class Reserva(val horaA:Boolean?,val horaB:Boolean?,
                   val horaC:Boolean?,val horaD:Boolean?,
                   val refUserA:String?,val refUserB:String?,
                   val refUserC:String?,val refUserD:String?,
                   val date:String?, val pista1:Boolean, val pista2:Boolean
                   )  {
    constructor() : this(false, false, false, false,
        "", "", "", "",
        "", false,false)
}

