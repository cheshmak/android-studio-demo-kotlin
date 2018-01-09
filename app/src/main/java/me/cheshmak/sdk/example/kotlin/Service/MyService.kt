package me.cheshmak.sdk.example.kotlin

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import org.json.JSONException
import org.json.JSONObject

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        try {
            Log.d("CHESHMAK_POPP", "SERVICE STARTED" + intent!!.getStringExtra("me.cheshmak.data"))

            //get json Data that you set in cheshmak panel
            val obj = JSONObject(intent.getStringExtra("me.cheshmak.data"))
            val myOption = obj.getString("MyKey")

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return Service.START_STICKY

    }
}
