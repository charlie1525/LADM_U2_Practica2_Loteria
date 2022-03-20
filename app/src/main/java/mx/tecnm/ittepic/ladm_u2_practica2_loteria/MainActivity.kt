package mx.tecnm.ittepic.ladm_u2_practica2_loteria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.tecnm.ittepic.ladm_u2_practica2_loteria.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgCartas.setImageResource(R.drawable.diablo2)


    }// fin del override


}// fin de la clase Main