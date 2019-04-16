package com.apriantrimulyana.electroniccity

import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.apriantrimulyana.electroniccity.model.DatabaseManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import kotlinx.android.synthetic.main.activity_detail_barang.*

class DetailBarang : AppCompatActivity() {
    private var getIdProduk: String? = null
    private var getNamaProduk: String? = null
    private var getGambarProduk: String? = null
    private var dm: DatabaseManager? = null
    private var tvJumlahData: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_barang)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }
        dm = DatabaseManager(this)

        getIdProduk     = intent.extras!!.getString("id_produk")
        getNamaProduk   = intent.extras!!.getString("nama_produk")
        getGambarProduk = intent.extras!!.getString("gambar_produk")

        tvJumlahData = tv_jumlah_data
        tv_dl_nama_produk.setText(getNamaProduk)

        try {
            val sGambar = getGambarProduk
            if (sGambar == "") {
                img_produk.setImageResource(R.drawable.no_image)
            } else {
                Glide.with(this)
                        .load(sGambar)
                        .centerCrop()
                        .error(resources.getDrawable(R.drawable.no_image)) // Error Gambar
                        .into(object : SimpleTarget<GlideDrawable>() {
                            override fun onResourceReady(resource: GlideDrawable, glideAnimation: GlideAnimation<in GlideDrawable>) {
                                // Gambar siap di show
                                img_produk.setImageDrawable(resource)
                            }

                            override fun onLoadFailed(e: Exception?, errorDrawable: Drawable?) {
                                // Gambar gagal diload
                            }

                            override fun onLoadStarted(placeholder: Drawable?) {
                                // Gambar gagal mulai diload
                            }
                        })
            }

        } catch (E: Exception) {

        }


        bt_order.setOnClickListener(View.OnClickListener {
            simpan_data()
            val moveWebView = Intent(this@DetailBarang, WebViewElectronicCity::class.java)
            startActivity(moveWebView)
        })
    }

    private fun simpan_data(){
        val data_check_keranjang = dm!!.ambilBarisKeranjang(getIdProduk!!)
        if (data_check_keranjang.size < 1) {
            dm!!.addRowKeranjang(getIdProduk!!, getNamaProduk!!, getGambarProduk!!)
        }
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
