package mx.tecnm.ittepic.ladm_u2_practica2_loteria

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.coroutines.*
import mx.tecnm.ittepic.ladm_u2_practica2_loteria.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var cartas: Barajeo
    lateinit var media: MediaPlayer
    lateinit var main: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCartasRstantes.visibility = View.INVISIBLE
        main = MainActivity()
        cartas = Barajeo(this)
        binding.imgCartas.setImageResource(R.drawable.esperanding)
        //Sección de los botones

        binding.btnBarajear.setOnClickListener {
            //shuffled()
            Toast.makeText(this, "Mazo barajeado!!", Toast.LENGTH_SHORT).show()
        }

        binding.btnInicar.setOnClickListener {
            cartas.start()
            when (cartas.indiceLocal) {
                54 -> {
                    cartas.terminarHilo()
                    Toast.makeText(
                        this,
                        "Se acabaron las cartas, vuelve a iniciar el juego",
                        Toast.LENGTH_LONG
                    ).show()
                    binding.imgCartas.setImageResource(R.drawable.esperanding)
                    binding.txtVTituloCartas.text = "Esperanding....."
                    cartas.indiceLocal = 0
                }
            }// fin del when del indice
        } // fin del primer boton, el de inicio

        binding.btnBuenas.setOnClickListener {
            media = MediaPlayer.create(this, R.raw.victorysound)
            media.start()
            binding.btnCartasRstantes.visibility = View.VISIBLE
        }

    }// fin del OnCreate

}// fin de la clase Main