package com.apriantrimulyana.electroniccity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_balik_nama.*

class BalikNama : AppCompatActivity() {
    private var etMasukkanNama: EditText? = null
    private var hsMasukkanNama: String = ""
    private var hsSatu: String = ""
    private var hsDua: String = ""
    private var tvSatu: TextView? = null
    private var tvDua: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_balik_nama)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        etMasukkanNama  = et_masukkan_nama
        tvSatu          = tv_satu
        tvDua           = tv_dua

        bt_balik.setOnClickListener(View.OnClickListener {
            hsMasukkanNama = etMasukkanNama!!.getText().toString().trim()
            satu()
            dua()
        })
    }


    private fun satu(){
        tvSatu!!.setText("")
        hsSatu = ""
        val full_hsMasukkanNama = hsMasukkanNama.split(" ")
        val jml_hsMasukkanNama = full_hsMasukkanNama.size
        for (i in 1 .. jml_hsMasukkanNama) {
            hsSatu = hsSatu+" "+full_hsMasukkanNama[jml_hsMasukkanNama-i]
        }
        tvSatu!!.setText(hsSatu)
    }


    private fun dua(){
        tvDua!!.setText("")
        hsDua = " "
        val full_hsMasukkanNama = hsMasukkanNama.split("")
        val jml_hsMasukkanNama = full_hsMasukkanNama.size
        for (i in 1 .. jml_hsMasukkanNama) {
            hsDua = hsDua+""+full_hsMasukkanNama[jml_hsMasukkanNama-i]
        }
        tvDua!!.setText(hsDua)
    }
}
