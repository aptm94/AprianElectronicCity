package com.apriantrimulyana.electroniccity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import com.apriantrimulyana.electroniccity.adapter.ListBarangRViewAdapter
import com.apriantrimulyana.electroniccity.model.DatabaseManager
import com.apriantrimulyana.electroniccity.model.ListModel
import com.apriantrimulyana.electroniccity.util.RecyclerTouchListener
import kotlinx.android.synthetic.main.activity_list_barang.*
import java.util.ArrayList

class ListBarang : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private var varSwipe: SwipeRefreshLayout? = null
    private var rvView: RecyclerView? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var postArrayList: ArrayList<ListModel>? = null
    private var adapter: ListBarangRViewAdapter? = null
    private var id_produk: String = ""
    private var nama_produk: String = ""
    private var gambar_produk: String = ""
    private var dm: DatabaseManager? = null
    private var tvJumlahData: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_barang)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }
        dm = DatabaseManager(this)

        varSwipe        = swipe
        rvView          = rv_main
        tvJumlahData    = tv_jumlah_data

        varSwipe!!.setOnRefreshListener(this)
        //varSwipe!!.post { calllistData() }

        rvView!!.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        rvView!!.layoutManager = layoutManager
        rvView!!.addOnItemTouchListener(RecyclerTouchListener(this,
                rvView!!, object : RecyclerTouchListener.ClickListener {

            override fun onClick(view: View, position: Int) {
                val j = postArrayList!![position]
                val id_produk       = j.getData1()
                val nama_produk     = j.getData2()
                val gambar_produk   = j.getData3()

                val detailBerita = Intent(this@ListBarang, DetailBarang::class.java)
                val detail = Bundle()
                detail.putString("id_produk", id_produk)
                detail.putString("nama_produk", nama_produk)
                detail.putString("gambar_produk", gambar_produk)
                detailBerita.putExtras(detail)
                startActivity(detailBerita)
            }

            override fun onLongClick(view: View?, position: Int) {

            }
        }))
    }


    fun calllistData() {
        varSwipe!!.isRefreshing = true
        postArrayList = ArrayList<ListModel>()
        /*for (i in 0 until 10) {
            val id_produk       = "id_produk "+i
            val nama_produk     = "nama_produk"+i
            val gambar_produk   = "gambar_produk"+i

            postArrayList!!.add(ListModel(id_produk, nama_produk, gambar_produk))
        }*/
        for (i in 0 until 3) {
            if(i == 0){
                id_produk       = "1"
                nama_produk     = "kulkas"
                gambar_produk   = "https://cdn.shopify.com/s/files/1/0791/5605/products/Kulkas_SJ-235GC-SD-SS-SP_1024x1024.png"
            }else if(i == 1){
                id_produk       = "2"
                nama_produk     = "Kamera"
                gambar_produk   = "https://cdn.shopify.com/s/files/1/0958/8620/products/X5S_KS_001.0_2048x.png"
            }else if(i == 2){
                id_produk       = "3"
                nama_produk     = "Televisi"
                gambar_produk   = "https://cdn.shopify.com/s/files/1/2660/5202/products/xvbxrko3lxyawhwhl94v_1400x.jpg"
            }


            postArrayList!!.add(ListModel(id_produk, nama_produk, gambar_produk))
        }
        adapter = ListBarangRViewAdapter(this@ListBarang, postArrayList!!)
        rvView!!.adapter = adapter
        varSwipe!!.isRefreshing = false
    }


    private fun check_jumlah_data(){
        val data_check_keranjang_all = dm!!.ambilBarisKeranjangAll()
        tvJumlahData!!.setText(""+data_check_keranjang_all.size)
    }


    override fun onRefresh() {
        calllistData()
    }

    override fun onResume() {
        super.onResume()
        check_jumlah_data()
        calllistData()
    }
}
