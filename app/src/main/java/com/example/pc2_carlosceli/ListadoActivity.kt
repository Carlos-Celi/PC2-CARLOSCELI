package com.example.pc2_carlosceli

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pc2_carlosceli.adapter.EquiposAdapter
import com.example.pc2_carlosceli.model.EquiposModel
import com.google.firebase.firestore.FirebaseFirestore

class ListadoActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado)

        val rvEquipo = findViewById<RecyclerView>(R.id.rvEquipo)

        val db = FirebaseFirestore.getInstance()
        var lstEquipos: List<EquiposModel>
        val btnGuardar: Button = findViewById(R.id.Guardar)



        db.collection("Liga 1")
            .addSnapshotListener { snap, e ->
                if (e != null) {
                    Log.i("ERROR", "Ocurrió un error")
                    return@addSnapshotListener
                }

                lstEquipos = snap!!.documents.map { document ->
                    EquiposModel(
                        document["nombre"].toString(),
                        document["año"].toString(),
                        document["nTItulos"].toString(),
                        document["imageUrl"].toString()
                    )
                }

                rvEquipo.adapter = EquiposAdapter(lstEquipos)
                rvEquipo.layoutManager = LinearLayoutManager(this)

            }
        btnGuardar.setOnClickListener{
            startActivity(Intent(this, RegistroActivity::class.java))
        }
    }
}
