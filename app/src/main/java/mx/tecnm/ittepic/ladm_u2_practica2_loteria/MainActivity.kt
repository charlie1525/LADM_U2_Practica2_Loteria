package mx.tecnm.ittepic.ladm_u2_practica2_loteria

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import mx.tecnm.ittepic.ladm_u2_practica2_loteria.databinding.ActivityMainBinding
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var barajas: Barajeo
    lateinit var media: MediaPlayer
    var vectorBarajeo = mutableListOf(1,2,3,4,5,6,7,8,9,10
        ,11,12,13,14,15,16,17,18,19,20
        ,21,22,23,24,25,26,27,28,29,30
        ,31,32,33,34,35,36,37,38,39,40
        ,41,42,43,44,45,46,47,48,49,50
        ,51,52,53,54)
    lateinit var recycler: RecyclerView
    lateinit var adapterMain:CustomAdapater
    lateinit var mediaCo: MediaPlayer

    private val scope = GlobalScope.launch(EmptyCoroutineContext,CoroutineStart.LAZY) {
       delay(1000)
        while(true) {
            mediaCo = MediaPlayer.create(baseContext, R.raw.ascensor3)
            mediaCo.start()
            delay(60000)
            mediaCo.stop()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var restantes = binding.btnCartasRstantes
        restantes.visibility = View.INVISIBLE

        var buenas = binding.btnBuenas
        buenas.visibility = View.INVISIBLE

        var inicio = binding.btnInicar
        inicio.visibility = View.INVISIBLE

        barajas = Barajeo(this,vectorBarajeo)
        binding.imgCartas.setImageResource(R.drawable.esperanding)

        scope.start()

        //----------------------------------------------------------------- Sección de los botones
        binding.btnBarajear.setOnClickListener {
            vectorBarajeo.shuffle()
            scope.cancel()
            Toast.makeText(this, "Mazo barajeado!!", Toast.LENGTH_SHORT).show()
            inicio.visibility = View.VISIBLE
            mediaCo.stop()
        }

        binding.btnInicar.setOnClickListener {
            if (barajas.estaaEjecutandose() || barajas.estaPausado()){
                Toast.makeText(this, "Veremos que hacemos", Toast.LENGTH_SHORT).show()
                barajas.indiceL=0
            }
            barajas.start()
            binding.imgCartas.setImageResource(R.drawable.esperanding)
            binding.txtVTituloCartas.text = "Esperanding....."
            buenas.visibility = View.VISIBLE
        } // fin del primer boton, el de inicio

        binding.btnBuenas.setOnClickListener {
            if (barajas.media.isPlaying){barajas.media.stop()}
            media = MediaPlayer.create(this, R.raw.victorysound)
            media.start()
            restantes.visibility = View.VISIBLE
            barajas.pausaDespausaBarajeo()
        }

        binding.btnCartasRstantes.setOnClickListener {
            recycler= findViewById<RecyclerView>(R.id.recyclerViewXML)
            adapterMain = CustomAdapater(barajas.vectorNombreCartas,barajas.vectorCartas)
            recycler.layoutManager = LinearLayoutManager(baseContext)
            recycler.adapter = adapterMain
            barajas.terminarBarajeo()
        }

    }// fin del OnCreate

}// fin de la clase Main