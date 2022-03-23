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
    var media: MediaPlayer = MediaPlayer()
    var media2: MediaPlayer = MediaPlayer()
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
            //shuffled(Random(Random.nextInt()))
            Toast.makeText(this, "Mazo barajeado!!", Toast.LENGTH_SHORT).show()
        }

        binding.btnInicar.setOnClickListener {
            cartas.start()
            binding.imgCartas.setImageResource(R.drawable.esperanding)
            binding.txtVTituloCartas.text = "Esperanding....."
        } // fin del primer boton, el de inicio

        binding.btnBuenas.setOnClickListener {
            media = MediaPlayer.create(this, R.raw.victorysound)
            media.start()
            binding.btnCartasRstantes.visibility = View.VISIBLE
        }

    }// fin del OnCreate

}// fin de la clase Main