package mx.tecnm.ittepic.ladm_u2_practica2_loteria

import android.media.MediaPlayer
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

class Barajeo(actMain: MainActivity, indice: ArrayList<Int>):Thread() {// fin de la clase
    var pausado = false; var localMain = actMain
    var ejecucion = true; var arrayIndices = indice
    lateinit var media: MediaPlayer

    var vectorSonidoCartas = arrayListOf(R.raw.gallo,R.raw.diablo,R.raw.dama,R.raw.catrin,
        R.raw.paraguas,R.raw.sirena,R.raw.escalera,R.raw.botella,R.raw.barril,R.raw.arbol,R.raw.melon,R.raw.valiente,R.raw.gorrito,
        R.raw.muerte,R.raw.pera,R.raw.bandera,R.raw.bandolon,R.raw.violoncello,R.raw.garza,R.raw.pajaro,R.raw.mano,R.raw.bota,R.raw.luna,
        R.raw.cotorro,R.raw.borracho,R.raw.negrito,R.raw.corazon,R.raw.sandia,R.raw.tambor,R.raw.camaron,R.raw.jaras,R.raw.musico,R.raw.arania,R.raw.soldado,R.raw.estrella,
        R.raw.cazo,R.raw.mundo,R.raw.apache,R.raw.nopal,R.raw.alacran,R.raw.rosa,R.raw.calavera,R.raw.campana,R.raw.cantarito,
        R.raw.venado,R.raw.sol2,R.raw.corona,R.raw.chalupa,R.raw.pino,R.raw.pescado,R.raw.palma,R.raw.maceta,R.raw.arpa,R.raw.rana)

    var vectorCartas = arrayListOf(R.drawable.gallo1,R.drawable.diablo2,R.drawable.dama3,R.drawable.catrin4,R.drawable.paraguas5,R.drawable.sirena6,
        R.drawable.escalera7,R.drawable.botella8,R.drawable.barril9,R.drawable.arbol10,R.drawable.melon11,R.drawable.valiente12,
        R.drawable.gorrto13,R.drawable.muerte14,R.drawable.pera15,R.drawable.bandera16,R.drawable.bandolon17,R.drawable.violon18,
        R.drawable.garza19,R.drawable.pajaro20,R.drawable.mano21,R.drawable.bota22,R.drawable.luna23,R.drawable.cotorro24,
        R.drawable.borracho25,R.drawable.negro26,R.drawable.cora27,R.drawable.sandia28,R.drawable.tambor29,R.drawable.camaron30,
        R.drawable.jaras31,R.drawable.musico32,R.drawable.arania33,R.drawable.sodlado34,R.drawable.estrella35,R.drawable.cazo36,
        R.drawable.mundo37,R.drawable.apache38,R.drawable.nopal39,R.drawable.alacran40,R.drawable.rosa41,R.drawable.calavera42,
        R.drawable.camapana43,R.drawable.cantaro44,R.drawable.venado45,R.drawable.sol46,R.drawable.corona47, R.drawable.chalupa48,
        R.drawable.pino49,R.drawable.pescado50,R.drawable.palma51,R.drawable.maceta52,R.drawable.arpa53,R.drawable.rana54
    )

    var vectorNombreCartas = arrayListOf("El gallo","El diablito","La dama","El catrín","El paraguas","La sirena",
        "La escalera","La botella","El barril","El Árbol","El melón","El valiente",
        "El gorrito","La muerte","La pera","La bandera","El bandolón","El violoncello",
        "La graza","El pajaro","La mano","La bota","La luna","El cotorro",
        "El Borracho","El negrito","El corazón","La sandía","El tambor","El camaron",
        "Las jaras","El músico","La araña","El soldado","La estrella","El cazo",
        "El mundo","El apache","El nopal","El alacran","La rosa","La clavera",
        "La campana","El cantarito","El venado","El sol","La corona","La Chalupa",
        "El pino","El pescado","La palma","La maceta","El arpa","La rana")


    override fun run() {
        super.run()
        while(ejecucion){
            if(!pausado){
                //All the code que ejecutara,aqui
                localMain.runOnUiThread{
                    media = MediaPlayer.create(localMain.baseContext,vectorSonidoCartas[arrayIndices[0]])
                    localMain.binding.imgCartas.setImageResource(vectorCartas[arrayIndices[0]])
                    localMain.binding.txtVTituloCartas.text = vectorNombreCartas[arrayIndices[0]]
                    dropearElementos(vectorSonidoCartas,vectorCartas,vectorNombreCartas,arrayIndices[0])
                    media.start()
                    //indiceL++

                }// modificación de lo visual dentro del MainThread
                sleep(9000)
                media.release()

            }// fin del if
        }// fin del while
    }// fin del metodo RUN

    fun dropearElementos(sonidos: ArrayList<Int>,cartas: ArrayList<Int>, nombres: ArrayList<String>,indice: Int){
        sonidos.removeAt(indice)
        cartas.removeAt(indice)
        nombres.removeAt(indice)
    }

    fun terminarBarajeo() {
        ejecucion = false
    }
    fun pausaBarajeo(){
        pausado = true
        media.pause()

    }

    fun despausaBarajeo(){
        pausado = false
        media.start()
    }
    fun estaEjecutandose():Boolean{
        return ejecucion
    }
    fun estaPausado():Boolean{
        return pausado
    }
}// fin de la clase