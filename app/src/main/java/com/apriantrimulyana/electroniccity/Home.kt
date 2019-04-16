package com.apriantrimulyana.electroniccity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.apriantrimulyana.electroniccity.model.DatabaseManager
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    private var dm: DatabaseManager? = null
    private var tvJumlahData: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        dm = DatabaseManager(this)
        tvJumlahData = tv_jumlah_data

        ll_list_barang.setOnClickListener(View.OnClickListener {
            val moveList = Intent(this@Home, ListBarang::class.java)
            startActivity(moveList)
        })

        ll_input_bintang.setOnClickListener(View.OnClickListener {
            val moveInputBintang = Intent(this@Home, InputBintang::class.java)
            startActivity(moveInputBintang)
        })

        ll_balik_nama.setOnClickListener(View.OnClickListener {
            val moveBalikNama = Intent(this@Home, BalikNama::class.java)
            startActivity(moveBalikNama)
        })
    }

    private fun check_jumlah_data(){
        val data_check_keranjang_all = dm!!.ambilBarisKeranjangAll()
        tvJumlahData!!.setText(""+data_check_keranjang_all.size)
    }

    override fun onResume() {
        super.onResume()
        check_jumlah_data()
    }
}
