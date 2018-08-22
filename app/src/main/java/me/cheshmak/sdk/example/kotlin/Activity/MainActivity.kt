package me.cheshmak.sdk.example.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import me.cheshmak.android.sdk.core.Cheshmak
import me.cheshmak.android.sdk.core.network.SinglePushResponseListener
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        receiveCheshmakPush()

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

    public fun getCheshmakID(view:View) {
        Cheshmak.getSinglePushUniqueId(applicationContext, object : SinglePushResponseListener {
            override fun onUniqueIdReceived(uniqueId: String) {

                if (isFinishing) {
                    return
                }

                Toast.makeText(this@MainActivity, "Unique id is $uniqueId", Toast.LENGTH_SHORT).show()
            }

            override fun onError(throwable: Throwable) {

                if (isFinishing) {
                    return
                }

                Toast.makeText(this@MainActivity, "error happened, see logCat", Toast.LENGTH_SHORT).show()
                throwable.printStackTrace()
            }
        })
    }

    private fun receiveCheshmakPush() {
        val intent = intent
        if (intent != null) {
            if (intent.extras != null) {
                Toast.makeText(applicationContext, " Cheshmak push notification data " + "\n" +
                        intent.extras!!.getString("me.cheshmak.data") + " " +
                        intent.extras!!.getString("title") + "\n" + intent.extras!!.getString("message"), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
