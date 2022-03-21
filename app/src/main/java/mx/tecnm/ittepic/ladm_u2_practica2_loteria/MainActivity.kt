package mx.tecnm.ittepic.ladm_u2_practica2_loteria

import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.coroutines.*
import mx.tecnm.ittepic.ladm_u2_practica2_loteria.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var cartas: Carta
    lateinit var media: MediaPlayer
    lateinit var main: MainActivity
    var indice = 0
    lateinit var contexto:Context

    var vectorSonidoCartas = arrayOf(R.raw.gallo,R.raw.diablo,R.raw.dama,R.raw.catrin,R.raw.paraguas,R.raw.sirena,R.raw.escalera,R.raw.botella
        ,R.raw.barril,R.raw.arbol,R.raw.melon,R.raw.valiente,R.raw.gorrito,R.raw.muerte,R.raw.pera,R.raw.bandera,R.raw.bandolon,R.raw.violoncello,R.raw.garza,R.raw.pajaro
        ,R.raw.mano,R.raw.bota,R.raw.luna,R.raw.cotorro,R.raw.borracho,R.raw.negrito,R.raw.corazon,R.raw.sandia,R.raw.tambor,R.raw.camaron,R.raw.jaras,R.raw.musico
        ,R.raw.arania,R.raw.soldado,R.raw.estrella,R.raw.cazo,R.raw.mundo,R.raw.apache,R.raw.nopal,R.raw.alacran,R.raw.rosa,R.raw.calavera,R.raw.campana,R.raw.cantarito
        ,R.raw.venado,R.raw.sol2,R.raw.corona,R.raw.chalupa,R.raw.pino,R.raw.pescado,R.raw.palma,R.raw.maceta,R.raw.arpa,R.raw.rana)

    var vectorCartas = arrayOf(R.drawable.gallo1,R.drawable.diablo2,R.drawable.dama3,R.drawable.catrin4,
        R.drawable.paraguas5,R.drawable.sirena6,R.drawable.escalera7,R.drawable.botella8,R.drawable.barril9,R.drawable.arbol10,R.drawable.melon11,R.drawable.valiente12,
        R.drawable.gorrto13,R.drawable.muerte14,R.drawable.pera15,R.drawable.bandera16,R.drawable.bandolon17,R.drawable.violon18,R.drawable.garza19,R.drawable.pajaro20,
        R.drawable.mano21,R.drawable.bota22,R.drawable.luna23,R.drawable.cotorro24,R.drawable.borracho25,R.drawable.negro26,R.drawable.cora27,R.drawable.sandia28,
        R.drawable.tambor29,R.drawable.camaron30,R.drawable.jaras31,R.drawable.musico32,R.drawable.arania33,R.drawable.sodlado34,R.drawable.estrella35,R.drawable.cazo36,
        R.drawable.mundo37,R.drawable.apache38,R.drawable.nopal39,R.drawable.alacran40,R.drawable.rosa41,R.drawable.calavera42,R.drawable.camapana43,R.drawable.cantaro44,
        R.drawable.venado45,R.drawable.sol46,R.drawable.corona47,R.drawable.chalupa48,R.drawable.pino49,R.drawable.pescado50,R.drawable.palma51,R.drawable.maceta52,
        R.drawable.arpa53,R.drawable.rana54)

    var vectorNombreCartas = arrayOf("El gallo","El diablito","La dama","El catrín","El paraguas","La sirena","La escalera","La botella","El barril","El Árbol","El melón","El valiente"
        ,"El gorrito"," La muerte","La pera","La bandera","El bandolón","El violoncello","La graza","El pajaro","La mano","La bota","La luna","El cotorro","El Borracho","El negrito","El corazón"
        ,"La sandía","El tambor","El camaron","Las jaras","El músico","La araña","El soldado","La estrella","El cazo","El mundo","El apache","El nopal","El alacran","La rosaa","La clavera","La campana"
        ,"El cantarito","El venado","El sol","La corona","La Chalupa","El pino","El pescado","La palma","La maceta","El arpa","La rana")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCartasRstantes.visibility = View.INVISIBLE
        main = MainActivity()
        cartas = Carta(this,vectorNombreCartas,vectorCartas,vectorSonidoCartas)
        indice = cartas.indiceLocal
        binding.imgCartas.setImageResource(R.drawable.esperanding)
        contexto = applicationContext
        //Sección de los botones

        binding.btnBarajear.setOnClickListener {
            vectorCartas.shuffle()
            Toast.makeText(this, "Mazo barajeado!!", Toast.LENGTH_SHORT).show()
        }

        binding.btnInicar.setOnClickListener {
            cartas.start()
            when(cartas.indiceLocal){
                54 -> {
                    cartas.terminarHilo()
                    Toast.makeText(this,"Se acabaron las cartas, vuelve a iniciar el juego",Toast.LENGTH_LONG).show()
                    binding.imgCartas.setImageResource(R.drawable.esperanding)
                    binding.txtVTituloCartas.text ="Esperanding....."
                }
            }// fin del when del indice
        } // fin del primer boton, el de inicio

        binding.btnBuenas.setOnClickListener {
            media = MediaPlayer.create(this,R.raw.victorysound)
            media.start()
            binding.btnCartasRstantes.visibility = View.VISIBLE
        }

    }// fin del OnCreate


}// fin de la clase Main