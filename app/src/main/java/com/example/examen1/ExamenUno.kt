package com.example.examen1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.examen1.databinding.ActivityExamenUnoBinding

class ExamenUno : AppCompatActivity() {

    private lateinit var binding: ActivityExamenUnoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamenUnoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var toal = intent.getStringExtra("Total")
        var cicloUno = intent.getStringExtra("CicloUno")
        var cicloDos = intent.getStringExtra("CicloDos")

        binding.btnBorrar.setOnClickListener {
            onBackPressed()
        }

        Total()
        CicloUno()
        CicloDos()
    }

    private fun Total(){

    }

    private fun CicloUno(){

    }

    private fun CicloDos(){

    }
}