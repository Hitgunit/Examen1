package com.example.examen1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.examen1.databinding.ActivityExamenUnoBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

class ExamenUno : AppCompatActivity() {

    //Se obtiene el binding
    private lateinit var binding: ActivityExamenUnoBinding
    //Se inicializan las variables job para poder utilizarlas
    private var job1: Job? = null
    private var job2: Job? = null
    private var job3: Job? = null
    //Se crean los channels para que s epuedan comunicar
    private var job2Channel = Channel<Unit>()
    private var job3Channel = Channel<Unit>()
    //Se crea un status paa informar que el job 1 termino
    private var job1Status = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamenUnoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Se pasan los datos de el layout anterior
        var total = intent.getStringExtra("Total")
        var cicloUno = intent.getStringExtra("CicloUno")
        var cicloDos = intent.getStringExtra("CicloDos")

        //Borra los datos
        binding.btnBorrar.setOnClickListener {
            onBackPressed()
        }
        //Manda llamar el inicio de las corrutinas con sus valores
        Iniciar(total, cicloUno, cicloDos)
    }

    //Se inician las corrutinas
    private fun Iniciar(total: String?, cicloUno: String?, cicloDos: String?){
        //Cnacela la corrutina si esque habia una
        job1?.cancel()
        //Se lanza la corrutina
        job1 = GlobalScope.launch(Dispatchers.Main){
            //S emand llma rle metodo d ela corrutina
            Total(total)
            //Cuando se complete se pone true el status para terminar el bucle
            job1Status = true
            //se cancelan las corrutinas ya que termino el tiempo
            job2?.cancel()
            job3?.cancel()
        }

        job2?.cancel()
        job2 = GlobalScope.launch(Dispatchers.Main){
            while (!job1Status){
                CicloUno(cicloUno)
                //Se manda una señal con el chanel para comentar que inicie el job 3
                job3Channel.send(Unit)
                //Se esper auna señal del job 3 para poder proseguir con al corutina
                job2Channel.receive()
            }

        }

        job3?.cancel()
        job3 = GlobalScope.launch(Dispatchers.Main){
            //Recive la señal paracomenzar
            job3Channel.receive()
            CicloDos(cicloDos)
            //Manda la señal de que termino
            job2Channel.send(Unit)

        }
    }
    suspend private fun Total( total: String?){
        var totalInt = total!!.toInt()
        //Se asigna el valor total de el progress bar
        binding.ProgressBarTotal.max = totalInt
        //Se ejecuta un bucle para determinar cuando se acabo el tiempo
        while (totalInt >= 0){
            delay(1000)
            //Se actualiza loo datos
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