package com.example.examen1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.examen1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnComenzar.setOnClickListener {
            Accion()
        }

    }

    private fun Accion(){
        if (binding.txtTotal.text.toString().isBlank() || binding.txtCicloUno.text.toString().isBlank() || binding.txtCicloDos.text.toString().isBlank()){
            Toast.makeText(this, "No pueden estar vacios los campos", Toast.LENGTH_LONG).show()
        } else{
            val intent = Intent(this, ExamenUno::class.java)
            intent.putExtra("Total", binding.txtTotal.text.toString())
            intent.putExtra("CicloUno", binding.txtCicloUno.text.toString())
            intent.putExtra("CicloDos", binding.txtCicloDos.text.toString())
            startActivity(intent)
        }
    }


}