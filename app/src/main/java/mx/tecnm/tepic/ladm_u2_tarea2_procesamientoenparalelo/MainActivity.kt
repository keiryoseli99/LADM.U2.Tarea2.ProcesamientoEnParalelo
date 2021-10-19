package mx.tecnm.tepic.ladm_u2_tarea2_procesamientoenparalelo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hilo = Hilo(this)

        button1.setOnClickListener {
            try {
                hilo.start()
            }catch (io: Exception){
                Toast.makeText(this, "ERROR HILO EN EJECUCION", Toast.LENGTH_SHORT).show()
            }
        }
        button2.setOnClickListener {
            hilo.pausar()
            Toast.makeText(this, "HILO PAUSADO/REANUDADO", Toast.LENGTH_SHORT).show()
        }
        button3.setOnClickListener {
            hilo.reiniciar()
            Toast.makeText(this, "HILO REINICIADO", Toast.LENGTH_SHORT).show()
        }
        button4.setOnClickListener {
            hilo.terminar()
            Toast.makeText(this, "HILO TERMINADO", Toast.LENGTH_SHORT).show()
        }
    }
}

class Hilo(p: MainActivity): Thread(){
    val puntero = p
    var pausado = false
    var cicloActivo = true
    var imagenes = arrayOf(R.drawable.icono_1,R.drawable.icono_2,R.drawable.icono_3,R.drawable.icono_4)
    var indice = 0

    fun pausar(){
        pausado = !pausado
    }
    fun reiniciar(){
        cicloActivo = true
        indice = 0
    }
    fun terminar(){
        cicloActivo = !cicloActivo
    }
    override fun run() {
        super.run()
        while (cicloActivo){
            if (!pausado){
                puntero.runOnUiThread(){
                    if (indice == 4){
                        indice = 0
                    }
                    puntero.imageView.setImageResource(imagenes[indice])
                    indice++
                }
            }//if
            sleep(2000)
        }
    }
}