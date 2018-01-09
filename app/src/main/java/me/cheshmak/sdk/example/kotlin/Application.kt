package me.cheshmak.sdk.example.kotlin

import android.app.Application
import me.cheshmak.android.sdk.advertise.ChesAdv
import me.cheshmak.android.sdk.core.Cheshmak

/**
 * Created by Amin.Rahkan7@gmail.com on 1/9/18.
 */
class Application : Application() {


    override fun onCreate() {
        super.onCreate()

        Cheshmak.with(this)
        // Replace <YOUR_APP_KEY> below with the real APP KEY that has been provided in the panel
        Cheshmak.initTracker("/j0DtEit12p0PLEsqzP+Lg==")
        Cheshmak.isTestDevice(true)// Device is set for test


        // init Advertise
        ChesAdv.initiate(applicationContext)
        ChesAdv.enableBannerAds() // enable advertise banner
        ChesAdv.setTestMode(true) // enable test mode

        //ChesAdv.disableBannerAds() //for disable banner use this if you want
    }

}