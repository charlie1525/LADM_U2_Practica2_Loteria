package mx.tecnm.ittepic.ladm_u2_practica2_loteria

import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.tecnm.ittepic.ladm_u2_practica2_loteria.databinding.ActivityMainBinding
import kotlin.coroutines.EmptyCoroutineContext


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var barajas: Barajeo
    lateinit var mediaVic: MediaPlayer
    var vectorBarajeo = arrayListOf(1,2,3,4,5,6,7,8,9,10
        ,11,12,13,14,15,16,17,18,19,20
        ,21,22,23,24,25,26,27,28,29,30
        ,31,32,33,34,35,36,37,38,39,40
        ,41,42,43,44,45,46,47,48,49,50
        ,51,52,53,54)
    lateinit var recycler: RecyclerView
    lateinit var adapterMain:CustomAdapater
    lateinit var mediaCo: MediaPlayer
    var mediaBack: MediaPlayer ?= MediaPlayer()

    // ------------------------------------------------------- fin de la declaracion de las variables globales
    // ------------------------------------------------------- Inicio de la decalraacin de las corrutinas

    private val initBackmedia = GlobalScope.launch(EmptyCoroutineContext,CoroutineStart.LAZY) {
       delay(500)
        while(true) {
            mediaCo = MediaPlayer.create(baseContext, R.raw.ascensor3)
            mediaCo.start()
            delay(120000)
            mediaCo.stop()
        }
    }
    private val whileMusicMedia = GlobalScope.launch(EmptyCoroutineContext,CoroutineStart.LAZY) {
        while(true){
            mediaBack = MediaPlayer.create(baseContext,R.raw.fondopiano)
            mediaBack!!.start()
            delay(175000)
            mediaBack!!.stop()
        }
    }

    private val victorySound = GlobalScope.launch(EmptyCoroutineContext,CoroutineStart.LAZY) {
        mediaVic = MediaPlayer.create(baseContext, R.raw.victorysound)
        mediaVic.start()
        delay(7000)
        mediaVic.release()
    }

    private val cardView = GlobalScope.launch(EmptyCoroutineContext,CoroutineStart.LAZY){
        runOnUiThread{
            recycler = findViewById(R.id.recyclerViewXML)
            adapterMain = CustomAdapater(barajas.vectorNombreCartas, barajas.vectorCartas)
            recycler.layoutManager = LinearLayoutManager(baseContext)
            recycler.adapter = adapterMain
        }
    }
// ----------------------------------------------------------------- fin de la declaracion de las corrtinas
// ----------------------------------------------------------------- inicio de los metodos de manipulacion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AlertDialog.Builder(this)
            .setTitle("Aviso!")
            .setMessage("Tiene musica de fondo, tarda un poco en iniciar." +
                "\nAdemás hay un intervalo de 9 segundos en las cartas, esto ya que algunas frases estan muy largas")
            .setNeutralButton("nice",{d,i ->{}})
            .show()

        val restantes = binding.btnCartasRstantes
        val buenas = binding.btnBuenas
        val inicio = binding.btnInicar
        val barajasB = binding.btnBarajear
        val floating = binding.floatinButton

        buenas.visibility = View.INVISIBLE
        restantes.visibility = View.INVISIBLE
        inicio.visibility = View.INVISIBLE
        floating.visibility = View.INVISIBLE

        barajas = Barajeo(this,vectorBarajeo)
        binding.imgCartas.setImageResource(R.drawable.esperanding)

        initBackmedia.start()

        //----------------------------------------------------------------- Sección de los botones
        binding.btnBarajear.setOnClickListener {
            vectorBarajeo.shuffle()
            initBackmedia.cancel()
            Toast.makeText(this, "Mazo barajeado!!", Toast.LENGTH_SHORT).show()
            inicio.visibility = View.VISIBLE
            mediaCo.release()
            whileMusicMedia.start()
        }

        binding.btnInicar.setOnClickListener {
            barajas.start()
            binding.imgCartas.setImageResource(R.drawable.esperanding)
            binding.txtVTituloCartas.text = "Esperanding....."
            buenas.visibility = View.VISIBLE
            barajasB.visibility = View.INVISIBLE
            inicio.visibility = View.INVISIBLE

        } // fin del primer boton, el de inicio

        binding.btnBuenas.setOnClickListener {
            if(!barajas.estaPausado()){
                binding.btnBuenas.text = "No buenas :("
                barajas.pausaBarajeo()
                Toast.makeText(this, "Presiona de nuevo para despausar", Toast.LENGTH_SHORT).show()
                restantes.visibility = View.VISIBLE
                barajasB.visibility = View.INVISIBLE
                inicio.visibility = View.INVISIBLE
                return@setOnClickListener
            }

            buenas.text = "Buenas!!"
            barajas.despausaBarajeo()
            restantes.visibility = View.INVISIBLE
            barajasB.visibility = View.VISIBLE
            inicio.visibility = View.VISIBLE
            victorySound.start()
            if (victorySound.isCompleted){
                victorySound.cancel()
            }

        }// fin del set on click para buenas

        binding.btnCartasRstantes.setOnClickListener {
            if(barajas.estaEjecutandose()){
                cardView.start()
                whileMusicMedia.cancel()
                mediaBack!!.release()
                barajas.terminarBarajeo()
                buenas.visibility = View.INVISIBLE
                floating.visibility = View.VISIBLE
                Toast.makeText(this, "Si quiere volver a jugar, reinicie el juego con el boton de abajo", Toast.LENGTH_LONG).show()
            }
            Toast.makeText(this, "Juego terminado", Toast.LENGTH_SHORT).show()
            return@setOnClickListener
        }

        floating.setOnClickListener {
            if (Build.VERSION.SDK_INT >= 11) {
                recreate()
            } else {
                val intent = intent
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                finish()
                overridePendingTransition(0, 0)
                startActivity(intent)
                overridePendingTransition(0, 0)
            }
        }

    }// fin del OnCreate

}// fin de la clase Main