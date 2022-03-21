package mx.tecnm.ittepic.ladm_u2_practica2_loteria

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.tecnm.ittepic.ladm_u2_practica2_loteria.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var cartas: Carta
    lateinit var media: MediaPlayer
    var vectorSonidoCartas = arrayOf(R.raw.gallo,R.raw.diablo,R.raw.dama,R.raw.catrin,R.raw.paraguas,R.raw.sirena,R.raw.escalera,R.raw.botella
        ,R.raw.barril,R.raw.arbol,R.raw.melon,R.raw.valiente,R.raw.gorrito,R.raw.muerte,R.raw.pera,R.raw.bandera,R.raw.bandolon,R.raw.violoncello,R.raw.garza,R.raw.pajaro
        ,R.raw.mano,R.raw.bota,R.raw.luna,R.raw.cotorro,R.raw.borracho,R.raw.negrito,R.raw.corazon,R.raw.sandia,R.raw.tambor,R.raw.camaron,R.raw.jaras,R.raw.musico
        ,R.raw.arania,R.raw.soldado,R.raw.estrella,R.raw.cazo,R.raw.mundo,R.raw.apache,R.raw.nopal,R.raw.alacran,R.raw.rosa,R.raw.calavera,R.raw.campana,R.raw.cantarito
        ,R.raw.venado,R.raw.sol2,R.raw.corona,R.raw.chalupa,R.raw.pino,R.raw.pescado,R.raw.palma,R.raw.maceta,R.raw.arpa,R.raw.rana)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cartas = Carta(this)
        media = MediaPlayer.create(this,vectorSonidoCartas[19])
        binding.imgCartas.setImageResource(R.drawable.diablo2)
        binding.btnInicar.setOnClickListener {
            cartas.media2.start()
        }


    }// fin del override


}// fin de la clase Main