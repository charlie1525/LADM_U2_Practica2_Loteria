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
    lateinit var media: MediaPlayer

    override fun run() {
        super.run()

        while(ejecucion){
            if(!pausado){
                //All the code que ejecutara,aquí
                localMain.runOnUiThread{
                    media = MediaPlayer()
                    localMain.binding.imgCartas.setImageResource(localMain.vectorCartas[indiceLocal])
                    localMain.binding.txtVTituloCartas.text = vectoNombres[indiceLocal]
                    RepoAudios(indiceLocal).start()
                }// modificación de lo visual dentro del MainThread
                indiceLocal++
                sleep(9000)

            }// fin del iff
        }// fin del while
    }// fin del metodo RUN

    fun RepoAudios(indice:Int) = GlobalScope.launch(Dispatchers.Main) {
        while(true) {
            for (iterator in vectoSonido) {
                media = MediaPlayer.create(localMain.contexto,vectoCartas[indice])
            }
            media.start()
            delay(9000)
        }
    }

    fun terminarHilo() {ejecucion = false}
    fun pausarHilo() {pausado = true}
    fun despausarHilo() {pausado = false}
    fun estaPausado(): Boolean {return pausado}
    fun estaEjecutandose():Boolean{return ejecucion}


}// fin de la clase