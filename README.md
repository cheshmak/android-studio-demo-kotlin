# android-studio-demo-kotlin
Cheshmak SDK - Android Studio Demo App - Kotlin

This repository shows how to use Cheshmak SDK in a demo application written in android. You may use it to either bootstrap your own project, or as an example to use common features in the Cheshmak SDK. Please feel free to fork this project and make pull requests.

# What is Cheshmak ? 
Cheshmak is a mobile backend as a service platform offering the following services to mobile app developers free of charge:
* App Analytics
* Push Notification
* Crash Reporting
* Ad Monetization
Please visit our website for further information: https://www.cheshmak.me

# Installation

# 1.Permissions

Insert the following permissions into your AndroidManifest.xml file.

```xml
​<uses-permission android:name="android.permission.INTERNET" />​
​<uses-permission android:name="android.permission.VIBRATE" />​
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```
The last two permissions are optional and are recommended to increase ad monetization and targeting.

# 2.Build.gradle
Add the Cheshmak repo to your build.gradle file:

```java
repositories {
 jcenter()
 //Add Maven block to your repositories section
 maven {
 url 'https://repository.cheshmak.me'
 }
}
```

Next, add the below dependencies to build.gradle file:

```java
compile 'me.cheshmak:analytics:2.0.5'
compile 'com.google.android.gms:play-services-gcm:10.0.1'
```
 
 # 3. Initializing the SDK 
 Before you can initialize and use the Cheshmak SDK, ensure that you have already configured your app in the [Cheshmak panel](https://panel.cheshmak.me). You will receive an APP KEY for your app in the panel.
When you initialize the SDK, you make it ready for use in your app. It sets initial values and performs other setup tasks. This is required for the features of the Cheshmak SDK to work.
First, create an application class (look [here](https://stackoverflow.com/questions/12834379/extending-android-application-class) if you need assistance)
Next, paste the following code snippet into your app's Application class ```onCreate()``` method. 

```kotlin
        Cheshmak.with(this)
        // Replace <YOUR_APP_KEY> below with the real APP KEY that has been provided in the panel
        Cheshmak.initTracker("/j0DtEit12p0PLEsqzP+Lg==")
        Cheshmak.isTestDevice(true)// Device is set for test
        
```
        
# 4. Advertisement Display 
First, add the below code to your application class: 

```kotlin 
        // init Advertise
        ChesAdv.initiate(applicationContext)
        ChesAdv.enableBannerAds() // enable advertise banner

```
Second, add the banner view to your xml file where you want to show ads

```xml
   <me.cheshmak.android.sdk.advertise.Banner
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
   />

```
for disable showing banner use bellow line code. 

```kotlin
CheshAdv.disableBannerAds()
```

# 5. Test Mode 

Use the following code to enable or disabling the test mode. 

```kotlin 
        ChesAdv.setTestMode(true) // enable test mode
```
# 6. Push notification Handling

To open an activity once a user clicks on a push notification, insert the following snippet into your manifest file: 

```xml 
​<activity
android:name=".YourActivity"​
android:label="your_activity_name">​
​<meta-data android:name="cheshmakPush" android:value="openActivityOnPush"/>​
​</activity>
```
# 7. Receiving Data through Push Notification

for receive data from push you can get data in the following way. 

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
  //some code
  val intent = intent
        if (intent != null) {
            if (intent.extras != null) {
                Toast.makeText(applicationContext, " Cheshmak push notification data " + "\n" +
                        intent.extras!!.getString("me.cheshmak.data") + " " +
                        intent.extras!!.getString("title") + "\n" + intent.extras!!.getString("message"), Toast.LENGTH_SHORT).show()
            }
        }
}
 
```

# 8. Using Push to Open Services 
You may use push notifications to send instructions to the device to start a service. Please see below as an example:

```xml 
​
​<service android:name=".MyService">​
<meta-data android:name="cheshmakPush"​
android:value="startServiceOnPush" />​
​</service>​
 
```

```kotlin
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
 
```

# 9. Tagging
You may tag a user by using the following code: 

```kotlin 
Cheshmak.sendTag("premiumUser");
```

```kotlin 
        //Add new Tags and send list of tags
        val tagList = ArrayList<String>()
        tagList.add("freeUser")
        tagList.add("TagA")
        tagList.add("TagB")
        Cheshmak.sendTags(tagList)
```

Remove a tag: 

```kotlin 
Cheshmak.deleteTag("premiumUser")
```

```kotlin 
        val tags = ArrayList<String>()
        tags.add("premiumUser")
        tags.add("tagA")
        tags.add("tagB")
        Cheshmak.deleteTags(tags)
```

Remove all tages:


```kotlin 
​Cheshmak.deleteAllTags()
```

# 10. Exception Handling:

 send exceptions:
 
```kotlin 
  try { 
  //Do some buggy things
        } catch (e: Exception) {
            Cheshmak.trackException("Image-resize-error", e)
        }

```

send exception with high priority: 

```kotlin 
​
    try {
      //Do some buggy things
        } catch (ex: Exception) {
            Cheshmak.trackException("memory-exception-error", ex, true)
        }
 
```

Send exception without a title:

```kotlin 
​
try{
 //Do some buggy things​
 }
​catch ( ex:Exception) {
 Cheshmak.trackException(ex);
}
 
```

Please visit the [Documentation](https://www.cheshmak.me/docs/%D9%86%D8%B5%D8%A8-%DA%86%D8%B4%D9%85%DA%A9-%D8%AF%D8%B1-android-studio/) page for further details on the Cheshmak features.









 
 
