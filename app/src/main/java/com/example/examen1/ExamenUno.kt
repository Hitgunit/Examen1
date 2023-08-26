package com.example.examen1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.examen1.databinding.ActivityExamenUnoBinding
import kotlinx.coroutines.*

class ExamenUno : AppCompatActivity() {

    private lateinit var binding: ActivityExamenUnoBinding
    private var job1: Job? = null
    private var job2: Job? = null
    private var job3: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamenUnoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var total = intent.getStringExtra("Total")
        var cicloUno = intent.getStringExtra("CicloUno")
        var cicloDos = intent.getStringExtra("CicloDos")

        binding.btnBorrar.setOnClickListener {
            onBackPressed()
        }

        Iniciar(total, cicloUno, cicloDos)
    }

    private fun Iniciar(total: String?, cicloUno: String?, cicloDos: String?){
        job1?.cancel()
        job1 = GlobalScope.launch(Dispatchers.Main){
            Total(total)
        }

        job2?.cancel()
        job2 = GlobalScope.launch(Dispatchers.Main){
            CicloUno(cicloUno)
        }

        job3?.cancel()
        job3 = GlobalScope.launch(Dispatchers.Main){
            CicloDos(cicloDos)
        }
    }
    suspend private fun Total( total: String?){
        var totalInt = total!!.toInt()
        binding.ProgressBarTotal.max = totalInt
        while (totalInt >= 0){
            delay(1000)
            binding.etqTotal.text = totalInt.toString()
            binding.ProgressBarTotal.progress = totalInt
            totalInt--
        }
    }

    suspend private fun CicloUno(cicloUno: String?){
        var cicloUnoInt = cicloUno!!.toInt()
        binding.ProgresBarCicloUno.max = cicloUnoInt
        while (cicloUnoInt >= 0){
            delay(1000)
            binding.etqCicloUno.text = cicloUnoInt.toString()
            binding.ProgresBarCicloUno.progress = cicloUnoInt
            cicloUnoInt--
        }
    }

    suspend private fun CicloDos(cicloDos: String?){
        var cicloDosInt = cicloDos!!.toInt()
        binding.ProgresBarCicloDos.max = cicloDosInt
        while (cicloDosInt >= 0){
            delay(1000)
            binding.etqCicloDos.text = cicloDosInt.toString()
            binding.ProgresBarCicloDos.progress = cicloDosInt
            cicloDosInt--
        }
    }
}