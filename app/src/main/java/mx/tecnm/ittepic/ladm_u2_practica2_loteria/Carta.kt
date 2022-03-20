package mx.tecnm.ittepic.ladm_u2_practica2_loteria

import android.media.MediaPlayer

class Carta(actMain: MainActivity):Thread() {// fin de la clase
    var pausado = false; var localMain = actMain
    var ejecucion = true; var media= MediaPlayer()

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

    var vectorSonidoCartas = arrayOf("")

    override fun run() {
        super.run()
        while(ejecucion){
            if(!pausado){
                //All the code que ejecutara, aquí
                localMain.runOnUiThread{

                }// modificación de lo visual dentro del MainThread

            }// fin del iff
        }// fin del while
    }// fin del metodo RUN


}// fin de la clase