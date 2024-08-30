package br.com.cotemig.exercicio2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        * Nome: Gabriele Staniele Ferreira
        * Matricula: 72201258
        *
        * */

        findViewById<Button>(R.id.btIncluir).setOnClickListener {
            var i = Intent(this, AnimalActivity::class.java)
            startActivity(i)
        }
    }

    override fun onResume() {
        super.onResume()

        chamaAPI()
    }

    fun exibeDados(lista: ArrayList<AnimalModel>) {
        val listview = findViewById<ListView>(R.id.listview)
        val adapter = AnimalAdapter(this, lista)
        listview.adapter = adapter

        listview.setOnItemClickListener { parent, view, position, id ->
            var i = Intent(this, AnimalActivity::class.java)
            i.putExtra("id", lista[position].id)
            startActivity(i)
        }
    }

    fun chamaAPI() {
        val instace = RetrofitUtils.getRetrofit("https://6660d8a763e6a0189fe7b380.mockapi.io")

        val service = instace.create(IAnimalService::class.java)

        service.get().enqueue(object : Callback<ArrayList<AnimalModel>> {
            override fun onResponse(
                call: Call<ArrayList<AnimalModel>>,
                response: Response<ArrayList<AnimalModel>>
            ) {
                val lista = response.body()
                if (lista != null) {
                    exibeDados(lista)
                }
            }

            override fun onFailure(call: Call<ArrayList<AnimalModel>>, t: Throwable) {
                exibeMensagem("Falha ao chamar a api")
            }

        })

    }

    fun exibeMensagem(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }
}