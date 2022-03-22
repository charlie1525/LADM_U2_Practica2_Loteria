package mx.tecnm.ittepic.ladm_u2_practica2_loteria

import android.media.MediaPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Carta(actMain: MainActivity,Nombres: Array<String>,Cartas: Array<Int>,Sonidos: Array<Int>):Thread() {// fin de la clase
    var pausado = false; var localMain = actMain
    var ejecucion = true; var indiceLocal =0
    var vectoNombres = Nombres; var vectoCartas = Cartas; var vectoSonido = Sonidos

    override fun run() {
        super.run()

        while(ejecucion){
            if(!pausado){
                //All the code que ejecutara,aquí
                localMain.runOnUiThread{
                    localMain.binding.imgCartas.setImageResource(vectoCartas[indiceLocal])
                    localMain.binding.txtVTituloCartas.text = vectoNombres[indiceLocal]
                    localMain.media = MediaPlayer.create(localMain.baseContext,vectoSonido[indiceLocal++])
                    localMain.media.start()
                }// modificación de lo visual dentro del MainThread
                sleep(9000)

            }// fin del if
        }// fin del while
    }// fin del metodo RUN

    fun terminarHilo() {ejecucion = false}
    fun pausarHilo() {pausado = true}
    fun despausarHilo() {pausado = false}
    fun estaPausado(): Boolean {return pausado}
    fun estaEjecutandose():Boolean{return ejecucion}

    fun sincronia(){

    }

}// fin de la clase