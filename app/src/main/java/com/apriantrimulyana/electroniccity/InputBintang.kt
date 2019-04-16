package com.apriantrimulyana.electroniccity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.apriantrimulyana.electroniccity.model.ListModel
import kotlinx.android.synthetic.main.activity_input_bintang.*

class InputBintang : AppCompatActivity() {
    private var tvSatu: TextView? = null
    private var tvDua: TextView? = null
    private var tvTiga: TextView? = null
    private var hsSatu: String? = ""
    private var hsDua: String? = ""
    private var hsTiga: String? = ""
    private var iJmlBintang: Int? = null
    private var etJmlBintang: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_bintang)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        tvSatu          = tv_satu
        tvDua           = tv_dua
        tvTiga          = tv_tiga
        etJmlBintang    = et_jml_bintang

        bt_calc.setOnClickListener(View.OnClickListener {
            var sJmlBintang = etJmlBintang!!.getText().toString().trim()
            iJmlBintang = Integer.parseInt(sJmlBintang)
            satu()
            dua()
            tiga()
        })
    }

    private fun satu(){
        tvSatu!!.setText("")
        hsSatu = ""
        for (i in 1 ..this!!.iJmlBintang!!) {
            for (j in 1 .. i) {
                hsSatu = hsSatu+"*"
            }
            hsSatu = hsSatu+"\n"
        }
        tvSatu!!.setText(hsSatu)
    }

    private fun dua(){
        tvDua!!.setText("")
        hsDua = ""
        for (i in 1 .. iJmlBintang!!) {
            for (j in iJmlBintang!!-i downTo 0) {
                hsDua = hsDua+"*"
            }
            hsDua = hsDua+"\n"
        }
        tvDua!!.setText(hsDua)
    }

    private fun tiga(){
        tvTiga!!.setText("")
        hsTiga = ""
        for (i in 1 ..this!!.iJmlBintang!!) {
            for (j in 1 .. i) {
                hsTiga = hsTiga+"*"
            }
            hsTiga = hsTiga+"\n"
        }
        tvTiga!!.setText(hsTiga)

    }
}
