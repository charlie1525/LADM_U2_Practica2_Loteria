package mx.tecnm.ittepic.ladm_u2_practica2_loteria

import android.media.MediaPlayer
import android.provider.MediaStore

class Carta(actMain: MainActivity,Nombres: Array<String>,Cartas: Array<Int>):Thread() {// fin de la clase
    var pausado = false; var localMain = actMain
    var ejecucion = true; var indice =0
    var vectoNombres = Nombres; var vectoCartas = Cartas

    override fun run() {
        super.run()

        while(ejecucion){
            if(!pausado){
                //All the code que ejecutara, aquí
                localMain.runOnUiThread{
                    localMain.binding.imgCartas.setImageResource(localMain.vectorCartas[indice++])
                    //localMain.binding.txtVTituloCartas.text = vectoNombres.
                }// modificación de lo visual dentro del MainThread
                sleep(9000)

            }// fin del iff
        }// fin del while
    }// fin del metodo RUN

    fun terminarHilo() {ejecucion = false}
    fun pausarHilo() {pausado = true}
    fun despausarHilo() {pausado = false}
    fun estaPausado(): Boolean {return pausado}
    fun estaEjecutandose():Boolean{return ejecucion}


}// fin de la clase