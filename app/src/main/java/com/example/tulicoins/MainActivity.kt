package com.example.tulicoins

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var esGasto = true
    private val TAG = "DEBUG_TULICOINS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: Iniciando actividad...")

        try {
            setContentView(R.layout.activity_main)
            Log.d(TAG, "onCreate: Layout cargado correctamente")
        } catch (e: Exception) {
            Log.e(TAG, "Error cargando layout", e)
            Toast.makeText(this, "${e.message}", Toast.LENGTH_LONG).show()
            return
        }

        val inputMonto = findViewById<TextInputEditText?>(R.id.inputMonto)
        val inputFecha = findViewById<TextInputEditText?>(R.id.inputFecha)
        val btnAccion = findViewById<Button?>(R.id.btnAccion)
        val btnIngreso = findViewById<LinearLayout?>(R.id.btnIngreso)
        val btnGasto = findViewById<LinearLayout?>(R.id.btnGasto)

        if (inputMonto == null || inputFecha == null || btnAccion == null || btnIngreso == null || btnGasto == null) {
            Toast.makeText(this, "Error: alguna vista es nula", Toast.LENGTH_LONG).show()
            Log.e(TAG, "Alguna vista es nula. inputMonto=$inputMonto, inputFecha=$inputFecha, btnAccion=$btnAccion, btnIngreso=$btnIngreso, btnGasto=$btnGasto")
            return
        }

        Log.d(TAG, "findViewById: Vistas encontradas correctamente ✅")

        val hoy = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        inputFecha.hint = "Fecha (por defecto: $hoy)"

        btnIngreso.setOnClickListener {
            esGasto = false
            btnAccion.text = "Agregar ingreso"
            Toast.makeText(this, "Modo ingreso", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Modo cambiado a Ingreso")
        }

        btnGasto.setOnClickListener {
            esGasto = true
            btnAccion.text = "✨ Agregar gasto"
            Toast.makeText(this, "Modo gasto", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Modo cambiado a Gasto")
        }

        btnAccion.setOnClickListener {
            Log.d(TAG, "Click en botón acción detectado ✅")
            try {
                val monto = inputMonto.text?.toString()?.trim() ?: ""
                val fecha = if (!inputFecha.text.isNullOrEmpty()) inputFecha.text.toString() else hoy

                if (monto.isEmpty()) {
                    Toast.makeText(this, "Ingrese un monto", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "Monto vacío")
                } else {
                    val tipo = if (esGasto) "Gasto" else "Ingreso"
                    Toast.makeText(this, "$tipo de $$monto agregado ($fecha)", Toast.LENGTH_LONG).show()
                    Log.d(TAG, "Registro agregado: $tipo de $monto en $fecha")

                    inputMonto.text?.clear()
                    inputFecha.text?.clear()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error en acción principal", e)
                Toast.makeText(this, "Error en acción: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }

        Log.d(TAG, "onCreate: Configuración completada sin errores")
    }
}
