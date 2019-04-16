package com.apriantrimulyana.electroniccity.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.ArrayList

class DatabaseManager(
 //end variable simpan json
    private val context: Context) {
    private val dbHelper:DatabaseOpenHelper
    private val db: SQLiteDatabase

    init{
        dbHelper = DatabaseOpenHelper(context)
        db = dbHelper.writableDatabase
    }

    private class DatabaseOpenHelper(context: Context): SQLiteOpenHelper(context, NAMA_DB, null, DB_VERSION) {
        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(CREATE_TABLE_KERANJANG)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVer:Int, newVer:Int) {
            db.execSQL("DROP TABLE IF EXISTS $NAMA_TABEL_KERANJANG")

            onCreate(db)
        }
    }

    fun close() {
        dbHelper.close()
    }


 //table simpan keranjang

    fun addRowKeranjang(id_produk:String, nama_produk:String, gambar_produk:String) {
        val values = ContentValues()
        values.put(ROW_KERANJANG_ID_PRODUK, id_produk)
        values.put(ROW_KERANJANG_NAMA_PRODUK, nama_produk)
        values.put(ROW_KERANJANG_GAMBAR_PRODUK, gambar_produk)

        try{
            db.insert(NAMA_TABEL_KERANJANG, null, values)
        }catch (e:Exception) {
            Log.e("DB ERROR", e.toString())
            e.printStackTrace()
        }
    }


    /*fun deleteJsonAll(json_name:String) {
        try {
            db.delete(NAMA_TABEL_KERANJANG, "$ROW_JSON_NAMA='$json_name'", null)
        }catch (e:Exception) {
            e.printStackTrace()
            Log.e("Error", e.toString())
        }
    }*/


    fun ambilBarisKeranjang(id_produk:String): ArrayList<ArrayList<Any>> {
        val dataArray = ArrayList<ArrayList<Any>>()
        var cur: Cursor? = null
        try {
            cur = db.query(NAMA_TABEL_KERANJANG, arrayOf(ROW_ID_KERANJANG,
                    ROW_KERANJANG_ID_PRODUK,
                    ROW_KERANJANG_NAMA_PRODUK,
                    ROW_KERANJANG_GAMBAR_PRODUK),
        "$ROW_KERANJANG_ID_PRODUK = '$id_produk'", null, null, null, null)
            cur!!.moveToFirst()
            if (!cur!!.isAfterLast) {
                do {
                    val dataList = ArrayList<Any>()
                    dataList.add(cur!!.getString(0))
                    dataList.add(cur!!.getString(1))
                    dataList.add(cur!!.getString(2))
                    dataArray.add(dataList)
                }
                while (cur!!.moveToNext())
            }
        } catch (e:Exception) {
            e.printStackTrace()
            Log.e("DB ERROR ", "ambilBarisJsonPilih $e")
            //Toast.makeText(context, "gagal ambil semua baris:" + e.toString(), Toast.LENGTH_SHORT).show();
        } finally {
            // this gets called even if there is an exception somewhere above
            if (cur != null)
                cur!!.close()
        }
        return dataArray
    }

    fun ambilBarisKeranjangAll(): ArrayList<ArrayList<Any>> {
        val dataArray = ArrayList<ArrayList<Any>>()
        var cur: Cursor? = null
        try {
            cur = db.query(NAMA_TABEL_KERANJANG, arrayOf(ROW_ID_KERANJANG,
                    ROW_KERANJANG_ID_PRODUK,
                    ROW_KERANJANG_NAMA_PRODUK,
                    ROW_KERANJANG_GAMBAR_PRODUK),
                    null, null, null, null, null)
            cur!!.moveToFirst()
            if (!cur!!.isAfterLast) {
                do {
                    val dataList = ArrayList<Any>()
                    dataList.add(cur!!.getString(0))
                    dataList.add(cur!!.getString(1))
                    dataList.add(cur!!.getString(2))
                    dataArray.add(dataList)
                }
                while (cur!!.moveToNext())
            }
        } catch (e:Exception) {
            e.printStackTrace()
            Log.e("DB ERROR ", "ambilBarisJsonPilih $e")
            //Toast.makeText(context, "gagal ambil semua baris:" + e.toString(), Toast.LENGTH_SHORT).show();
        } finally {
            // this gets called even if there is an exception somewhere above
            if (cur != null)
                cur!!.close()
        }
        return dataArray
    }



    companion object {
        private val NAMA_DB = "db_electronic_city"
        private val DB_VERSION = 2

        //variable simpan json
        private val ROW_ID_KERANJANG            = "keranjang_id"
        private val ROW_KERANJANG_ID_PRODUK     = "keranjang_id_produk"
        private val ROW_KERANJANG_NAMA_PRODUK   = "keranjang_nama_produk"
        private val ROW_KERANJANG_GAMBAR_PRODUK = "keranjang_gambar_produk"
        private val NAMA_TABEL_KERANJANG        = "tb_keranjang"
        private val CREATE_TABLE_KERANJANG      = ("create table "
        + NAMA_TABEL_KERANJANG          + " ("
        + ROW_ID_KERANJANG              + " integer PRIMARY KEY autoincrement,"
        + ROW_KERANJANG_ID_PRODUK       + " text,"
        + ROW_KERANJANG_NAMA_PRODUK     + " text,"
        + ROW_KERANJANG_GAMBAR_PRODUK   + " text)")
        //end variable simpan json
    }
 //end voucher
}
