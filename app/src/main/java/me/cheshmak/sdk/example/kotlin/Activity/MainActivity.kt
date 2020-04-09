package me.cheshmak.sdk.example.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import me.cheshmak.android.sdk.core.Cheshmak
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Add new Tags and send list of tags
        val tagList = ArrayList<String>()
        tagList.add("freeUser")
        tagList.add("TagA")
        tagList.add("TagB")
        Cheshmak.sendTags(tagList)


        val tags = ArrayList<String>()
        tags.add("premiumUser")
        tags.add("tagA")
        tags.add("tagB")
        Cheshmak.deleteTags(tags)


    }

}
