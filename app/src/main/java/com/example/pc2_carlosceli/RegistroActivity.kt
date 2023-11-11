package com.example.pc2_carlosceli

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.pc2_carlosceli.model.EquiposModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import android.view.View

class RegistroActivity:AppCompatActivity() {
    fun C(view: View){
        startActivity(Intent(this,ListadoActivity::class.java))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val txtNombreEquipo: EditText = findViewById(R.id.txtFullName)
        val txtAño: EditText = findViewById(R.id.txtAño)
        val txtNtitulos: EditText = findViewById(R.id.txtTitulos)
        val txtUrl: EditText=findViewById(R.id.txtUrl)

        val btnSave: Button = findViewById(R.id.btnSaveRegister)
        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("Liga 1")
        val fakeEmail = "hola@example.com"
        val fakePassword = "fakepassword"



        btnSave.setOnClickListener {

            val  nombreEquip = txtNombreEquipo.text.toString()
            val  añof = txtAño.text.toString()
            val  nTitulos = txtNtitulos.text.toString()
            val  urlImage = txtUrl.text.toString()



            auth.createUserWithEmailAndPassword(fakeEmail,fakePassword)
                .addOnCompleteListener(this){task->
                    if(task.isSuccessful){
                        val user: FirebaseUser? = auth.currentUser
                        val uid = user?.uid
                        val equiposmodel = EquiposModel(nombreEquip, añof, nTitulos,urlImage)
                        collectionRef.add(equiposmodel)
                            .addOnCompleteListener{

                        }.addOnFailureListener{error ->
                            Snackbar
                                .make(
                                    findViewById(android.R.id.content)
                                    ,"Ocurrió un error al registrar el modelo"
                                    , Snackbar.LENGTH_LONG
                                ).show()
                        }
                        Snackbar
                            .make(
                                findViewById(android.R.id.content)
                                ,"Registro exitoso del usuario"
                                , Snackbar.LENGTH_LONG
                            ).show()


                    }else{
                        Snackbar
                            .make(
                                findViewById(android.R.id.content)
                                ,"Ocurrió un error al registrarse"
                                , Snackbar.LENGTH_LONG
                            ).show()
                    }


                }

        }

    }

    fun V(view: View){
        startActivity(Intent(this,ListadoActivity::class.java))
    }
    }





