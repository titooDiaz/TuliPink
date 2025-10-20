package com.example.tulicoins

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var esGasto = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputMonto = findViewById<EditText>(R.id.inputMonto)
        val inputFecha = findViewById<EditText>(R.id.inputFecha)
        val btnAccion = findViewById<Button>(R.id.btnAccion)
        val btnIngreso = findViewById<LinearLayout>(R.id.btnIngreso)
        val btnGasto = findViewById<LinearLayout>(R.id.btnGasto)

        // Fecha automática
        val hoy = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        inputFecha.hint = "Fecha (por defecto: $hoy)"

        // Botones para cambiar modo
        btnIngreso.setOnClickListener {
            esGasto = false
            btnAccion.text = "Agregar ingreso"
            Toast.makeText(this, "Modo ingreso", Toast.LENGTH_SHORT).show()
        }

        btnGasto.setOnClickListener {
            esGasto = true
            btnAccion.text = "Agregar gasto"
            Toast.makeText(this, "Modo gasto", Toast.LENGTH_SHORT).show()
        }

        // Acción principal
        btnAccion.setOnClickListener {
            val monto = inputMonto.text.toString()
            val fecha = if (inputFecha.text.isNotEmpty()) inputFecha.text.toString() else hoy

            if (monto.isEmpty()) {
                Toast.makeText(this, "Ingrese un monto", Toast.LENGTH_SHORT).show()
            } else {
                val tipo = if (esGasto) "Gasto" else "Ingreso"
                Toast.makeText(this, "$tipo de $$monto agregado ($fecha)", Toast.LENGTH_LONG).show()
                inputMonto.text.clear()
                inputFecha.text.clear()
            }
        }
    }
}
