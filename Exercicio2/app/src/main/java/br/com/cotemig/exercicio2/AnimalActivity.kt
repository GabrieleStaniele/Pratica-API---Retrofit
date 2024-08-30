package br.com.cotemig.exercicio2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimalActivity : AppCompatActivity() {
    var isEdit: Boolean = false
    var animal = AnimalModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal)

        var id = intent.getStringExtra("id")

        if (!id.isNullOrEmpty()) {
            findViewById<Button>(R.id.btExcluir).visibility = View.VISIBLE
            isEdit = true
            getById(id)
        }

        findViewById<Button>(R.id.btVoltar).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.btExcluir).setOnClickListener {
            delete()
        }

        findViewById<Button>(R.id.btSalvar).setOnClickListener {
            val etNome = findViewById<EditText>(R.id.etNome)
            val etRaca = findViewById<EditText>(R.id.etRaca)
            val etIdade = findViewById<EditText>(R.id.etIdade)
            val etUrl = findViewById<EditText>(R.id.etUrl)

            if (etNome.text.toString().isNullOrEmpty() || etRaca.text.toString().isNullOrEmpty() ||
                etIdade.text.toString().isNullOrEmpty() || etUrl.text.toString().isNullOrEmpty()) {
                exibeMensagem("Preencha todos os campos")
            } else {
                animal.nome = etNome.text.toString()
                animal.idade = etIdade.text.toString()
                animal.raca = etRaca.text.toString()
                animal.foto = etUrl.text.toString()

                if (isEdit) {
                    put(animal)
                } else {
                    post(animal)
                }
            }
        }
    }

    fun exibeDados(obj: AnimalModel) {
        val etNome = findViewById<EditText>(R.id.etNome)
        val etRaca = findViewById<EditText>(R.id.etRaca)
        val etIdade = findViewById<EditText>(R.id.etIdade)
        val etUrl = findViewById<EditText>(R.id.etUrl)
        val imageView = findViewById<ImageView>(R.id.imageView2)

        Glide
            .with(this)
            .load(obj.foto)
            .into(imageView);

        imageView.visibility = View.VISIBLE

        etNome.setText(obj.nome)
        etRaca.setText(obj.raca)
        etIdade.setText(obj.idade)
        etUrl.setText(obj.foto)
    }


    fun getById(id: String) {
        val instace = RetrofitUtils.getRetrofit("https://6660d8a763e6a0189fe7b380.mockapi.io")

        val service = instace.create(IAnimalService::class.java)

        service.getById(id).enqueue(object : Callback<AnimalModel> {
            override fun onResponse(
                call: Call<AnimalModel>,
                response: Response<AnimalModel>
            ) {
                val obj = response.body()
                if (obj != null) {
                    animal = obj
                    exibeDados(animal)
                } else {
                    exibeMensagem("Não foi possivel encontrar o registro")
                }
            }

            override fun onFailure(call: Call<AnimalModel>, t: Throwable) {
                exibeMensagem("Falha ao chamar a api")
            }

        })

    }

    fun post(animal: AnimalModel) {
        val instace = RetrofitUtils.getRetrofit("https://6660d8a763e6a0189fe7b380.mockapi.io")

        val service = instace.create(IAnimalService::class.java)

        service.post(animal).enqueue(object : Callback<Any> {
            override fun onResponse(
                call: Call<Any>,
                response: Response<Any>
            ) {
                val code = response.code()
                if (code == 200 || code == 201) {
                    finish()
                } else {
                    exibeMensagem("Não foi possivel salvar o registro")
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                exibeMensagem("Falha ao chamar a api")
            }

        })

    }

    fun put(animal: AnimalModel) {
        val instace = RetrofitUtils.getRetrofit("https://6660d8a763e6a0189fe7b380.mockapi.io")

        val service = instace.create(IAnimalService::class.java)

        service.put(animal.id, animal).enqueue(object : Callback<Any> {
            override fun onResponse(
                call: Call<Any>,
                response: Response<Any>
            ) {
                val code = response.code()
                if (code == 200 || code == 201) {
                    finish()
                } else {
                    exibeMensagem("Não foi possivel editar o registro")
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                exibeMensagem("Falha ao chamar a api")
            }

        })

    }

    fun delete() {
        val instace = RetrofitUtils.getRetrofit("https://6660d8a763e6a0189fe7b380.mockapi.io")

        val service = instace.create(IAnimalService::class.java)

        service.delete(animal.id).enqueue(object : Callback<Any> {
            override fun onResponse(
                call: Call<Any>,
                response: Response<Any>
            ) {
                val code = response.code()
                if (code == 200 || code == 201) {
                    finish()
                } else {
                    exibeMensagem("Não foi possivel excluir o registro")
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                exibeMensagem("Falha ao chamar a api")
            }

        })

    }

    fun exibeMensagem(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }
}