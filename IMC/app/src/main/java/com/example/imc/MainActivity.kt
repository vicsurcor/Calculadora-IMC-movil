package com.example.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.delay
import kotlin.concurrent.schedule
import java.util.Timer

class MainActivity : AppCompatActivity() {

    private lateinit var peso: EditText;
    private lateinit var altura: EditText;
    private lateinit var resultado: TextView;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        peso = findViewById(R.id.peso);
        altura = findViewById(R.id.altura);
        resultado = findViewById(R.id.resultado);
        peso.setOnFocusChangeListener { v, hasFocus ->

            if (hasFocus) {

                (v as EditText).text.clear();

            }
        }
        altura.setOnFocusChangeListener { v, hasFocus ->

            if (hasFocus) {

                (v as EditText).text.clear();

            }
        }
    }
    fun calcularIMC(view: View) {
        var pesoStr = peso.text.toString()
        var alturaStr = altura.text.toString()
        val altura: Float;

         if (!(pesoStr.contains(Regex("[a-zA-Z]")) || alturaStr.contains(Regex("[a-zA-Z]")))) {
             if (pesoStr.isNotEmpty() && alturaStr.isNotEmpty()) {
                 if (pesoStr.contains(",")){
                     pesoStr = alturaStr.replace(",", ".")
                 }
                 val peso = pesoStr.toFloat()

                 if (alturaStr.contains(",")){
                     alturaStr = alturaStr.replace(",", ".")
                 }

                 altura = alturaStr.toFloat()
                 if (peso > 0 && altura > 0){
                     val imc = peso / (altura * altura)
                     val resultadoStr = String.format("Tu IMC es %.2f", imc)
                     resultado.text = resultadoStr;
                     Timer().schedule(2000){
                         if (imc < 18.5){
                             resultado.text = getString(R.string.deber_a_comer_mas)
                         }
                         else if (imc < 24.9){
                             resultado.text = getString(R.string.estado_bueno)
                         }
                         else if (imc < 29.9){
                             resultado.text = getString(R.string.sufre_sobrepeso)
                         }
                         else {
                             resultado.text = getString(R.string.sufre_obesidad)
                         }
                     }
                 }
                 else {
                     resultado.text = getString(R.string.error_numeros)
                 }

             }
             else {
                 resultado.text = getString(R.string.introduzca_los_datos)
             }
         }
         else {

             resultado.text = getString(R.string.error_caracteres)

         }
    }
}