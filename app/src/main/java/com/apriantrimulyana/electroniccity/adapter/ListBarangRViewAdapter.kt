package com.apriantrimulyana.electroniccity.adapter

import android.app.Activity
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.apriantrimulyana.electroniccity.R
import com.apriantrimulyana.electroniccity.model.ListModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import kotlinx.android.synthetic.main.activity_detail_barang.*
import kotlinx.android.synthetic.main.list_barang_rv_item.view.*
import pl.droidsonroids.gif.GifImageView
import java.util.ArrayList

class ListBarangRViewAdapter(private val activity: Activity, ListItems: MutableList<ListModel>) : RecyclerView.Adapter<ListBarangRViewAdapter.ViewHolder>() {
    private val inflater: LayoutInflater? = null
    private var ListItems: MutableList<ListModel>? = null
    private val arraylist: ArrayList<ListModel>

    init {
        this.ListItems = ListItems
        this.arraylist = ArrayList<ListModel>()
        this.arraylist.addAll(ListItems)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val j = ListItems!![position]

        holder.tvNamaProduk.setText(j.getData2())

        try {
            val sGambar = j.data3.trim()
            if (sGambar == "") {
                holder.imgProduk.setImageResource(R.drawable.no_image)
            } else {
                Glide.with(activity)
                        .load(sGambar)
                        .centerCrop()
                        .error(activity.resources.getDrawable(R.drawable.no_image)) // Error Gambar
                        .into(object : SimpleTarget<GlideDrawable>() {
                            override fun onResourceReady(resource: GlideDrawable, glideAnimation: GlideAnimation<in GlideDrawable>) {
                                // Gambar siap di show
                                holder.imgProduk.setImageDrawable(resource)
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


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_barang_rv_item, parent, false)
        return ViewHolder(v)
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var tvNamaProduk: TextView
        var imgProduk: GifImageView

        init {
            tvNamaProduk = v.tv_nama_produk
            imgProduk = v.gambar
        }
    }

    override fun getItemCount(): Int {
        return ListItems!!.size
    }
}