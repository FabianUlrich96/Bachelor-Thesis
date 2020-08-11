fun main(){}

//ID: 1088231
class MainActivity : AppCompatActivity() {

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val myAsync = MyAsync()
    myAsync.execute()

}
}

class MyAsync: AsyncTask&lt;Void, Void, Void&gt;() {
val url = URL("https://gismeteo.ru/")
val conn = url.openConnection() as HttpURLConnection

override fun doInBackground(vararg params: Void?): Void? {

    conn.requestMethod = "POST"
    conn.doInput = true
    conn.doOutput = true
    conn.connect()

    val sb = StringBuilder()

    val bufferReader = BufferedReader(InputStreamReader(conn.inputStream, "UTF-8"))

    var line = bufferReader.readLine()

    while (line != null) {
        sb.append(line)
        Log.d("!", line)
        line = bufferReader.readLine()
    }


    return null
}

override fun onPostExecute(result: Void?) {
    super.onPostExecute(result)
}

}

}
//ID: 716879
package com.example.sfp.parsejson

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    lateinit var click:Button
    lateinit var data:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        click = findViewById(R.id.button) as Button
        data = findViewById(R.id.fetchedata) as TextView

        click.setOnClickListener(object:View.OnClickListener{
            override fun onClick(view:View) {
                val process = fetchData()
                process.execute()
            }
        })
    }
}

}
//ID: 716879
package com.example.sfp.parsejson

import android.os.AsyncTask
import android.util.Log
import android.widget.TextView

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class fetchData : AsyncTask&lt;Void, Void, TextView&gt;() {
    var data: String = ""
    private var dataParsed = ""
    private var singleParsed = ""
    override fun doInBackground(vararg p0: Void?): TextView? {
        try {
            val url = URL("https://api.myjson.com/bins/b1kxl")

            val httpURLConnection = url.openConnection() as HttpURLConnection
            val inputStream = httpURLConnection.inputStream
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            var line: String? = ""
            while (line != null) {
                line = bufferedReader.readLine()
                data += line!!
            }

            val JA = JSONArray(data)
            for (i in 0 until JA.length()) {
                val JO = JA.get(i) as JSONObject
                singleParsed = "Name:" + JO.get("name") + "\n" +
                        "Password:" + JO.get("password") + "\n" +
                        "Contact:" + JO.get("contact") + "\n" +
                        "Country:" + JO.get("country") + "\n"

                dataParsed += singleParsed
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return null
    }


    override fun onPostExecute(aVoid: TextView) {
        super.onPostExecute(aVoid)
        aVoid.text = this.dataParsed
    }
}

}
//ID: 717002
fun decodeImageResource(imageResId: Int): Bitmap? {
    try {
        val options = BitmapFactory.Options().apply { inScaled = false }
        return BitmapFactory.decodeResource(AppResources.appContext?.resources, imageResId, options)
    } catch (e: Exception) {
        e.log()
    }
    return null
}

}
//ID: 980518
class HistoryFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate&lt;HistoryFragmentViewBinding&gt;(
            inflater,
            R.layout.history_fragment_view,
            container,
            false
        )
        val historyViewModel = ViewModelProviders.of(this, viewModelFactory).get(HistoryViewModel::class.java)
        val adapter = TrackedActivityAdapter(TrackedActivity.DIFF_CALLBACK)
        binding.trackedActivityRv.adapter = adapter
        binding.trackedActivityRv.layoutManager = LinearLayoutManager(binding.root.context)
        historyViewModel.getTrackedActivities().observe(this,
            Observer&lt;PagedList&lt;TrackedActivity&gt;&gt; { t -&gt;
                adapter.submitList(t)
            })
        binding.executePendingBindings()
        return binding.root
    }
}

}
//ID: 1089079
fun YrKxz(args: Array&lt;String&gt;){
    println("Hello, World!")
}

//ID: 880492
class RxEditText {

companion object {
    fun getTextWatcherObservable(editText: EditText): Observable&lt;String&gt; {
        var subject = PublishSubject.create&lt;String&gt;()

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(!s.isNullOrEmpty())
                    subject.onNext(s.toString())
            }

        })
        return subject
    }
}}

}
//ID: 1089355
     override fun onActivityCreated(savedInstanceState: Bundle?) {
                super.onActivityCreated(savedInstanceState)
                gameSessionViewModel =
                    ViewModelProvider(requireParentFragment())[GameSessionViewModel::class.java].apply {
                        val session = gameSession.value
                        )
                    }
        }

}
//ID: 1042007
internal fun getData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(GetInt::class.java)
        val call = service.Beru()

        call.enqueue(object : Callback&lt;List&lt;GetLots&gt;&gt; {
            override fun onResponse(call: Call&lt;List&lt;GetLots&gt;&gt;, response: Response&lt;List&lt;GetLots&gt;&gt;) {  
                textView1.text = response.body()?.get(0)?.photos?.get(0)?.url.toString()
            }

            override fun onFailure(call: Call&lt;List&lt;GetLots&gt;&gt;, t: Throwable) {
            }
        })
    }

}
//ID: 1042007
data class GetLots(

    @SerializedName("photos")
    var photos: List&lt;Photo&gt;? = null,

    @SerializedName("_id")
    var id: String? = null,

    @SerializedName("classification")
    var classification: Classification? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("catalog")
    var catalog: Catalog? = null,

    @SerializedName("count")
    var count: Int? = null,

    @SerializedName("date")
    var date: String? = null,

    @SerializedName("cost")
    var cost: Int? = null,

    @SerializedName("info")
    var info: String? = null
)

}
//ID: 881162
data class Article(
    val content: String?,

    val publishedAt: String?,

    val author: String?,

    val urlToImage: String?,

    val title: String?,

    val source: Source?,

    val description: String?,

    val url: String?
)

data class Result (
        val articles: List&lt;Article&gt;?,

        val totalResults: Int?,

        val status: String?

)
data class Source(
        var id : Int?,

        var name : String?
)

}
//ID: 982524
object Singleton {
      lateinit var context : Context

    }

}
//ID: 882216
class MainActivity : AppCompatActivity() {
    var articles: Observable&lt;ArrayList&lt;Article&gt;&gt;? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = ApiService.create()

        val articles = apiService.getTopHeadlines("techcrunch", APIUrl.newsApi)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            result -&gt; result.articles
                        },
                        {
                            throwable: Throwable? -&gt; Log.d("Ошибка",throwable.toString())
                        }
                )

        val db = AppDatabase.getInstance(this)
        val articleDao = db.articleDao()
        articleDao.insert(articles)
    }
}

}
//ID: 1043131
@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(MovieDatabase::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_database"
                )
                .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}

}
//ID: 719737
var GLOBAL_VAR:Int? = null
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doInBackground()
    }
    private var urlConnection: HttpURLConnection? = null
    private var reader: BufferedReader? = null
    private var resultJson = ""
    private var url = URL("https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&amp;origins=53.7095281,91.4277445&amp;DC&amp;destinations=53.696365,91.386070&amp;mode=driving&amp;key=AIzaSyCJYCg5StSenKMjLgEn3zAbAAwpuBESKQc")

    private fun doInBackground() {
        // получаем данные с внешнего ресурса
        try {
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection!!.requestMethod = "GET"
            urlConnection!!.connect()
            val inputStream = urlConnection!!.inputStream
            val buffer = StringBuffer()
            reader = BufferedReader(InputStreamReader(inputStream))
            var line: String?
            do {
                line = reader!!.readLine()
                if (line == null)
                    break
                buffer.append(line)
            } while (true)
            resultJson = buffer.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
            val client = OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(false)
                    .build()
            val request = Request.Builder()
                    .url(url)
                    .build()
            client.newCall(request).enqueue(object : Callback {

                override fun onResponse(call: Call, response: Response?){
                    val responseString = response!!.body()!!.string()
                    val resultJson = JSONObject(responseString)
                    val rows = resultJson.getJSONArray("rows")
                    val obj = rows.getJSONObject(0)
                    val elements = obj.getJSONArray("elements")
                    val element = elements.getJSONObject(0)
                    val distance = element.getJSONObject("distance")
                    var value = distance.getInt("value")
                    value /= 1000
                    Log.d(LOG_TAG, value.toString())

                }
                override fun onFailure(call: Call?, e: IOException?) {
                    Log.d(LOG_TAG, "some error")
                }
            })
    }
    companion object {
        var LOG_TAG = "&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;LOG&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;"
    }
}

}
//ID: 661087
class LinkItem(val titleId: Int) {

override fun equals(other: Any?) = other?.javaClass == javaClass &amp;&amp; titleId == (other as LinkItem).titleId

override fun hashCode() = titleId

constructor(titleId: String) : this()
}

}
//ID: 720249
@Suppress("DEPRECATION")
class ParseTask: AsyncTask&lt;Int, Int, Int&gt;(){
    var GLOBAL_VAR:Int? = 0
    override fun doInBackground(vararg p0: Int?): Int? {
        var bebe:Int = 0
        "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&amp;origins=59.9085638,30.3952677&amp;DC&amp;destinations=59.906638,30.3984863&amp;mode=driving&amp;key=AIzaSyCJYCg5StSenKMjLgEn3zAbAAwpuBESKQc".httpGet().responseString { request, response, result -&gt;
            //do something with response
            when (result) {
                is Result.Failure -&gt; {
                    val error = result.getAs&lt;String&gt;()
                    Log.d(LOG_TAG, error)
                }
                is Result.Success -&gt; {
                    var data = result.getAs&lt;String&gt;()
                    val resultJson = JSONObject(data)

                    val rows = resultJson.getJSONArray("rows")
                    val obj = rows.getJSONObject(0)
                    val elements = obj.getJSONArray("elements")
                    val element = elements.getJSONObject(0)
                    val distance = element.getJSONObject("distance")
                    val value = distance.getInt("value")

                    Log.d(LOG_TAG, "" + value)
                    GLOBAL_VAR = value
                }
            }
        }
        return 10
    }

}
//ID: 1044243
**
 * Kotlin extensions for dependency injection
 */

inline fun &lt;reified T : ViewModel&gt; FragmentActivity.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, factory)[T::class.java]
}

inline fun &lt;reified T : ViewModel&gt; Fragment.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, factory)[T::class.java]
}

}
//ID: 721142
data class User(val firstName: String = "", val lastName: String = "") {
    class Deserializer : ResponseDeserializable&lt;User&gt; {
        override fun deserialize(content: String) = Gson().fromJson(content, User::class.java)!!

    }
}

}
//ID: 1092694
package com.example.testapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun test(view: View) {
        var num = 0
        for (i in 0..4)
            num++
        val testSay = Toast.makeText(this, num.toString(), Toast.LENGTH_SHORT)
        testSay.show()
    }
}

}
//ID: 884752
@Entity (tableName = "article")
open class Article {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = 0

    @ColumnInfo(name = "content")
    var content: String? = ""

    @ColumnInfo(name = "publishedAt")
    var publishedAt: String? = ""

    @ColumnInfo(name = "author")
    var author: String? = ""

    @ColumnInfo(name = "urlToImage")
    var urlToImage: String? = ""

    @ColumnInfo(name = "title")
    var title: String? = ""


    @TypeConverters({SourcesConverter.class})
    @ColumnInfo(name = "source")
    var sources: Sources? = null

    @ColumnInfo(name = "description")
    var description: String? = ""

    @ColumnInfo(name = "url")
    var url: String? = ""
}

}
//ID: 884752
data class Source(
        var id : String?,

        var name : String?
)

}
//ID: 834367
class MainActivity: ... SwipeRefreshLayout.OnRefreshListener {

private val mSwipeRefreshLayout:SwipeRefreshLayout? = null

override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)        
        setContentView(R.layout.activity_main)

mSwipeRefreshLayout?.setColorSchemeResources(R.color.colorPrimary)

    override fun onRefresh() {
        Handler().postDelayed({    
            mSwipeRefreshLayout!!.isRefreshing = false
            toast("ОК")
        }, 400)
    }
}

}
//ID: 986187
class Users {
    var name: String = ""
    var phone: String = ""
    var password: String = ""
    var image: String = ""
    var address: String = ""

    constructor()

    constructor(name: String, phone: String, password: String, image: String, address: String) {
        this.name = name
        this.phone = phone
        this.password = password
        this.image = image
        this.address = address
    }
}

}
//ID: 722922
fun reserveBadCaseDevice(workerId: Int,
                             condition: DeviceCondition,
                             result: (Unit) -&gt; Unit,
                             errorText: (String) -&gt; Unit,
                             errorId: (Int) -&gt; Unit) {
        val data = ReservedWorkerData(
                DeviceState.TAKE,
                condition, null,
                workerId)

        val call = apiService.postAsyncReserveDevice(data, prefManager.getPrefToken())
        call.enqueue(object : Callback&lt;Void&gt; {
            override fun onResponse(call: Call&lt;Void&gt;?, response: Response&lt;Void&gt;?) {
                response?.let {
                    if (response.isSuccessful) {
                        result.invoke(Unit)
                    } else {
                        errorText(apiErrorUtil.parse(response).message)
                    }
                }
            }

            override fun onFailure(call: Call&lt;Void&gt;?, t: Throwable?) {
                errorId(apiErrorUtil.parse(t).message)
            }
        })
    }

}
//ID: 886685
val map = mapOf(1 to "one", 2 to "two", 3 to "three")
Log.e(TAG, "$map")

}
//ID: 886685
Log.d(TAG, "${
            listOf(1, 2, 3, 4, 8, 9)
                    .map {
                        Log.d(TAG, "map")
                        it * it
                    }
                    .find { it &gt; 6 }
}")

}
//ID: 886685
Log.d(TAG, "${
            listOf(1, 2, 3, 4, 8, 9)
                    .map {
                        Log.d(TAG, "map $it")
                        it * it
                    }
                    .find { it &gt; 6 }
}")

}
//ID: 1094835
class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item -&gt;
        when (item.itemId) {
            R.id.navigation_event -&gt; {
                val fragment =
                    EventFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true

            }
            R.id.navigation_person -&gt; {
                val fragment =
                    PersonFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_reports -&gt; {
                val fragment =
                    ReportFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -&gt; {
                val fragment =
                    SettingsFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)


        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        val fragment = EventFragment()
        addFragment(fragment)




    }
   private fun addFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }

}
//ID: 665120
override fun showTimer(timeOut: Long) {
        val timer = object : CountDownTimer(timeOut, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                actionWarning.text = String.format(AppResources.getString(R.string.wait),
                        millisUntilFinished / 1000)
            }

            override fun onFinish() {
            }
        }.start()
    }

}
//ID: 1095272
    fun getNameAnimal(name : String) : String {
       var nameAnimal = " "

       val api = ApiService.create()

       api.getAnimal("Cat")
       .subscribeOn(Schedulers.io())
       .observeOn(AndroidSchedulers.mainThread())
       .subscribe(
           { animal -&gt; 
                 // It works
                 Log.i(LOG, animal.name) 
                 // It NOT works (empty value)
                 nameAnimal = animal.name
           },
           { error -&gt;
                 Log.e(LOG, error.printStackTrace())
           }
       )
       return nameAnimal
    }

}
//ID: 1048041
val array = arrayOf(1, 2, 3)

for(i in 0..array.lastIndex-1)
{
    println("${array[i]} compare ${array[i+1]}")
}

}
//ID: 772562
val intent = Intent(this, MainActivity::class.java)
intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
val pendingIntent = PendingIntent.getActivity(this, 0, intent,
        PendingIntent.FLAG_ONE_SHOT)

}
//ID: 888138
public fun Char.equals(other: Char, ignoreCase: Boolean = false): Boolean {
    if (this == other) return true
    if (!ignoreCase) return false

    if (this.toUpperCase() == other.toUpperCase()) return true
    if (this.toLowerCase() == other.toLowerCase()) return true
    return false
}

}
//ID: 725967
package json.usage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val URL = "https://api.myjson.com/"
    private var irest: IRest? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        irest!!.getName().enqueue(object : Callback&lt;Data&gt; {
            override fun onResponse(call: Call&lt;Data&gt;, response: Response&lt;Data&gt;) {
                Log.e("&lt;&lt;&lt;&lt;", "&gt;&gt;&gt;&gt;")
            }

            override fun onFailure(call: Call&lt;Data&gt;?, t: Throwable?) {
                TODO("not implemented")
            }
        })
    }
}

}
//ID: 725967
package json.usage

class Data {
    var firstname: String? = null
}

}
//ID: 888494
data class Point(val x: Int, val y: Int) {
    companion object {
        val TAG = "Kotlin"
    }

    override fun equals(other: Any?): Boolean {
        Log.d(TAG, "overrided equals")
        if (other === this) return true
        if (other !is Point) return false
        return other.x == x &amp;&amp; other.y == y
    }
}

}
//ID: 888494
val n = null
Log.d(TAG, "null ${if (n is Any?) "is" else "isn't"} type of Any?")

}
//ID: 888531
class MainActivity : AppCompatActivity() {

    private val mUiHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val task = Runnable {
            for (item: Int in 0 until 5){
                TimeUnit.SECONDS.sleep(2);
                if (item == 2)
                    mUiHandler.post { Toast.makeText(applicationContext, "I am at the middle of background task", Toast.LENGTH_SHORT).show() }
            }
            mUiHandler.post { Toast.makeText(applicationContext, "Background task is completed", Toast.LENGTH_SHORT).show() }
        }

        //Thread(task).start()
        val myWorkerThread = MyWorkerThread("Hello")
        myWorkerThread.apply {
            start()
            prepareHandler()
            postTask(task)
            //quit()
        }
    }

    class MyWorkerThread(nameT: String): HandlerThread(nameT){
        private lateinit var instance: Handler

        fun postTask(task: Runnable){
            instance.post(task)
        }

        fun prepareHandler(){
            instance = Handler(this.looper)
        }
    }
}

}
//ID: 939878
android {
sourceSets {
   main.java.srcDirs += 'src/main/kotlin'
}
}

}
//ID: 888720
&lt;RelativeLayoutxmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity"&gt;

    &lt;android.support.v4.view.ViewPager
        android:id="@+id/viewPager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_above="@+id/viewPagerDots" /&gt;

}
//ID: 888720
    data class ViewPagerItem(
    val imageId: Int,
    val title: String,
    val description: String)

}
//ID: 889651
abstract class Attribute&lt;T&gt; (protected var value : T) {
    abstract fun add(otherValue : T)
}

class IntAttribute(value : Int) : Attribute&lt;Int&gt;(value) {
    override fun add(otherValue : Int) {
        value += otherValue
    }
}

class DoubleAttribute(value : Double) : Attribute&lt;Double&gt;(value) {
    override fun add(otherValue : Double) {
        value += otherValue
    }
}

}
//ID: 990845
open class AbstractMapper&lt;E : AbstractEntity, D : AbstractDto&gt; @Autowired constructor(

        protected val mapper: ModelMapper

) : EntityDtoMapper&lt;E, D&gt;

}
//ID: 991071
open class UserParamsDto(

        var user: Long? = null,
        var height: Int? = null,
        var weight: Double? = null,
        var gender: String? = null,
        var birthDate: LocalDateTime? = null

) : AbstractDto()

open class AbstractDto(

        var id: Long? = null,
        var created: LocalDateTime? = null,
        var updated: LocalDateTime? = null
)

}
//ID: 611568
class MainActivity : AppCompatActivity(), IMainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.front)

        setSupportActionBar(toolbar)

        toolbar.visibility = View.GONE

        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
                .replace(R.id.layout, AuthFragment())
                .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        when(id) {
            R.id.action_settings -&gt; return true
            R.id.action_logout -&gt; logout()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
        VKSdk.logout()
    }

    override fun callVkLogin() {
        VKSdk.login(
                this,
                VKScope.FRIENDS,
                VKScope.EMAIL,
                VKScope.WALL,
                VKScope.PHOTOS,
                VKScope.NOHTTPS,
                VKScope.MESSAGES,
                VKScope.DOCS,
                VKScope.GROUPS,
                VKScope.PAGES,
                VKScope.MESSAGES,
                VKScope.OFFLINE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        Timber.tag("VKAccessToken")
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, object : VKCallback&lt;VKAccessToken&gt; {
            override fun onResult(res: VKAccessToken) {
                Timber.d(res.accessToken)
            }
            override fun onError(error: VKError) {
                Timber.d("code: %s, message: %s", error.errorCode.toString(), error.errorMessage)
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun getContext(): Context {
        return this
    }
}

}
//ID: 611568
class AuthFragment : Fragment() {

    var v: IMainView? = null
    private val auth by bindView&lt;UIButton&gt;(R.id.auth)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = activity as IMainView
        return inflater?.inflate(R.layout.fragment_login, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        presenter.bindView(this)
        auth.onClick {
            v?.callVkLogin()
        }
    }

}

}
//ID: 942316
data class QuestionWithArrayValues(
        ...,
        val _value: List&lt;String&gt;?

) : Question


data class QuestionWithStringValues(

        ...,
        val _value: String?

) : Question

}
//ID: 942316
class QuestionsConverter{

    @TypeConverter
    fun fromQuestionsDbList(questions: List&lt;Question&gt;?): String? {
        if (questions == null) {
            return null
        }
        val gson = Gson()
        return try {
            val type = object : TypeToken&lt;List&lt;QuestionWithStringValues&gt;&gt;() { }.type
            gson.toJson(questions, type)
        } catch (exception: Exception) {
            val type = object : TypeToken&lt;List&lt;QuestionWithArrayValues&gt;&gt;() { }.type
            gson.toJson(questions, type)
        }
    }

    @TypeConverter
    fun toQuestionsDbList(questions: String?): List&lt;Question&gt;? {
        if (questions == null) {
            return null
        }
        val gson = Gson()
        return try {
            val type = object : TypeToken&lt;List&lt;QuestionWithStringValues&gt;&gt;() {}.type
            gson.fromJson&lt;List&lt;QuestionWithStringValues&gt;&gt;(questions, type)
        } catch (exception: Exception) {
            val type = object : TypeToken&lt;List&lt;QuestionWithArrayValues&gt;&gt;() {}.type
            gson.fromJson&lt;List&lt;QuestionWithArrayValues&gt;&gt;(questions, type)
        }
    }
}

}
//ID: 942316
@TypeConverters(QuestionsConverter::class)
abstract class AppDatabase : RoomDatabase() ...

}
//ID: 728780
class ParseTask(GLOBAL_POS: LatLng, GLOBAL_POS_PROVIDER: LatLng) : AsyncTask&lt;String, Void, String&gt;() {

    var weakActivity: WeakReference&lt;MapsActivity&gt;? = null

    override fun doInBackground(vararg p0: String?): String? {
        ...   
           println(GLOBAL_ADDRESS)
        ...

        return GLOBAL_ADDRESS
    }

    override fun onPostExecute(result: String?) {
        val activity = weakActivity!!.get()
        if (activity != null){
            activity.address.text = GLOBAL_ADDRESS.toString()
        }
    }
}

}
//ID: 728780
try {
    Thread.sleep(600)
} catch (e: Exception) {

}

}
//ID: 891420
    @Entity
    @Table(name = "news_type")
    data class NewsStatus (

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = 
    "status_seq")
    val status_id: Long = 1,

    val type: String? = "draft"

}
//ID: 776400
&lt;LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_height="match_parent"
    android:layout_width="match_parent"
    android:orientation="horizontal"&gt;
    &lt;fragment
          android:id="@+id/map"
          android:name="com.google.android.gms.maps.SupportMapFragment"
          android:layout_width="match_parent"
          android:layout_height="match_parent"/&gt;
&lt;/LinearLayout&gt;

}
//ID: 1052288
class Fragment1 : DaggerFragment(), View {

    @Inject
    lateinit var presenter: Presenter
    ...
}

}
//ID: 891593
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState) 

    sendCodeButton.setOnClickListener {
        viewModel.requestResetCode()
    }

    viewModel.codeRequestSuccess.observe(this, object  : Observer&lt;Boolean&gt; {
        override fun onChanged(t: Boolean?) {
            if (t != null &amp;&amp; t == true) {
                view?.findNavController()?.navigate(R.id.action_forgotPasswordFragment_to_resetCodeFragment)
            }
        }
    })
}

}
//ID: 891593
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    backArrow.setOnClickListener {
        view.findNavController().popBackStack()
    }
}

}
//ID: 729221
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    ext.kotlin_version = '1.1.3-2'
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.0.0-beta7'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        classpath "org.jetbrains.kotlin:kotlin-android-extensions:$kotlin_version"
        classpath 'com.google.gms:google-services:3.1.0'
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 729221
buildscript {
    repositories {
        maven { url 'https://maven.fabric.io/public' }
    }

    dependencies {
        classpath 'io.fabric.tools:gradle:1.+'
    }
}

apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'
apply plugin: 'io.fabric'
apply plugin: 'kotlin-kapt'

repositories {
    maven { url 'https://maven.fabric.io/public' }
}

android {
    compileSdkVersion 26
    buildToolsVersion '26.0.2'
    defaultConfig {
        applicationId "com.example.darkt.makeyouself"
        minSdkVersion 23
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }

    dataBinding {
        enabled = true
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}


dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlin_version"
    implementation 'com.android.support:appcompat-v7:26.0.1'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'

//    implementation 'com.google.firebase:firebase-auth:11.0.4'

    def firebaseLibVersion = '11.2.0'
    compile "com.google.firebase:firebase-core:$firebaseLibVersion"
    compile "com.google.firebase:firebase-auth:$firebaseLibVersion"
    compile "com.google.firebase:firebase-storage:$firebaseLibVersion"
    compile "com.google.firebase:firebase-database:$firebaseLibVersion"
    compile "com.google.firebase:firebase-messaging:$firebaseLibVersion"
    compile "com.google.firebase:firebase-config:$firebaseLibVersion"
    compile "com.google.android.gms:play-services-auth:$firebaseLibVersion"
    compile "com.google.android.gms:play-services-places:$firebaseLibVersion"

    compile 'com.kelvinapps:rxfirebase:0.0.15'

    compile 'com.firebaseui:firebase-ui-database:1.2.0'

    implementation 'com.android.support:design:26.0.2'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.0'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.0'
    kapt "com.android.databinding:compiler:3.0.0-beta7"

    compile('com.crashlytics.sdk.android:crashlytics:2.6.8@aar') {
        transitive = true
    }
}

apply plugin: 'com.google.gms.google-services'

}
//ID: 670104
interface Example {
    fun one()
    fun two()
}

}
//ID: 841877
class EntryItem constructor(var lable: String, var url: String, var username: String, var password: String, var icon: Array&lt;ByteArray&gt;?, var parrent: Group?) {


}

}
//ID: 729777
java.lang.IllegalArgumentException: Parameter specified as non-null is null: 
method kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull, parameter result
at ru.kbais.kotlionprj.MainMapActivity$ParserTask.onPostExecute(MainMapActivity.kt:0)
at ru.kbais.kotlionprj.MainMapActivity$ParserTask.onPostExecute(MainMapActivity.kt:125)

}
//ID: 842218
    private var locationManager: LocationManager? = null

val locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                te.append("n " + location.longitude + " " + location.latitude)
            }

            fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}

            fun onProviderEnabled(provider: String) {}

            fun onProviderDisabled(provider: String) {}
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener)
}
//ID: 730349
fun &lt;A, B&gt;List&lt;A&gt;.pmap(f: suspend (A) -&gt; B): List&lt;B&gt; = runBlocking {
    map { async(CommonPool) { f(it) } }.map { it.await() }
}

}
//ID: 730349
var i:Int = 0; //atomic
onEach { i++ } }
    .delayWhen{ i &gt; 9 }
    .map{ async(CommonPool){ e -&gt; { doSomeStuff(e) } } }
    .map { it.await() }
    .onEach { i-- }

}
//ID: 730432
class Sharedpref private constructor(context: Context) : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("main", Context.MODE_PRIVATE)

    fun getToken (): Boolean = sharedPreferences.getBoolean("USER_TOKEN_KEY", false)

    fun saveToken(token: Boolean) {
        sharedPreferences.edit().putBoolean("USER_TOKEN_KEY", token).apply()
    }

    fun prefClear() {
        sharedPreferences.edit().clear().apply()
    }

    fun saveName(name: String, value: String) {
        sharedPreferences.edit().putString(name, value).apply()
    }

    fun getName(name: String, value: String): String? = sharedPreferences.getString(name, value)

    companion object {
        private var INSTANCE: Sharedpref? = null

        fun getInstance(context: Context): Sharedpref {

            if (INSTANCE == null) {
                synchronized(Sharedpref::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Sharedpref(context)
                    }
                }
            }
            return INSTANCE!!
        }
    }
}

}
//ID: 944171
doAsync {
    val text = URL("url").readText()
    uiThread {
        ...
    }
}

}
//ID: 778306
inner class EnterCodeTextWatcher(private var prevFocus: EditText?, private var nextFocus: EditText?) : TextWatcher {

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (s.isNullOrEmpty() &amp;&amp; prevFocus != null){
            prevFocus?.requestFocus()
            prevFocus?.isCursorVisible = true
        }
        else if (!s.isNullOrEmpty() &amp;&amp; nextFocus != null){
            nextFocus?.requestFocus()
            nextFocus?.isCursorVisible = true
        }
    }

}

}
//ID: 893573
override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                          savedInstanceState: Bundle?): View? {
    val rootView: View = inflater.inflate(R.layout.fragment_file_list, container, false)

    return rootView
}

}
//ID: 893573
    fun itemClick(selectedItem: String?) {
            if (view == null) {
                Log.w(DEBUG_TAG, "View is NULL")
                return
            }
            val list = view!!.findViewById(R.id.list) as RecyclerView
            ...

}
//ID: 944683
    class MainActivity : AppCompatActivity(), TextWatcher {
        var a: Double = 0.0
        var b: Double = 0.0
        var c: Double = 0.0
        var x1: Double = 0.0
        var x2: Double = 0.0
        var flag: Boolean = false


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            aValue.addTextChangedListener(this)    
            bValue.addTextChangedListener(this)    
            cValue.addTextChangedListener(this)    
            x1Value.addTextChangedListener(this)    
            x2Value.addTextChangedListener(this) 

        }


        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


            try {
                a = aValue.text.toString().toDouble()
                b = bValue.text.toString().toDouble()
                c = cValue.text.toString().toDouble()
                x1 = x1Value.text.toString().toDouble()
                x2 = x2Value.text.toString().toDouble()

            } catch (e: Exception) {}



                if (a != 0.0 &amp;&amp; b != 0.0 &amp;&amp; c != 0.0) {
                    var D = b * b - 4 * a * c

                    var x1_number = ((-1)) * b + Math.sqrt(D) / 2 * a
                    var x2_number = ((-1)) * b - Math.sqrt(D) / 2 * a

                    if (D &lt; 0) {

                        isSol.text = "No real roots"
                    }
                    else{

                            x1Value.setText("$x1_number", TextView.BufferType.EDITABLE)
                            x2Value.setText("$x2_number", TextView.BufferType.EDITABLE)

                        }

                }




        }
    }
}

}
//ID: 994682
class CategoryBaseAdapter(list: ArrayList&lt;Items&gt;) : BaseAdapter (){
private val list = list
override fun getView(position:Int, convertView: View?, parent: ViewGroup?):View{
    // Inflate the custom view
    val inflater = parent?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val view = inflater.inflate(R.layout.shop_item,null)

    // Get the custom view widgets reference
    val tv = view.findViewById&lt;TextView&gt;(R.id.tv_name)
    val card = view.findViewById&lt;CardView&gt;(R.id.card_view)

    // Display color name on text view
    tv.text = list[position].name

    Picasso.get().load("http://first-gadget.ru/" + list[position].img).into(view.image)

    // Set a click listener for card view
    card.setOnClickListener{
        // Show selected color in a toast message
        Toast.makeText(parent.context,
                "Clicked : ${list[position].name}",Toast.LENGTH_SHORT).show()
    }

    // Finally, return the view
    return view
}

override fun getItem(position: Int): Any? {
    return list[position]
}

override fun getItemId(position: Int): Long {
    return position.toLong()
}

// Count the items
override fun getCount(): Int {
    return list.size
}

}
//ID: 945361
class ChargeReceiver : BroadcastReceiver() {
    companion object {
        const val TAG = "ChargeReceiver"
    }
    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG, "receiver called...")
    }
}

}
//ID: 894540
fun GwNBq(args: Array&lt;String&gt;) {
    var i1: Int? = null
    var i2: Int = i1.fixNull()
    print(i2)
}

@Suppress("UNCHECKED_CAST")
fun &lt;T : Number&gt; T?.fixNull(): T {
    if (this == null) return 0 as T
    return this
}

//ID: 995148
@JsonInclude(JsonInclude.Include.NON_EMPTY)
open class AbstractDto : Serializable {

    open var id: Long? = null
    open var created: LocalDateTime? = null
    open var updated: LocalDateTime? = null
}

open class UserDto : AbstractDto() {

    open var height: Int? = null
    open var weight: Double? = null
    open var gender: String? = null
    open var birthDate: LocalDateTime? = null
    open val contacts: List&lt;ContactDto&gt; = ArrayList()
}

}
//ID: 995148
@RestController
@RequestMapping("/user")
@ControllerImpl
class UserController : AbstractController&lt;User, UserDto, UserMapper, UserRepository, UserServiceImpl&gt;()

open class AbstractController&lt;
        E : AbstractEntity,
        D : AbstractDto,
        M : AbstractMapper&lt;E, D&gt;,
        R : CommonRepository&lt;E&gt;,
        S : AbstractService&lt;E, D, M, R&gt;
        &gt; : CommonController&lt;D&gt; {

    private val service: S? = null

    override fun save(dto: D?): ResponseEntity&lt;D&gt; = ResponseEntity.ok(service!!.save(dto)!!)

    override fun update(dto: D): ResponseEntity&lt;D&gt; = ResponseEntity.ok(service!!.update(dto)!!)

    override fun get(id: Long): ResponseEntity&lt;D&gt; = ResponseEntity.ok(service!!.get(id)!!)

    override fun getAll(pageable: Pageable): ResponseEntity&lt;Page&lt;D&gt;&gt; = ResponseEntity.ok(service!!.getAll(pageable))

    override fun delete(id: Long): ResponseEntity&lt;Boolean&gt; = ResponseEntity.ok(service!!.delete(id))
}

interface CommonController&lt;D : AbstractDto&gt; {

    @PostMapping
    fun save(dto: D?): ResponseEntity&lt;D&gt;

    @PutMapping
    fun update(dto: D): ResponseEntity&lt;D&gt;

    @GetMapping("/all")
    fun get(id: Long): ResponseEntity&lt;D&gt;

    @GetMapping
    fun getAll(pageable: Pageable): ResponseEntity&lt;Page&lt;D&gt;&gt;

    @DeleteMapping
    fun delete(id: Long): ResponseEntity&lt;Boolean&gt;
}

}
//ID: 1055428
class MainActivity : AppCompatActivity() {

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val liveData = DataController.getData()

    liveData?.observe(this, Observer&lt;String&gt;() {
        @Override
        fun onChanged(@Nullable value:String) {
            textView.text = value
        }
    });
}

fun refresh(view: View) {
    DataController.refresh()
}

object DataController {                                 //  это синглтон!
private val liveData = MutableLiveData&lt;String&gt;()

fun getData(): LiveData&lt;String&gt;? {
    return liveData
}

fun getCurrentDateTime(): Date {
    return Calendar.getInstance().time
}

fun refresh(){
    val date = getCurrentDateTime()
    val dateInString = date.toString()
    liveData.value = dateInString
}

}
//ID: 844942
class ListScreenFragment : android.support.v4.app.Fragment() {
    var pathDb = ""
    var passwordDb = ""
    var pathItem = ""
    var breadCrumpsArray: LinkedList&lt;GroupItem&gt;? = null
    var groupItem: GroupItem? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.list_activity, container, false)
        return view
    }
}

}
//ID: 995779
open class ContactDto: AbstractDto() {

    open var phone: String? = null
    open var email: String? = null
    open var user: Long? = null
}

}
//ID: 1056107
override fun getToken(email: String, password: String) {

    val client = OkHttpClient()

    val formBody = FormBody.Builder()
        .add("email", "artur.yashchenko@nure.ua")
        .add("password", "123123")
        .build()
    val request = Request.Builder()
        .url("http://192.168.0.101:8080/login")
        .post(formBody)
        .build()

    try {
        val response = client.newCall(request).execute()
        print(response.body)
        // Do something with the response.
    } catch (e: IOException) {
        e.printStackTrace()
    }

}

&lt;?xml version="1.0" encoding="utf-8"?&gt;

}
//ID: 895902
data class GroupResponse&lt;T&gt;(
        @SerializedName("response")
        @Expose
        val response: Response&lt;T&gt;?
)

data class Response&lt;T&gt;(
        val count: Int,
        val items: List&lt;T&gt;
)

}
//ID: 674243
interface MyRunnable{
    fun run()
}

class MyThread(runnable : MyRunnable){    
}

fun test(){
    Thread({})     // All Alright

    MyThread({})   //Exception. Type mismatch &lt;&lt;-- Why ?
}

}
//ID: 781343
{ a : Int?, b : Int? -&gt; a!! + b!! }
{ a : Int?, b : Int? -&gt; a?.plus(b!!) }

}
//ID: 781343
{ a : Int?, b : Int? -&gt; when {
    a == null &amp;&amp; b == null -&gt; 0
    a == null -&gt; b
    b == null -&gt; a
    else -&gt; a + b
}}

}
//ID: 734424
inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var city:TextView = name_city as TextView
        var latitude_:TextView = latitude as TextView
        var longitude_:TextView = longitude as TextView
    }

}
//ID: 948456
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(
    tableName = "article_table", indices = [Index(
        value = ["title"],
        unique = true
    )]
)
data class Article(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "source_name") var sourceName: String,
    @ColumnInfo(name = "author_name") var authorName: String,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "url") var url: String,
    @ColumnInfo(name = "image_url") var imageUrl: String,
    @ColumnInfo(name = "publish_date") var publishDate: String

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Article

        if (sourceName != other.sourceName) return false
        if (authorName != other.authorName) return false
        if (title != other.title) return false
        if (url != other.url) return false
        if (imageUrl != other.imageUrl) return false
        if (publishDate != other.publishDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = sourceName.hashCode()
        result = 31 * result + authorName.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + imageUrl.hashCode()
        result = 31 * result + publishDate.hashCode()
        return result
    }
}

}
//ID: 998158
class CardsViewModel : ViewModel() {
    val selected = MutableLiveData&lt;PaymentCard&gt;()

    companion object {
        @JvmStatic
        var keyId = 0
    }
}

}
//ID: 998748
    &lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;LinearLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity"&gt;

    &lt;fragment
            android:name="com.e.myapplication9.MainFragment"
            android:id="@+id/fragment"
            android:layout_width="match_parent"
            android:layout_height="match_parent"/&gt;
&lt;/LinearLayout&gt;

}
//ID: 998748
package com.e.myapplication9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    var arr: Array&lt;String&gt; = arrayOf("Example")
    val mainFragment = MainFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainFragment.arguments?.putStringArray("arr",arr)
    }
}

}
//ID: 898513
fun calculateEvenDigits(input:String): Int{
    var b = 0
    for(i in input){
        if (i.toInt() % 2 == 0){
            b+=i.toInt()
            println("i = $i, b = $b")
        }
    }
    return b
}
fun hsPnj(args: Array&lt;String&gt;) {
    val a : String? = readLine()
    if(a!=null){
        print(calculateEvenDigits(a.toString()))
    }
}

//ID: 848138
&lt;ImageView
    android:id="@+id/ivLMA"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:scaleType="centerCrop"
    android:src="@drawable/ic_eye"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent" /&gt;

}
//ID: 898665
buildscript {
    // Consider moving these values to `gradle.properties`
    ext.kotlin_version = '1.3.0-rc-146'
    ext.kotlin_gradle_plugin_version = '1.3.0-rc-198'
    ext.kotlinx_coroutines = '1.0.0-RC1'

    repositories {
        maven { url "https://kotlin.bintray.com/kotlin-eap" }
        mavenCentral()
        jcenter()
        google()
    }
    dependencies {
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.2.51"
    }
}

plugins {
    id 'org.jetbrains.kotlin.jvm' version "1.1.51"
}

apply plugin: 'idea'
apply plugin: 'application'
group 'by.kotlin'
version '1.0-SNAPSHOT'

mainClassName = 'MainKt'

repositories {
    maven { url "https://kotlin.bintray.com/kotlin-eap" }
    mavenCentral()
    jcenter()
    google()
}

dependencies {
    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    implementation "org.jetbrains.kotlin:kotlin-stdlib-common:$kotlin_version"
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinx_coroutines"   
}

compileKotlin {
    kotlinOptions.jvmTarget = "1.8"
}
compileTestKotlin {
    kotlinOptions.jvmTarget = "1.8"
}

}
//ID: 1059048
class TestReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if ((intent != null) and (context != null)) {
            if (intent?.action.equals(MainActivity.ACTION_ID)) {
                Toast.makeText(context, intent?.getStringExtra("test_message"), Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}

}
//ID: 1059378
var res = 0
var (c) = readLine()!!.split(' ').map(String::toInt)
val num = arrayListOf&lt;Int&gt;()
while (c != 0) {
    num.add(c % 10)
    c /= 10 
}
for (i in num) {
    if (i % 3 == 0) {
        res += i
    }
}
print(res)

}
//ID: 783712
class Sort (vararg orders: SortOrder) {

var orders: List&lt;SortOrder&gt; = ArrayList()

companion object {
    fun sort(vararg orders: SortOrder): Sort {
        return Sort(*orders)
    }
}

init {
    this.orders = Arrays.asList(*orders)
}

override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || javaClass != other.javaClass) return false
    val sort = other as Sort?

    return orders == sort!!.orders
}

}
//ID: 1059516
class NewsViewModel(
repository: NewsRepository,
query: String
) : BaseViewModel() {

val article = MutableLiveData&lt;Article&gt;()

fun setArticle(article: Article) {
    this.article.value = article
}

}

}
//ID: 1059516
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModel.article.observe(viewLifecycleOwner, Observer {
        requireActivity().loadImage(it.urlToImage ?: "", collapsingImage)
        titleTextView.text = it.title
    })
}

}
//ID: 848966
@Throws(Exception::class)
private fun freeDomainsByDictionary(_dictName: String, zone: String)

}
//ID: 1000317
class TasksFragment : Fragment() {

private lateinit var ownViewModel: OwnViewModel

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    ownViewModel = ViewModelProviders.of(this).get(OwnViewModel::class.java)
}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_tasks_list, container, false)
        val recyclerView = view.findViewById&lt;RecyclerView&gt;(R.id.listTasks)

        // Set the adapter
        val adapter = MyTasksRecyclerViewAdapter(listener, context!!)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)


        ownViewModel.allTasks.observe(viewLifecycleOwner, Observer { tasksList -&gt;
            tasksList?.let {

                adapter.setTaskList(it)
            }
        })

        return view
    }
}

}
//ID: 1060320
val result = async {
    goToRemoteService(request)
}
try {
    result.await()
} catch (e: Exception) {
    e.sout 
}

}
//ID: 737380
class GalleryAdapter(ctx: Context, private val imagesPath: List&lt;Bitmap&gt;) : RecyclerView.Adapter&lt;GalleryAdapter.ItemViewHolder&gt;() {
    private val mLayoutInflater: LayoutInflater = LayoutInflater.from(ctx)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder {
        return ItemViewHolder(mLayoutInflater.inflate(R.layout.item_image, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder?, position: Int) {
        holder?.imgView?.setImageBitmap(imagesPath[position])
    }

    override fun getItemCount(): Int {
        return imagesPath.size
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgView: ImageView = view.findViewById(R.id.imageItem)
    }
} 

}
//ID: 951529
import android.arch.persistence.room.*
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.NonNull

@Entity(tableName = "Recipe")
data class Recipe(
        @PrimaryKey @NonNull @ColumnInfo(name = "id")
        var id: Int = 0,
        @ColumnInfo(name = "title")
        var title: String = "",
        @ColumnInfo(name = "isBookmarked")
        var isBookmarked: Boolean = false,
        @Ignore
        var ingredients: MutableList&lt;Ingredients&gt; = ArrayList(),
        @Ignore
        var insctructions: MutableList&lt;Instruction&gt; = ArrayList()
) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readString(), parcel.readInt() == 0, mutableListOf&lt;Ingredients&gt;().apply {
        parcel.readArrayList(Ingredients::class.java.classLoader)
    })

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeInt(if (isBookmarked) 1 else 0)
        parcel.writeList(ingredients)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator&lt;Recipe&gt; = object : Parcelable.Creator&lt;Recipe&gt; {
            override fun createFromParcel(parcel: Parcel): Recipe {
                return Recipe(parcel)
            }

            override fun newArray(size: Int): Array&lt;Recipe?&gt; {
                return arrayOfNulls(size)
            }
        }
    }
}

}
//ID: 1000690
data class Note(/*val id: Int,*/
val name:String,
val text: String,
val date: String) {

}
//ID: 951860
package com.greenprojectinfo.ideabank

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTA = findViewById(R.id.btnTA)
        btnTA.setOnClickListener {
            val intent = Intent(this, Donation::class.java)
            startActivity(intent)
        }
        val btnDon = findViewById(R.id.btnDon)
        btnDon.setOnClickListener {
            val intent = Intent(this, TakingAway::class.java)
            startActivity(intent)
        }
        val btnMEN = findViewById(R.id.btnMEN)
        btnMEN.setOnClickListener {
            val intent = Intent(this, Mentor::class.java)
            startActivity(intent)

        }

        val btnMon = findViewById(R.id.btnMon)
        btnMon.setOnClickListener {
            val intent = Intent(this, Fond::class.java)
            startActivity(intent)

        }
        val btnSql = findViewById(R.id.btnSql)
        btnSql.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)

        }

    }

}
//ID: 737866
Implement Shop.getSetOfProductsOrderedByEveryCustomer() using fold.



data class Shop(val name: String, val customers: List&lt;Customer&gt;)

data class Customer(val name: String, val city: City, val orders: List&lt;Order&gt;) {
override fun toString() = "$name from ${city.name}"
}

data class Order(val products: List&lt;Product&gt;, val isDelivered: Boolean)

data class Product(val name: String, val price: Double) {
override fun toString() = "'$name' for $price"
}

}
//ID: 737866
fun Shop.getSetOfProductsOrderedByEveryCustomer(): Set&lt;Product&gt; {
val allProducts = customers.flatMap { it.orders.flatMap { it.products }}.toSet()
return customers.fold(allProducts, {
    orderedByAll, customer -&gt;
    orderedByAll.intersect(customer.orders.flatMap { it.products }.toSet())
})
}

}
//ID: 1108298
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main()
    }

    private fun pmGHz() {
        val frag1 = MainFragment()
        val frag2 = SinFrag()

        reb_fr_main.setOnClickListener {frChange(frag2)}
        reb_fr_sin.setOnClickListener {frChange(frag1)}

    }

    private fun frChange(fragment: Fragment) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmento, fragment)
            fragmentTransaction.commit()
    }
}

//ID: 952173
package com.greenprojectinfo.ideabank

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTA = findViewById(R.id.btnTA)
        btnTA.setOnClickListener {
            val intent = Intent(this, Donation::class.java)
            startActivity(intent)
        }
        val btnDon = findViewById(R.id.btnDon)
        btnDon.setOnClickListener {
            val intent = Intent(this, TakingAway::class.java)
            startActivity(intent)
        }
        val btnMEN = findViewById(R.id.btnMEN)
        btnMEN.setOnClickListener {
            val intent = Intent(this, Mentor::class.java)
            startActivity(intent)

        }

        val btnMon = findViewById(R.id.btnMon)
        btnMon.setOnClickListener {
            val intent = Intent(this, Fond::class.java)
            startActivity(intent)

        }
        val btnBtn = findViewById(R.id.btnBtn)
        btnBtn.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)

        }

    }



    }

}
//ID: 952375
val intent = Intent() //Expecting an element
intent.action = packageName.plus(".granted")
intent.putExtra(Constants.GRANT_NAME, Manifest.permission.CAMERA)
sendBroadcast(intent)﻿//Unexpected tokens (use ';' to separate expressions on the same line)

}
//ID: 850992
class EntryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.entry_activity)
        val host = supportFragmentManager.findFragmentById(R.id.host_fragment) as NavHostFragment ?: return
        val navController = host.navController
        NavigationUI.setupActionBarWithNavController(this,navController,container)
        NavigationUI.setupWithNavController(bottomNavigationView,navController)
        NavigationUI.setupWithNavController(drawerNavigation,navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.host_fragment).navController.navigateUp()
    }
}

}
//ID: 902138
class Adapter(val itemsList: ArrayList&lt;Template&gt;) : RecyclerView.Adapter&lt;Adapter.ViewHolder&gt;()  {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_layout, parent, false)
      val h : ViewHolder = ViewHolder(v)
      h.itemView.setOnClickListener(View.OnClickListener { })
      return ViewHolder(v)
  }

  override fun getItemCount(): Int {
      return itemsList.size
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val tamplates: Template = itemsList[position]
      holder?.title1?.text = tamplates.getTitle()
      holder?.content1?.text = tamplates.getBody()
      holder.itemView.setOnClickListener(View.OnClickListener {
          Log.d("CLIIICK " ,  tamplates.getId().toString())
      })

  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      val title1 = itemView.findViewById(R.id.title) as TextView
      val content1 = itemView.findViewById(R.id.content) as TextView

  }

}

}
//ID: 1002174
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutmanger: LinearLayoutManager
    private lateinit var myAdapter: MyAdapter
    val arrayL: ArrayList&lt;String&gt; = arrayListOf("one","two","three")

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item -&gt;
        when (item.itemId) {
            R.id.navigation_home -&gt; {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -&gt; {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -&gt; {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        recyclerView = findViewById(R.id.recyclerView_news)
        layoutmanger = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        myAdapter = MyAdapter(arrayL)

        recyclerView.adapter = myAdapter
    }
}

}
//ID: 1002174
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val arrayData:ArrayList&lt;String&gt;): RecyclerView.Adapter&lt;MyAdapter.ItemViewHolder&gt;() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val context = parent.context
        val layoutIdForListItem = R.layout.test_adapter 
        val inflater = LayoutInflater.from(context)                                                                   
        val view = inflater.inflate(layoutIdForListItem, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setData(arrayData[position])
    }

    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.text)
        fun setData(str: String){
            title.text = str
        }

    }

}

}
//ID: 1062561
var count: Long
    get() = if (чтото) field else 0
    set(value) {if (value&gt;0) field = value}

}
//ID: 1062578
import java.util.* 

val scan = Scanner(System.`in`) 

fun NmPbT(args: Array&lt;String&gt;) { 
var sum = 0 
val n = scan.nextInt() 
for (i in 1..n) sum += i 
print(sum) 
}

//ID: 1110181
    @Entity(tableName = "tips_of_the_day")
data class Name(
    @ColumnInfo(name = "name")
    var name: String?
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}

}
//ID: 739858
class UserAdapter (private var users: ArrayList&lt;User&gt;) : RecyclerView.Adapter&lt;UserAdapter.UserViewHolder&gt;() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.user_card_view, parent, false)
        return UserViewHolder(v)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder?, position: Int) {
        holder?.let {
            it.bind(users[position])
            it.delete.setOnClickListener(View.OnClickListener {
                users.removeAt(position)
                notifyItemRangeRemoved(position, 1)
                notifyItemRangeChanged(position, itemCount)
        })
        }
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userId = itemView.findViewById&lt;TextView&gt;(R.id.user_card_id)
        var userFio = itemView.findViewById&lt;TextView&gt;(R.id.user_card_fio)
        val edit = itemView.findViewById&lt;Button&gt;(R.id.user_card_replace_button)
        val delete = itemView.findViewById&lt;Button&gt;(R.id.user_card_delete_button)
        lateinit var user: User

        fun bind(user: User) {
            this.user = user
            userFio.text = user.firstName + " " + user.lastName
            userId.text = "id = ${user.id}"
        }
    }

    class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
            outRect?.apply {
                bottom = space
                top = space
            }
        }
    }
}

}
//ID: 903875
class AdapterC (val countryList: Collection&lt;Countries&gt;): RecyclerView.Adapter&lt;AdapterC.ViewHolder&gt;() {
override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {

    val v = LayoutInflater.from(p0?.context).inflate(R.layout.country_list,p0,false)

    return  ViewHolder(v)
}

override fun getItemCount(): Int {

    return countryList.size
}

override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
    val country : Countries=countryList.toTypedArray()[p1]
    p0?.textViewName.text=country.name


}


class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val textViewName= itemView.findViewById(R.id.TextViewCountry) as TextView
}}

}
//ID: 1004340
 var files: ArrayList&lt;File&gt; = arrayListOf(File(null, null))

 override fun onBindViewHolder(h: RecyclerView.ViewHolder, position: Int) {
    val holder = h as DocumentFilesViewHolder

    if (position != files.size - 1) {
        holder.file.setImageBitmap(files[position].bitmap)

        holder.cancel.setOnClickListener {
            deleteFileCallback!!(position)
        }
    } else {
        holder.file.setOnClickListener {
            addFileCallback!!(position)
        }
    }
}

}
//ID: 1004586
fun ageDescription(age: Int): String {
    return when {
        age / 10 % 10 == 1 -&gt; "$age лет"
        age % 10 == 1 -&gt; "$age год"
        age / 10 % 10 == 9 -&gt; "$age лет"
        else -&gt; "$age года"
    }
}

}
//ID: 905147
fun sZACg() = runBlocking&lt;Unit&gt; {
    launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
        println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
        delay(500)
        println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
    }  
}

//ID: 742438
@Entity(tableName = "monthly_budget")
data class MonthlyBudget(@ColumnInfo(name = "budget_for_month")var budgetForMonth: Int,
                         @ColumnInfo(name = "date" ) var date: Date = Date()) {

@ColumnInfo(name = "id")
@PrimaryKey(autoGenerate = true)
var id: Long = 0
}

}
//ID: 790913
fun &lt;T&gt; List&lt;T&gt;.toPairs(): List&lt;List&lt;T&gt;&gt; =
    mapIndexed { index, t -&gt; Pair(index, t) }
            .groupBy { it.first / 2 }
            .map { it.value.map { it.second } }

}
//ID: 856802
class Group(val topic: String,
                    val name: String,
                    val lastMessage: message?,
                    val id: Int) 
class Message(val id: String,
                   val updatedAt: String,
                   val insertedAt: String,
                   val body: String?,
                   val authorId: String)

}
//ID: 857144
class Authentication: Fragment(){

private lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.auth,container,false)



}
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    authBtn.setOnClickListener{
        progressBar.setVisibility(View.VISIBLE)
        edt_number.setEnabled(false)
        authBtn.setEnabled(false)

        val phone = edt_number.text.toString()

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone, // Phone number to verify
                60,             // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                activity!!,           // Activity (for callback binding)
                mCallbacks)
        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential?) {

            }

            override fun onVerificationFailed(p0: FirebaseException?) { TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
    }
}

}
//ID: 958413
data class LoginResponse(
    val accost: String? = null,
    val name: String? = null,
    val fullname: String? = null,
)

}
//ID: 744367
data class Row(val row:List&lt;User&gt;)
data class User(val name: String, val address: String)

}
//ID: 857859
open class App : Application(){

        open lateinit var component: ApplicationComponent

        override fun onCreate() {
            super.onCreate()
            createDaggerComponentGraph()

        }

        open fun getAppComponent():ApplicationComponent{
            return component
        }

        open fun createDaggerComponentGraph(){
            component = DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(this))
                    .build()
        }

    }

}
//ID: 857859
class MainActivity : AppCompatActivity(){
@Inject lateinit var mFragmentArticles : ArticlesFragment

override fun onCreate(savedInstanceState: Bundle?) {
    (application as? App)?.component?.inject(this)
    super.onCreate(savedInstanceState)
} }

}
//ID: 858095
val IMG_REQUEST = 1
lateinit var bitmap: Bitmap

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.add_event)

    val intent = Intent()
    intent.type = "image/*"
    intent.action = Intent.ACTION_GET_CONTENT
    startActivityForResult(intent, IMG_REQUEST)

override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    super.onActivityResult(requestCode, resultCode, data)
    if (resultCode == IMG_REQUEST &amp;&amp; requestCode == Activity.RESULT_OK) {

        val path: Uri = data.data

        try {
            bitmap = MediaStore.Images.Media.getBitmap(contentResolver, path)
            ivMyPic.setImageBitmap(bitmap)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

}
//ID: 959364
override fun onItemClick(deepLink: String, position: Int) {
             //...

            val item: Center? = items.asSequence().filterIndexed { index, any -&gt;
                    index == position &amp;&amp; any is Center
                }.map {
                    it as Center
                }.firstOrNull()

            //...
}

}
//ID: 792669
@Entity
class User (
    @PrimaryKey val deviceId: Int,
    val name: String,
    val password: String
)

@Dao
interface UserDao {
    @Query("SELECT * FROM name")
    fun getAll(): List&lt;User&gt;

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDao
}

}
//ID: 1008038
override fun onItemClick(view: View?, position: Int, drawerItem: IDrawerItem&lt;*&gt;): Boolean {

                if (result.drawerItems[position-1].isEnabled){
                    result.closeDrawer()
                    return true
                }

                var fragment: Fragment? = null
                when (position) {
                    1 -&gt; {
                        fragment = TestFragment1()
                    }
                    2 -&gt; {
                        fragment = TestFragment2()
                    }
                }

                doAsync {
                    TimeUnit.MILLISECONDS.sleep(1000)
                    if (fragment != null) {
                        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.frame_container, fragment)
                        fragmentTransaction.commit()
                    }
                }

                return false
            }

}
//ID: 1008613
// Конструктор
public class TESTCLSS constructor()
{
    for (i in 1..20)
    {
        print(i);
    }
}

}
//ID: 515359
@Entity
@Table(name = "t_payment")
data class PaymentEntity(
        **@ManyToOne(cascade = CascadeType.DETACH)**
        @JoinColumn(name = "user_id", nullable = false)
        var user: User? = null) {
}

}
//ID: 961621
class PacksAdapter : RecyclerView.Adapter&lt;PacksAdapter.ViewHolder&gt;() {

    var onPackClickListener: OnPackClickListener? = null
        set(value) {
            field = value
        }

    var packList: ArrayList&lt;Animal&gt; = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_animalpack_4, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(packList[0], context)
    }


 class ViewHolder(view: View) : RecyclerView.ViewHolder(view),View.OnClickListener {
        ...
        fun bind(pack: AnimalPackFull, context: Context) {
            ...
        }

        override fun onClick(v: View?) {
            onPackClickListener.
        }
    }

    interface OnPackClickListener {
        fun onPackClicked(packId: Int)
    }
}

}
//ID: 961621
 override fun onClick(v: View?) {
                onPackClickListener.
            }

}
//ID: 860994
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;FrameLayout
        android:id="@+id/mainContainer"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/&gt;

&lt;/LinearLayout&gt;

}
//ID: 1117761
@Entity
data class ObjectEntity(
    val active: Boolean,
    val comment: String?,
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val folderId: Long,
    val imei: String?,
    val name: String?,
    @TypeConverters(ObjectSettingsTypeConvert::class)
    var settings: List&lt;ObjectSetting&gt;? ,
    val stateNumber: String?,
    val typeId: Long
)

data class ObjectSetting(
    val id: Long,
    val name: String?,
    val type: Int
)

}
//ID: 861212
private fun getViewPagerListener(): OnPageChangeListener {
    return object : OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            if (position + positionOffset  == 0f) { // first page
                println("LEFT")
            } else if (position + positionOffset == 5f) { // 5th, the last page
                println("RIGHT")
            }
        }

        override fun onPageSelected(position: Int) {
        }

        override fun onPageScrollStateChanged(state: Int) {
        }
    }
}

}
//ID: 1118067
data class Task(
val id: Int,
val number: String,
val time: Long?,
val r: List&lt;RaRa&gt;,
val status: String

}
//ID: 1119160
..\Playground\:16:1: error: expecting a top level declaration
print(r);
^
..\Playground\:16:6: error: expecting a top level declaration
print(r);
     ^
..\Playground\:16:7: error: expecting a top level declaration
print(r);
      ^
..\Playground\:16:8: error: expecting a top level declaration
print(r);
       ^
..\Playground\:3:29: error: type mismatch: inferred type is Char but String was expected
    var r = arrayOf&lt;String&gt;(t[0]);
                            ^
..\Playground\:6:14: error: for-loop range must have an 'iterator()' method
    for(i in t[t.last - 1]){
             ^
..\Playground\:6:16: error: type mismatch: inferred type is Char but Int was expected
    for(i in t[t.last - 1]){
               ^
..\Playground\:6:18: error: function invocation 'last()' expected
    for(i in t[t.last - 1]){
                 ^
..\Playground\:8:15: error: unresolved reference: add
            r.add([c, i]);
              ^
..\Playground\:8:19: error: unsupported [Collection literals outside of annotations]
            r.add([c, i]);
                  ^
..\Playground\:13:7: error: unresolved reference: add
    r.add(c);
      ^

}
//ID: 1119103
data class Chat(var firstMessage: List&lt;String&gt;, var secondMessage: List&lt;String&gt;) {

    constructor(): this(emptyList(), emptyList())
}

}
//ID: 749495
buildscript {
    ext.kotlin_version = '1.1.60'
    repositories {
        jcenter()
        google()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.0.1'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

repositories {
    mavenCentral()
}

allprojects {
    repositories {
        jcenter()
        google()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 797070
@Module
class ActivityModule {

    @Provides
    @NonNull
    @Singleton
    fun getActivitys() : TestClass = TestClass()
}

@Module
class AppModule(val context: Context) {

    @Provides
    @Singleton
    fun getContexts() : Context = context
}

@Component(modules = [(ActivityModule::class), (AppModule::class)])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}

}
//ID: 797070
class MainActivity : AppCompatActivity() {

    @Inject lateinit var testClass: TestClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App().getComponent().inject(this)

        Log.d("MAIN", testClass.getString())
    }
}

}
//ID: 797070
Error:(25, 17) Unresolved reference: DaggerAppComponent
Error:Execution failed for task ':app:compileDebugKotlin'.
&gt; Compilation error. See log for more details 

}
//ID: 797070
apply plugin: 'com.android.application'

apply plugin: 'kotlin-android'

apply plugin: 'kotlin-android-extensions'

android {
    compileSdkVersion 26
    defaultConfig {
        applicationId "info.android_developer_community.dagerss"
        minSdkVersion 21
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation"org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlin_version"
    implementation 'com.android.support:appcompat-v7:26.1.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'


    implementation "com.google.dagger:dagger:2.14"
    implementation "com.google.dagger:dagger-android:2.14"
    implementation "com.google.dagger:dagger-android-support:2.14"
    kapt "com.google.dagger:dagger-android-processor:2.14"
    kapt "com.google.dagger:dagger-compiler:2.14"
    kapt "com.google.dagger:dagger-android-support:2.14"
}

}
//ID: 964250
class UserWithKeys(var id: Int, var name: String, var phone: String, val email: String, val sum: Int, val score: Int,
                   var level: Int, var publickey: String, var privatekey: String, var refererLink: String, var favorites: Array&lt;Int&gt;)

}
//ID: 691446
fun Activity?.addFragment(fragment: Fragment?) {
    if (this == null || fragment == null) return
    try {
        fragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.createTagName())
                .addToBackStack(null).commit()
    } catch (ignored: IllegalStateException) {
    }
}

}
//ID: 1119845
var t = object : CountDownTimer (10000, 1000)

override fun onTick(millisUntilFinished: Long) {
            t.cancle
        }

override fun onFinish() {}

}
//ID: 797735
package com.example.android.justjava


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

        fun submitOrder(view: View) {
        int numberOfCoffees 2
        display(numberOfCoffees)
        displayPrice(numberOfCoffees * 5)
    }


    fun display(number: Int) {
        val quantityTextView = findViewById&lt;View&gt;(R.id.quantity_text_view) as TextView
        quantityTextView.text = ("" + number)
    }


    private fun displayPrice(number: Int) {
        val priceTextView = findViewById&lt;View&gt;(R.id.price_text_view) as TextView
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number))
    }
         }

}
//ID: 1072971
var inputStream: InputStream = File("app/src/main/assets/Kotlin1.in").inputStream();
var lineList = mutableListOf&lt;String&gt;()
inputStream.bufferedReader().useLines { lines -&gt; lines.forEach { lineList.add(it) } }

}
//ID: 1120195
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        main()
    }

    private fun gvzoP(){
        b_del.setOnClickListener {view2}
        Log.d("fwt","Fgdgdg")
    }
}

//ID: 1120232
    val navController = findNavController(R.id.nav_host_fragment)
    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_tizim), drawerLayout)
    setupActionBarWithNavController(navController, appBarConfiguration)
    navView.setupWithNavController(navController)

}
//ID: 864102
class WorkoutDetailFragment : Fragment() {
    private var workoutId: Long = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle): View? {
        return inflater.inflate(R.layout.fragment_workout_detail, container, false)
    }

    override fun onStart() {
        super.onStart()
        val view = view
        if (view != null) {
            val title = view.findViewById&lt;TextView&gt;(R.id.textTitle)
            val workout = Workout.workouts[workoutId.toInt()]
            title.text = workout.name
            val description = view.findViewById&lt;View&gt;(R.id.textDescription) as TextView
            description.text = workout.description
        }
    }

    fun setWorkout(id: Long) {
        this.workoutId = id
    }
}

}
//ID: 1013205
    &lt;?xml version="1.0" encoding="utf-8"?&gt;
    &lt;FrameLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:id="@+id/activityContainer"
        android:layout_width="match_parent"
        android:layout_height="match_parent"&gt;

        &lt;fragment
            android:id="@+id/nav_host_fragment"
            android:name="androidx.navigation.fragment.NavHostFragment"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:defaultNavHost="true"
            app:navGraph="@navigation/main_graph" /&gt;

    &lt;/FrameLayout&gt;

}
//ID: 1013350
class OnBoardFragment : Fragment() {

    var pageTitle : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_on_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        on_board_text_view.text = pageTitle

    }

    fun setTitle(title : String) {
        pageTitle = title

    }
}

}
//ID: 1120833
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme2)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_cash_box, R.id.navigation_statistics, R.id.navigation_receipt, R.id.navigation_reports))
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}

}
//ID: 1120833
private val TAB_TITLES = arrayOf(
    R.string.tab_statistics,
    R.string.tab_revenue_by_the_hour
)
class CBSPAdapterTab(private val context: FragmentActivity?, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        val bundle1 = Bundle()
        when (position) {
            0 -&gt; {
                fragment = TabStatisticsFragment()
                fragment.setArguments(bundle1)
                return fragment
            }
            1 -&gt; {
                fragment = TabRevenueByTheHourFragment()
                fragment.setArguments(bundle1)
                return fragment
            }
            else-&gt; null!!
        }
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return context?.resources?.getString(TAB_TITLES[position])
    }
    override fun getCount(): Int {
        return 2
    }

}
//ID: 1120833
class CashPlaceholderFragment : Fragment() {
    private lateinit var pageViewModel: CashPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(CashPageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }
    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        @JvmStatic
        fun newInstance(sectionNumber: Int): CashPlaceholderFragment {
            return CashPlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}

}
//ID: 965845
try {
       Thread.sleep(timeout)
    } catch (ex: InterruptedException) {
       return@Runnable
}

}
//ID: 965845
try {
       Thread.sleep(timeout)
    } catch (ex: InterruptedException) {
       thread.interrupt()
}

}
//ID: 1121061
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when {
            requestCode == 1 &amp;&amp; resultCode == Activity.RESULT_OK -&gt; {
                    val content = data.data?.let { uri -&gt; contentResolver.openInputStream(uri).use { it!!.readBytes()} }
                    val encodedFileString = content!!.toString(StandardCharsets.UTF_8)
            }
        }

}

}
//ID: 1074087
data class Photo(val id: String? = null,
                 val color: String? = null,
                 val created_at: String? = null,
                 val description: String? = null,
                 val downloads: Int? = null): Serializable

}
//ID: 1074087
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.fullscreen_activity)

    val bundle = intent.extras
}

}
//ID: 751593
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSend.setOnClickListener({
            val numberText = R.string.phoneNumber
            val messageText = "Hello, member!"
            SmsManager.getDefault()
                    .sendTextMessage(numberText.toString(), null, messageText.toString(), null, null)

        })
    }
}

}
//ID: 1074359
fun IVoMC() {
val a = 0.000001
println(a)

//ID: 1121625
try {
            val db = AppDatabase.getDatabase(this)
            val settingsDao = db.settingsDao()
            AsyncTask.execute {
                try {
                    val settings = settingsDao.get
                    if (settings.token == "") {
                        val temp = Settings()
                        temp.apiUrl = settings.apiUrl
                        settingsDao.insert(temp)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    settingsDao.insert(Settings(apiUrl = ConstantsApiUrl.BaseURL))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

}
//ID: 1122037
uildscript {
    ext.nav_version = '1.0.0'
    repositories {
        google()
        mavenCentral()
        maven { url "https://dl.bintray.com/drummer-aidan/maven/" }
        jcenter()
        maven { url 'https://jitpack.io' }
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:4.1.0-alpha08'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72"
        classpath 'com.google.gms:google-services:4.3.3'
        classpath 'com.google.firebase:firebase-crashlytics-gradle:2.0.0'
    }

}

allprojects {
    repositories {
        google()
        maven { url 'https://jitpack.io' }
        maven { url "https://dl.bintray.com/drummer-aidan/maven/" }
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 1122310
try {
            val items = listOf&lt;String&gt;()
            val test = items[20]
            Crashlytics.log("Здесь")
        }
        catch (e : Exception){
            Crashlytics.log(e)
        }

}
//ID: 1075540
class MainActivity : AppCompatActivity() {
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fifth)
    val recyclerView = findViewById&lt;RecyclerView&gt;(R.id.recycler_view)
    val recyclerAdapter = RecyclerAdapter()
    recyclerView.adapter = recyclerAdapter
    for (i in 1..100){
    recyclerAdapter.value.add(i) //Здесь неправильно
    }
  }
}

}
//ID: 967516
import com.arellomobile.mvp.MvpView

interface MainView : MvpView {

    fun test()

}

}
//ID: 1123121
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.example.stat.R
import ru.example.stat.presenters.EventPresenter
import ru.example.stat.views.EventView


class EventFragment : MvpAppCompatFragment(R.layout.fragment_events), EventView{

    private val eventPresenter by moxyPresenter { EventPresenter() }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_events, container, false)
}

}
//ID: 1076563
fun NnnwK() {
    var s = 0.0

    for (i in 1..10){

        s+=Math.pow(-1.0, i.toDouble())*(i+1) / (1*2*3*4*5*6*7*8*9*10)
    }
    print(s)
}

//ID: 1123753
@Entity
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String,
    val dob: Int,
    val height: Int,
    val weight: Int
)

}
//ID: 1123822
class PlaceView : FrameLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    private val headerView = findViewById&lt;TextView&gt;(R.id.viewPagerHeader)
    private val imageView = findViewById&lt;ImageView&gt;(R.id.viewPagerImage)
    private val urlView = findViewById&lt;TextView&gt;(R.id.viewPagerTextUrl)
    private val addressView = findViewById&lt;TextView&gt;(R.id.viewPagerTextPlace)

    var header: String? = ""
        set(value) {
            field = value
            headerView.text = value
        }

    var imageResource: Int? = null
        set(value) {
            field = value
            imageResource?.let { imageView.setImageResource(it) }
        }

    var url: String? = ""
        set(value) {
            field = value
            urlView.text = url
        }

    var address: String? = ""
        set(value) {
            field = value
            addressView.text = address
        }

}


}
//ID: 1123822
class ViewPagerAdapter : RecyclerView.Adapter&lt;ViewPagerHolder&gt;() {

    var values: List&lt;PlacesModel?&gt; = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {

        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.viewpageritem, parent, false)
        return parent.context.viewPagerHolder(view)
    }

    override fun getItemCount(): Int = PlacesFilter.TAKES_PLACES


    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        val request = values[position]?.properties?.responseMetadata?.searchRequest?.request
        val found = values[position]?.properties?.responseMetadata?.searchResponse?.found
        var name: String? = ""
        var description: String? = ""
        var url: String? = "Данные отсутсвуют"

        //если элементы найдены
        if (found != 0) {

            val properties = values[position]?.features?.get(0)?.properties
            name = properties?.name
            description = properties?.description

            if (properties?.companyMetaData != null) {
                url = properties.companyMetaData.url
            }
        }

        holder.bind(
            name,
            MAIN_DICTIONARY[request] ?: R.drawable.empty,
            url,
            description
        )
    }
}

}
//ID: 1016822
private fun showAllNoteDB() {
    ref.addValueEventListener(object:ValueEventListener{
        override fun onCancelled(p0: DatabaseError) {
        }

        override fun onDataChange(p0: DataSnapshot) {
            if(p0!!.exists()) {
                for(i in p0.children){
                    val noteItem=i.getValue(Note::class.java)
                    items.add(noteItem!!)
                }
            }
        }

    })
}

}
//ID: 1124082
class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        OpenButtonProfile.setOnClickListener {
            val intent = Intent(activity, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}

}
//ID: 969222
override fun findItemsByDetails(listDetails: List&lt;String&gt;, count: Int): Flowable&lt;MutableList&lt;Item&gt;&gt; {
        return itemDao.findItemsByDetails(listDetails, count).flatMapPublisher { list -&gt;
            itemDao.getItemsByIds(list).flatMapIterable { items -&gt; items }.flatMapSingle {
                itemDao.getAllDetailsItems(it.idItem).map { list -&gt;
                    mapper.mapDetailItem(it, list)
                }.toFlowable().toList()
            }
        }
}

}
//ID: 1124378

class RecyclerAdapter(
    private val workoutList: ArrayList&lt;Workout&gt;
) : RecyclerView.Adapter&lt;RecyclerAdapter.ViewHolder&gt;() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workout: Workout = workoutList[position]
        holder.numberApproaches.text = workout.numberApproaches
        holder.dateText.text = workout.dateText
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var numberApproaches: TextView = item.findViewById(R.id.number_approaches)
        var dateText: TextView = item.findViewById(R.id.date_text)
    }

    override fun getItemCount(): Int {
        return workoutList.size
    }
}


}
//ID: 868477
fun onDialogPause() {
    if (Build.VERSION.SDK_INT &gt;= Build.VERSION_CODES.M) {
        isDialogVisible = false
        mCancellationSignal?.cancel()
    }
}

}
//ID: 1017409
    class AcountActivity : BaseActivity() {
    private val Tag = "AcountActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_layout)
        setSupportActionBar(toolbar)
        setupBottomNavigation()
    }

}
//ID: 969673
object Logger {

    @set:Inject
    lateinit var repository: Repository

    init {
        //How to inject????
    }

    private fun send() {
        GlobalScope.launch {
            repository.sendLogs(someLogs)
        }
    }

    ....

}

}
//ID: 1077960
blok1.addTextChangedListener(textWatcher())

private fun textWatcher(): TextWatcher {
    return object :
        TextWatcher {
        override fun afterTextChanged(editable: Editable) {
            try {
                val none = Integer.parseInt(editable.toString())
                if (none == 0) {
                    editable.replace(0, editable.length, "0.")
                }
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }
    }
}

}
//ID: 757854
abstract class SunshineBaseActivity: AppCompatActivity(), IScreenNavigator
{
@Inject lateinit var mContext: Context
....//
}

}
//ID: 757854
class SunshineApp: Application()
{
companion object
{
    fun getInstance(context: Context): SunshineApp
    {
        return context.applicationContext as SunshineApp
    }
}

val appComponent: AppComponent by lazy {
DaggerAppComponent.builder().appModule(AppModule(this)).
networkModule(NetworkModule()).build()
}

override fun onCreate()
{
    super.onCreate()
    appComponent.inject(this)
}
}

}
//ID: 757854
override fun onCreate()
{
super.onCreate()
appComponent.inject(this)
}

}
//ID: 757854
class SunshineApp: Application()
{
companion object
{
    fun getInstance(context: Context): SunshineApp
    {
        return context.applicationContext as SunshineApp
    }
}

val appComponent: AppComponent by lazy {
    DaggerAppComponent.builder().appModule(AppModule(this)).networkModule(NetworkModule()).build()
}
}

}
//ID: 972660
class TimelineDelegate(callback: Callback): 
AbsListItemAdapterDelegate&lt;BaggageTimelineItem, IRootInterface&lt;List&lt;MyObj&gt;&gt;, 
TimelineDelegate.ViewHolder&gt;()
{
override fun onCreateViewHolder(parent: ViewGroup): ViewHolder
{
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

override fun isForViewType(item: IRootInterface&lt;List&lt;MyObj&gt;&gt;, items: 
MutableList&lt;IRootInterface&lt;List&lt;MyObj&gt;&gt;&gt;, position: Int): Boolean
{
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

override fun onBindViewHolder(p0: BaggageTimelineItem, p1: ViewHolder, p2: MutableList&lt;Any&gt;)
{
    TODO("not implemented") //To change body of created functions use File | 
Settings | File Templates.
}

class ViewHolder(view: View): BaseHolder&lt;List&lt;MyObj&gt;&gt;(view)
{
    override fun onBind(iItem: List&lt;MyObj&gt;?)
    {
        TODO("not implemented") //To change body of created functions use 
File | Settings | File Templates.
    }
}
}

interface Callback: ClickListener

}
//ID: 1020436
class LoginActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById&lt;View&gt;(R.id.buttonLogin).setOnClickListener {
            val intent = Intent(this.applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

}
//ID: 701047
abstract class Dad(params: Array&lt;Any&gt; = arrayOf()) : GrandDad(params) {
    ...
}

}
//ID: 807136
 &lt;?xml version="1.0" encoding="utf-8"?&gt;

&lt;RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.lev.homechek.view.MainActivity"&gt;

&lt;android.support.v7.widget.Toolbar
    android:id="@+id/toolbar"
    app:title="@string/nav_news"
    app:titleTextColor="@color/white"
    android:background="@color/colorPrimary"
    android:layout_width="match_parent"
    app:popupTheme="@style/android:Theme.Holo.Light"
    android:theme="@style/MainMenu"
    android:layout_height="?attr/actionBarSize"/&gt;

&lt;LinearLayout
    android:orientation="vertical"
    android:id="@+id/container"
    android:layout_width="match_parent"
    android:layout_height="match_parent" /&gt;

&lt;/RelativeLayout&gt;

}
//ID: 807136
 class StorageNew: Fragment() {

fun newInstance(): Fragment {
    return StorageNew()
}

override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

    var view = inflater!!.inflate(R.layout.fragment_storage_new, container)

    return view
}

}
//ID: 924197
class LooperParameter(fragment : DataReadyCallback) : Runnable {
    companion object {
        const val TAG: String = "LooperParameter"
        const val DELAY_RESPONSE: Long = 200
    }

    private val handler : Handler = Handler()
    private val dataReadyCallback : DataReadyCallback = fragment

    fun startLooper(){
        handler.post(this)
    }

    fun stopLooper(){
        handler.removeCallbacks(this)
    }

    override fun run() {
        dataReadyCallback.dataReady()
        handler.postDelayed(this, DELAY_RESPONSE)
        Log.i(TAG, "New data called...")
    }

    interface DataReadyCallback {
        fun dataReady()
    }
}
}

}
//ID: 1128355
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toastMe(Str: String) {
        val myToast = Toast.makeText(this,Str, Toast.LENGTH_SHORT)
        myToast.show()
    }

    fun RegistrationCallFragment() {

        val bundle = Bundle()
        bundle.putString("phone", 
            FragmentManager.findFragment&lt;SecondFragment&gt;(View(this)).view?.findViewById&lt;EditText&gt;(R.id.phone_signup)?.text.toString()
        )
        bundle.putString("email",
            FragmentManager.findFragment&lt;SecondFragment&gt;(View(this)).view?.findViewById&lt;EditText&gt;(R.id.email_signup)?.text.toString()
        )
        bundle.putString("password",
            FragmentManager.findFragment&lt;SecondFragment&gt;(View(this)).view?.findViewById&lt;EditText&gt;(R.id.pasword_signup)?.text.toString()
        )

        Registration_second.getNewInstance(args = bundle)
    }
}

}
//ID: 1128355
override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    // Inflate the layout for this fragment

    //val extras = getIntent()?.getExtras();
    //if(extras != null) {
    //    val phone = extras.getString("phone", "")
    //    val email = extras.getString("email", "")
    //    val password = extras.getString("password", "")
    //return inflater.inflate(R.layout.fragment_registration_second, container, false)

    val phone = arguments?.getString("phone", "")
    val email = arguments?.getString("email", "")
    val password = arguments?.getString("password", "")
    Log.d("PHONE BLYAT", phone+email+password+"THis")
    print(phone+email+password)
    val activ = activity
    (activ as MainActivity).toastMe(phone.toString())
    return inflater.inflate(R.layout.fragment_registration_second, container, false)
}

}
//ID: 974554
package ru.site.a1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

 private var url: String = ""

 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  setContentView(R.layout.activity_main)

  url = "http://192.168.42.250:8000/"

  webView1.loadUrl(url)

 }
}

}
//ID: 1021699
private fun subscribeOnErrors() {
    if(!baseViewModel().activateProOutcome.hasObservers()) {
        baseViewModel().errorOutcome.observe(this, Observer {
            when (it) {
                is Outcome.Failure -&gt; {
                    if (activity is BaseActivity) {
                        (activity as BaseActivity).processApiError(it)
                    }
                }
                is Outcome.Success -&gt; {
                    if (activity is BaseActivity) {
                        (activity as BaseActivity).processCodeError(it.data)
                    }
                }
            }
        })
    }
}

fun processApiError(it: Outcome.Failure&lt;*&gt;) {
    if (it.error == null) {
        showToast(R.string.global_error)
    } else {
        if (it.error.MessageType == 1) {
            showDialogError(it.error.MessageText, false, object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    finish()
                }
            })
        } else {
            showDialogError(it.error.MessageText)
        }
    }
}

}
//ID: 873390
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools" &gt;

    &lt;data&gt;

        &lt;variable
            name="viewModel"
            type="ua.com.im_a_welder.imawelder.viewmodel.DataItemViewModel"/&gt;
    &lt;/data&gt;

    &lt;androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"&gt;

        &lt;ImageView
            android:id="@+id/imageView"
            android:layout_width="match_parent"
            android:layout_height="100dp"
            android:layout_marginTop="8dp"
            android:layout_marginBottom="8dp"
            app:imageUrl="@{viewModel.images}"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            tools:layout_editor_absoluteX="0dp" /&gt;

        &lt;TextView
            android:id="@+id/title_tv"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:layout_gravity="center"
            android:layout_marginStart="16dp"
            android:layout_marginBottom="8dp"
            android:background="@color/colorPrimaryDark"
            android:gravity="center"
            android:textColor="@android:color/white"
            android:textSize="28sp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:text="@{viewModel.title}"
            tools:text="Item Title" /&gt;

    &lt;/androidx.constraintlayout.widget.ConstraintLayout&gt;

&lt;/layout&gt; 

}
//ID: 925009
class ScheduleRepository {
    private var viewModel: BaseViewModel? = null
    private var context: Context? = null

    fun setViewModel(context: Context, viewModel: BaseViewModel) {
        this.viewModel = viewModel
        this.context = context
    }

    fun getWeek(groupId: String, date: String): MutableLiveData&lt;RequestResultWeek&gt; {
        val data = MutableLiveData&lt;RequestResultWeek&gt;()

        getAPIService().getWeek(groupId, date).enqueue(object: Callback&lt;RequestResultWeek&gt; {
            override fun onFailure(call: Call&lt;RequestResultWeek&gt;?, t: Throwable?) {
                viewModel?.isLoading?.value = false
                viewModel?.title?.value = context?.getString(R.string.request_error)
            }

            override fun onResponse(call: Call&lt;RequestResultWeek&gt;?, response: Response&lt;RequestResultWeek&gt;?) {
                data.postValue(response?.body())
            }
        })

        return data
    }
}

}
//ID: 808491
fun openMe(view: View) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
    }

}
//ID: 808392
apply plugin: 'com.android.library'
apply plugin: 'kotlin-android'

android {
    compileSdkVersion 27

    defaultConfig {
        minSdkVersion 16
        targetSdkVersion 27
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])

    implementation 'com.android.support:appcompat-v7:27.1.0'
    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"

    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
}
repositories {
    mavenCentral()
}

}
//ID: 808935
class UserData : RealmObject() {
var name: String = ""
var value: String = ""
}

}
//ID: 1082631
class ResultGroup(var userId: Int, var movieRating: List&lt;MovieRating&gt;? = null)

class MovieRating(public var movieTitle: String? = null, var rating: Int? = null)

}
//ID: 1082631
val a = resultList.groupBy { {ResultGroup::userId};{MovieRating::movieTitle} }
        .mapValues { it.value.sumBy { it.movieRating........... }  }

}
//ID: 1083021
fun oQskO(){
    println("Hello, World!")
}

//ID: 927447
    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
    val bitmapDrawable = BitmapDrawable(bitmap)
    select_button.setBackgroundDrawable(bitmapDrawable)

}
//ID: 811385
apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'org.jetbrains.kotlin.android.extensions'
apply plugin: 'kotlin-kapt'
...
androidExtensions {
    experimental = true
}
...
dependencies {
...
compile "org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlin_version"
...
}

}
//ID: 876938
sealed class Lce&lt;out T&gt; {

    abstract fun &lt;R&gt; map(f: (T) -&gt; R): Lce&lt;R&gt;

    inline fun doOnData(f: (T) -&gt; Unit) {
        if (this is Success) {
            f(data)
        }
    }

    data class Success&lt;out T&gt;(val data: T) : Lce&lt;T&gt;() {
        override fun &lt;R&gt; map(f: (T) -&gt; R): Lce&lt;R&gt; = Success(f(data))
    }

    data class Error(val message: String) : Lce&lt;Nothing&gt;() {
        constructor(t: Throwable) : this(t.message ?: "")

        override fun &lt;R&gt; map(f: (Nothing) -&gt; R): Lce&lt;R&gt; = this
    }

    object Loading : Lce&lt;Nothing&gt;() {
        override fun &lt;R&gt; map(f: (Nothing) -&gt; R): Lce&lt;R&gt; = this
    }

    companion object {
        inline fun &lt;T&gt; exec(copy: (Lce&lt;T&gt;) -&gt; Unit, f: () -&gt; T) {
            copy(Lce.Loading)
            try {
                copy(Lce.Success(f()))
            } catch (e: Exception) {
                copy(Lce.Error(e))
            }
        }
    }
}

fun &lt;T&gt; Lce&lt;T&gt;.orElse(defaultValue: T): T = (this as? Lce.Success)?.data ?: defaultValue

}
//ID: 646269
 pass.textView.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            if (editable.toString().isNotEmpty()) validatePassword()
            else pass.setValid()
        }
    })

    name.textView.addTextChangedListener(object : TextWatcher{
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s!!.isNotEmpty()) isNameValid()
            else name.setValid()
        }
    })

}
//ID: 1132517
override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            myInterface=context as MyInterface
        }catch (e: Exception){
            Log.d("myLog",e.message)
        }
    }

}
//ID: 979324
class PrivacyPolicyTest : DialogFragment()  {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val myview = inflater.inflate(R.layout.activity_privacy_policy_test,container,false)
        MySingleton.getInstance().getPrivacyPolicy()
        return myview
    }

}

}
//ID: 706830
class AccountModelNode private constructor(val key: String, val isLeaf: Boolean) {

constructor(key: String, value: AccountCardViewModel) : this(key, true) {
    this.value = value
}

constructor(key: String, list: List&lt;AccountModelNode&gt;) : this(key, false) {
    this.list = list
}

var value: AccountCardViewModel? = null
    private set
var list: List&lt;AccountModelNode&gt;? = null
    private set
}

}
//ID: 1085796
apply plugin: 'com.android.application'

apply plugin: 'kotlin-android'

apply plugin: 'kotlin-android-extensions'

android {
    compileSdkVersion 28
    buildToolsVersion "29.0.3"
    defaultConfig {
        applicationId "com.example.myapplication"
        minSdkVersion 24
        targetSdkVersion 24
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.61"
    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.core:core-ktx:1.2.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    testImplementation 'junit:junit:4.13'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
}

}
//ID: 764686
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.lfom.modbuster.R
import java.io.File


class ConfigFileFinder : AppCompatActivity() {

    private val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_file_finder)
    }

    private val FIND_FILE: Int = 0xA


    public fun onFindFile(view: View) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(intent, FIND_FILE)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == FIND_FILE &amp;&amp; resultCode == Activity.RESULT_OK) {
            Log.w(TAG, data?.data?.path )
            val inpFile = File(data?.data?.path ?: return)

            if (!inpFile.canRead()) {
                Log.e(TAG, "Can't read file ${inpFile.absolutePath}")
            }
            try {
                val newFile = inpFile.copyTo(File(filesDir.path, inpFile.name), false)
            } catch (e: Exception) {
                Log.e(TAG, e.toString())
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}
//ID: 764686
public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == FIND_FILE &amp;&amp; resultCode == Activity.RESULT_OK) {
            if (data == null) {
                Log.w(TAG, "Empty Intent for file copy")
                return
            }

            try {

                val inpFileStream = baseContext.contentResolver.openInputStream(data.data)
                val outFileStream = openFileOutput("default.prj", Context.MODE_PRIVATE)

                inpFileStream.use { input -&gt;
                    outFileStream.use { output -&gt;
                        input.copyTo(output)
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, e.toString())
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}
//ID: 479248
apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'

buildscript {
    ext.kotlin_version = '1.0.0-beta-4584'
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        classpath "org.jetbrains.kotlin:kotlin-android-extensions:$kotlin_version"
    }
}
repositories {
    mavenCentral()
}

android {
    compileSdkVersion 23
    buildToolsVersion "23.0.1"

    defaultConfig {
        applicationId "com.example"
        minSdkVersion 15
        targetSdkVersion 23
        versionCode 1
        versionName "1.0"
        resValue "string", "tray__authority", "${applicationId}.tray"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
    sourceSets {
        main.java.srcDirs += 'src/main/kotlin'
    }
}

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    androidTestCompile 'com.android.support.test:testing-support-lib:0.1'
    androidTestCompile 'com.android.support.test.espresso:espresso-core:2.0'

    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    compile "com.android.support:appcompat-v7:23.1.0"
    compile 'com.android.support:recyclerview-v7:23.1.0'
    compile 'com.android.support:cardview-v7:23.1.0'
    compile 'com.michaelpardo:activeandroid:3.1.0-SNAPSHOT'
    compile 'net.grandcentrix.tray:tray:1.0.0-rc1'
    compile 'com.melnykov:floatingactionbutton:1.3.0'
    compile 'me.drakeet.materialdialog:library:1.2.8'

}

}
//ID: 1135391
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SplashFragment.newInstance())
                    .commit()
        }
    }

}
//ID: 1135722
class ItemAdapter(
        list: List&lt;Item&gt;,
        private val viewModel: ItemViewModel
) : RecyclerView.Adapter&lt;ItemAdapter.ItemHolder&gt;() {

    var list: List&lt;Item&gt; = list
        set(list) {
            field = list
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding: ItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item,
                parent, false)

        val listener = object : ItemListener {
            override fun onItemClick(item: Item) {
              Toast.makeText(parent.context, item.title, Toast.LENGTH_SHORT).show()
            }

            override fun onButtonMoreClick(item: Item) {
                PopupMenu(parent.context, binding.btnMore).apply {
                    menuInflater.inflate(R.menu.item_menu, menu)
                    setOnMenuItemClickListener { item -&gt;
                        when (item.itemId) {
                            R.id.edit -&gt; {
                                binding.tvTitle.visibility = View.GONE
                                binding.fielTitle.visibility = View.VISIBLE
                            }
                            R.id.delete -&gt; viewModel.delete(item)
                        }
                        true
                    }
                    show()
                }
            }

            override fun onButtonSaveClick(item: Item) {
                binding.tvTitle.visibility = View.VISIBLE
                binding.fielTitle.visibility = View.GONE
                viewModel.update(item)
            }

        }

        binding.viewModel = viewModel
        binding.callback = listener
        return ItemHolder(binding)
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }


    class ItemHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.item = item
        }
    }
}

}
//ID: 1029994
private val _score = MutableLiveData&lt;Int&gt;()
val score: LiveData&lt;Int&gt;
    get() = _score

}
//ID: 1136082
val navController = findNavController(R.id.nav_host_fragment)
val appBarConfiguration = AppBarConfiguration(navController.graph)

}
//ID: 815685
buildscript {
    ext.kotlin_version = "1.2.31"
    repositories {
        google()
        jcenter()
    }
    dependencies {
        .....
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        .....
    }
}

}
//ID: 815685
apply plugin: 'kotlin-android'

apply plugin: 'kotlin-android-extensions'

apply plugin: 'kotlin-kapt'

android {
    .........
    androidExtensions {
        experimental = true
    }
}

dependencies {
    ..........
    // kotlin
    implementation "org.jetbrains.kotlin:kotlin-reflect:$kotlin_version"
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    .........
}

}
//ID: 1032248
&lt;androidx.drawerlayout.widget.DrawerLayout
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"&gt;

    &lt;com.google.android.material.appbar.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"&gt;

            &lt;androidx.appcompat.widget.Toolbar
                android:id="@+id/toolbar"
                android:background="@color/colorPrimary"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                app:layout_scrollFlags="enterAlways" /&gt;
    &lt;/com.google.android.material.appbar.AppBarLayout&gt;

        &lt;TextView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:text="test"/&gt;
    &lt;/LinearLayout&gt;

    &lt;com.google.android.material.navigation.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        app:headerLayout="@layout/nav_header"
        app:itemIconTint="#9988CC"
        app:menu="@menu/menu_navigation" /&gt;

&lt;/androidx.drawerlayout.widget.DrawerLayout&gt;

}
//ID: 1137922
fun hDTwf(args: Array&lt;String&gt;) {
    println("Hello World!")
}

//ID: 1137922
"C:\Program Files\Java\jdk-14.0.1\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2020.1.1\lib\idea_rt.jar=59825:C:\Program Files\JetBrains\IntelliJ IDEA 2020.1.1\bin" -Dfile.encoding=windows-1251 -classpath "C:\Program Files\JetBrains\IntelliJ IDEA 2020.1.1\plugins\Kotlin\kotlinc\lib\kotlin-compiler.jar;C:\Program Files\JetBrains\IntelliJ IDEA 2020.1.1\plugins\Kotlin\kotlinc\lib\kotlin-reflect.jar;C:\Program Files\JetBrains\IntelliJ IDEA 2020.1.1\plugins\Kotlin\kotlinc\lib\kotlin-stdlib.jar;C:\Program Files\JetBrains\IntelliJ IDEA 2020.1.1\plugins\Kotlin\kotlinc\lib\kotlin-script-runtime.jar" org.jetbrains.kotlin.cli.jvm.K2JVMCompiler -kotlin-home "C:\Program Files\JetBrains\IntelliJ IDEA 2020.1.1\plugins\Kotlin\kotlinc" -script C:/Users/freQuensy/IdeaProjects/untitled/main.kts
main.kts:1:10: warning: parameter 'args' is never used
fun fdHKX(args: Array&lt;String&gt;) {
         ^

Process finished with exit code 0


//ID: 655393
var mLocalItems: MutableList&lt;String&gt;? = null
override fun setElements(items: MutableList&lt;*&gt;) {
    mLocalItems = items
}

}
//ID: 1140031
None of the following functions can be called with the arguments supplied.

&lt;init&gt;(Context, Int, Array&lt;(out) TypeVariable(T)!&gt;)
  where T = TypeVariable(T) for 
  constructor ArrayAdapter&lt;T : Any!&gt;(context: Context, resource: Int, objects: Array&lt;(out) T!&gt;) defined in android.widget.ArrayAdapter
&lt;init&gt;(Context, Int, Int)
  where T = TypeVariable(T) for 
  constructor ArrayAdapter&lt;T : Any!&gt;(context: Context, resource: Int, textViewResourceId: Int) defined in android.widget.ArrayAdapter
&lt;init&gt;(Context, Int, (Mutable)List&lt;TypeVariable(T)!&gt;)
  where T = TypeVariable(T) for 
  constructor ArrayAdapter&lt;T : Any!&gt;(context: Context, resource: Int, objects: (Mutable)List&lt;T!&gt;) defined in android.widget.ArrayAdapter Alt+Shift+Enter Alt+Enter

}
//ID: 1035826
override fun &lt;T : ViewModel&gt; create(modelClass: Class&lt;T&gt;): T = when {
    modelClass.isAssignableFrom(M1ViewModel::class.java) -&gt; M1ViewModel(dataManager, schedulerProvider)
    modelClass.isAssignableFrom(C1ViewModel::class.java) -&gt; C1ViewModel(dataManager, schedulerProvider)
    modelClass.isAssignableFrom(C2ViewModel::class.java) -&gt; C2ViewModel(dataManager, schedulerProvider)
    else -&gt; throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
} as T

}
//ID: 716218
buildscript {
ext.kotlin_version = '1.1.4-3'
repositories {
    jcenter()
}
dependencies {
    classpath 'com.android.tools.build:gradle:2.3.3'
    classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    // NOTE: Do not place your application dependencies here; they belong
    // in the individual module build.gradle files
}
}

allprojects {
repositories {
    jcenter()
}
}

task clean(type: Delete) {
delete rootProject.buildDir
}

}
//ID: 821142
class StyleChanger{
            object companion {
                    val TAG  = "Style companion"

                    var bordergrid : Drawable? =   null

                    init {
                            try {
                                   bordergrid =  ResourcesCompat.getDrawable(Resources.getSystem() ,R.drawable.border , null)
                            }
                            catch (e : Exception) {
                                    Log.d(TAG, e.toString())
                            }
                    }
            }

    }

}
//ID: 1141376
val intent = Intent(this, this.javaClass).apply { putExtra(KEY_SKIP_PIN, true) }
(this as? Activity)?.intent = intent

}
//ID: 1142618
holder.planValue.setText(String.format(Locale.getDefault(), &quot;%4f&quot;, dataList[position].planHa))
dataList[position].haTextWatcher?.let { holder.planValue.removeTextChangedListener(it) }
holder.planValue.filters = arrayOf(DecimalDigitsInputFilter( 4, 4 ))
dataList[position].haTextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {...}
        }
holder.planValue.addTextChangedListener(dataList[position].haTextWatcher)

}
//ID: 823301
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view -&gt;
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -&gt; return true
            else -&gt; return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_main_page -&gt; {
                // Handle the camera action
            }
            R.id.nav_poisk -&gt; {

            }
            R.id.nav_history -&gt;  {

            }
            R.id.nav_ischu_zhilyo -&gt; {

            }
            R.id.nav_dobavit_predlo -&gt; {

            }
            R.id.nav_help-&gt; {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}

}
//ID: 823787
package ssamvel.httpexes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset
import javax.xml.datatype.DatatypeConstants.SECONDS
import android.os.AsyncTask
import ssamvel.httpexes.R.id.textView
import java.io.BufferedInputStream



class MainActivity : AppCompatActivity() {

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
}

fun hello(view: View) {
    val task = CatTask()
    task.execute()
}

internal inner class CatTask : AsyncTask&lt;Void, Void, Void&gt;() {

    override fun onPreExecute() {
        super.onPreExecute()

    }

    override fun doInBackground(vararg params: Void): Void? {
        var urlConnection: HttpURLConnection
        var url = URL("http://www.cyberforum.ru")
        urlConnection = url.openConnection() as HttpURLConnection
        var reader = BufferedReader(InputStreamReader(urlConnection.inputStream))

        return null
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
    }
}

}

}
//ID: 828506
class MainActivity : AppCompatActivity() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val users = ArrayList&lt;Users&gt;()
        val adapter = CustomAdapter(this, users)

        val repository = SearchRepositoryProvider.provideSearchRepository()

        compositeDisposable.add(
                repository.searchUsers()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({ result -&gt;
                            adapter.setListUsers(result.users)
                        }, { error -&gt;
                            error.printStackTrace()
                        })
        )

        recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}

}
//ID: 1146408
private val SaveFirebaseData = CoroutineScope(Dispatchers.Default).launch {
    while (this.isActive) {
        try {
            delay(15000)
            SaveUserDataOnFirebase(applicationContext)
        }
        catch (e: Exception) { println(e) }
    }
}

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    SaveFirebaseData.start()
}

override fun onStop() {
    super.onStop()
    SaveFirebaseData.cancel()
}

override fun onStart() {
    super.onStart()
    SaveFirebaseData.start()
}

override fun onDestroy() {
    super.onDestroy()
    SaveFirebaseData.cancel()
}

}
//ID: 765881
apply plugin: 'com.android.application'

android {
    compileSdkVersion 26
    defaultConfig {
        applicationId "ru.system_gps.assistant"
        minSdkVersion 15
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:26.1.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
}

}
//ID: 1042453
    @SerializedName("firstName")
    @Expose
    public String firstName;
    @SerializedName("contractData")
    @Expose
    public List&lt;ContractDatum&gt; contractData = new ArrayList&lt;ContractDatum&gt;();

    public Example withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Example withContractData(List&lt;ContractDatum&gt; contractData) {
        this.contractData = contractData;
        return this;
    }   
}

}
//ID: 767887
apply plugin: 'com.android.application'

android {
    compileSdkVersion 26
    defaultConfig {
    applicationId "com.example.test.myapp"
    minSdkVersion 19
    targetSdkVersion 26
    versionCode 1
    versionName "1.0"
    testInstrumentationRunner  "android.support.test.runner.AndroidJUnitRunner"
}
buildTypes {
    release {
        minifyEnabled false
        proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
    }
  }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:26.1.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
}

}
//ID: 767608
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    &gt;

    &lt;include
        layout="@layout/app_bar_home"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" /&gt;

    &lt;ScrollView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"&gt;

    &lt;LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical"&gt;

        &lt;ImageView
            android:layout_width="wrap_content"
            android:layout_height="140sp"
            android:layout_marginTop="55dp"
            android:background="@drawable/background_home"&gt;
        &lt;/ImageView&gt;

    &lt;/LinearLayout&gt;

        &lt;Button
            android:id="@+id/button_work"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Робота"
            android:onClick="onClick"
            android:theme="@style/CustomAnimationButton" /&gt;
    &lt;/ScrollView&gt;

    &lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_home"
        app:menu="@menu/activity_home_drawer" /&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;
    &lt;!-- copy from activity_home and paste it here.--&gt;

}
//ID: 720035
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.0.0-beta6'


        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 767910
    apply plugin: 'com.android.application'

android {
    compileSdkVersion 26
    defaultConfig {
        applicationId "com.hfad.constraintliving"
        minSdkVersion 23
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
    compile 'com.android.support.constraint:constraint-layout:1.1.0-beta4'
    compile 'com.android.support:appcompat-v7:26.1.0'
    compile 'com.android.support:support-v4:26.1.0'
    compile 'com.android.support:design:26.1.0'
}

}
//ID: 1091806
apply plugin: 'com.android.application'
apply plugin: 'com.google.gms.google-services'

android {
compileSdkVersion 29
buildToolsVersion "29.0.2"
defaultConfig {
    applicationId "com.example.projectmy"
    minSdkVersion 23
    targetSdkVersion 29
    versionCode 1
    versionName "1.0"
    testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
}
buildTypes {
    release {
        minifyEnabled false
        proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
    }
}
}

dependencies {
implementation fileTree(dir: 'libs', include: ['*.jar'])
implementation 'androidx.appcompat:appcompat:1.0.2'
implementation 'com.google.android.gms:play-services-maps:16.1.0'
implementation 'com.google.firebase:firebase-database:16.0.4'
implementation 'androidx.legacy:legacy-support-v4:1.0.0'
implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
testImplementation 'junit:junit:4.12'
androidTestImplementation 'androidx.test.ext:junit:1.1.0'
androidTestImplementation 'androidx.test.espresso:espresso-core:3.1.1'
implementation 'com.google.android.material:material:1.0.0'
implementation 'com.firebaseui:firebase-ui-auth:6.2.0'
}

}
//ID: 1093423
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.6.0'


        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()

    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 1093423
apply plugin: 'com.android.application'

android {
    compileSdkVersion 29
    buildToolsVersion "29.0.2"

    defaultConfig {
        applicationId "com.example.testgradle"
        minSdkVersion 16
        targetSdkVersion 29
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])

    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'

    //noinspection GradleCompatible
    implementation 'com.android.support:appcompat-v7:28.0.0'
    //noinspection GradleCompatible
    implementation 'com.android.support:design:28.0.0'
}

}
//ID: 1046443
&lt;FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    &gt;

    &lt;androidx.recyclerview.widget.RecyclerView
        android:id="@+id/rv_num"
        android:layout_width="match_parent"
        android:layout_height="match_parent"&gt;

    &lt;/androidx.recyclerview.widget.RecyclerView&gt;

&lt;/FrameLayout&gt;

}
//ID: 490602
&lt;receiver android:name=".MyReceiver"
          android:enabled="true"
          android:exported="true"
          android:label="MyReceiver"
          android:process=":myreceiver"&gt;
  &lt;intent-filter&gt;
    &lt;action android:name="ANDROID.INTENT.ACTION.BOOT_COMPLETED" /&gt;
  &lt;/intent-filter&gt;
&lt;/receiver&gt;

}
//ID: 664751
&lt;receiver
    android:name=".activity.HomeActivity$MusicIntentReceiver"
    android:enabled="true"
    android:exported="true" &gt;
    &lt;intent-filter&gt;
        &lt;action android:name="android.intent.action.HEADSET_PLUG" /&gt;
    &lt;/intent-filter&gt;
&lt;/receiver&gt;

}
//ID: 1048292
   apply plugin: 'com.android.application'

android {
    compileSdkVersion 'Google Inc.:Google APIs:23'
    buildToolsVersion "24.0.0 rc1"

    defaultConfig {
        applicationId "info.androidhive.glide"
        minSdkVersion 15
        targetSdkVersion 23
        versionCode 1
        versionName "1.0"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:23.2.1'
    compile 'com.android.support:design:23.2.1'
    compile 'com.android.support:support-v4:23.2.1'

    // RecyclerView
    compile 'com.android.support:recyclerview-v7:23.1.1'

    // volley
    compile 'com.android.volley:volley:1.0.0'

    // glide
    compile 'com.github.bumptech.glide:glide:3.7.0'
}

}
//ID: 840633
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.1.3'


        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 497334
&lt;string name="food"&gt;Food&lt;/string&gt;
&lt;string name="clothes"&gt;Clothes&lt;/string&gt;
&lt;string name="math"&gt;Math&lt;/string&gt;
&lt;string name="science"&gt;Science&lt;/string&gt;

&lt;string-array name="food"&gt;
    &lt;item&gt;Noisettes&lt;/item&gt;
    &lt;item&gt;Apple&lt;/item&gt;
    &lt;item&gt;Pelmeni&lt;/item&gt;
    &lt;item&gt;Pineaple&lt;/item&gt;
    &lt;item&gt;Cabbage&lt;/item&gt;
    &lt;item&gt;Squash&lt;/item&gt;
    &lt;item&gt;Orange&lt;/item&gt;
    &lt;item&gt;Lemon&lt;/item&gt;
    &lt;item&gt;Jelly&lt;/item&gt;
    &lt;item&gt;Honney&lt;/item&gt;
    &lt;item&gt;Sausage&lt;/item&gt;
    &lt;item&gt;Pizza&lt;/item&gt;
    &lt;item&gt;Onion&lt;/item&gt;
    &lt;item&gt;Lemonade&lt;/item&gt;
    &lt;item&gt;Tea&lt;/item&gt;
&lt;/string-array&gt;

&lt;string-array name="math"&gt;
    &lt;item&gt;Arabic number&lt;/item&gt;
    &lt;item&gt;Integral&lt;/item&gt;
    &lt;item&gt;Square root&lt;/item&gt;
    &lt;item&gt;Add&lt;/item&gt;
    &lt;item&gt;Minus&lt;/item&gt;
    &lt;item&gt;Remainder&lt;/item&gt;
    &lt;item&gt;Float number&lt;/item&gt;
    &lt;item&gt;Integer&lt;/item&gt;
    &lt;item&gt;Function&lt;/item&gt;
    &lt;item&gt;Divide&lt;/item&gt;
    &lt;item&gt;Variable&lt;/item&gt;
    &lt;item&gt;Constant&lt;/item&gt;
    &lt;item&gt;Zero&lt;/item&gt;
    &lt;item&gt;Infinity&lt;/item&gt;
    &lt;item&gt;Limit&lt;/item&gt;
&lt;/string-array&gt;

&lt;string-array name="science"&gt;
    &lt;item&gt;Atom&lt;/item&gt;
    &lt;item&gt;Molecule&lt;/item&gt;
    &lt;item&gt;Star&lt;/item&gt;
    &lt;item&gt;Planet&lt;/item&gt;
    &lt;item&gt;Plasma&lt;/item&gt;
    &lt;item&gt;E=mc2&lt;/item&gt;
    &lt;item&gt;Newton&lt;/item&gt;
    &lt;item&gt;Sphere&lt;/item&gt;
    &lt;item&gt;Rainbow&lt;/item&gt;
    &lt;item&gt;Big bang&lt;/item&gt;
    &lt;item&gt;Black hole&lt;/item&gt;
    &lt;item&gt;Singularity&lt;/item&gt;
    &lt;item&gt;Sound waves&lt;/item&gt;
    &lt;item&gt;Light speed&lt;/item&gt;
    &lt;item&gt;Eddison`s lamp&lt;/item&gt;
&lt;/string-array&gt;

}
//ID: 844045
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;fragment xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/list_frag"
    class="com.hfad.workout.WorkoutListFragment" /&gt;

}
//ID: 844045
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;fragment xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.hfad.workout.WorkoutDetailFragment"
    android:id="@+id/detail_frag"/&gt;

}
//ID: 844045
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:orientation="vertical"
tools:context="com.hfad.workout.WorkoutDetailFragment"&gt;

&lt;!-- TODO: Update blank fragment layout --&gt;
&lt;TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text=""
    android:textAppearance="?android:attr/textAppearanceLarge"
    android:id="@+id/textTitle" /&gt;
&lt;TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text=""
    android:id="@+id/textDescription"/&gt;
&lt;FrameLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/stop_watch_container"&gt;
&lt;/FrameLayout&gt;

}
//ID: 561360
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.1.2'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 845467
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android"&gt;
&lt;PreferenceScreen
    android:key="@string/preference_key"
    android:layout="@layout/preference_item_normal"
    android:title="@string/preference_title" /&gt;
&lt;/PreferenceScreen&gt;

}
//ID: 896032
buildscript {
    ext.kotlin_version = '1.2.71'
    ext.liveData_version = '1.1.1'
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.2.1'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 896032
apply plugin: 'com.android.application'

apply plugin: 'kotlin-android'

apply plugin: 'kotlin-android-extensions'

android {
    compileSdkVersion 28
    defaultConfig {
        applicationId "com.example.danilshik.hamsters"
        minSdkVersion 15
        targetSdkVersion 28
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    implementation 'com.android.support:appcompat-v7:28.0.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'

    //LiveData
    implementation "android.arch.lifecycle:extensions:$liveData_version"
    annotationProcessor "android.arch.lifecycle:compiler:$liveData_version"
}

}
//ID: 616798
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
repositories {
    jcenter()
}
dependencies {
    classpath 'com.android.tools.build:gradle:2.2.3'

    // NOTE: Do not place your application dependencies here; they belong
    // in the individual module build.gradle files
}
}

allprojects {
repositories {
    jcenter()
}
}

task clean(type: Delete) {
delete rootProject.buildDir
}

}
//ID: 948140
&lt;item name="one_m" type="string"&gt;1m&lt;/item&gt;
&lt;item name="five_m" type="string"&gt;5m&lt;/item&gt;
&lt;integer-array name="xmlStringsPeriodValue"&gt;
    &lt;item&gt;@string/one_m&lt;/item&gt;
    &lt;item&gt;@string/five_m&lt;/item&gt;
&lt;/integer-array&gt;

}
//ID: 782269
&lt;activity android:label="TestBrowser" android:name=".ww"&gt;
        &lt;intent-filter tools:ignore="AppLinkUrlError"&gt;
            &lt;action android:name="android.intent.action.VIEW"/&gt;
            &lt;data android:scheme="https"/&gt;
            &lt;data android:scheme="http"/&gt;
            &lt;category android:name="android.intent.category.DEFAULT"/&gt;
        &lt;/intent-filter&gt;
    &lt;/activity&gt;

}
//ID: 504181
Call call = client.newCall(request);
Response response = call.execute();
jsonObject = new JSONObject(response.body().string());
if (response.isSuccessful())
{
    response.body().close();
    call.cancel();
}

}
//ID: 785890
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.0.1'


        // NOTE: Do not place your application dependencies here; they belon
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 785890
apply plugin: 'com.android.application'

android {
    compileSdkVersion 26
    defaultConfig {
        applicationId "com.bakuard.maze"
        minSdkVersion 15
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner 
       "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 
            'proguard-rules.pro'
        }
    }
 }

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:26.1.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-
    core:3.0.1'
}

}
//ID: 622978
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.2.3'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 1063577
android {
    compileSdkVersion 29
    buildToolsVersion "29.0.2"
    defaultConfig {
        applicationId "com.example.homecontrol"
        minSdkVersion 16
        targetSdkVersion 29
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

}
//ID: 1004196
buildscript {
ext.kotlin_version = '1.3.40'
repositories {
    google()
    jcenter()
}
dependencies {
    classpath 'com.android.tools.build:gradle:3.4.1'
    classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    classpath "org.jetbrains.kotlin:kotlin-android-extensions:$kotlin_version"
    classpath 'com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.1'
}
}

task clean(type: Delete) {
delete rootProject.buildDir

}
//ID: 744119
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        jcenter()
        google()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.0.0'
        classpath 'com.google.gms:google-services:3.1.1'
        classpath 'eu.f3rog.blade:plugin:2.2.0'
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        jcenter()
        google()
        maven {
            url "https://maven.google.com"
        }
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 744840
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start"&gt;

    &lt;include
        layout="@layout/toolbar"
        android:layout_width="match_parent"
        android:layout_height="match_parent" /&gt;

    &lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_start"
        app:menu="@menu/menu_main" /&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 744840
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.design.widget.CoordinatorLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    android:background="@android:color/holo_purple"
    tools:context="com.example.march.exider.MainActivity"&gt;

    &lt;include layout="@layout/content_main" /&gt;

    &lt;android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="@style/AppTheme.AppBarOverlay"&gt;

        &lt;android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:popupTheme="@style/AppTheme.PopupOverlay" /&gt;


    &lt;/android.support.design.widget.AppBarLayout&gt;




&lt;/android.support.design.widget.CoordinatorLayout&gt;

}
//ID: 1008420
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start"&gt;

    &lt;include layout="@layout/content_start"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        /&gt;

    &lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_main"
        app:menu="@menu/nav_menu"
        /&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 1008420
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".StartActivity"
    &gt;


    &lt;android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="@style/AppTheme.AppBarOverlay"&gt;

        &lt;android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:popupTheme="@style/AppTheme.PopupOverlay" /&gt;

    &lt;/android.support.design.widget.AppBarLayout&gt;

    &lt;include layout="@layout/content_main" /&gt;


&lt;/android.support.design.widget.CoordinatorLayout&gt;

}
//ID: 1008420
 &lt;?xml version="1.0" encoding="utf-8"?&gt;
   &lt;android.support.constraint.ConstraintLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:layout_behavior="@string/appbar_scrolling_view_behavior"
    tools:context=".StartActivity"
    tools:showIn="@layout/content_start"&gt;
        &lt;fragment
             ...
            /&gt;
      &lt;/android.support.constraint.ConstraintLayout&gt;

}
//ID: 746388
apply plugin: 'com.android.application'  
android {
compileSdkVersion 26
defaultConfig {
    applicationId "com.example.myapplication"
    minSdkVersion 15
    targetSdkVersion 26
    versionCode 1
    versionName "1.0"
    testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
}
buildTypes {
    release {
        minifyEnabled false
        proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
    }
}  
}  

dependencies {  
implementation fileTree(dir: 'libs', include: ['*.jar'])
implementation 'com.android.support:appcompat-v7:26.1.0'
implementation 'com.android.support:design:26.1.0'
testImplementation 'junit:junit:4.12'
androidTestImplementation 'com.android.support.test:runner:1.0.1'
androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'  
}  

}
//ID: 746388
// Top-level build file where you can add configuration options common to all sub-projects/modules.  
buildscript {  

repositories {
    google()
    jcenter()
}
dependencies {
    classpath 'com.android.tools.build:gradle:3.0.0'


    // NOTE: Do not place your application dependencies here; they belong
    // in the individual module build.gradle files
}  
}  

allprojects {  
repositories {
    google()
    jcenter()
}
}  

task clean(type: Delete) {  
delete rootProject.buildDir
}  

}
//ID: 860804
 &lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/fragment_container"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
 /&gt;

}
//ID: 860804
public class MyFragment extends Fragment {
}

}
//ID: 1070486
  dependencies {
        implementation 'com.yandex.android:mapkit:3.4.0'  

    implementation 'com.google.android.gms:play-services-location:17.0.0'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
    implementation 'androidx.recyclerview:recyclerview:1.1.0'
    implementation 'com.google.code.gson:gson:2.8.6'
    implementation 'com.squareup.retrofit2:retrofit:2.0.2'
    implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
    }


allprojects {
    repositories {
        maven {
            url "http://maven.google.com/"
        }
        google()
        jcenter()

    }
}

}
//ID: 461432
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;
    &lt;!-- The main content view --&gt;
    &lt;FrameLayout
        android:id="@+id/content_frame"
        android:layout_width="match_parent"
        android:layout_height="match_parent"&gt;

        &lt;LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical"&gt;

            &lt;LinearLayout
                android:id="@+id/headerbar"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"&gt;

                &lt;include layout="@layout/toolbar" /&gt;

            &lt;/LinearLayout&gt;

            &lt;LinearLayout
                android:id="@+id/main_content"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical"&gt;


            &lt;/LinearLayout&gt;

        &lt;/LinearLayout&gt;

    &lt;/FrameLayout&gt;

    &lt;!-- The navigation drawer --&gt;
    &lt;ListView
        android:id="@+id/left_drawer"
        android:layout_width="240dp"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:background="#fff"
        android:choiceMode="singleChoice"
        android:divider="@android:color/transparent"
        android:dividerHeight="0dp" /&gt;
&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 461459
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;
    &lt;!-- The main content view --&gt;
    &lt;FrameLayout
        android:id="@+id/content_frame"
        android:layout_width="match_parent"
        android:layout_height="match_parent"&gt;

        &lt;LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical"&gt;

            &lt;LinearLayout
                android:id="@+id/headerbar"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"&gt;

                &lt;include layout="@layout/toolbar" /&gt;

            &lt;/LinearLayout&gt;

            &lt;LinearLayout
                android:id="@+id/main_content"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical"&gt;


            &lt;/LinearLayout&gt;

        &lt;/LinearLayout&gt;

    &lt;/FrameLayout&gt;

    &lt;!-- The navigation drawer --&gt;
    &lt;ListView
        android:id="@+id/left_drawer"
        android:layout_width="240dp"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:background="#fff"
        android:choiceMode="singleChoice"
        android:divider="@android:color/transparent"
        android:dividerHeight="0dp" /&gt;
&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 1118454
if (BuildConfig.DEBUG) {
        Timber.plant(Timber.DebugTree())
    }

}
//ID: 798026
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.google.gms:google-services:3.2.0'


        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 798026
apply plugin: 'com.android.application'
apply plugin: 'com.google.gms.google-services'

android {
    compileSdkVersion 26
    defaultConfig {
        applicationId "com.example.danil.smssecurity"
        minSdkVersion 15
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:26.1.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
    compile 'com.google.firebase:firebase-core:11.8.0'
}

}
//ID: 1014101
buildscript {
    repositories {
        google()
        jcenter() {url "http://jcenter.bintray.com"}
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.4.2'
        classpath 'com.google.gms:google-services:4.3.0'
    }
}

apply plugin: 'com.android.application'
apply plugin: 'com.google.gms.google-services'

dependencies {
    implementation fileTree(dir: 'bin', include: ['*.jar'])
    implementation fileTree(dir: 'libs', include: ['*.jar'])
}

android {
    sourceSets {
        main {
            manifest.srcFile 'AndroidManifest.xml'
            //java.srcDirs = ['src']
            res.srcDirs = ['res']
            assets.srcDirs = ['assets']
            jniLibs.srcDirs = ['libs']
        }
    }

    compileSdkVersion 29
    buildToolsVersion '29.0.0'
    defaultConfig {
        targetSdkVersion 29
    }

    lintOptions {
        abortOnError false
    }
}

apply plugin: 'android-library'

allprojects {
    repositories {
        google()
        jcenter {
            url "http://jcenter.bintray.com/"
        }
        mavenCentral()
        flatDir {
            dirs 'libs'
        }
    }
}

}
//ID: 1014101
buildscript {
    repositories {
        google()
        jcenter() {url "http://jcenter.bintray.com"}
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.4.2'
        classpath 'com.google.gms:google-services:4.3.0'
    }
}

apply plugin: 'com.android.application'
apply plugin: 'com.google.gms.google-services'

dependencies {
    implementation fileTree(dir: 'bin', include: ['*.jar'])
    implementation fileTree(dir: 'libs', include: ['*.jar'])
}

android {
    sourceSets {
        main {
            manifest.srcFile 'AndroidManifest.xml'
            //java.srcDirs = ['src']
            res.srcDirs = ['res']
            assets.srcDirs = ['assets']
            jniLibs.srcDirs = ['libs']
        }
    }

    compileSdkVersion 29
    buildToolsVersion '29.0.0'
    defaultConfig {
        targetSdkVersion 29
    }

    lintOptions {
        abortOnError false
    }
}

apply plugin: 'android-library'

allprojects {
    repositories {
        google()
        jcenter {
            url "http://jcenter.bintray.com/"
        }
        mavenCentral()
        flatDir {
            dirs 'libs'
        }
    }
}

}
//ID: 1014101
buildscript {
    repositories {
        google()
        jcenter() {url "http://jcenter.bintray.com"}
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.4.2'
        classpath 'com.google.gms:google-services:4.3.0'
    }
}

apply plugin: 'com.android.application'
apply plugin: 'com.google.gms.google-services'

dependencies {
    implementation fileTree(dir: 'bin', include: ['*.jar'])
    implementation fileTree(dir: 'libs', include: ['*.jar'])
}

android {
    sourceSets {
        main {
            manifest.srcFile 'AndroidManifest.xml'
            //java.srcDirs = ['src']
            res.srcDirs = ['res']
            assets.srcDirs = ['assets']
            jniLibs.srcDirs = ['libs']
        }
    }

    compileSdkVersion 29
    buildToolsVersion '29.0.0'
    defaultConfig {
        targetSdkVersion 29
    }

    lintOptions {
        abortOnError false
    }
}

apply plugin: 'android-library'

allprojects {
    repositories {
        google()
        jcenter {
            url "http://jcenter.bintray.com/"
        }
        mavenCentral()
        flatDir {
            dirs 'libs'
        }
    }
}

}
//ID: 801397
apply plugin: 'com.android.application'

android {
    compileSdkVersion 26
    defaultConfig {
        applicationId "com.example.ebalabanova.ttt"
        minSdkVersion 19
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:26.1.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
}

}
//ID: 801397
// Top-level build file where you can add configuration options common to 
// all sub-projects/modules.

buildscript {

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.0.1'


        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 755410
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.0.1'
        classpath 'com.google.gms:google-services:3.0.0'
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        jcenter()
        maven{
            url "https://maven.google.com"
        }
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 1078549
buildscript {
ext.kotlin_version = '1.3.50'
repositories {
    google()
    jcenter()

}
dependencies {
    classpath 'com.android.tools.build:gradle:3.5.3'
    classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    // NOTE: Do not place your application dependencies here; they belong
    // in the individual module build.gradle files
}
}

allprojects {
repositories {
    google()
    jcenter()

}
}

task clean(type: Delete) {
delete rootProject.buildDir
}

}
//ID: 526277
&lt;include
    layout="@layout/app_bar_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent" /&gt;

&lt;android.support.design.widget.NavigationView
    android:id="@+id/nav_view"
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:layout_gravity="start"
    android:fitsSystemWindows="true"
    app:headerLayout="@layout/nav_header_main"
    app:menu="@menu/activity_main_drawer" /&gt;

}
//ID: 870662
&lt;resources&gt;

    &lt;string name="app_name"&gt;Простейшее&lt;/string&gt;
    &lt;string-array name="options1"&gt;
        &lt;item&gt;Пункт_1&lt;/item&gt;
        &lt;item&gt;Пункт_2&lt;/item&gt;
        &lt;item&gt;Пункт_3&lt;/item&gt;
    &lt;/string-array&gt;

    &lt;string-array name="options2"&gt;
        &lt;item&gt;Пункт_4&lt;/item&gt;
        &lt;item&gt;Пункт_5&lt;/item&gt;
        &lt;item&gt;Пункт_6&lt;/item&gt;
    &lt;/string-array&gt;

&lt;/resources&gt;

}
//ID: 1126925
apply plugin: 'com.android.application'

android {
    compileSdkVersion 29
    buildToolsVersion "29.0.2"
    defaultConfig {
        applicationId "com.example.blagodari"
        minSdkVersion 17
        targetSdkVersion 29
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'androidx.recyclerview:recyclerview:1.1.0'
    implementation 'androidx.cardview:cardview:1.0.0'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    implementation 'com.google.android.material:material:1.1.0'
    implementation 'androidx.navigation:navigation-fragment:2.2.2'
    implementation 'androidx.navigation:navigation-ui:2.2.2'
    implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
}

}
//ID: 472532
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;
    &lt;fragment xmlns:android="http://schemas.android.com/apk/res/android"
              xmlns:tools="http://schemas.android.com/tools"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:id="@+id/map"
              tools:context="xx.xxx.xxx.CourierActivity"
              android:name="com.google.android.gms.maps.MapFragment"/&gt;
   &lt;!-- other elements --&gt;
&lt;/RelativeLayout&gt;

}
//ID: 529614
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.1.0'
        compile 'com.android.support:cardview-v7:23.3.0'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}
allprojects {
    repositories {
        jcenter()
    }
}
task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 1024522
apply plugin: 'com.android.application'

android {
    compileSdkVersion 29
    buildToolsVersion "29.0.2"
    defaultConfig {
        applicationId "com.example.navigatiobarexmpl"
        minSdkVersion 15
        targetSdkVersion 29
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.appcompat:design:1.1.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test:runner:1.2.0'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'

}

}
//ID: 977539

xml:

}
//ID: 927205
&lt;fragment
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:map="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
android:id="@+id/map"
class="com.google.android.gms.maps.SupportMapFragment"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context=".activities.navigation.MapsActivity" /&gt;

}
//ID: 1083957
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:1.2.3'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        jcenter()
    }
}

}
//ID: 1086591
        &lt;activity android:name=".MainActivity"&gt;
            &lt;intent-filter&gt;
                &lt;action android:name="android.intent.action.MAIN" /&gt;

                &lt;category android:name="android.intent.category.LAUNCHER" /&gt;
            &lt;/intent-filter&gt;

                        &lt;intent-filter android:label="22"&gt;
                            &lt;action android:name="android.intent.action.VIEW" /&gt;
                            &lt;category android:name="android.intent.category.DEFAULT" /&gt;
                            &lt;category android:name="android.intent.category.BROWSABLE" /&gt;
                            &lt;data android:scheme="myapplnfy"/&gt;
                        &lt;/intent-filter&gt;

        &lt;/activity&gt;

}
//ID: 536627
&lt;FrameLayout
xmlns:android="http://schemas.android.com/apk/res/android"
android:id="@+id/fragment_container"
android:layout_width="match_parent"
android:layout_height="match_parent"/&gt;

}
//ID: 648998
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.3.0'
        classpath 'me.tatarka:gradle-retrolambda:3.3.1'
        classpath "io.realm:realm-gradle-plugin:3.0.0"

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        jcenter()
        mavenCentral()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 651215
buildscript {
    ext.kotlin_version = '1.1.1'
    apply from: 'scripts/dependencies.gradle'
    apply from: 'scripts/testDependencies.gradle'
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.3.1'
        classpath 'me.tatarka:gradle-retrolambda:3.6.0'
        classpath "io.realm:realm-gradle-plugin:2.3.1"
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }
}

allprojects {
    repositories {
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 816527
apply plugin: 'com.android.application'

android {
    compileSdkVersion 27
    buildToolsVersion '27.0.3'
    defaultConfig {

        applicationId "com.example.sergey.loginpage"
        minSdkVersion 16
        targetSdkVersion 27
        versionCode 1
        versionName "1.0"
        multiDexEnabled true
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
        manifestPlaceholders = [onesignal_app_id               : "2c668d75-ec07-4364-bf6c-666a0f052ec5",
                                onesignal_google_project_number: "392170537213"]
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

buildscript {
    repositories {
        maven { url 'https://plugins.gradle.org/m2/'}
        google()
    }
    dependencies {
        classpath 'gradle.plugin.com.onesignal:onesignal-gradle-plugin:0.8.1'


    }
}

apply plugin: 'com.onesignal.androidsdk.onesignal-gradle-plugin'

repositories {
    maven { url 'https://maven.google.com' }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:27.1.1'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
    implementation 'com.google.code.gson:gson:2.8.2'
    implementation 'com.googlecode.json-simple:json-simple:1.1.1'
    implementation 'com.android.support:appcompat-v7:27.1.1'
    implementation 'com.mcxiaoke.volley:library-aar:1.0.0'
    implementation 'com.android.support:design:27.1.1'
    implementation 'com.squareup.picasso:picasso:2.71828'
    implementation 'com.android.support.constraint:constraint-layout:1.1.0'
    implementation 'com.neovisionaries:nv-websocket-client:2.4'
    implementation 'org.java-websocket:Java-WebSocket:1.3.8'
    implementation 'io.socket:socket.io-client:0.8.3'
    implementation 'com.onesignal:OneSignal:3.8.4'
    implementation 'com.google.android.gms:play-services-gcm:15.0.0'
    implementation 'com.google.android.gms:play-services-location:15.0.0'
    testImplementation 'junit:junit:4.12'
} 

}
//ID: 712331
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/apk/res-auto"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context=".MainActivity"&gt;

&lt;android.support.v7.widget.Toolbar
    android:id="@+id/toolbar"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="?attr/colorPrimary"
    android:elevation="4dp"
    android:minHeight="?attr/actionBarSize"
    android:paddingTop="@dimen/tool_bar_top_padding"
    android:transitionName="actionBar" /&gt;

&lt;/RelativeLayout&gt;

}
//ID: 1031668
    android {
    compileSdkVersion 28
    defaultConfig {
        applicationId "shihzaman.com.my"
        minSdkVersion 19
        targetSdkVersion 28
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'androidx.appcompat:appcompat:1.0.2'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.0'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.1.1'
    }  

}
//ID: 484245
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start"&gt;

    &lt;fragment xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:map="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/map"
        android:name="com.google.android.gms.maps.SupportMapFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context="bertam.stopdps.MapActivity" /&gt;

    &lt;include
        layout="@layout/app_bar_map"
        android:layout_width="match_parent"
        android:layout_height="match_parent" /&gt;


    &lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_map"
        app:menu="@menu/activity_map_drawer" /&gt;
    &lt;/android.support.v4.widget.DrawerLayout&gt;

app_bar_map: 

&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:context="bertam.stopdps.MapActivity"&gt;

    &lt;android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom"
        android:theme="@style/AppTheme.AppBarOverlay"&gt;

        &lt;android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:background="?attr/colorPrimary"
            app:popupTheme="@style/AppTheme.PopupOverlay" /&gt;
    &lt;/android.support.design.widget.AppBarLayout&gt;

    &lt;ImageView
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|end"
        android:layout_margin="@dimen/fab_margin"
        android:src="@drawable/rec_off" /&gt;


&lt;/android.support.design.widget.CoordinatorLayout&gt;

}
//ID: 1140218
apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'

android {
    compileSdkVersion 29
    buildToolsVersion &quot;29.0.3&quot;
    

    defaultConfig {
        applicationId &quot;com.example.searchgame&quot;
        minSdkVersion 14
        targetSdkVersion 29
        versionCode 1
        versionName &quot;1.0&quot;

        testInstrumentationRunner &quot;androidx.test.runner.AndroidJUnitRunner&quot;
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation &quot;org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version&quot;
    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.core:core-ktx:1.3.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'androidx.preference:preference:1.1.1'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
}
`

}
//ID: 1035056
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;ScrollView
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;
&lt;TableLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="@android:color/black"&gt;
        &lt;TableRow
            android:layout_width="match_parent"
            android:layout_height="match_parent"&gt;
        &lt;/TableRow&gt;
        &lt;TableRow
            android:layout_width="match_parent"
            android:layout_height="match_parent"&gt;
        &lt;/TableRow&gt;
        &lt;TableRow
            android:layout_width="match_parent"
            android:layout_height="match_parent"&gt;
        &lt;/TableRow&gt;
        &lt;TableRow
            android:layout_width="match_parent"
            android:layout_height="match_parent"&gt;
        &lt;/TableRow&gt;
        &lt;TableRow
            android:layout_width="match_parent"
            android:layout_height="match_parent"&gt;
        &lt;/TableRow&gt;
&lt;/TableLayout&gt;
&lt;/ScrollView&gt;



}
//ID: 1036175
class FilterFragment : BottomSheetDialogFragment()
{
    companion object
    {
        val TAG = "BORROM_FILTER"
    }

    private lateinit var viewModel: FilterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val inflater= (context as Activity).getLayoutInflater()
        val fragmentBinding: FragmentBottomFilterMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bottom_filter_main, null, false)

        val rootView = fragmentBinding.root

        if (Build.VERSION.SDK_INT &gt;= Build.VERSION_CODES.LOLLIPOP)
        {
            rootView.apply_btn.letterSpacing = 0f
        }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FilterViewModel::class.java)
    }
}

class MainHandler()
{
    public fun onClickItem(view: View)
    {

    }
}

}
//ID: 1036175
&lt;layout
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"&gt;

&lt;data&gt;
&lt;/data&gt;

&lt;LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"&gt;

    &lt;include
        layout="@layout/layout_filter_main"
        android:id="@+id/main_layout" /&gt;

    &lt;include
        layout="@layout/layout_filter_dates"
        android:id="@+id/dates_layout"
        android:visibility="gone" /&gt;

&lt;/LinearLayout&gt;

}
//ID: 1037875
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.5.1'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()

    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 717098
&lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    tools:context="com.example.kombo.eplog.fragments.MusContent"&gt;

    &lt;android.support.v4.view.ViewPager
        android:id="@+id/pager"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:fitsSystemWindows="true"
        app:layout_behavior="@string/appbar_scrolling_view_behavior"&gt;

    &lt;/android.support.v4.view.ViewPager&gt;

&lt;/FrameLayout&gt;

}
//ID: 484963
&lt;android.support.v4.widget.DrawerLayout
  xmlns:android="http://schemas.android.com/apk/res/android"
  android:id="@+id/drawer_layout"
  android:layout_width="match_parent"
  android:layout_height="match_parent"&gt;

  &lt;FrameLayout
   android:layout_width="match_parent"
   android:layout_height="match_parent"/&gt;

  &lt;android.support.v4.widget.SwipeRefreshLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/refresher"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.shppandroid1.app.MainActivty"&gt;

     &lt;WebView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/wv"/&gt;

  &lt;/android.support.v4.widget.SwipeRefreshLayout&gt;

    &lt;LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/ll_bg"&gt;

    &lt;/LinearLayout&gt;

  &lt;FrameLayout
    android:id="@+id/content_frame"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/&gt;

  &lt;LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="290dp"
    android:layout_gravity="start"
    android:id="@+id/left_drawer"
    android:orientation="vertical"
    android:layout_height="match_parent"&gt;


    &lt;ImageView
        android:id="@+id/image"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/header" /&gt;


    &lt;ListView
        android:id="@+id/left_drawer_child"
        android:layout_width="290dp"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:background="#FFFFFF"
        android:paddingLeft="3sp"
        android:paddingRight="3sp"
        android:listSelector="#C2C2C2"/&gt;

    &lt;/LinearLayout&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 717415
    &lt;android.support.v4.view.ViewPager
    android:id="@+id/vp_search"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;android.support.v4.view.PagerTabStrip
        android:id="@+id/pts_search"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="top"
        android:background="@color/colorPrimary" /&gt;
&lt;/android.support.v4.view.ViewPager&gt;



 public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    pagerAdapter = new AdapterPageSearch(getActivity().getSupportFragmentManager());
    viewPager.setAdapter(pagerAdapter);
}

}
//ID: 312733
&lt;string-array name="nav_drawer_items"&gt;
    &lt;item&gt;Главная&lt;/item&gt;
    &lt;item&gt;Игры для ПК&lt;/item&gt;
    &lt;item&gt;Игры для PSP&lt;/item&gt;
    &lt;item&gt;Игры для XBOX&lt;/item&gt;
    &lt;item&gt;Android&lt;/item&gt;
    &lt;item&gt;Фильмы HD&lt;/item&gt;
    &lt;item&gt;Программы&lt;/item&gt;
    &lt;item&gt;Обои для ПК&lt;/item&gt;
    &lt;item&gt;Музыка&lt;/item&gt;
    &lt;item&gt;iPhone/iPad&lt;/item&gt;
    &lt;item&gt;Журналы&lt;/item&gt;
&lt;/string-array&gt;

&lt;array name="nav_drawer_icons"&gt;
    &lt;item&gt;@drawable/ic_home&lt;/item&gt;
    &lt;item&gt;@drawable/ic_pc&lt;/item&gt;
    &lt;item&gt;@drawable/ic_psp&lt;/item&gt;
    &lt;item&gt;@drawable/ic_xbox&lt;/item&gt;
    &lt;item&gt;@drawable/ic_android&lt;/item&gt;
    &lt;item&gt;@drawable/ic_video&lt;/item&gt;
    &lt;item&gt;@drawable/ic_software&lt;/item&gt;
    &lt;item&gt;@drawable/ic_wallpapers&lt;/item&gt;
    &lt;item&gt;@drawable/ic_music&lt;/item&gt;
    &lt;item&gt;@drawable/ic_apple&lt;/item&gt;
    &lt;item&gt;@drawable/ic_book&lt;/item&gt;
&lt;/array&gt;

}
//ID: 1041664
class TrainingsDiffUtils(
private val oldItem: List&lt;ItemGrouper&gt;,
private val newItem: ArrayList&lt;ItemGrouper&gt;
) : DiffUtil.Callback() {

override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    val oldItem = oldItem[oldItemPosition]
    val newItem = newItem[newItemPosition]

    return when {
        oldItem is Training &amp;&amp; newItem is Training -&gt;
            ((oldItem)).id == ((newItem)).id
        oldItem is MoreItem &amp;&amp; newItem is MoreItem -&gt; true
        else -&gt; false
    }
}

override fun getOldListSize(): Int = oldItem.size

override fun getNewListSize(): Int = newItem.size

override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = true
}

}
//ID: 314158
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;LinearLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"&gt;
    &lt;LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"&gt;
        &lt;Button
                android:id="@+id/btnTest"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:onClick="onClickTest"
                android:text="dsfgdfg"&gt;
        &lt;/Button&gt;
    &lt;/LinearLayout&gt;
    &lt;fragment
            android:id="@+id/map"
            android:name="com.google.android.gms.maps.SupportMapFragment"
            android:layout_width="match_parent"
            android:layout_height="match_parent"&gt;
    &lt;/fragment&gt;
&lt;/LinearLayout&gt;

}
//ID: 717963
public class Contact {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title_ru")
    @Expose
    private String titleRu;

    @SerializedName("title_eng")
    @Expose
    private String titleEng;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("img_url")
    @Expose
    private String imageUrl;

    @SerializedName("weight")
    @Expose
    private int weight;

    @SerializedName("menu_id")
    @Expose
    private int menuId;

    @SerializedName("status")
    @Expose
    private int status;

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @return The name
     */
    public String getTitleRu() {
        return titleRu;
    }

    /**
     * @return The email
     */
    public String getTitleEng() {
        return titleEng;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getWeight() {
        return weight;
    }

    public int getMenuId() {
        return menuId;
    }

    public int getStatus() {
        return status;
    }
}

}
//ID: 718288
&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_height="match_parent"
    android:layout_width="match_parent"
    android:id="@+id/myDrawer"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    tools:context="zyklen.com.clientcoord.MapsActivity"&gt;

     &lt;fragment
         android:id="@+id/map"
         android:name="com.google.android.gms.maps.SupportMapFragment"
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         android:layout_alignParentTop="true"
         android:layout_alignParentLeft="true"
         android:layout_alignParentStart="true" /&gt;


    &lt;RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"&gt;

        &lt;include
            layout="@layout/toolbar"/&gt;

        &lt;include
            layout="@layout/floating_menu"/&gt;
    &lt;/RelativeLayout&gt;



    &lt;android.support.design.widget.NavigationView
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:id="@+id/navik"
        app:menu="@menu/navigation_menu"
        android:layout_gravity="start"
        app:itemTextColor="#383636"
        app:itemIconTint="#131313"
        app:headerLayout="@layout/navigation_header"&gt;

    &lt;/android.support.design.widget.NavigationView&gt;




&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 766512
apply plugin: 'com.android.application'

android {
    compileSdkVersion 26
    defaultConfig {
        applicationId "com.example.x.picassomodule"
        minSdkVersion 15
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:26.1.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
    compile 'com.squareup.picasso:picasso:2.5.2'
}

}
//ID: 1090499
fun archiveNote(id: Int?, context: Context) {
        apiService().archiveNote("Bearer " + context.getSharedPreferences("app_data", 0).getString("access_token", ""), id).enqueue(object : Callback&lt;NoteAction&gt; {
            override fun onResponse(call: Call&lt;NoteAction&gt;, response: Response&lt;NoteAction&gt;) {
                if (response.isSuccessful) {

                } else {
                    if (response.code() == 500) {
                        Toast.makeText(context, context.getString(R.string.server_error_500), Toast.LENGTH_SHORT).show()
                    } else {
                        try {
                            val jObjError = JSONObject(Objects.requireNonNull&lt;ResponseBody&gt;(response.errorBody()).string())
                            error["message"] = jObjError.getString("message")
                            error["request_no"] = "20"
                            workingWithErrors(error, context)
                        } catch (e: Exception) {
                            //Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                }
            }

            override fun onFailure(call: Call&lt;NoteAction&gt;, t: Throwable) {

            }

        })
    }

}
//ID: 1090499
if (response.isSuccessful) {

} else {

}

}
//ID: 767037
apply plugin: 'com.android.application'

android {
    compileSdkVersion 26
    buildToolsVersion "25.0.3"
    defaultConfig {
        applicationId "com.football.fariz.football"
        minSdkVersion 16
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    compile 'com.android.support:support-v4:26.0.1'
    compile 'com.daasuu:BubbleLayout:1.2.0'
    compile 'com.android.support:appcompat-v7:26.0.1'
    compile 'com.squareup.picasso:picasso:2.5.2'
    compile 'com.squareup.okhttp3:okhttp:3.9.1'
    compile 'com.android.support:design:26.0.1'
    compile 'com.google.android.gms:play-services-maps:11.0.4'
    compile 'com.google.android.gms:play-services-location:11.0.4'
    compile 'com.mcxiaoke.volley:library-aar:1.0.0'
    compile 'com.android.support:cardview-v7:26.0.1'
    compile 'com.android.support.constraint:constraint-layout:1.0.2'
    compile 'com.google.firebase:firebase-messaging:11.0.4'
    compile 'com.jaredrummler:android-device-names:1.1.5'
    compile 'com.android.support:support-vector-drawable:26.+'
}

}
//ID: 982449
@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase : RoomDatabase() {
abstract fun wordDao(): WordDao

  companion object {
    @Volatile
    private var INSTANCE: WordRoomDatabase? = null

    fun getDatabase(
            context: Context,
            scope: CoroutineScope
    ): WordRoomDatabase {
        // if the INSTANCE is not null, then return it,
        // if it is, then create the database
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
            )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
            INSTANCE = instance
            // return instance
            instance
        }
    }

    private class WordDatabaseCallback(
            private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        /**
         * Override the onOpen method to populate the database.
         * For this sample, we clear the database every time it is created or opened.
         */
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            // If you want to keep the data through app restarts,
            // comment out the following line.
            INSTANCE?.let { database -&gt;
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.wordDao())
                }
            }
        }
    }

    /**
     * Populate the database in a new coroutine.
     * If you want to start with more words, just add them.
     */
    suspend fun populateDatabase(wordDao: WordDao) {
        // Start the app with a clean database every time.
        // Not needed if you only populate on creation.
        wordDao.deleteAll()

        var word = Word("Hello")
        wordDao.insert(word)
        word = Word("World!")
        wordDao.insert(word)
    }
  }
}

}
//ID: 982697
&lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".AFragment"
    android:id="@+id/frame_kek"&gt;

}
//ID: 428890
  java.lang.IllegalStateException: Could not find a method 
onClickCancel(View) in the activity class android.view.ContextThemeWrapper for onClick handler on view class android.widget.Button with id 'button5'

}
//ID: 660527
  jsonObject = new JSONObject(getResponse);
                JSONObject jsonObject1 = jsonObject.getJSONObject("data");

                JSONObject json = jsonObject1.getJSONObject("179746");

}
//ID: 1091016
@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

}

}
//ID: 831575
&lt;uses-permission android:name="android.permission.READ_PHONE_STATE" /&gt;
&lt;uses-permission android:name="android.permission.WAKE_LOCK" /&gt;
&lt;uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" /&gt;
&lt;uses-permission android:name="android.permission.VIBRATE" /&gt;

&lt;service
    android:name=".ManagerService"
    android:enabled="true"
    android:exported="true" /&gt;
&lt;service
    android:name=".AlarmService"
    android:enabled="true"
    android:exported="true"
    android:permission="android.permission.VIBRATE" /&gt;

&lt;receiver
    android:name=".AlarmReceiver"
    android:enabled="true"
    android:exported="true"
    android:permission="android.permission.WAKE_LOCK" /&gt;
&lt;receiver
    android:name=".BootReceiver"
    android:enabled="true"
    android:exported="true"
    android:permission="android.permission.RECEIVE_BOOT_COMPLETED"&gt;
    &lt;intent-filter&gt;
        &lt;action android:name="android.intent.action.BOOT_COMPLETED" /&gt;
        &lt;action android:name="android.intent.action.QUICKBOOT_POWERON" /&gt;
        &lt;action android:name="com.htc.intent.action.QUICKBOOT_POWERON" /&gt;
        &lt;action android:name="android.intent.action.REBOOT" /&gt;

        &lt;category android:name="android.intent.category.DEFAULT" /&gt;
    &lt;/intent-filter&gt;
&lt;/receiver&gt;

}
//ID: 548609
@Component(modules = DogModule.class)
@Singleton
public interface AppComponent {
    void inject(MainActivity mainActivity);
}

}
//ID: 548609
public class App extends Application {




    public static AppComponent getComponent() {

         return DaggerAppComponent
                .builder()
                .dogModule(new DogModule())
                .build();

    }

//    @Override
//    public void onCreate() {
//        super.onCreate();
//        component = buildComponent();
//    }
//
//
//    protected AppComponent buildComponent() {
//
//
//        return DaggerAppComponent
//                .builder()
//                .dogModule(new DogModule())
//                .build();
//    }




}

}
//ID: 660913
&lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_gallery"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#313130"
    android:orientation="vertical"
    tools:context=".PreviewActivity"&gt;

    &lt;android.support.v4.view.ViewPager
        android:id="@+id/preview_pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/&gt;

&lt;/RelativeLayout&gt;

}
//ID: 602944
 @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                saveImage();
            } else {
            }  
    }

}
//ID: 429897
&lt;receiver android:name=".BCreceiver" android:enabled="true"&gt;         
    &lt;intent-filter android:priority="1000000000"&gt;
        &lt;action android:name="android.intent.action.MEDIA_BUTTON" /&gt;
        &lt;category android:name="com.example.MOJE" /&gt;
    &lt;/intent-filter&gt;
&lt;/receiver&gt; 

}
//ID: 883203
&lt;string-array name="di_heads"&gt;
    &lt;item&gt;&lt;![CDATA[&lt;b&gt;&amp;nbsp;&amp;nbsp;*********]]&gt;&lt;/item&gt;
    &lt;item&gt;&lt;![CDATA[&lt;b&gt;&amp;nbsp;&amp;nbsp;*********]]&gt;&lt;/item&gt;
&lt;/string-array&gt;

}
//ID: 719949
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"&gt;

    &lt;android.support.design.widget.TabLayout
        android:id="@+id/tabs"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:tabMode="fixed"/&gt;

    &lt;android.support.v4.view.ViewPager
        android:id="@+id/viewPager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior" /&gt;

&lt;/LinearLayout&gt;

}
//ID: 549138
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;fragment
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:name="***.OtherFragment"
        android:id="@+id/fragment"
        tools:layout="@layout/fr_other" /&gt;

    &lt;fragment
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:name="***.ButtonOrderFragment"
        android:id="@+id/fragment5"
        tools:layout="@layout/afr_application_form_subscription" /&gt; 
&lt;/LinearLayout&gt;

}
//ID: 153522
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;resources&gt;

    &lt;integer-array name="captions_item"&gt;
        &lt;item&gt;@array/geometry&lt;/item&gt;
        &lt;item&gt;@array/probability_theory_captions&lt;/item&gt;
    &lt;/integer-array&gt;

&lt;/resources&gt;

}
//ID: 1044586
class WordRepository {

private WordDao mWordDao;
private LiveData&lt;List&lt;Word&gt;&gt; mAllWords;

WordRepository(Application application) {
    WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
    mWordDao = db.wordDao();
    mAllWords = mWordDao.getAlphabetizedWords();
}

LiveData&lt;List&lt;Word&gt;&gt; getAllWords() {
    return mAllWords;
}

void insert(Word word) {
    new insertAsyncTask(mWordDao).execute(word);
}

private static class insertAsyncTask extends AsyncTask&lt;Word, Void, Void&gt; {

    private WordDao mAsyncTaskDao;

    insertAsyncTask(WordDao dao) {
        mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final Word... params) {
        mAsyncTaskDao.insert(params[0]);
        return null;
    }
}
}

}
//ID: 768750
/ Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.0.1'


        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url "https://maven.google.com"
        }
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 431067
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v7.widget.Toolbar xmlns:android="http://schemas.android.com/apk/res/android"
android:id="@+id/toolbar"
android:elevation="4dp"
android:layout_width="match_parent"
android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
android:layout_height="wrap_content"
android:background="@color/status_bar_orange"/&gt;

}
//ID: 833317
Failed to resolve: com.android.databinding:baseLibrary:3.2.0-alpha04
Show in File
Show in Project Structure dialog


Failed to resolve: com.android.databinding:library:3.2.0-alpha04
Open File
Show in Project Structure dialog


Failed to resolve: com.android.databinding:adapters:3.2.0-alpha04
Open File
Show in Project Structure dialog

}
//ID: 1091936
private boolean checkOverlayPermission() {
        if (Build.VERSION.SDK_INT &gt;= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(this)) {
                return true;
            } else {
                startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), OVERLAY_PERMISSION_REQUEST);
                return false;
            }
        }
        return true;
    }

}
//ID: 662230
&lt;android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="@style/ThemeOverlay.AppCompat.Dark"&gt;

        &lt;android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:layout_scrollFlags="scroll|enterAlways" /&gt;

        &lt;android.support.design.widget.TabLayout
            android:id="@+id/tablayout"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:tabGravity="fill"
            app:tabMode="fixed" /&gt;

    &lt;/android.support.design.widget.AppBarLayout&gt;

    &lt;android.support.v4.view.ViewPager
        android:id="@+id/viewpager"
        android:layout_width="match_parent"
        android:layout_height="match_parent" /&gt;

&lt;/android.support.design.widget.CoordinatorLayout&gt;

}
//ID: 768601
&lt;LinearLayout xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    &gt;
    &lt;LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"&gt;

        &lt;Button
            android:id="@+id/SMarker"
            android:layout_width="400dp"
            android:layout_height="wrap_content"
            android:onClick="AddMA"
            android:text="Set Marker" /&gt;

    &lt;/LinearLayout&gt;
&lt;com.github.clans.fab.FloatingActionButton
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_alignParentBottom="true"
    android:layout_alignParentRight="true"
    /&gt;
    &lt;fragment xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:map="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/map"
        android:name="com.google.android.gms.maps.SupportMapFragment"
        android:layout_width="400dp"
        android:layout_height="464dp"
        tools:context="com.example.karas.mapv2.MapsActivity" &gt;
    &lt;/fragment&gt;

&lt;/LinearLayout&gt;

}
//ID: 1045609
apply plugin: 'com.android.application'

android {
    compileSdkVersion 29
    buildToolsVersion "29.0.2"
    defaultConfig {
        applicationId "dima113xxx.idz_math"
        minSdkVersion 16
        targetSdkVersion 29
        versionCode 2
        versionName "2"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'androidx.appcompat:appcompat:1.0.2'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.1.1'
    implementation 'com.android.billingclient:billing:1.2'
}

}
//ID: 1045609
  // Top-level build file where you can add configuration options common to all sub-projects/modules.

    buildscript {
        repositories {
            google()
            jcenter()

        }
        dependencies {
            classpath 'com.android.tools.build:gradle:3.5.1'

            // NOTE: Do not place your application dependencies here; they belong
            // in the individual module build.gradle files
        }
    }

    allprojects {
        repositories {
            google()
            jcenter()

        }
    }

    task clean(type: Delete) {
        delete rootProject.buildDir
    }

}
//ID: 489176
    &lt;receiver
       android:name=".Reciver" 
       android:enabled="true"
       android:exported="true" &gt;
              &lt;intent-filter&gt;
                    &lt;action android:name="android.intent.action.BOOT_COMPLETED" /&gt;  
                &lt;/intent-filter&gt;
   &lt;/receiver&gt;

}
//ID: 884579
fun View.placeBackgroundDrawable(drawable: Drawable) {
    if (Build.VERSION.SDK_INT &gt;= Build.VERSION_CODES.JELLY_BEAN) {
        background = drawable
    } else {
        @Suppress("DEPRECATION")
        setBackgroundDrawable(drawable)
    }
}

fun Int.isEven() = rem(2) == 0

}
//ID: 327632
&lt;integer-array name="fizic_img"&gt;
    &lt;item&gt;0x7f020001&lt;/item&gt;
    &lt;item&gt;0x7f020002&lt;/item&gt;
    &lt;item&gt;0x7f020003&lt;/item&gt;
    &lt;item&gt;0x7f020004&lt;/item&gt;
    &lt;item&gt;0x7f020005&lt;/item&gt;
    &lt;item&gt;0x7f020006&lt;/item&gt;
    &lt;item&gt;0x7f020007&lt;/item&gt;
    &lt;item&gt;0x7f020008&lt;/item&gt;
    &lt;item&gt;0x7f020009&lt;/item&gt;
    &lt;item&gt;0x7f02000a&lt;/item&gt;
&lt;/integer-array&gt;

}
//ID: 662943
com.harbinger.simple_http_request, PID: 23475
                                                                                       java.lang.IllegalStateException: Could not execute method for android:onClick
                                                                                           at android.support.v7.app.AppCompatViewInflater$DeclaredOnClickListener.onClick(AppCompatViewInflater.java:293)
                                                                                           at android.view.View.performClick(View.java:5624)
                                                                                           at android.view.View$PerformClick.run(View.java:22441)
                                                                                           at android.os.Handler.handleCallback(Handler.java:751)
                                                                                           at android.os.Handler.dispatchMessage(Handler.java:95)
                                                                                           at android.os.Looper.loop(Looper.java:154)
                                                                                           at android.app.ActivityThread.main(ActivityThread.java:6317)
                                                                                           at java.lang.reflect.Method.invoke(Native Method)
                                                                                           at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:872)
                                                                                           at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:762)
                                                                                        Caused by: java.lang.reflect.InvocationTargetException
                                                                                           at java.lang.reflect.Method.invoke(Native Method)
                                                                                           at android.support.v7.app.AppCompatViewInflater$DeclaredOnClickListener.onClick(AppCompatViewInflater.java:288)
                                                                                           at android.view.View.performClick(View.java:5624) 
                                                                                           at android.view.View$PerformClick.run(View.java:22441) 
                                                                                           at android.os.Handler.handleCallback(Handler.java:751) 
                                                                                           at android.os.Handler.dispatchMessage(Handler.java:95) 
                                                                                           at android.os.Looper.loop(Looper.java:154) 
                                                                                           at android.app.ActivityThread.main(ActivityThread.java:6317) 
                                                                                           at java.lang.reflect.Method.invoke(Native Method) 
                                                                                           at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:872) 
                                                                                           at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:762) 
                                                                                        Caused by: java.lang.ClassCastException: android.support.constraint.ConstraintLayout cannot be cast to android.widget.TextView
                                                                                           at com.harbinger.simple_http_request.MainActivity.getCurrencyClick(MainActivity.java:30)
                                                                                           at java.lang.reflect.Method.invoke(Native Method) 
                                                                                           at android.support.v7.app.AppCompatViewInflater$DeclaredOnClickListener.onClick(AppCompatViewInflater.java:288) 
                                                                                           at android.view.View.performClick(View.java:5624) 
                                                                                           at android.view.View$PerformClick.run(View.java:22441) 
                                                                                           at android.os.Handler.handleCallback(Handler.java:751) 
                                                                                           at android.os.Handler.dispatchMessage(Handler.java:95) 
                                                                                           at android.os.Looper.loop(Looper.java:154) 
                                                                                           at android.app.ActivityThread.main(ActivityThread.java:6317) 
                                                                                           at java.lang.reflect.Method.invoke(Native Method)

}
//ID: 550937
&lt;android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:fitsSystemWindows="true"
tools:context="net.artsait.myapplication.MainActivity"&gt;

&lt;android.support.design.widget.AppBarLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:theme="@style/AppTheme.AppBarOverlay"&gt;

    &lt;android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:background="?attr/colorPrimary"
        app:popupTheme="@style/AppTheme.PopupOverlay" /&gt;


    &lt;android.support.design.widget.TabLayout
        android:id="@+id/tabs"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:tabMode="fixed"
        app:tabGravity="fill"/&gt;

&lt;/android.support.design.widget.AppBarLayout&gt;

&lt;android.support.v4.view.ViewPager
    android:id="@+id/viewpager"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:layout_behavior="@string/appbar_scrolling_view_behavior"  /&gt;

&lt;include layout="@layout/content_main" /&gt;

}
//ID: 722109
&lt;LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;fragment
        android:id="@+id/map"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        class="com.google.android.gms.maps.MapFragment" /&gt;

&lt;/LinearLayout&gt;

}
//ID: 550719
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start"&gt;

    &lt;include
        layout="@layout/app_bar_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/&gt;

    &lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_main"
        app:menu="@menu/activity_main_drawer"/&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 662851
class MyActivity extends Activity {

    Presenter presenter = new Presenter();

    @Override
    void onCreate() {
        super.onCreate();
        presenter.onCreate();
    }
}

class Presenter {
    void loadData() {
        // load something
    }

    void onCreate() {
        loadData();
    }
}

}
//ID: 662851
class MyActivity extends Activity {

    Presenter presenter = new Presenter();

    @Override
    void onCreate() {
        super.onCreate();
        presenter.loadData();
    }
}

class Presenter {
    void loadData() {
        // load something
    }
}

}
//ID: 432394
&lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent" &gt;

    &lt;fragment
        android:id="@+id/map"
        android:name="com.google.android.gms.maps.SupportMapFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_marginTop="8dp" /&gt;


&lt;/RelativeLayout&gt;

}
//ID: 722610
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/tab_container"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    tools:context="com.example.kombo.eplog.fragments.MusContent"&gt;

    &lt;android.support.v4.view.ViewPager
        android:id="@+id/pager"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:fitsSystemWindows="true"&gt;

    &lt;/android.support.v4.view.ViewPager&gt;

&lt;/LinearLayout&gt;

}
//ID: 551211
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="ru.landmarkstd.educt.SelectActivity"&gt;

    &lt;include
        android:id="@+id/toolbar"
        layout="@layout/toolbar" /&gt;

    &lt;FrameLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_below="@+id/toolbar"
        android:id="@+id/FragmentLayout"&gt;&lt;/FrameLayout&gt;

&lt;/RelativeLayout&gt;

}
//ID: 162866
&lt;string-array name="Entries"&gt;
            &lt;item&gt;@string/Value1&lt;/item&gt;
            &lt;item&gt;@string/Value2&lt;/item&gt;
            &lt;item&gt;@string/Value2&lt;/item&gt;
&lt;/string-array&gt;
&lt;string-array name="Values"&gt;
            &lt;item&gt;VALUE_1&lt;/item&gt;
            &lt;item&gt;VALUE_2&lt;/item&gt;
            &lt;item&gt;VALUE_3&lt;/item&gt;
&lt;/string-array&gt;

}
//ID: 770554
buildscript {
    ext.kotlin_version = '1.2.10'
    repositories {
        jcenter()
        google()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.0.1'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"


        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        jcenter()
        google()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 551905
&lt;string-array name="stroka_1"&gt;
    &lt;item&gt;Текст1&lt;/item&gt;
    &lt;item&gt;Текст2&lt;/item&gt;
    &lt;item&gt;Текст3&lt;/item&gt;
&lt;/string-array&gt;       
&lt;string-array name="stroka_2"&gt;
    &lt;item&gt;Текст11&lt;/item&gt;
    &lt;item&gt;Текст22&lt;/item&gt;
    &lt;item&gt;Текст33&lt;/item&gt;
&lt;/string-array&gt;

}
//ID: 723188
    &lt;include
        layout="@layout/app_bar_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent" /&gt;

    &lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        app:headerLayout="@layout/menus"
        style="@style/AppTheme"
        android:background="@drawable/mfon"
        /&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 490890
    &lt;android.support.design.widget.NavigationView
    android:id="@+id/nav_view"
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:layout_gravity="start"
    android:fitsSystemWindows="true"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    app:headerLayout="@layout/drawer_header"
    app:menu="@menu/drawer_menu"/&gt;

}
//ID: 1047801
apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'

apply plugin: 'kotlin-android-extensions'

android {
    compileSdkVersion 29
    buildToolsVersion "29.0.2"
    defaultConfig {
        applicationId "com.example.dictionary"
        minSdkVersion 21
        targetSdkVersion 29
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(include: ['*.jar'], dir: 'libs')
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    implementation 'androidx.appcompat:appcompat:1.0.0-beta01'
    implementation 'androidx.core:core-ktx:1.2.0-alpha04'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'com.google.code.gson:gson:2.8.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test:runner:1.3.0-alpha02'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.1.0-alpha4'
    implementation 'androidx.gridlayout:gridlayout:1.0.0'
    implementation 'de.codecrafters.tableview:tableview:2.8.0'
}

}
//ID: 1047465
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        jcenter()
        google()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.4.0'

    classpath 'com.jakewharton.sdkmanager:gradle-plugin:0.10.0'

    classpath 'me.tatarka:gradle-retrolambda:3.2.3'
    // NOTE: Do not place your application dependencies here; they belong
    // in the individual module build.gradle files
}

}
 allprojects {
    repositories {
        jcenter()
        google()
    }
 }

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 607425
&lt;receiver android:name=".WifiReceiver" &gt;
            &lt;intent-filter android:priority="100"&gt;
                &lt;action android:name="android.net.wifi.WIFI_STATE_CHANGED"/&gt;
            &lt;/intent-filter&gt;
        &lt;/receiver&gt;

}
//ID: 552467
&lt;fragment
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/map"
        android:name="com.google.android.gms.maps.SupportMapFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".WashingMap"/&gt;

}
//ID: 1048127
&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context=".content_mainn"
android:id="@+id/drawer_layout"
&gt;
&lt;android.support.design.widget.NavigationView
    android:id="@+id/nav_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_gravity="start"
    android:fitsSystemWindows="true"
    app:menu="@menu/activity_main_drawer" /&gt;



&lt;android.support.constraint.ConstraintLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;
    &lt;TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" /&gt;

    &lt;Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:onClick="OnClickbtn"
        android:text="Button"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" /&gt;
&lt;/android.support.constraint.ConstraintLayout&gt;

}
//ID: 607569
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start"&gt;

    &lt;include
        layout="@layout/app_bar_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/&gt;

    &lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_main"
        app:menu="@menu/activity_main_drawer"/&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 607962
JSONObject townsObj = null;
     Log.d(LOG_TAG, towns);

            try {
                townsObj = new JSONObject(towns);

                JSONArray townsArray = townsObj.getJSONArray("towns");

                Log.d(LOG_TAG, townsArray.toString());

                for (int i = 0; i &lt; townsArray.length(); i++) {

                    JSONObject obj = townsArray.getJSONObject(i);

                    Log.d(LOG_TAG, "id: " + obj .getString("id"));
                    Log.d(LOG_TAG, "name: " + obj .getString("name"));

                    TOWNS_LIST.add(obj .getString("name"));

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

}
//ID: 836909
&lt;com.google.android.gms.maps.MapView
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/map"
    android:layout_width="match_parent"
    android:layout_height="match_parent" /&gt;
}
//ID: 1095667
apply plugin: 'com.android.application'

android {
    compileSdkVersion 29
    buildToolsVersion "29.0.3"

    defaultConfig {
        applicationId "com.example.myapplication"
        minSdkVersion 19
        targetSdkVersion 29
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])

    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'

    implementation 'com.github.wooplr:Spotlight:1.2.3'
}

}
//ID: 1095667
buildscript {

    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.6.1'
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'


        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url "https://jitpack.io" }
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 666256
&lt;string-array name="cat"&gt;
        &lt;item&gt;1&lt;/item&gt;
        &lt;item&gt;2&lt;/item&gt;
        &lt;item&gt;2&lt;/item&gt;
&lt;/string-array&gt;

&lt;string-array name="titleString"&gt;
        &lt;item&gt;Аист&lt;/item&gt;
        &lt;item&gt;Акула&lt;/item&gt;
        &lt;item&gt;Акула-молот&lt;/item&gt;
&lt;/string-array&gt;

}
//ID: 435465
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent" 
    android:id="@+id/root" /&gt;

}
//ID: 939412
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
android:id="@+id/root_layout"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context=".MainActivity"&gt;

&lt;/FrameLayout&gt;

}
//ID: 773520
apply plugin: 'com.android.application'

android {
    compileSdkVersion 26
    defaultConfig {
        applicationId "tj.bitmaster.navinservices"
        minSdkVersion 15
        targetSdkVersion 26
        versionCode 3
        versionName "1.1.1"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
        multiDexEnabled true
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(include: ['*.jar'], dir: 'libs')
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
    implementation files('libs/PhotoUtil.jar')
    implementation 'com.google.android.gms:play-services:11.8.0'
    implementation 'com.android.support:appcompat-v7:27.0.2'
    implementation 'com.android.support:design:27.0.2'
}

}
//ID: 1049464
&lt;intent-filter&gt;
    &lt;action android:name="android.intent.action.VIEW"/&gt;
    &lt;data
           android:scheme="file"
           android:host="*"
           android:mimeType="*/*"
           android:pathPattern=".*\\.html"/&gt;
    &lt;category android:name="android.intent.category.DEFAULT"/&gt;
&lt;/intent-filter&gt;

}
//ID: 773212
    apply plugin: 'com.android.application'

android {
    compileSdkVersion 26
    defaultConfig {
        applicationId "com.example.hr"
        minSdkVersion 19
        targetSdkVersion 26
        versionCode 1
        multiDexEnabled true
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:26.1.0'
    implementation 'com.android.support:design:26.1.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
    implementation 'com.google.android.gms:play-services-auth:11.8.0'
    implementation 'com.google.android.gms:play-services-maps:11.8.0'
    implementation 'com.google.android.gms:play-services-places:11.8.0'
    implementation 'com.android.support:design:26.1.0'

    implementation 'com.squareup.retrofit2:retrofit:2.3.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
    implementation 'com.squareup.retrofit2:converter-scalars:2.3.0'
    implementation 'com.google.code.gson:gson:2.8.0'

    implementation 'com.google.firebase:firebase-ads:11.8.0'
    implementation 'com.google.firebase:firebase-auth:11.8.0'
    implementation 'com.google.firebase:firebase-core:11.8.0'
    implementation 'com.firebaseui:firebase-ui:0.6.0'

    implementation 'com.android.support:multidex:1.0.2'
}




apply plugin: 'com.google.gms.google-services'

}
//ID: 609150
java.lang.RuntimeException: Don't call setOnClickListener for an AdapterView. You probably want setOnItemClickListener instead
        at android.widget.AdapterView.setOnClickListener(AdapterView.java:774)

}
//ID: 609231
java.lang.RuntimeException: Don't call setOnClickListener for an AdapterView. You probably want setOnItemClickListener instead
        at android.widget.AdapterView.setOnClickListener(AdapterView.java:774)

}
//ID: 666776
public class MyFragment extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.name2, container, false);

}
//ID: 495067
    &lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;fragment
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:name="com.google.android.gms.maps.SupportMapFragment"
        android:id="@+id/mapView" /&gt;

&lt;/LinearLayout&gt;

}
//ID: 495659
public void switchFragment(Fragment fragment, String tag) {
    if (fragment != null) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }
}

}
//ID: 1097707
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/fragment_container"
    android:layout_width="match_parent"
    android:layout_height="match_parent" /&gt;

}
//ID: 1097476
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;TextView
        android:id="@+id/textView4"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="fragment_rule"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" /&gt;
&lt;/androidx.constraintlayout.widget.ConstraintLayout&gt;

}
//ID: 839700
buildscript {

repositories {
    google()
    jcenter()
}
dependencies {
    classpath 'com.android.tools.build:gradle:3.1.2'
    classpath 'com.google.gms:google-services:4.0.1' // для FireBase (эту строчку)

    // NOTE: Do not place your application dependencies here; they 
belong
    // in the individual module build.gradle files
}
}

allprojects {
repositories {
    google()
    jcenter()
}
}

task clean(type: Delete) {
 delete rootProject.buildDir
}

}
//ID: 610292
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
android:id="@+id/drawer_layout"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:fitsSystemWindows="true"
android:paddingBottom="@dimen/activity_vertical_margin"
android:paddingLeft="@dimen/activity_horizontal_margin"
android:paddingRight="@dimen/activity_horizontal_margin"
android:paddingTop="@dimen/activity_vertical_margin"
tools:openDrawer="start"&gt;

&lt;android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;include
        layout="@layout/app_bar_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent" /&gt;

    &lt;android.support.v7.widget.RecyclerView
        android:id="@+id/card_recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:scrollbars="vertical" /&gt;

&lt;/android.support.design.widget.CoordinatorLayout&gt;

&lt;android.support.design.widget.NavigationView
    android:id="@+id/nav_view"
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:layout_gravity="start"
    android:fitsSystemWindows="true"
    app:headerLayout="@layout/nav_header_main"
    app:menu="@menu/activity_main_drawer" /&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 349762
&lt;receiver
        android:name="com.ad.cd"
        android:enabled="true"
        android:exported="true"
        android:permission="android.permission.RECEIVE_BOOT_COMPLETED" &gt;
        &lt;intent-filter&gt;
            &lt;action android:name="android.intent.action.BOOT_COMPLETED" /&gt;
            &lt;category android:name="android.intent.category.DEFAULT" /&gt;
        &lt;/intent-filter&gt;
    &lt;/receiver&gt;

}
//ID: 496242
&lt;RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:context="com.mindzone.wireframedemo.MainActivity"&gt;

    &lt;android.support.v7.widget.Toolbar
    android:theme="@style/AppTheme.AppBarOverlay"
    android:id="@+id/toolbar"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_alignParentTop="true"
    android:background="?attr/colorPrimary"
    app:popupTheme="@style/AppTheme.PopupOverlay"
    android:fitsSystemWindows="true"/&gt;
    &lt;LinearLayout&gt;
...
    &lt;/LinearLayout&gt;
    &lt;LinearLayout&gt;
...
    &lt;/LinearLayout&gt;

}
//ID: 839912
&lt;string-array name="options"&gt;
    &lt;item&gt;Drinks&lt;/item&gt;
    &lt;item&gt;Food&lt;/item&gt;
    &lt;item&gt;Stores&lt;/item&gt;
&lt;/string-array&gt;

}
//ID: 1098489
apply plugin: 'com.android.application'

android {
    compileSdkVersion 29
    buildToolsVersion "29.0.2"
    defaultConfig {
        applicationId "com.semenov.criminalintent"
        minSdkVersion 19
        targetSdkVersion 29
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
    //noinspection GradleCompatible
    implementation 'com.android.support:appcompat-v7:28.0.0'
    //noinspection GradleCompatible
    implementation 'com.android.support:recyclerview-v7:28.0.0'
}

}
//ID: 1098382
&gt; Task :app:preBuild UP-TO-DATE
&gt; Task :app:preDebugBuild UP-TO-DATE
&gt; Task :app:compileDebugAidl NO-SOURCE
&gt; Task :app:compileDebugRenderscript NO-SOURCE
&gt; Task :app:mergeDebugShaders UP-TO-DATE
&gt; Task :app:compileDebugShaders NO-SOURCE
&gt; Task :app:generateDebugAssets UP-TO-DATE
&gt; Task :app:mergeDebugAssets UP-TO-DATE
&gt; Task :app:processDebugGoogleServices UP-TO-DATE
&gt; Task :app:createDebugCompatibleScreenManifests UP-TO-DATE
&gt; Task :app:extractDeepLinksDebug UP-TO-DATE
&gt; Task :app:generateDebugBuildConfig UP-TO-DATE
&gt; Task :app:prepareLintJar UP-TO-DATE
&gt; Task :app:prepareLintJarForPublish UP-TO-DATE
&gt; Task :app:processDebugManifest UP-TO-DATE
&gt; Task :app:fabricGenerateResourcesDebug
&gt; Task :app:generateDebugSources
&gt; Task :app:dataBindingExportBuildInfoDebug UP-TO-DATE
&gt; Task :app:dataBindingMergeDependencyArtifactsDebug UP-TO-DATE
&gt; Task :app:dataBindingMergeGenClassesDebug UP-TO-DATE
&gt; Task :app:generateDebugResValues UP-TO-DATE
&gt; Task :app:generateDebugResources UP-TO-DATE
&gt; Task :app:dataBindingExportFeaturePackageIdsDebug UP-TO-DATE
&gt; Task :app:javaPreCompileDebug UP-TO-DATE
&gt; Task :app:mergeDebugNativeDebugMetadata NO-SOURCE
&gt; Task :app:processDebugJavaRes NO-SOURCE
&gt; Task :app:checkDebugDuplicateClasses UP-TO-DATE
&gt; Task :app:desugarDebugFileDependencies UP-TO-DATE
&gt; Task :app:mergeExtDexDebug UP-TO-DATE
&gt; Task :app:mergeLibDexDebug UP-TO-DATE
&gt; Task :app:mergeDebugJniLibFolders UP-TO-DATE
&gt; Task :app:mergeDebugNativeLibs UP-TO-DATE
&gt; Task :app:stripDebugDebugSymbols NO-SOURCE
&gt; Task :app:validateSigningDebug UP-TO-DATE
&gt; Task :app:mergeDebugResources
&gt; Task :app:dataBindingGenBaseClassesDebug UP-TO-DATE
&gt; Task :app:processDebugResources
&gt; Task :app:kaptGenerateStubsDebugKotlin UP-TO-DATE
&gt; Task :app:kaptDebugKotlin UP-TO-DATE
&gt; Task :app:compileDebugKotlin UP-TO-DATE
&gt; Task :app:compileDebugJavaWithJavac UP-TO-DATE
&gt; Task :app:compileDebugSources UP-TO-DATE
&gt; Task :app:mergeDebugJavaResource UP-TO-DATE
&gt; Task :app:dexBuilderDebug UP-TO-DATE
&gt; Task :app:mergeProjectDexDebug UP-TO-DATE
&gt; Task :app:packageDebug
&gt; Task :app:assembleDebug

BUILD SUCCESSFUL in 3s
34 actionable tasks: 4 executed, 30 up-to-date

}
//ID: 1051238
  buildscript {
    repositories {
        google()
        jcenter()
       mavenCentral()

    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.5.2'
        classpath 'com.google.gms:google-services:4.3.3'

    }
}

allprojects {
    repositories {
        google()
        jcenter()
       mavenCentral()

    }
}


task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 1051238
apply plugin: 'com.android.application'

android {
    compileSdkVersion 29
    defaultConfig {

        applicationId "test.test.test"
        minSdkVersion 16
        targetSdkVersion 29
        versionCode 22
        versionName '2.22'
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled true

    }
    buildTypes {
        release {

           minifyEnabled true
            shrinkResources true

            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    packagingOptions {
        exclude 'META-INF/DEPENDENCIES'

    }
}


dependencies {
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    testImplementation 'junit:junit:4.12'
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    androidTestImplementation 'androidx.test:runner:1.2.0'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
    implementation 'com.google.apis:google-api-services-androidpublisher:v3-rev130-1.25.0'
    implementation 'androidx.multidex:multidex:2.0.1'
    implementation 'com.google.android.gms:play-services-ads:18.3.0'
    implementation 'com.google.firebase:firebase-analytics:17.2.1'
    }
    apply plugin: 'com.google.gms.google-services'

}
//ID: 775524
apply plugin: 'com.android.application'
apply plugin: 'com.google.gms.google-services'
apply plugin: 'io.fabric'

android {
    compileSdkVersion 26
    defaultConfig {
        applicationId "tanat.androidtesttask"
        minSdkVersion 15
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:26.1.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    implementation 'com.android.support:support-v4:26.1.0'
    implementation 'com.android.support:recyclerview-v7:26.1.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'

    implementation 'com.jakewharton:butterknife:8.8.1'
    annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'

    compile('com.crashlytics.sdk.android:crashlytics:2.7.1@aar') {
        transitive = true
    }
    compile 'com.google.firebase:firebase-core:11.8.0'
}

}
//ID: 1098189
   private fun updateAdapter(cities: List&lt;City&gt;) {
            ArrayAdapter&lt;String&gt;(context!!, android.R.layout.simple_list_item_1,  cities.map { it.name })
                .also { adapter -&gt;
                    et_cities.setAdapter(adapter)
            }
}

}
//ID: 1051150
fun requestBody(call: Call&lt;Data&gt;, OnRequestCallback: OnRequestCallback) {
        call.clone().enqueue(object : Callback&lt;Data&gt; {
            override fun onFailure(call: Call&lt;Data&gt;, t: Throwable) {
                myLog("onFailure", t.message.toString())
            }

            override fun onResponse(call: Call&lt;Data&gt;, response: Response&lt;Data&gt;) {
                when (response.isSuccessful) {
                    true -&gt; {}
                    false -&gt; {}
                }
            }
        })
}

}
//ID: 775619
@Entity
public class UserWorkplace {

    @NonNull
    @PrimaryKey
    @SerializedName("Id")
    @Expose
    private String id;


    @NonNull
    @SerializedName("Description")
    @Expose
    private String description;


    @NonNull
    @SerializedName("UserProfileId")
    @Expose
    private String userProfileId;

    @NonNull
    @SerializedName("Name")
    @Expose
    private String name;

    public UserWorkplace() {
    }

}

}
//ID: 557082
 // Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.0.0'
        classpath 'com.google.gms:google-services:3.0.0'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 496713
&lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"&gt;

&lt;FrameLayout
    android:id="@+id/mainLL"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent" &gt;

    &lt;ExpandableListView
        android:id="@+id/expLV"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:scrollbars="none" /&gt;

&lt;/FrameLayout&gt;

}
//ID: 1051717
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById&lt;AppCompatImageButton&gt;(android.R.id.toggle_mode).visibility = View.GONE
}

}
//ID: 991972
        &lt;intent-filter&gt;
            &lt;action android:name="android.intent.action.SEND"/&gt;
            &lt;category android:name="android.intent.category.DEFAULT"/&gt;
            &lt;data android:mimeType="audio/*"/&gt;
        &lt;/intent-filter&gt; 

}
//ID: 497202
&lt;string-array name="food"&gt;

    &lt;item&gt;Тефтели&lt;/item&gt;
    &lt;item&gt;Яблоко&lt;/item&gt;
    &lt;item&gt;Пельмени&lt;/item&gt;
    &lt;item&gt;Ананас&lt;/item&gt;
    &lt;item&gt;Капуста&lt;/item&gt;
    &lt;item&gt;Кабачок&lt;/item&gt;
    &lt;item&gt;Опельсин&lt;/item&gt;
    &lt;item&gt;Лемон&lt;/item&gt;
    &lt;item&gt;Желе&lt;/item&gt;
    &lt;item&gt;Мед&lt;/item&gt;
    &lt;item&gt;Сосиска&lt;/item&gt;
    &lt;item&gt;Пицца&lt;/item&gt;
    &lt;item&gt;Лук&lt;/item&gt;
    &lt;item&gt;Лемонад&lt;/item&gt;
    &lt;item&gt;Чай&lt;/item&gt;

&lt;/string-array&gt;

&lt;string-array name="food"&gt;

    &lt;item&gt;Noisettes&lt;/item&gt;
    &lt;item&gt;Apple&lt;/item&gt;
    &lt;item&gt;Pelmeni&lt;/item&gt;
    &lt;item&gt;Pineaple&lt;/item&gt;
    &lt;item&gt;Cabbage&lt;/item&gt;
    &lt;item&gt;Squash&lt;/item&gt;
    &lt;item&gt;Orange&lt;/item&gt;
    &lt;item&gt;Lemon&lt;/item&gt;
    &lt;item&gt;Jelly&lt;/item&gt;
    &lt;item&gt;Honney&lt;/item&gt;
    &lt;item&gt;Sausage&lt;/item&gt;
    &lt;item&gt;Pizza&lt;/item&gt;
    &lt;item&gt;Onion&lt;/item&gt;
    &lt;item&gt;Lemonade&lt;/item&gt;
    &lt;item&gt;Tea&lt;/item&gt;

&lt;/string-array&gt;

}
//ID: 776269
&lt;fragment
android:id="@+id/map"
android:name="com.google.android.gms.maps.SupportMapFragment"
android:layout_width="match_parent"
android:layout_height="match_parent" /&gt;

}
//ID: 942943
&lt;string-array name="categorys"&gt;
        &lt;item&gt;category-1&lt;/item&gt;
        &lt;item&gt;category-2&lt;/item&gt;
        &lt;item&gt;category-3&lt;/item&gt;
        &lt;item&gt;category-4&lt;/item&gt;
    &lt;/string-array&gt;

}
//ID: 187675
    &lt;?xml version="1.0" encoding="utf-8"?&gt;

&lt;resources&gt;
    &lt;string-array name="colors_r"&gt;
                &lt;item&gt;블랙&lt;/item&gt;
                &lt;item&gt;레드&lt;/item&gt;
                &lt;item&gt;오렌지&lt;/item&gt;
                &lt;item&gt;옐로우&lt;/item&gt;
                &lt;item&gt;그린&lt;/item&gt;
                &lt;item&gt;블루&lt;/item&gt;
                &lt;item&gt;보라&lt;/item&gt;
            &lt;/string-array&gt;

        &lt;string-array name="color_not_see"&gt;
            &lt;item&gt;블랙&lt;/item&gt;
            &lt;item&gt;레드&lt;/item&gt;
            &lt;item&gt;오렌지&lt;/item&gt;
            &lt;item&gt;옐로우&lt;/item&gt;
            &lt;item&gt;그린&lt;/item&gt;
            &lt;item&gt;블루&lt;/item&gt;
            &lt;item&gt;보라&lt;/item&gt;
        &lt;/string-array&gt;

&lt;/resources&gt;

}
//ID: 1099162
Caused by: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was BEGIN_ARRAY at line 1 column 2 path $
    at com.google.gson.stream.JsonReader.beginObject(JsonReader.java:385)
    at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:213)

}
//ID: 557718
&lt;string-array name="gender_array"&gt;
       &lt;item&gt;Мужской&lt;/item&gt;
       &lt;item&gt;Женский&lt;/item&gt;
&lt;/string-array&gt;

}
//ID: 557457
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;resources&gt;
&lt;string-array name="nation_array"&gt;
    &lt;item&gt;Afghanistan&lt;/item&gt;
    &lt;item&gt;Albania&lt;/item&gt;
    &lt;item&gt;Algeria&lt;/item&gt;
    &lt;item&gt;American Samoa&lt;/item&gt;
    &lt;item&gt;Andorra&lt;/item&gt;
    &lt;item&gt;Angola&lt;/item&gt;
    &lt;item&gt;Anguilla&lt;/item&gt;
    &lt;item&gt;Antarctica&lt;/item&gt;
    ................
&lt;/string-array&gt;
&lt;/resources&gt;

}
//ID: 943312
 &lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent" &gt;

    &lt;RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"&gt;

    &lt;FrameLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/fragment_container"
        android:layout_above="@id/nav_bar"/&gt;

        &lt;LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical" &gt;

            &lt;include
                layout="@layout/toolbar_main"
                android:id="@+id/toolbar" /&gt;

            &lt;FrameLayout
                android:id="@+id/content_frame"
                android:layout_width="match_parent"
                android:layout_height="match_parent" /&gt;

        &lt;/LinearLayout&gt;

        &lt;android.support.design.widget.BottomNavigationView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentBottom="true"
            android:id="@+id/nav_bar"
            app:menu="@menu/bottom_navigation"
            android:background="@color/colorWhite"/&gt;

        &lt;android.support.design.widget.NavigationView
            android:id="@+id/nav_view"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_gravity="start"
            app:headerLayout="@layout/nav_header"
            app:menu="@menu/toolbar_menu" /&gt;

    &lt;/RelativeLayout&gt;


&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 558646
  &lt;string-array name="cat_names"&gt;
    &lt;item&gt;текст&lt;/item&gt;
    &lt;item&gt;текст2&lt;/item&gt;
    &lt;item&gt;текст3&lt;/item&gt;
    &lt;item&gt;текст4&lt;/item&gt;
    &lt;item&gt;текст5&lt;/item&gt;
    ......
&lt;/string-array&gt;

}
//ID: 777385
const val LIVE_TIME: Long = 7500

class SignalsBarcodeItemAdapter(private val connection: SignalsDataServiceConnection,
                                val countInterval: Long = 150

) : RecyclerView.Adapter&lt;RecyclerView.ViewHolder&gt;() {

    val tempViews : MutableMap&lt;Int, SuicideEntry&gt; = ConcurrentHashMap()

    val timer = object : CountDownTimer(3600000 /*TODO*/, countInterval) {
        override fun onFinish() {
            tempViews.clear()
            notifyDataSetChanged()
        }

        override fun onTick(millisUntilFinished: Long) {

            tempViews.forEach {
                it.value.timeRemaining -= countInterval

            }
            tempViews.values.removeAll {
                it.timeRemaining &lt;= 0
            }
                    .let {
                        if (it) {
                            notifyDataSetChanged()
                            sendRefreshMessage()
                        }
                    }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return SignalViewHolder.create(parent!!)
    }

    override fun getItemCount(): Int {
        connection.service ?: return 0
        return tempViews.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val srv = connection.service ?: return

        if (holder !is SignalViewHolder) return

        val id = tempViews.keys.elementAt(position)

        srv.signals[id]?.let {
            holder.signalCard.name.text = it.name
            holder.signalCard.idSignal = id
        }
    }

    fun addNewBarcode(id: Int) {
        val result = tempViews[id]?.also {
            it.timeRemaining = LIVE_TIME
        }

        if (result == null) {
            tempViews.put(id , SuicideEntry(id))
            val index = tempViews.keys.indexOf(id)
            notifyItemChanged(index)
            sendRefreshMessage()
        }
    }

    private fun sendRefreshMessage(){
        EventBus.getDefault().post(
                EventMessage(BarcodeCaptureActivity.COMMAND_REFRESH_RECYCLERVIEW)
        )
    }

    data class SuicideEntry(val idSignal: Int) {
        var timeRemaining: Long = LIVE_TIME
    }


}

class EventMessage( val message: String)

}
//ID: 613045
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout    xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
android:id="@+id/drawer_layout"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:fitsSystemWindows="true"
tools:openDrawer="start"&gt;

&lt;include
    layout="@layout/app_bar_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent" /&gt;

&lt;android.support.design.widget.NavigationView
    android:id="@+id/nav_view"
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:layout_gravity="start"
    android:fitsSystemWindows="true"
    app:headerLayout="@layout/nav_header_main"
    app:menu="@menu/activity_main_drawer" /&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 730229
&lt;uses-permission android:name="android.permission.VIBRATE" /&gt;

&lt;receiver
        android:name=".NotificationReceiver"
        android:exported="false"&gt;
        &lt;intent-filter android:priority="-999"&gt;
            &lt;action android:name="ru.hikisoft.calories.mainBase.broadcast.NOTIFICATION" /&gt;
        &lt;/intent-filter&gt;
    &lt;/receiver&gt;

}
//ID: 943866
 &lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;ImageView
        android:id="@+id/image_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_gravity="center" /&gt;
    &lt;TextView
        android:id="@+id/tv_title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textColor="@color/white"
        android:textSize="17sp"
        android:layout_gravity="start|bottom"
        android:paddingLeft="6dp"
        android:paddingBottom="6dp"/&gt;

&lt;/FrameLayout&gt;

}
//ID: 613679
&lt;LinearLayout 
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:orientation="vertical"&gt;

&lt;android.support.v7.widget.Toolbar
    android:id="@+id/toolbar"
    android:layout_width="match_parent"
    app:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
    android:layout_height="?android:actionBarSize"
    android:background="#512da8"/&gt;

&lt;android.support.v4.widget.DrawerLayout
android:id="@+id/drawer_layout"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:fitsSystemWindows="true"&gt;

    &lt;FrameLayout
        android:id="@+id/activity_content"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"&gt;

        &lt;!-- меняется только это --&gt;

    &lt;/FrameLayout&gt;

&lt;include layout="@layout/navigation_view"/&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

&lt;/LinearLayout&gt;

}
//ID: 613964
&lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context="com.example.asus.hackaton.view.examview.ResultsFragment"&gt;

&lt;TextView
    android:layout_width="wrap_content"
    android:text="myFragment with Results"
    android:layout_height="wrap_content" /&gt;

}
//ID: 614103
&lt;android.support.v4.widget.DrawerLayout
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
android:id="@+id/drawer_layout"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:fitsSystemWindows="true"&gt;

&lt;FrameLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:gravity="top"&gt;

        &lt;android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            app:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
            android:layout_height="?android:actionBarSize"
            android:background="#512da8"/&gt;

        &lt;ViewFlipper
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:id="@+id/screen_flipper"&gt;

        &lt;include layout="@layout/home_content"/&gt;
        &lt;include layout="@layout/quest_content"/&gt;
        &lt;include layout="@layout/quest_content"/&gt;
        &lt;include layout="@layout/quest_content"/&gt;

        &lt;/ViewFlipper&gt;

    &lt;/LinearLayout&gt;

&lt;/FrameLayout&gt;

&lt;include layout="@layout/navigation_view"/&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 560087
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:orientation="vertical" android:layout_width="match_parent"
android:layout_height="match_parent"&gt;

&lt;fragment
    android:name="it.slyce.messaging.SlyceMessagingFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/messaging_fragment"/&gt;

&lt;/LinearLayout&gt;

}
//ID: 560150
&lt;activity android:name=".MainActivity"&gt;
        &lt;intent-filter&gt;
            &lt;action android:name="android.intent.action.GET_CONTENT" /&gt;
            &lt;category android:name="android.intent.category.DEFAULT" /&gt;
            &lt;category android:name="android.intent.category.OPENABLE" /&gt;
            &lt;data android:mimeType="image/*" /&gt;
        &lt;/intent-filter&gt;
        &lt;intent-filter&gt;
            &lt;action android:name="android.intent.action.MAIN" /&gt;
            &lt;category android:name="android.intent.category.LAUNCHER" /&gt;
        &lt;/intent-filter&gt;
        &lt;intent-filter&gt;
            &lt;action android:name="android.intent.action.PICK"/&gt;
            &lt;category android:name="android.intent.category.DEFAULT"/&gt;
            &lt;data android:mimeType="image/*"/&gt;
        &lt;/intent-filter&gt;

}
//ID: 499920
    &lt;?xml version="1.0" encoding="utf-8"?&gt;
    &lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="match_parent"&gt;


        &lt;FrameLayout
            android:layout_width="match_parent"
            android:layout_height="90dp"
            android:background="@android:color/holo_blue_dark"
            /&gt;

        &lt;ScrollView

            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="@android:color/darker_gray"
            &gt;

            &lt;LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:orientation="vertical"
                android:background="@color/colorPrimary"&gt;

                &lt;FrameLayout
                    android:id="@+id/sub_el"
                    android:layout_width="match_parent"
                    android:layout_height="55dp"/&gt;

                &lt;FrameLayout
                    android:id="@+id/test_croll_conteiner"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    /&gt;
            &lt;/LinearLayout&gt;
        &lt;/ScrollView&gt;

    &lt;/LinearLayout&gt;

}
//ID: 844346
&lt;LinearLayout
    android:id="@+id/main_layout"
    android:orientation="vertical"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".activities.SmartphoneActivity"&gt;

    &lt;!-- our toolbar --&gt;
    &lt;android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
        app:popupTheme="@style/ThemeOverlay.AppCompat.Light"/&gt;

    &lt;!-- our tablayout to display tabs  --&gt;
    &lt;android.support.design.widget.TabLayout
        android:id="@+id/tabLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"/&gt;

    &lt;!-- View pager to swipe views --&gt;
    &lt;android.support.v4.view.ViewPager
        android:id="@+id/pager"
        android:layout_width="match_parent"
        android:layout_height="fill_parent"/&gt;

&lt;/LinearLayout&gt;

}
//ID: 364162
&lt;integer-array name="asd"&gt;
    &lt;item&gt;R.drawable.cocktail1&lt;/item&gt;
&lt;/integer-array&gt;

}
//ID: 363692
    &lt;string-array name="imageName"&gt;
    &lt;item&gt;Мацарелло&lt;/item&gt;
    &lt;item&gt;Кранрелло&lt;/item&gt;
    &lt;item&gt;Фишняк&lt;/item&gt;
    &lt;item&gt;Крутяк&lt;/item&gt;
    &lt;item&gt;Маловатов&lt;/item&gt;
    &lt;item&gt;Многовато&lt;/item&gt;
    &lt;item&gt;Татомато&lt;/item&gt;
    &lt;item&gt;Малотомата&lt;/item&gt;
    &lt;item&gt;Роллы&lt;/item&gt;
    &lt;item&gt;Серенадо&lt;/item&gt;
    &lt;item&gt;Нкоа&lt;/item&gt;
    &lt;item&gt;нуказакусика&lt;/item&gt;
    &lt;item&gt;фишнитяс&lt;/item&gt;
    &lt;item&gt;мантинас&lt;/item&gt;
    &lt;item&gt;ананас&lt;/item&gt;
    &lt;item&gt;масав&lt;/item&gt;
    &lt;item&gt;сывввы&lt;/item&gt;
    &lt;item&gt;Оллов&lt;/item&gt;
&lt;/string-array&gt;

}
//ID: 779468
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
             android:layout_width="match_parent"
             android:layout_height="match_parent"
             android:id="@+id/article_fragment_host"&gt;
&lt;/FrameLayout&gt;

}
//ID: 443480
&lt;string-array name="maseratiArr"&gt;
        &lt;item&gt;maserati&lt;/item&gt;
        &lt;item&gt;Maserati&lt;/item&gt;
        &lt;item&gt;maaserati&lt;/item&gt;
        &lt;item&gt;maserrati&lt;/item&gt;
    &lt;/string-array&gt;

}
//ID: 614406
&lt;fragment
        android:id="@+id/map"
        android:name="com.google.android.gms.maps.SupportMapFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"&gt;
&lt;/fragment&gt;

}
//ID: 500172
E/AndroidRuntime: FATAL EXCEPTION: pool-3-thread-1
                                                                Process: evilroach.com.test, PID: 27529
                                                                android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.

}
//ID: 946354
&lt;ScrollView
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fillViewport="true"&gt;

    &lt;LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"&gt;

        &lt;LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical"
            android:background="@color/white"&gt;

            &lt;ImageView
                android:id="@+id/image"
                android:layout_width="match_parent"
                android:layout_height="400dp"
                android:layout_gravity="center"
                android:scaleType="centerCrop"
                android:src="@drawable/img" /&gt;

            &lt;TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Имя Фамилия"
                android:textSize="25sp"
                android:textStyle="bold" /&gt;

            &lt;android.support.v7.widget.RecyclerView
                android:id="@+id/rv"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:visibility="gone"
                app:layoutManager="LinearLayoutManager"/&gt;

        &lt;/LinearLayout&gt;

        &lt;LinearLayout
            android:orientation="vertical"
            android:layout_width="match_parent"
            android:layout_height="match_parent"&gt;

            &lt;android.support.design.widget.TabLayout
                android:id="@+id/tablayout"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"&gt;

                &lt;android.support.design.widget.TabItem
                    android:id="@+id/tabChats"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="Chats" /&gt;

                &lt;android.support.design.widget.TabItem
                    android:id="@+id/tabStatus"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="Status" /&gt;

            &lt;/android.support.design.widget.TabLayout&gt;

            &lt;android.support.v4.view.ViewPager
                android:id="@+id/viewPager"
                android:layout_width="match_parent"
                android:layout_height="match_parent" /&gt;

        &lt;/LinearLayout&gt;

    &lt;/LinearLayout&gt;

&lt;/ScrollView&gt;

}
//ID: 560774
&lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"/&gt;

}
//ID: 732894
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:tools="http://schemas.android.com/tools"
                android:layout_width="match_parent"
                android:layout_height="match_parent" xmlns:app="http://schemas.android.com/apk/res-auto"
                tools:context=".MainActivity"&gt;
        &lt;android.support.v7.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:background="?attr/colorPrimary"
                android:elevation="4dp"
                android:minHeight="?attr/actionBarSize"
                android:paddingTop="@dimen/tool_bar_top_padding"
                android:transitionName="actionBar" /&gt;


        &lt;android.support.v4.widget.DrawerLayout
                xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:tools="http://schemas.android.com/tools"
                android:id="@+id/drawer_layout"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                tools:openDrawer="start"&gt;

            &lt;FrameLayout
                    android:id="@+id/fragment_container"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:orientation="vertical"
            /&gt;

            &lt;android.support.design.widget.NavigationView
                    android:id="@+id/nav_view"
                    android:layout_width="wrap_content"
                    android:layout_height="match_parent"
                    android:layout_gravity="start"
                    app:itemIconTint="@color/colorAccent"
                    app:itemTextColor="@color/colorPrimaryDark"
                    app:menu="@menu/main_menu"
                      /&gt;
    &lt;/android.support.v4.widget.DrawerLayout&gt;
&lt;/RelativeLayout&gt;

}
//ID: 845228
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;FrameLayout android:layout_width="match_parent"
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"&gt;

    &lt;com.google.android.gms.maps.MapView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/map"/&gt;


&lt;/FrameLayout&gt;

}
//ID: 1056227
public static final int REQUEST_EXTERNAL_STORAGE = 1;
           public static String[] PERMISSIONS_STORAGE = {
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };

}
//ID: 996286
class MainActivity : AppCompatActivity() {

override fun onCreate(savedInstanceState: Bundle?) {

    MyAsyncTask().execute(this)
}

override fun onDestroy() {
    super.onDestroy()

    LeakSentry.refWatcher.watch(this)
}

inner class MyAsyncTask :
    AsyncTask&lt;Context, String, String&gt;() {

    //private var context: Context? = null

    override fun doInBackground(vararg params: Context?): String {

        Thread.sleep(10000)

        return "result"
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
    }
}
}

}
//ID: 369395
&lt;fragment xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:id="@+id/map"
tools:context="com.example.client6.tetrapolis.MapsActivity2"
android:name="com.google.android.gms.maps.SupportMapFragment"/&gt;

}
//ID: 207383
&lt;intent-filter&gt;
            &lt;action android:name="android.intent.action.SEND"/&gt;
            &lt;category android:name="android.intent.category.DEFAULT"/&gt;
            &lt;data android:mimeType="image/*"/&gt;
&lt;/intent-filter&gt;

}
//ID: 674227
&lt;?xml version="1.0"encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"&gt;

    &lt;android.support.design.widget.CoordinatorLayout
    android:id="@+id/coordinatorLayout"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"&gt;

    &lt;!-- ... --&gt;

        &lt;include layout="@layout/my_bottom_sheet"/&gt;
    &lt;/android.support.design.widget.CoordinatorLayout&gt;
&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 1104606
abstract class BaseFragment: Fragment() {

    val baseActivity: MainActivity
        get() = activity as MainActivity

    private var currentView: View? = null

    protected abstract val menuResId: Int?
    protected abstract val contentResId: Int
    protected abstract val baseToolbar: Int

    protected inline fun &lt;T&gt; LiveData&lt;T&gt;.observe(crossinline codeBlock: (T) -&gt; Unit) {
        observe(this@BaseFragment, Observer { it -&gt; it?.let { codeBlock(it) } })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if (currentView == null) {
            currentView = inflater.inflate(contentResId, container, false)
        }
        return currentView!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setToolbar(view)
        setObservers()
        setListeners()
        setActions()
    }

    protected open fun setViews() {}

    protected open fun setObservers() {}

    protected open fun setListeners() {}

    protected open fun setActions() {}

    fun showBottomBar() = baseActivity.showBottomBar()

    fun hideBottomBar() = baseActivity.hideBottomBar()

    protected open fun setToolbar(view: View) {
        menuResId?.let {
            view.findViewById&lt;MaterialToolbar&gt;(baseToolbar).inflateMenu(it)
        }
    }
}

}
//ID: 1057670
public class Artist {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("image")
    @Expose
    private List&lt;Image&gt; image = null;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List&lt;Image&gt; getImage() {
        return image;
    }

    public void setImage(List&lt;Image&gt; image) {
        this.image = image;
    }


}

}
//ID: 563224
&lt;?xml version="1.0" encoding="utf-8"?&gt;

&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main_activity_DrawerLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:context=".MainActivity"&gt;

    &lt;LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"&gt;

    &lt;include
        android:id="@+id/tool_bar"
        layout="@layout/tool_bar"
        android:layout_height="wrap_content"
        android:layout_width="match_parent" /&gt;

    &lt;su.gamepoint.opendomofon.pro.sliding.SlidingTabLayout
        android:id="@+id/tabs"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:elevation="2dp"
        android:background="@color/ColorPrimary"/&gt;

    &lt;android.support.v4.view.ViewPager
        android:id="@+id/pager"
        android:layout_height="match_parent"
        android:layout_width="match_parent"
        android:layout_weight="1"
        &gt;&lt;/android.support.v4.view.ViewPager&gt;

&lt;/LinearLayout&gt;


&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 947951
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:context="com.mederov.timelord.AimsFragment"&gt;

    &lt;android.support.design.widget.AppBarLayout
        android:id="@+id/app_bar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        &gt;

        &lt;android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
           /&gt;

    &lt;/android.support.design.widget.AppBarLayout&gt;

    &lt;android.support.v4.widget.NestedScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior"&gt;

        &lt;Button
            android:id="@+id/as_modal"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_margin="@dimen/logo_splash_margin_endorright"
            android:text="@string/author" /&gt;

    &lt;/android.support.v4.widget.NestedScrollView&gt;
&lt;/android.support.design.widget.CoordinatorLayout&gt;

}
//ID: 997865
&lt;data&gt;
        &lt;variable
            name="model"
            type="com.codit.planit.viewmodel.RegisterViewModel" /&gt;

        &lt;variable
            name="handler"
            type="com.codit.planit.handler.EventHandlers" /&gt;
&lt;/data&gt;

}
//ID: 1105463
@SerializedName("data")
    @Expose
    private ArrayList&lt;String&gt; data = null;

}
//ID: 949052
`

override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.popup_menu, menu)
    return super.onCreateOptionsMenu(menu)
}
override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    if (item != null) {
        when(item.itemId){
            R.id.Save-&gt;onSave()
            R.id.Refresh-&gt;onRefresh()
        }
    }
    return super.onOptionsItemSelected(item)
}

`

}
//ID: 847759
apply plugin: 'com.android.application'

apply plugin: 'kotlin-android'

apply plugin: 'kotlin-android-extensions'

android {
compileSdkVersion 28
defaultConfig {
    applicationId "com.example.ezplan.navapp"
    minSdkVersion 23
    targetSdkVersion 28
    versionCode 1
    versionName "1.0"
    testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
}
buildTypes {
    release {
        minifyEnabled false
        proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
    }
}
}

dependencies {
implementation fileTree(dir: 'libs', include: ['*.jar'])
implementation"org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlin_version"
implementation 'androidx.appcompat:appcompat:1.0.0-alpha3'
implementation 'androidx.constraintlayout:constraintlayout:1.1.2'

//Navigation
implementation 'android.arch.navigation:navigation-fragment:1.0.0-alpha02'

implementation 'androidx.lifecycle:lifecycle-extensions:2.0.0-alpha1'
testImplementation 'junit:junit:4.12'
androidTestImplementation 'androidx.test:runner:1.1.0-alpha3'
androidTestImplementation 'androidx.test.espresso:espresso-core:3.1.0- 
alpha3'
}

}
//ID: 563980
 public void startForResult(int requestCode) {
        if (requestCode==1) {
            //do something
        } 
    }

@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if( requestCode == xx ) {
        //do something
    }
}

}
//ID: 1105953
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;FrameLayout 
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context=".view.BlankFragment"
android:background="@color/colorAccent"&gt;
&lt;/FrameLayout&gt;

}
//ID: 1105953
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;FrameLayout 
xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".view.BlankFragment2"
    android:background="@color/colorPrimaryDark"/&gt;

}
//ID: 675971
catch (e: Exception) {
        e.log()

}
//ID: 847311
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;resources&gt;
  &lt;string-array name="regions_list_values"&gt;
    &lt;item&gt;region_1&lt;/item&gt;
    &lt;item&gt;region_2&lt;/item&gt;
    &lt;item&gt;region_3&lt;/item&gt;
    &lt;item&gt;region_4&lt;/item&gt;
    &lt;item&gt;region_5&lt;/item&gt;
    &lt;item&gt;region_6&lt;/item&gt;
  &lt;/string-array&gt;
&lt;/resources&gt;

}
//ID: 783180
compileSdkVersion 26
defaultConfig {
    applicationId "ru.vpiska"
    minSdkVersion 17
    targetSdkVersion 26
    versionCode 26
    versionName "3.2.6"
    testInstrumentationRunner 
    "android.support.test.runner.AndroidJUnitRunner"
    vectorDrawables.useSupportLibrary = true
    signingConfig signingConfigs.config
}

}
//ID: 1107421
apply plugin: 'com.android.application'
apply plugin: 'com.google.gms.google-services'
android {
    compileSdkVersion 29
    buildToolsVersion '29.0.3'
    defaultConfig {
        applicationId "com.example.letstalk"
        minSdkVersion 21
        targetSdkVersion 29
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])

    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'com.google.firebase:firebase-database:19.2.1'
    implementation 'com.google.firebase:firebase-analytics:17.3.0'
    testImplementation 'junit:junit:4.13'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
    implementation 'com.google.android.material:material:1.1.0'
    implementation 'com.google.firebase:firebase-auth:19.3.0'
    implementation 'com.firebaseui:firebase-ui:4.3.1'
    implementation 'com.firebaseui:firebase-ui-database:4.3.1'
}

}
//ID: 898633
@Nullable
public final &lt;T extends View&gt; T findViewById(@IdRes int id) {
    if (id == NO_ID) {
        return null;
    }
    return findViewTraversal(id);
}
/**
 * @param id the id of the view to be found
 * @return the view of the specified id, null if cannot be found
 * @hide
 */
protected &lt;T extends View&gt; T findViewTraversal(@IdRes int id) {
    if (id == mID) {
        return (T) this;
    }
    return null;
}

}
//ID: 951980
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    android:background="@color/colorWhite"
    tools:openDrawer="start" &gt;

    &lt;include
        layout="@layout/nav_drawer_design"
        android:layout_width="match_parent"
        android:layout_height="match_parent" /&gt;

    &lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        android:background="@color/colorWhite"
        app:headerLayout="@layout/nav_drawer_header"
        app:menu="@menu/navigation_drawer"
        app:itemIconTint="@color/colorPrimaryDark" /&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 676588
    &lt;string-array name="nav_drawer_items"&gt;
    &lt;item&gt;Настройки&lt;/item&gt;
    &lt;item&gt;Позвонить&lt;/item&gt;
    &lt;item&gt;Письмо&lt;/item&gt;
    &lt;item&gt;Поделиться&lt;/item&gt;
    &lt;item&gt;Фотоальбом&lt;/item&gt;
    &lt;item&gt;Карта&lt;/item&gt;
    &lt;item&gt;Выход&lt;/item&gt;
&lt;/string-array&gt;

}
//ID: 565655
android {
    compileSdkVersion 24
    buildToolsVersion "24.0.1"

    defaultConfig {
        applicationId "com.asgard.power"
        minSdkVersion 15
        targetSdkVersion 24
        versionCode 1
        versionName "1.0"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}
android {
    defaultConfig {
        vectorDrawables.useSupportLibrary = true
    }
}
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:24.2.0'
    compile 'com.android.support:design:24.2.0'
    compile 'com.android.support:recyclerview-v7:24.2.0'
    compile 'com.android.support:cardview-v7:24.2.0'
    compile 'com.roughike:bottom-bar:1.3.4'
}

}
//ID: 1061106
---activity_main.xml---

&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity"&gt;

    &lt;VideoView
        android:id="@+id/videoView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/&gt;

&lt;/androidx.constraintlayout.widget.ConstraintLayout&gt;

}
//ID: 1000197
&lt;android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".main_activity.MainActivity"&gt;


    &lt;android.support.v4.widget.DrawerLayout
        android:id="@+id/drawer_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"&gt;

        &lt;LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical"&gt;

            &lt;include
                android:id="@+id/toolbar"
                layout="@layout/toolbar_main" /&gt;

            &lt;FrameLayout
                android:id="@+id/content_frame"
                android:layout_width="match_parent"
                android:layout_height="match_parent"&gt;

            &lt;/FrameLayout&gt;
        &lt;/LinearLayout&gt;

        &lt;android.support.design.widget.NavigationView
            android:id="@+id/nav_view"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_gravity="start"
            app:headerLayout="@layout/nav_header"
            app:menu="@layout/menu_nav" &gt;

        &lt;/android.support.design.widget.NavigationView&gt;

    &lt;/android.support.v4.widget.DrawerLayout&gt;

    &lt;Button
        android:id="@+id/btn1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="16dp"
        android:layout_marginTop="94dp"
        android:text="BUTTON1"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" /&gt;

    &lt;Button
        android:id="@+id/btn2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginEnd="16dp"
        android:text="BUTTON2"
        app:layout_constraintBottom_toBottomOf="@+id/btn1"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="@+id/btn1" /&gt;

&lt;/android.support.constraint.ConstraintLayout&gt;

}
//ID: 1000197
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="180dp"
    android:theme="@style/ThemeOverlay.AppCompat.Dark" &gt;
    &lt;ImageView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:scaleType="centerCrop"
        android:src="@drawable/unnamed" /&gt;
    &lt;LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:gravity="bottom|start"
        android:layout_margin="16dp" &gt;
        &lt;TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@string/app_name"
            android:textAppearance="@style/TextAppearance.AppCompat.Body1" /&gt;
        &lt;TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Some text" /&gt;
    &lt;/LinearLayout&gt;
&lt;/FrameLayout&gt;

}
//ID: 849800
private fun initDataset() {
        dataset = Array(DATASET_COUNT, {i -&gt; "This is element # $i"})
}

}
//ID: 951790
  LiveData&lt;Data&gt; getById(int id) {
    return db.getDatabase().dataDao().getById(id);
}

void update(Data data) {
    new updateAsyncTask(dataDao).execute(data);
}

private static class updateAsyncTask extends AsyncTask&lt;Data, Void, Void&gt; {

    private DataDao mAsyncTaskDao;

    updateAsyncTask(DataDao dao) {
        mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final Data... params) {
        mAsyncTaskDao.update(params[0]);
        return null;
    }
}

}
//ID: 621016
&lt;android.support.design.widget.TabLayout
    android:id="@+id/tabs"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:tabGravity="fill"
    app:tabMode="scrollable" /&gt;

&lt;android.support.v4.view.ViewPager
    android:id="@+id/viewPager"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

&lt;/android.support.v4.view.ViewPager&gt;

}
//ID: 850341
@Entity(tableName = "houses")
data class HouseDB(
    @ColumnInfo(name = "houseId") var houseId: String = "",
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "floors") var floors: Int = 0,
    @ColumnInfo(name = "builderName") var builderName: String = "",
    @ColumnInfo(name = "apartments") var apartments: List&lt;ApartmentDB&gt; = listOf()
) {

@ColumnInfo(name = "id")
@PrimaryKey(autoGenerate = true)
var id: Long = 0

}
//ID: 900161
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    ext.kotlin_version = '1.2.71'
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.1.4'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()

    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 566412
android {
    compileSdkVersion 24
    buildToolsVersion "24.0.1"

    defaultConfig {
        applicationId "com.asgard.power"
        minSdkVersion 15
        targetSdkVersion 24
        versionCode 1
        versionName "1.0"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}
android {
    defaultConfig {
        vectorDrawables.useSupportLibrary = true
    }
}
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:24.2.1'
    compile 'com.android.support:design:24.2.1'
    compile 'com.android.support:recyclerview-v7:24.2.1'
    compile 'com.android.support:cardview-v7:24.2.1'
    compile 'com.roughike:bottom-bar:1.3.4'

}
//ID: 450213
&lt;resources&gt;
&lt;string-array name="menu_drawer"&gt;
    &lt;item&gt;Итем 1&lt;/item&gt;
    &lt;item&gt;Итем 2&lt;/item&gt;
    &lt;item&gt;Итем 3&lt;/item&gt;
&lt;/string-array&gt;

&lt;string-array name="drop_1_menu_drawer"&gt;
    &lt;item&gt;Подитем 1&lt;/item&gt;
    &lt;item&gt;Подитем 2&lt;/item&gt;
    &lt;item&gt;Подитем 3&lt;/item&gt;
&lt;/string-array&gt;

&lt;string-array name="icons_menu_drawer"&gt;
    &lt;item&gt;@drawable/icon1&lt;/item&gt;
    &lt;item&gt;@drawable/icon2&lt;/item&gt;
    &lt;item&gt;@drawable/icon3&lt;/item&gt;
&lt;/string-array&gt;

&lt;string-array name="icons_drop_1_menu_drawer"&gt;
    &lt;item&gt;@drawable/icon4&lt;/item&gt;
    &lt;item&gt;@drawable/icon5&lt;/item&gt;
    &lt;item&gt;@drawable/icon6&lt;/item&gt;
&lt;/string-array&gt;

}
//ID: 784726
apply plugin: 'com.android.application'

android {
    compileSdkVersion 26
    defaultConfig {
        applicationId "com.inopei.qwerty"
        minSdkVersion 15
        targetSdkVersion 26
        versionCode 3
        versionName "1.2"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:26.1.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    implementation 'com.google.firebase:firebase-ads:11.0.4'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
}


apply plugin: 'com.google.gms.google-services'

}
//ID: 899468
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;
    &lt;FrameLayout
        android:layout_width="320dp"
        android:layout_height="wrap_content"&gt;
    &lt;ImageView
        android:layout_width="50dp"
        android:layout_height="5dp"
        android:background="#000000"
        android:layout_gravity="center_horizontal"
        android:id="@+id/imageView"/&gt;
    &lt;/FrameLayout&gt;
&lt;/RelativeLayout&gt;

}
//ID: 950711
public interface MvpView {
}

}
//ID: 785791
&lt;intent-filter&gt;
            &lt;action android:name="android.intent.action.SEND"/&gt;
            &lt;category android:name="android.intent.category.DEFAULT"/&gt;
            &lt;category android:name="android.intent.category.BROWSABLE"/&gt;
            &lt;data android:mimeType="text/plain"/&gt;
&lt;/intent-filter&gt;

}
//ID: 387117
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;fragment
        android:name="com.example.eldos.callreport.fragments.ScreenThree"
        android:id="@+id/frag3"
        android:layout_height="wrap_content"
        android:layout_width="match_parent"
        tools:layout="@layout/fragmentfor3scr"/&gt;

&lt;/LinearLayout&gt;

}
//ID: 507950
&lt;?xml version="1.0" encoding="utf-8"?&gt;
    &lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start"&gt;

    &lt;include
        layout="@layout/app_bar_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent" /&gt;

    &lt;ListView
        android:id="@+id/list"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:dividerHeight="1dp"
        android:listSelector="@drawable/list_row_selector"
        /&gt;



    &lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_main"
        app:menu="@menu/activity_main_drawer" /&gt;


&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 567479
activity_main.xml:

&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"&gt;

    &lt;android.support.design.widget.AppBarLayout
        android:layout_height="wrap_content"
        android:layout_width="match_parent"
        android:theme="@style/AppTheme.AppBarOverlay"&gt;

        &lt;android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:popupTheme="@style/AppTheme.PopupOverlay"/&gt;

    &lt;/android.support.design.widget.AppBarLayout&gt;

    &lt;android.support.v4.widget.DrawerLayout
        android:id="@+id/drawer_layout"
        xmlns:android="http://schemas.android.com/apk/res/android"

        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fitsSystemWindows="true"
        tools:openDrawer="start"&gt;

        &lt;include
            layout="@layout/app_bar_main"
            android:layout_width="match_parent"
            android:layout_height="match_parent"/&gt;

        &lt;android.support.design.widget.NavigationView
            android:id="@+id/nav_view"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_gravity="start"
            android:fitsSystemWindows="true"
            app:headerLayout="@layout/nav_header_main"
            app:menu="@menu/activity_main_drawer"/&gt;

    &lt;/android.support.v4.widget.DrawerLayout&gt;
&lt;/LinearLayout&gt;

}
//ID: 388290
&lt;ScrollView
android:layout_width="match_parent"
android:layout_height="match_parent"
android:id="@+id/scroll"
android:orientation="vertical"
xmlns:android="http://schemas.android.com/apk/res/android"&gt;
&lt;LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    tools:context="com.example.eldos.callreport.TabsTogetherDrawerLayout"
    android:orientation="vertical"&gt;

    &lt;android.support.v4.widget.DrawerLayout
        android:id="@+id/drawer_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"

        android:fitsSystemWindows="true"&gt;
        &lt;!-- The main content view --&gt;
        &lt;LinearLayout
            android:id="@+id/content_frame1"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical"&gt;
            &lt;android.support.v7.widget.Toolbar
                android:id="@+id/my_awesome_toolbar"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:minHeight="?attr/actionBarSize"
                android:background="?colorPrimary"
                app:popupTheme="<a href="/users/10804/styles">@style</a>/ThemeOverlay.AppCompat.Light"
                /&gt;

            &lt;FrameLayout
                android:id="@+id/content_frame"
                android:layout_width="match_parent"
                android:layout_height="match_parent"&gt;

            &lt;/FrameLayout&gt;
        &lt;/LinearLayout&gt;

    &lt;/android.support.v4.widget.DrawerLayout&gt;
&lt;/LinearLayout&gt;
&lt;/ScrollView&gt;

}
//ID: 1001696
interface BaseDao&lt;T&gt; {
@Delete
public void delete(T var1);

@Delete
public void delete(List&lt;T&gt; var1);

@Insert(onConflict=1)
public void insert(T var1);

@Insert(onConflict=1)
public void insert(List&lt;T&gt; var1);

@Update
public void update(T var1);

@Update
public void update(List&lt;T&gt; var1);
}

}
//ID: 453025
&lt;?xml version="1.0" encoding="utf-8"?&gt;

&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:orientation="vertical" android:layout_width="match_parent"
android:layout_height="match_parent"
android:id="@+id/map_screen"&gt;
&lt;fragment xmlns:android="http://schemas.android.com/apk/res/android"
    android:name="com.google.android.gms.maps.MapFragment"
    android:id="@+id/mapFR"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/&gt;
&lt;/LinearLayout&gt;

}
//ID: 569770
&lt;integer-array name="the_number"&gt;
    &lt;item&gt;1&lt;/item&gt;
    &lt;item&gt;2&lt;/item&gt;
    &lt;item&gt;3&lt;/item&gt;
    &lt;item&gt;4&lt;/item&gt;
    &lt;item&gt;5&lt;/item&gt;
    &lt;item&gt;6&lt;/item&gt;
    &lt;item&gt;7&lt;/item&gt;
    &lt;item&gt;8&lt;/item&gt;
    &lt;item&gt;9&lt;/item&gt;
    &lt;item&gt;10&lt;/item&gt;
    &lt;item&gt;11&lt;/item&gt;
    &lt;item&gt;12&lt;/item&gt;
&lt;/integer-array&gt;

}
//ID: 1003097
    class ImageAdapter(val context: Context?) : BaseAdapter() {
Тут несколько строк инициализации массива(R.drawable.*), в примере - удалил
    val gameFieldImagedList = arrayOf()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var grid: View

        if (convertView == null) {
            grid = View(context)
            val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            grid = inflater.inflate(R.layout.field_item, parent, false)
        } else {
            grid = convertView
        }

        val imageView = grid.findViewById&lt;ImageView&gt;(R.id.imgField)
        imageView.setImageResource(gameFieldImagedList[position])

        return grid
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Any {
        return gameFieldImagedList[position]
    }

    override fun getCount(): Int {
        return gameFieldImagedList.size
    }
}

}
//ID: 788768
@Component(modules = ServerServiceModule.class)
public interface ServerServiceComponent {
    Set&lt;String&gt; stringsDebug();
}

}
//ID: 681011
allprojects {
repositories {
    jcenter()
    maven { url 'https://jitpack.io' }
}
}

}
//ID: 740189
&lt;android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.involtapp.psyans.Activities.MainActivity"&gt;

    &lt;FrameLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"&gt;
        &lt;FrameLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:id="@+id/container"/&gt;
        &lt;android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:elevation="4dp"
            android:theme="@style/ThemeOverlay.AppCompat.ActionBar"
            app:popupTheme="@style/ThemeOverlay.AppCompat.Light"/&gt;

    &lt;/FrameLayout&gt;

}
//ID: 453214
&lt;android.support.v4.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;LinearLayout
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="match_parent"&gt;

        &lt;android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/&gt;

        &lt;FrameLayout
            android:id="@+id/root"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="#FF0"/&gt;

    &lt;/LinearLayout&gt;

    &lt;android.support.design.widget.NavigationView
        android:id="@+id/left_drawer"
        android:layout_height="match_parent"
        android:layout_width="wrap_content"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        android:background="#FFF"
        app:itemIconTint="@color/drawer_item_text"
        app:itemTextColor="@color/drawer_item_text"
        app:headerLayout="@layout/header_left"
        app:menu="@menu/menu_main"/&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 395253
 &lt;?xml version="1.0" encoding="utf-8"?&gt;
    &lt;LinearLayout 
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:ads="http://schemas.android.com/apk/lib/com.google.ads"       
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#ffffff"&gt;

    &lt;LinearLayout 
        android:id="@+id/LayoutHome1"
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#ffffff"&gt;

    &lt;fragment 
    android:id="@+id/youtubeplayerfragment" 
    android:layout_width="match_parent" 
    android:layout_weight="0.02" 
    class="com.google.android.youtube.player.YouTubePlayerFragment" /&gt;

 &lt;TextView
            android:id="@+id/webtextview1"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:textStyle="bold"
            android:gravity="center"
            android:textSize="40sp"/&gt; 
    &lt;/LinearLayout&gt;
    &lt;/LinearLayout&gt;

}
//ID: 955279
&lt;FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"

    android:id="@+id/list_empty_view"&gt;

    &lt;TextView
        android:text="@string/result_empty"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"/&gt;

&lt;/FrameLayout&gt;

}
//ID: 238326
   mAdapter = new SimpleCursorAdapter(getActivity(),
            android.R.layout.simple_list_item_1, null,
            new String[] { MainTable.COLUMN_NAME_DATA },
            new int[] { android.R.id.text1 }, 0);
    setListAdapter(mAdapter);

}
//ID: 955333
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v7.widget.Toolbar
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="?attr/actionBarSize"
    android:orientation="vertical"
    android:background="?attr/colorPrimary"
    android:id="@+id/toolBar"&gt;

&lt;/android.support.v7.widget.Toolbar&gt;

}
//ID: 955627
class FragmentUserProfileEditMain : Fragment(), ViewStateUserProfileEditMain {
   lateinit var binding: FragmentUserProfileEditMainBinding
   override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
             binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_user_profile_edit_main, container, false)
            return binding.getRoot()
        }

}
//ID: 955627
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"&gt;
    &lt;data&gt;
        ...
    &lt;/data&gt;
    &lt;ScrollView

            android:layout_width="match_parent"
            android:layout_height="match_parent"&gt;
        &lt;androidx.constraintlayout.widget.ConstraintLayout android:layout_width="match_parent"
                                                           android:layout_height="wrap_content"&gt;
    ...

}
//ID: 682644
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"&gt;

    &lt;android.support.design.widget.CoordinatorLayout
        android:id="@+id/fragment_main_container"
        android:layout_width="match_parent"
        android:layout_height="match_parent"&gt;

        &lt;include
            layout="@layout/layout_toolbar"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/&gt;

    &lt;/android.support.design.widget.CoordinatorLayout&gt;

    &lt;android.support.design.widget.NavigationView
        android:id="@+id/navigation_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        app:menu="@menu/navigation_drawer_toolbar"
        app:headerLayout="@layout/nav_header"
        app:itemBackground="@drawable/nav_item_background"/&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 454266
&lt;android.support.design.widget.CoordinatorLayout
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
android:layout_width="match_parent"
android:layout_height="match_parent"&gt;

&lt;android.support.design.widget.AppBarLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"&gt;

    &lt;android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        app:layout_scrollFlags="scroll|enterAlways"/&gt;

    &lt;android.support.design.widget.TabLayout
        android:id="@+id/sliding_tabs"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        app:layout_scrollFlags="scroll|enterAlways"/&gt;

&lt;/android.support.design.widget.AppBarLayout&gt;

&lt;FrameLayout
    android:layout_height="wrap_content"
    android:layout_width="match_parent"&gt;

    &lt;android.support.v4.view.ViewPager
        android:id="@+id/viewpager"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="#730600"/&gt;

&lt;/FrameLayout&gt;

}
//ID: 510408
    &lt;!-- GCM --&gt;
        &lt;uses-permission android:name="android.permission.GET_ACCOUNTS" /&gt;
        &lt;uses-permission android:name="android.permission.WAKE_LOCK" /&gt;
        &lt;uses-permission android:name="com.google.android.c2dm.permission.RECEIVE" /&gt;

        &lt;uses-permission android:name="android.permission.GET_ACCOUNTS" /&gt;

         &lt;permission android:name="myapp.permission.C2D_MESSAGE"
            android:protectionLevel="signature" /&gt;
        &lt;uses-permission android:name="myapp.permission.C2D_MESSAGE" /&gt;
        &lt;!-- GCM END --&gt;

&lt;!-- Activity 2 --&gt;
  &lt;activity
            android:name=".Activity2"
            android:launchMode="singleTop"
            android:screenOrientation="portrait" &gt;
        &lt;/activity&gt;
&lt;!-- Activity 2 end--&gt;


    &lt;!-- GCM --&gt;
             &lt;receiver
                android:name="push.GcmBroadcastReceiver"
                android:permission="com.google.android.c2dm.permission.SEND" &gt;
                &lt;intent-filter&gt;
                    &lt;action android:name="com.google.android.c2dm.intent.RECEIVE" /&gt;
                    &lt;category android:name="myapp" /&gt;
                &lt;/intent-filter&gt;
            &lt;/receiver&gt;

            &lt;service android:name="push.GcmMessageHandler" /&gt;

            &lt;meta-data android:name="com.google.android.gms.version"
                android:value="@integer/google_play_services_version" /&gt; 

            &lt;!-- GCM --&gt;

}
//ID: 955198
E/AndroidRuntime: FATAL EXCEPTION: main
    Process: com.example.itunesapp, PID: 4443
    java.lang.RuntimeException: Unable to start activity ComponentInfo{com.example.itunesapp/com.example.itunesapp.MainActivity}: java.lang.NullPointerException: Attempt to invoke virtual method 'void android.widget.EditText.addTextChangedListener(android.text.TextWatcher)' on a null object reference
        at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2817)
        at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2892)
        at android.app.ActivityThread.-wrap11(Unknown Source:0)
        at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1593)
        at android.os.Handler.dispatchMessage(Handler.java:105)
        at android.os.Looper.loop(Looper.java:164)
        at android.app.ActivityThread.main(ActivityThread.java:6541)
        at java.lang.reflect.Method.invoke(Native Method)
        at com.android.internal.os.Zygote$MethodAndArgsCaller.run(Zygote.java:240)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:767)
     Caused by: java.lang.NullPointerException: Attempt to invoke virtual method 'void android.widget.EditText.addTextChangedListener(android.text.TextWatcher)' on a null object reference
        at com.example.itunesapp.result.ResultFragment.onViewCreated(ResultFragment.java:51)
        at android.support.v4.app.FragmentManagerImpl.moveToState(FragmentManager.java:1471)
        at android.support.v4.app.FragmentManagerImpl.moveFragmentToExpectedState(FragmentManager.java:1784)
        at android.support.v4.app.FragmentManagerImpl.moveToState(FragmentManager.java:1852)
        at android.support.v4.app.BackStackRecord.executeOps(BackStackRecord.java:802)
        at android.support.v4.app.FragmentManagerImpl.executeOps(FragmentManager.java:2625)
        at android.support.v4.app.FragmentManagerImpl.executeOpsTogether(FragmentManager.java:2411)
        at android.support.v4.app.FragmentManagerImpl.removeRedundantOperationsAndExecute(FragmentManager.java:2366)
        at android.support.v4.app.FragmentManagerImpl.execPendingActions(FragmentManager.java:2273)
        at android.support.v4.app.FragmentManagerImpl.dispatchStateChange(FragmentManager.java:3273)
        at android.support.v4.app.FragmentManagerImpl.dispatchActivityCreated(FragmentManager.java:3229)
        at android.support.v4.app.FragmentController.dispatchActivityCreated(FragmentController.java:201)
        at android.support.v4.app.FragmentActivity.onStart(FragmentActivity.java:620)
        at android.support.v7.app.AppCompatActivity.onStart(AppCompatActivity.java:178)
        at android.app.Instrumentation.callActivityOnStart(Instrumentation.java:1333)
        at android.app.Activity.performStart(Activity.java:6992)
        at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2780)
        at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2892) 
        at android.app.ActivityThread.-wrap11(Unknown Source:0) 
        at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1593) 
        at android.os.Handler.dispatchMessage(Handler.java:105) 
        at android.os.Looper.loop(Looper.java:164) 
        at android.app.ActivityThread.main(ActivityThread.java:6541) 
        at java.lang.reflect.Method.invoke(Native Method) 
        at com.android.internal.os.Zygote$MethodAndArgsCaller.run(Zygote.java:240) 
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:767) 

}
//ID: 1005435
class MainActivity : AppCompatActivity() {

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val fragment = MainScreenFragment()
    supportFragmentManager.beginTransaction()
        .replace(R.id.fragment_container, fragment) // Type mismatch: inferred type is MainScreenFragment but Fragment was expected
        .commit()
}
...

}
//ID: 682945
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start"&gt;

    &lt;include
        layout="@layout/app_bar_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent" /&gt;

    &lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_main"
        app:menu="@menu/activity_main_drawer" /&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 956089
&lt;activity android:name=".MainActivity"&gt;
        &lt;intent-filter&gt;
            &lt;action android:name="android.intent.action.MAIN" /&gt;

            &lt;category android:name="android.intent.category.LAUNCHER" /&gt;

            &lt;action android:name="android.intent.action.VIEW"/&gt;
            &lt;category android:name="android.intent.category.DEFAULT"/&gt;
            &lt;category android:name="android.intent.category.BROWSABLE"/&gt;
            &lt;data
                android:host="com.divinkas.example.customspinner"
                android:scheme="https"
                /&gt;
            &lt;data
                android:host="com.divinkas.example.customspinner"
                android:scheme="http"
                /&gt;
        &lt;/intent-filter&gt;
    &lt;/activity&gt;

}
//ID: 1065951
apply plugin: 'com.android.application'
apply plugin: 'com.google.gms.google-services'

android {
    compileSdkVersion 29
    buildToolsVersion "29.0.2"
    defaultConfig {
        applicationId "com.example.myapplication"
        minSdkVersion 16
        targetSdkVersion 29
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'com.google.firebase:firebase-ads:18.3.0'
    implementation 'com.google.firebase:firebase-core:17.2.1'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
}

}
//ID: 510945
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;FrameLayout
        android:id="@+id/frame_content"
        android:layout_width="match_parent"
        android:layout_height="match_parent" /&gt;

    &lt;LinearView
        android:id="@+id/drawer"
        android:layout_width="240dp"
        android:layout_height="wrap_content"
        android:layout_gravity="start"
        android:choiceMode="singleChoice"
        android:divider="@android:color/transparent"
        android:dividerHeight="0dp"
        android:background="#ffffff" /&gt;
&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 904822
&lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context=".MainActivity"&gt;

    &lt;FrameLayout
        android:id="@+id/container"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_centerInParent="true" /&gt;


    &lt;me.relex.circleindicator.CircleIndicator
        android:id="@+id/indicator"
        android:layout_width="match_parent"
        android:layout_height="48dp"
        android:layout_alignParentBottom="true" /&gt;

    &lt;android.support.v4.view.ViewPager
        android:id="@+id/pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"&gt;

    &lt;/android.support.v4.view.ViewPager&gt;

&lt;/RelativeLayout&gt;

}
//ID: 511946
android {
    defaultConfig {
        vectorDrawables.useSupportLibrary = true
    }
}

}
//ID: 511838
allprojects {
repositories {
    jcenter()
    maven {
        url "https://jitpack.io"
    }
}

}
//ID: 956892
@Query("SELECT * FROM User ")
fun getAllUsers(): LiveData&lt;MutableList&lt;User&gt;&gt;

@Query("SELECT * FROM User ")
fun getAllUsersRx(): Flowable&lt;MutableList&lt;User&gt;&gt; 

}
//ID: 1112760
 &lt;fragment
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/map"
    tools:context=".MapsActivity"
    android:name="com.google.android.gms.maps.SupportMapFragment" /&gt;

}
//ID: 683147
&lt;application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher2"
        android:label="@string/app_name"
        android:theme="@style/AppTheme"
        android:name=".HeadspaceApplication"&gt;
        &lt;activity
            android:name=".MainActivity"
            android:label="@string/title_activity_main"
            android:launchMode="singleTop"&gt;
            &lt;intent-filter &gt;
                &lt;action android:name="android.intent.action.MAIN" /&gt;
                &lt;category android:name="android.intent.category.DEFAULT"/&gt;
                &lt;category android:name="android.intent.category.LAUNCHER" /&gt;
            &lt;/intent-filter&gt;
        &lt;/activity&gt;

}
//ID: 1112530
    recyclerSubtasks.setLayoutManager(new LinearLayoutManager(this) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                });
    adapter.setHasStableIds(true);
    recyclerSubtasks.setAdapter(adapter);

}
//ID: 626766
&lt;LinearLayout
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:orientation="vertical"&gt;

&lt;android.support.design.widget.AppBarLayout
    android:layout_height="wrap_content"
    android:layout_width="match_parent"&gt;

    &lt;android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:background="?attr/colorPrimary"/&gt;

&lt;/android.support.design.widget.AppBarLayout&gt;

&lt;android.support.v4.widget.DrawerLayout
    android:id="@+id/drawer_layout"
    xmlns:android="http://schemas.android.com/apk/res/android"

    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="false"
    tools:openDrawer="start"&gt;

    &lt;include
        layout="@layout/app_bar_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/&gt;

    &lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_main"
        app:menu="@menu/activity_main_drawer"/&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 242874
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;manifest xmlns:android="http://schemas.android.com/apk/res/android"
          package="com.example.al_3_broadcast_receiver"
          android:versionCode="1"
          android:versionName="1.0"&gt;
    &lt;uses-sdk android:minSdkVersion="10"/&gt;
    &lt;uses-permission android:name="android.permission.READ_PHONE_STATE"/&gt;
    &lt;application android:label="@string/app_name" android:icon="@drawable/ic_launcher"&gt;
        &lt;activity android:name="MainActivity"
                  android:label="@string/app_name"&gt;
            &lt;intent-filter&gt;
                &lt;action android:name="android.intent.action.MAIN"/&gt;
                &lt;category android:name="android.intent.category.LAUNCHER"/&gt;
            &lt;/intent-filter&gt;
        &lt;/activity&gt;
        &lt;activity android:name=".CallActivity"&gt;
            &lt;intent-filter android:priority="999"&gt;
                &lt;action android:name="android.intent.action.ANSWER"/&gt;
                &lt;category android:name="android.intent.category.DEFAULT"/&gt;
            &lt;/intent-filter&gt;
        &lt;/activity&gt;
        &lt;receiver android:name=".CallBroadcastReceiver"&gt;
            &lt;intent-filter android:priority="99999"&gt;
                &lt;action android:name="android.intent.action.PHONE_STATE"/&gt;
            &lt;/intent-filter&gt;
        &lt;/receiver&gt;
    &lt;/application&gt;
&lt;/manifest&gt;

}
//ID: 244388
&lt;?xml version="1.0" encoding="utf-8"?&gt;

&lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                android:layout_width="match_parent"
                android:layout_height="match_parent"&gt;

    &lt;fragment
            android:id="@+id/newMap"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            class="com.google.android.gms.maps.SupportMapFragment"
            android:name="com.google.android.gms.maps.SupportMapFragment"
            /&gt;
  &lt;FrameLayout
            android:id="@+id/mLayout"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            /&gt;

&lt;/RelativeLayout&gt;

}
//ID: 512713
@Singleton
@Component(modules = {NetWorkApiModule.class})
 public interface AppComponent {
 void inject(MainActivity mainActivity);
}

}
//ID: 1064892
apply plugin: 'com.android.application'
apply plugin: "androidx.navigation.safeargs"

android {
    signingConfigs {
        release {
            ...
        }
    }
    compileSdkVersion 29
    defaultConfig {
        applicationId 'ooo.cron.dagestan'
        minSdkVersion 21
        targetSdkVersion 29
        multiDexEnabled true
        versionCode 202
        versionName "2.0.2"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
        ndk {
            ndk.abiFilters 'armeabi-v7a', 'arm64-v8a', 'x86', 'x86_64'
        }
        signingConfig signingConfigs.release
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
            signingConfig signingConfigs.release
        }
        debug {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
            signingConfig signingConfigs.release
        }
    }

    compileOptions {
        sourceCompatibility 1.8
        targetCompatibility 1.8
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    ...
    implementation "com.github.lespinsideg:SimplePanorama:0.3.1"
    ...
    implementation project(path: ':domain')
    implementation project(path: ':data')
}

}
//ID: 513148
&lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"&gt;

&lt;com.example.android.camera2basic.AutoFitTextureView
    android:id="@+id/texture"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"&gt;

&lt;/com.example.android.camera2basic.AutoFitTextureView&gt;

}
//ID: 1067111
@Database(entities = {Business.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {


    public abstract BusinessDao businessDao();
}

}
//ID: 857899
public class MyApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector&lt;Activity&gt; dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent
                .builder()
                .context(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector&lt;Activity&gt; activityInjector() {
        return dispatchingAndroidInjector;
    }
}

}
//ID: 745031
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="false"
    tools:openDrawer="start"&gt;

    &lt;include
        layout="@layout/app_bar_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent" /&gt;

    &lt;android.support.design.widget.NavigationView

        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        app:headerLayout="@layout/nav_header_main"
        app:menu="@menu/activity_main_drawer"/&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 745031
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.md.nurkan.cafeugolok.activity.MainActivity"&gt;

    &lt;android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="@style/AppTheme.AppBarOverlay"&gt;

        &lt;android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:popupTheme="@style/AppTheme.PopupOverlay" /&gt;

    &lt;/android.support.design.widget.AppBarLayout&gt;

    &lt;include layout="@layout/content_main" /&gt;



&lt;/android.support.design.widget.CoordinatorLayout&gt;

}
//ID: 1067884
class ContactsViewModel @Inject constructor(private val contactsRepository: ContactsRepository) :
    ViewModel() {

    var mutableLiveData = MutableLiveData&lt;List&lt;ContactsModel&gt;&gt;()
    private val disposable = CompositeDisposable()

    fun getContactMutableLiveData(): MutableLiveData&lt;List&lt;ContactsModel&gt;&gt; {
        loadData()
        return mutableLiveData
    }

    fun loadData() {

       disposable.add(contactsRepository.modelSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver&lt;List&lt;ContactsModel&gt;&gt;() {
                override fun onSuccess(t: List&lt;ContactsModel&gt;) {
                getContactMutableLiveData().value = t
                }

                override fun onError(e: Throwable) {
                }
            })
        )
    }
}

}
//ID: 685846
&lt;string-array name="arr_vol"&gt;
    &lt;item&gt;Минимальный&lt;/item&gt;
    &lt;item&gt;Средний&lt;/item&gt;
    &lt;item&gt;Максимальный&lt;/item&gt;
&lt;/string-array&gt;

}
//ID: 573526
public class VideoUpload {

@SerializedName("id")
@Expose
private String id;
@SerializedName("videoUrl")
@Expose
String videoUrl;
@SerializedName("user_portrait_huge")
@Expose
private String userPortraitHuge;
@SerializedName("url")
@Expose
private String url;

public String getUserPortraitHuge() {
    return userPortraitHuge;
}

}
//ID: 1114377
override fun onActivityResult(requestCode: Int, resultCode: Int, result: Intent?) {
        super.onActivityResult(requestCode, resultCode, result)
        when {
            requestCode == 1 &amp;&amp; resultCode == Activity.RESULT_OK -&gt; {
                if (result != null) {
                    val documentFile: DocumentFile = DocumentFile.fromSingleUri(this, result.data!!)!!

                    val hereUrl: Uri? = result.data
                    val inputStream = this.contentResolver.openInputStream(hereUrl!!)
                    val byteArray = inputStream!!.readBytes()


                    Timber.i(byteToHex(byteArray).toString())
                }
            }
      }
}

}
//ID: 1068835
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;androidx.drawerlayout.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_alignParentBottom="false"
    android:fitsSystemWindows="true"
    tools:context=".MainActivity"&gt;

    &lt;androidx.appcompat.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="?attr/colorPrimary"

        app:titleTextColor="@android:color/white" /&gt;

    &lt;androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"

        android:padding="8dp" /&gt;


&lt;/androidx.drawerlayout.widget.DrawerLayout&gt;

}
//ID: 628793
android {
    defaultConfig {
        vectorDrawables.useSupportLibrary = true
    }
}

}
//ID: 744818
apply plugin: 'com.android.application'

android {
    compileSdkVersion 26
    defaultConfig {
        applicationId "com.packagename"
        minSdkVersion 15
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:26.1.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
}

}
//ID: 629549
com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
     at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:224)
     at retrofit2.converter.gson.GsonResponseBodyConverter.convert(GsonResponseBodyConverter.java:37)
     at retrofit2.converter.gson.GsonResponseBodyConverter.convert(GsonResponseBodyConverter.java:25)
     at retrofit2.ServiceMethod.toResponse(ServiceMethod.java:117)
     at retrofit2.OkHttpCall.parseResponse(OkHttpCall.java:211)
     at retrofit2.OkHttpCall$1.onResponse(OkHttpCall.java:106)
     at okhttp3.RealCall$AsyncCall.execute(RealCall.java:133)
     at okhttp3.internal.NamedRunnable.run(NamedRunnable.java:32)
     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1113)
     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:588)
     at java.lang.Thread.run(Thread.java:818)
 Caused by: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
     at com.google.gson.stream.JsonReader.beginObject(JsonReader.java:385)
     at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:213)
    ... 10 more

}
//ID: 459143
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/container"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".DashboardActivity"&gt;
    &lt;android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:background="?attr/colorPrimary"
        android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
        app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
        android:elevation="4dp" /&gt;
&lt;/LinearLayout&gt;

}
//ID: 685277
public static final Creator&lt;CommunicatorServiceImpl&gt; CREATOR = new Creator&lt;CommunicatorServiceImpl&gt;() {
    @Override
    public CommunicatorServiceImpl createFromParcel(Parcel in) {
        return new CommunicatorServiceImpl(in);
    }

    @Override
    public CommunicatorServiceImpl[] newArray(int size) {
        return new CommunicatorServiceImpl[size];
    }
};

}
//ID: 794011
    &lt;?xml version="1.0" encoding="utf-8"?&gt;
    &lt;android.support.constraint.ConstraintLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.eugene.myapplication.MainActivity"&gt;

    &lt;TextView
    android:id="@+id/Head"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Выберите цвет!"&gt;
    &lt;/TextView&gt;

    &lt;LinearLayout
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"&gt;

    &lt;Button
    android:id="@+id/green"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="10dp"
    android:layout_marginTop="30dp"
    android:text="Зеленый"&gt;
    &lt;/Button&gt;
    &lt;Button
    android:id="@+id/red"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="10dp"
    android:layout_marginTop="30dp"
    android:text="Красный"&gt;
    &lt;/Button&gt;
    &lt;Button
    android:id="@+id/blue"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="10dp"
    android:layout_marginTop="30dp"
    android:text="Синий"&gt;
    &lt;/Button&gt;
    &lt;/LinearLayout&gt;
    &lt;/android.support.constraint.ConstraintLayout&gt;

}
//ID: 960425
@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

   public abstract WordDao wordDao();
   private static WordRoomDatabase INSTANCE;

   static WordRoomDatabase getDatabase(final Context context) {
       if (INSTANCE == null) {
           synchronized (WordRoomDatabase.class) {
               if (INSTANCE == null) {
                   INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                           WordRoomDatabase.class, "word_database")
                             // Wipes and rebuilds instead of migrating 
                             // if no Migration object.
                            // Migration is not part of this practical.
                           .fallbackToDestructiveMigration()
                           .build();                
               }
           }
       }
       return INSTANCE;
   }
}

}
//ID: 459737
    // Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:+'
        classpath 'com.google.gms:google-services:1.3.0-beta1'
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        jcenter()
    }
}

}
//ID: 792823
&lt;string-array name="1-10"&gt;
        &lt;item&gt;Раз&lt;/item&gt;
        &lt;item&gt;Два&lt;/item&gt;
        &lt;item&gt;Три&lt;/item&gt;
        &lt;item&gt;Четыре&lt;/item&gt;
        &lt;item&gt;Пять&lt;/item&gt;
        &lt;item&gt;Шесть&lt;/item&gt;
        &lt;item&gt;Семь&lt;/item&gt;
        &lt;item&gt;Восемь&lt;/item&gt;
        &lt;item&gt;Девять&lt;/item&gt;
        &lt;item&gt;Десять&lt;/item&gt;
    &lt;/string-array&gt;

}
//ID: 1116963
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        getLastLocation()

        weatherTask().execute()

    }

public class Common {

    public static final String APP_ID = "a6g096e9c7045fffc90db97209788939";

    public static Location current_location = null;


}

}
//ID: 1116963
        override fun doInBackground(vararg params: String?): String? {
            return try{
                URL("https://api.openweathermap.org/data/2.5/weather?lat=${Common.current_location.latitude}&amp;lon=${Common.current_location.latitude}&amp;appid=${Common.APP_ID}").readText(
                    Charsets.UTF_8
                )
            }catch (e: Exception){
                null
            }
        }

}
//ID: 515742
public class UserAPI {
   @SerializedName("id")
   @Expose
   private int id;
   @SerializedName("sid")
   @Expose
   private int sid;
   @SerializedName("full_name")
   @Expose
   private String full_name;
   @SerializedName("iin")
   @Expose
   private String iin;  
...}

}
//ID: 631041
    &lt;intent-filter android:label="My Resumes"&gt;
        &lt;action android:name="android.intent.action.GET_CONTENT" /&gt;
        &lt;data android:mimeType="*/*"/&gt;
        &lt;category android:name="android.intent.category.DEFAULT" /&gt;
        &lt;category android:name="android.intent.category.OPENABLE" /&gt;
    &lt;/intent-filter&gt;

}
//ID: 415616
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content" android:layout_height="wrap_content"
    android:id="@+id/widgetWrapper"&gt;
&lt;/FrameLayout&gt;

}
//ID: 460723
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent" &gt;
  &lt;android.support.design.widget.CoordinatorLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"&gt;

    &lt;android.support.design.widget.AppBarLayout
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"&gt;

        &lt;android.support.design.widget.TabLayout
            android:id="@+id/tabLayout"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:tabIndicatorHeight="6dp"
            android:background="@color/primary"
            app:tabIndicatorColor="@color/accent"
            app:tabSelectedTextColor="@android:color/white"
            app:tabTextColor="@android:color/white"
            app:tabMode="fixed"
            app:tabGravity="center" /&gt;

    &lt;/android.support.design.widget.AppBarLayout&gt;

    &lt;android.support.v4.view.ViewPager
        android:id="@+id/viewPager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior" /&gt;

&lt;/android.support.design.widget.CoordinatorLayout&gt;
&lt;/LinearLayout&gt;

}
//ID: 414771
     buildTypes {
          release {
               minifyEnabled true
               proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
              signingConfig signingConfigs.config
          }
      }

}
//ID: 461846
&lt;string-array name="times"&gt;
    &lt;item&gt;Один&lt;/item&gt;
    &lt;item&gt;Два&lt;/item&gt;
&lt;/string-array&gt;

}
//ID: 912610
@TargetApi(Build.VERSION_CODES.M)
fun isCameraPermissionGranted(
    permissions: Array&lt;String&gt;,
    grantResults: IntArray
): Boolean {
    if (permissions.isEmpty()) {
        return false
    }
    val size = permissions.size
    for (i in 0 until size) {
        if (permission == permissions[i]) {
            return grantResults[i] == PackageManager.PERMISSION_GRANTED
        }
    }
    return false
}

}
//ID: 577332
&lt;android.support.design.widget.NavigationView
    android:id="@+id/nav_view"
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:layout_gravity="start"
    android:fitsSystemWindows="true"
    app:headerLayout="@layout/nav_header_main"
    app:menu="@menu/activity_main_drawer" /&gt;

}
//ID: 631332
    &lt;uses-permission android:name="android.permission.INTERNET"/&gt;        

    &lt;activity android:label="TestBrowser" android:name="WebActivity"&gt;
        &lt;intent-filter&gt;
            &lt;action android:name="android.intent.action.VIEW"/&gt;
            &lt;data android:scheme="http"/&gt;
            &lt;category android:name="android.intent.category.DEFAULT"/&gt;
        &lt;/intent-filter&gt;
    &lt;/activity&gt;

}
//ID: 416420
    &lt;item&gt;Barsik&lt;/item&gt;
    &lt;item&gt;Myrka&lt;/item&gt;
    &lt;item&gt;Vasiliy&lt;/item&gt;



&lt;/string-array&gt;

}
//ID: 518725
Caused by: java.lang.ClassCastException: android.support.v7.widget.AppCompatTextView cannot be cast to com.eranewgames.donatello.MyView.MyTextView
                                                                         at com.eranewgames.donatello.Auth.onCreate(Auth.java:33)

}
//ID: 795694
class BrowserActivity : AppCompatActivity(){

    lateinit var mMenu: Menu
    lateinit var mWebView : WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.m_browser)

        mWebView = findViewById(R.id.mbrowser)
        mMenu = Menu(this, this, mWebView) //context, activity, ...
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       return mMenu.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return mMenu.onOptionsItemSelected(item)
    }
}

}
//ID: 1118856
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;FrameLayout        xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:tools="http://schemas.android.com/tools"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                tools:context=".MainActivity"&gt;

            &lt;fragment
                    android:id="@+id/fragment"
                    tools:layout="@layout/fragment_login"
                    android:name="com.orekhov.oneclick.login.LoginFragment"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"/&gt;

&lt;/FrameLayout&gt;

}
//ID: 462686
&lt;receiver android:name=".notifications.CreateNotificationListReceiver"&gt;
        &lt;intent-filter
            android:priority = "999"&gt;
            &lt;action android:name="android.intent.action.TIME_SET"/&gt;
        &lt;/intent-filter&gt;
    &lt;/receiver&gt;

}
//ID: 862804
&lt;LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;
    &lt;me.grantland.widget.AutofitLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"&gt;
        &lt;me.grantland.widget.AutofitTextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:id="@+id/textSong"/&gt;
    &lt;/me.grantland.widget.AutofitLayout&gt;
&lt;/LinearLayout&gt;

}
//ID: 913444
 &lt;string-array name="spinner_date"&gt;
    &lt;item&gt;Сегодня&lt;/item&gt;
    &lt;item&gt;Завтра&lt;/item&gt;
    &lt;item&gt;Другой день&lt;/item&gt;
&lt;/string-array&gt;

}
//ID: 798393
java.lang.NullPointerException: Attempt to invoke interface method 'java.lang.Object java.util.List.get(int)' on a null object reference
                                                              at example.cwpo18.MainActivity$1.onResponse(MainActivity.java:45)

}
//ID: 964516
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.3.2'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'http://android.aviasales.ru/repositories/' }
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 964516
apply plugin: 'com.android.application'


android {
    compileSdkVersion 28
    defaultConfig {
        applicationId "ru.bruimafia.ticketsdiscount"
        minSdkVersion 16
        targetSdkVersion 28
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation "androidx.core:core:1.1.0-alpha05"
    implementation 'androidx.appcompat:appcompat:1.1.0-alpha03'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'com.google.android.material:material:1.1.0-alpha05'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test:runner:1.1.2-alpha02'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0-alpha02'
    implementation 'ru.aviasales:aviasalesSdk:2.1.16-sdk'
    implementation 'ru.aviasales.template:aviasalesSdkTemplate:2.1.16'
    implementation 'ru.aviasales.template:appodeallib:2.1.16'
}

}
//ID: 751269
     apiManager.getCities()
            .subscribe({
                if (it.data != null) {
                    Log.i("TAG", "response - onNext, data size: " + it.data.size)
                } else {
                    Log.i("TAG", "response - onNext, data is null ")
                }
            }, {
                Log.i("TAG", "response - load error: " + it.message)
            })

}
//ID: 1013577
apply plugin: 'com.android.application'

android {
    compileSdkVersion 29
    buildToolsVersion "29.0.1"
    defaultConfig {
        applicationId "com.example.myfirstapplication"
        minSdkVersion 19
        targetSdkVersion 29
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'androidx.appcompat:appcompat:1.0.2'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test:runner:1.2.0'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
}

}
//ID: 276955
    &lt;activity
        android:label="@string/app_name"
        android:screenOrientation="landscape"
        android:name=".MiniActivity" &gt;
        &lt;intent-filter &gt;
            &lt;action android:name="android.intent.action.VIEW" /&gt;
            &lt;category android:name="android.intent.category.DEFAULT" /&gt;
            &lt;data android:scheme="content"/&gt;
            &lt;data android:scheme="file"/&gt;
            &lt;data android:mimeType="video/*"/&gt;
        &lt;/intent-filter&gt;

}
//ID: 580528
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent" android:layout_height="match_parent"
    android:id="@+id/calendarFL"&gt;

    &lt;CalendarView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/calendarView" /&gt;
&lt;/FrameLayout&gt;

}
//ID: 749975
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.0.0'


        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url 'https://download.01.org/crosswalk/releases/crosswalk/android/maven2'
        }
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 749975
apply plugin: 'com.android.application'

android {
    compileSdkVersion 26
    defaultConfig {
        applicationId "com.example.qwe"
        minSdkVersion 16
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:26.1.0'
    compile 'com.android.support:support-v4:26.1.0'
    compile 'com.android.support:recyclerview-v7:26.1.0'
    compile 'com.android.support:customtabs:26.1.0'
    compile 'org.xwalk:xwalk_core_library:20.50.533.12'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.0'
}

}
//ID: 581286
&lt;android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/coordinator"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"&gt;


        &lt;android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize" /&gt;
    &lt;/android.support.design.widget.AppBarLayout&gt;

    &lt;FrameLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/fragment_list_container"&gt;

    &lt;/FrameLayout&gt;

&lt;/android.support.design.widget.CoordinatorLayout&gt;

}
//ID: 692897
java.lang.IllegalStateException: 
  at android.view.View$DeclaredOnClickListener.resolveMethod(View.java:4532)
  at android.view.View$DeclaredOnClickListener.onClick(View.java:4496)
  at android.view.View.performClick(View.java:5265)
  at android.view.View$PerformClick.run(View.java:21534)
  at android.os.Handler.handleCallback(Handler.java:815)
  at android.os.Handler.dispatchMessage(Handler.java:104)
  at android.os.Looper.loop(Looper.java:207)
  at android.app.ActivityThread.main(ActivityThread.java:5728)
  at java.lang.reflect.Method.invoke(Native Method:0)
  at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:789)
  at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:679)

}
//ID: 865949
&lt;!-- Use DrawerLayout as root container for activity --&gt;
&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"&gt;

    &lt;!-- Layout to contain contents of main body of screen (drawer will slide over this) --&gt;
    &lt;FrameLayout
        android:id="@+id/content_frame"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        /&gt;

    &lt;android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:background="?attr/colorPrimary"
        android:theme="@style/Widget.AppCompat.ActionBar" /&gt;

    &lt;!-- Container for contents of drawer - use NavigationView to make configuration easier --&gt;
    &lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:menu="@menu/drawer_view"/&gt;

    &lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 967003
if (response.isSuccessful()) {
...
}

}
//ID: 580358
&lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_main"
        app:menu="@menu/activity_main_drawer" /&gt;

}
//ID: 752850
ava.lang.NullPointerException: Attempt to invoke interface method 'java.lang.Object java.util.List.get(int)' on a null object reference
                                                       at com.example.asus.testapp.ContentAdapter.onBindViewHolder(ContentAdapter.java:46)
                                                       at com.example.asus.testapp.ContentAdapter.onBindViewHolder(ContentAdapter.java:12)
}
//ID: 521388
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/main_content"
     android:fitsSystemWindows="true"

&gt;


    &lt;android.support.v4.view.ViewPager
        android:id="@+id/pager"
        xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"

        /&gt;


    &lt;ImageView
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="end|bottom"
        android:layout_margin="10dp"
        android:src="@drawable/speaker_icon_on_shadow"

        /&gt;
&lt;/android.support.design.widget.CoordinatorLayout&gt;

}
//ID: 1119593
class AlarmReceiver : BroadcastReceiver() {

    private val TAG = AlarmReceiver::class.java.simpleName

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "onReceive() called with: context = [$context], intent = [$intent]")
        val test = intent.getStringExtra("test")
        if (intent.action != null) {
            if (intent.action.equals(Constants.actionShowRecord, ignoreCase = true)) {
                val record = intent.getSerializableExtra(Record::class.java.simpleName) as? Record
                if (record != null) {
                    NotificationHelper.createRecordTimeNotification(context, record)
                }
            }
        }
    }
}

}
//ID: 1119607
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerViewChat"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/&gt;


&lt;/LinearLayout&gt;

}
//ID: 1074373
FATAL EXCEPTION: main
                                                                                     Process: com.example.user.googlestreetview13, PID: 14497
                                                                                     java.lang.IllegalStateException: Could not execute method for android:onClick
                                                                                         at android.support.v7.app.AppCompatViewInflater$DeclaredOnClickListener.onClick(AppCompatViewInflater.java:293)
                                                                                         at android.view.View.performClick(View.java:5265)
                                                                                         at android.view.View$PerformClick.run(View.java:21534)
                                                                                         at android.os.Handler.handleCallback(Handler.java:815)
                                                                                         at android.os.Handler.dispatchMessage(Handler.java:104)
                                                                                         at android.os.Looper.loop(Looper.java:207)
                                                                                         at android.app.ActivityThread.main(ActivityThread.java:5728)
                                                                                         at java.lang.reflect.Method.invoke(Native Method)
                                                                                         at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:789)
                                                                                         at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:679)
                                                                                      Caused by: java.lang.reflect.InvocationTargetException
                                                                                         at java.lang.reflect.Method.invoke(Native Method)
                                                                                         at android.support.v7.app.AppCompatViewInflater$DeclaredOnClickListener.onClick(AppCompatViewInflater.java:288)
                                                                                         at android.view.View.performClick(View.java:5265) 
                                                                                         at android.view.View$PerformClick.run(View.java:21534) 
                                                                                         at android.os.Handler.handleCallback(Handler.java:815) 
                                                                                         at android.os.Handler.dispatchMessage(Handler.java:104) 
                                                                                         at android.os.Looper.loop(Looper.java:207) 
                                                                                         at android.app.ActivityThread.main(ActivityThread.java:5728) 
                                                                                         at java.lang.reflect.Method.invoke(Native Method) 
                                                                                         at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:789) 
                                                                                         at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:679) 
                                                                                      Caused by: java.lang.NullPointerException: Attempt to invoke virtual method 'void com.google.android.gms.maps.StreetViewPanorama.animateTo(com.google.android.gms.maps.model.StreetViewPanoramaCamera, long)' on a null object reference
                                                                                         at com.zarubaandrej.facebook.arubainkarnator.MainActivity.oCl(MainActivity.java:78)
                                                                                         at java.lang.reflect.Method.invoke(Native Method) 
                                                                                         at android.support.v7.app.AppCompatViewInflater$DeclaredOnClickListener.onClick(AppCompatViewInflater.java:288) 
                                                                                         at android.view.View.performClick(View.java:5265) 
                                                                                         at android.view.View$PerformClick.run(View.java:21534) 
                                                                                         at android.os.Handler.handleCallback(Handler.java:815) 
                                                                                         at android.os.Handler.dispatchMessage(Handler.java:104) 
                                                                                         at android.os.Looper.loop(Looper.java:207) 
                                                                                         at android.app.ActivityThread.main(ActivityThread.java:5728) 
                                                                                         at java.lang.reflect.Method.invoke(Native Method) 
                                                                                         at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:789) 
                                                                                         at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:679) 

}
//ID: 693988
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              xmlns:tools="http://schemas.android.com/tools"
              android:orientation="vertical"
              android:layout_width="match_parent"
              android:layout_height="match_parent"&gt;

    &lt;fragment
                android:name="com.example.leo.myapplication.ButtonFragment"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:id="@+id/button_fragment"
                tools:layout="@layout/button_layout"/&gt;

    &lt;fragment
              android:id="@+id/fragment_image"
              android:name="com.example.leo.myapplication.ImageFragment"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              tools:layout="@layout/image_layout"/&gt;


&lt;/LinearLayout&gt;

}
//ID: 635900
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
android:id="@+id/drawer_layout"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:fitsSystemWindows="true"
tools:openDrawer="start"&gt;

    &lt;android.support.design.widget.NavigationView
    android:id="@+id/nav_view"
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:layout_gravity="start"
    android:fitsSystemWindows="true"
    app:headerLayout="@layout/nav_header_main"
    app:menu="@menu/activity_main_drawer" /&gt;

    &lt;include layout="@layout/photo"&gt;&lt;/include&gt;
    &lt;include layout="@layout/app_bar_main"&gt;&lt;/include&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 635900
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"&gt;
    &lt;!--tools:context="com.example.zaki.mycamera.PhotoActivity"--&gt;

    &lt;android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="@style/AppTheme.AppBarOverlay"&gt;

        &lt;android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:popupTheme="@style/AppTheme.PopupOverlay" /&gt;

    &lt;/android.support.design.widget.AppBarLayout&gt;


&lt;/android.support.design.widget.CoordinatorLayout&gt;

}
//ID: 1075184
public class ViewModelActivity extends ViewModel {

    private NetworkEndpoints networkEndpoints = UnsplashClient
            .getUnsplashClient()
            .create(NetworkEndpoints.class);

    private MutableLiveData&lt;List&lt;Photo&gt;&gt; mutableLiveData = new MutableLiveData&lt;&gt;();

    public MutableLiveData&lt;List&lt;Photo&gt;&gt; getMutableLiveData() {
        mutableLiveData = initData();
        return mutableLiveData;
    }

    private MutableLiveData&lt;List&lt;Photo&gt;&gt; initData() {
        networkEndpoints.getRandomPhotos(10).enqueue(new Callback&lt;List&lt;Photo&gt;&gt;() {
            @Override
            public void onResponse(Call&lt;List&lt;Photo&gt;&gt; call, Response&lt;List&lt;Photo&gt;&gt; response) {
                mutableLiveData.setValue(response.body());

            }

            @Override
            public void onFailure(Call&lt;List&lt;Photo&gt;&gt; call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
} 

}
//ID: 915746
class PageFragment : BaseFragment() {

    private val pageName= "fly"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        ...

        val viewModel = ViewModelProviders.of(this).get(PageViewModel(activity!!.appliacation)::class.java)

        viewModel.getPages().observe(this, Observer&lt;Page&gt; { page -&gt;
            adapter.contents = page!!.content

        })
        viewModel.pageName = pageName

        return view
    }
}

}
//ID: 1013902
apply plugin: 'com.android.application'

android {

    dataBinding {
        enabled = true
    }
    compileSdkVersion 28
    defaultConfig {
        applicationId "com.example.testingsmr"
        minSdkVersion 19
        targetSdkVersion 28
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation fileTree(include: ['*.jar'], dir: 'libs')
    implementation 'androidx.appcompat:appcompat:1.0.2'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'androidx.vectordrawable:vectordrawable:1.0.1'
    implementation 'androidx.cardview:cardview:1.0.0'
    implementation 'androidx.recyclerview:recyclerview:1.1.0-alpha06'
    implementation 'com.github.PhilJay:MPAndroidChart:v3.1.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test:runner:1.2.0'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
    implementation 'com.google.android.material:material:1.0.0'
}

}
//ID: 1075618
apply plugin: 'com.android.application'

android {
    compileSdkVersion 29
    buildToolsVersion "29.0.2"
    defaultConfig {
        applicationId ".MyApp"
        minSdkVersion 19
        targetSdkVersion 29
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
        compileOptions {
            sourceCompatibility JavaVersion.VERSION_1_8
            targetCompatibility JavaVersion.VERSION_1_8
        }
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])

    implementation "androidx.room:room-rxjava2:2.2.3"
    implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'

    implementation "androidx.room:room-runtime:2.2.3"
    annotationProcessor  "androidx.room:room-compiler:2.2.3"

    implementation 'com.squareup.retrofit2:retrofit:2.7.1'
    implementation 'com.squareup.retrofit2:converter-gson:2.7.1'
    implementation 'com.squareup.picasso:picasso:2.71828'

    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'com.google.android.material:material:1.0.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'androidx.vectordrawable:vectordrawable:1.1.0'
    implementation 'androidx.navigation:navigation-fragment:2.2.0'
    implementation 'androidx.navigation:navigation-ui:2.2.0'
    implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
    //noinspection LifecycleAnnotationProcessorWithJava8
    annotationProcessor  "androidx.lifecycle:lifecycle-compiler:2.2.0"
    testImplementation 'junit:junit:4.13'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
}

}
//ID: 582161
&lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"&gt;

        &lt;LinearLayout
            android:id="@+id/linear_layout"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_gravity="start"
            android:background="@color/white"
            android:orientation="vertical"
            android:theme="@style/ThemeOverlay.AppCompat.Dark"&gt;

            ...

       &lt;/LinearLayout&gt;
&lt;/android.support.design.widget.NavigationView&gt;

}
//ID: 582169
&lt;uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" /&gt;
    &lt;receiver android:name=".StartAtBoot" android:enabled="true" android:exported="true"  &gt;
        &lt;intent-filter &gt;
            &lt;action android:name="android.intent.action.BOOT_COMPLETED" /&gt;
        &lt;/intent-filter&gt;
    &lt;/receiver&gt;

}
//ID: 634701
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
          xmlns:tools="http://schemas.android.com/tools"
          android:orientation="vertical"
          android:layout_width="match_parent"
          android:layout_height="match_parent"&gt;
    &lt;android.support.v4.view.ViewPager
    android:id="@+id/scheduler_datetime"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/&gt;
    &lt;/LinearLayout&gt;

}
//ID: 422738
&lt;LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" &gt;
    &lt;android.support.v7.widget.Toolbar
        android:id="@+id/Activity_Toolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:minHeight="?attr/actionBarSize"
        android:background="?attr/colorPrimary"
        android:elevation="10dp" /&gt; ← Не работает
    &lt;RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent" &gt;
        &lt;FrameLayout
            android:id="@+id/Activity_FC"
            android:layout_width="match_parent"
            android:layout_height="match_parent" /&gt;
        &lt;LinearLayout
            android:id="@+id/Activity_PTC"
            android:layout_width="match_parent"
            android:layout_height="match_parent" /&gt;
    &lt;/RelativeLayout&gt;
&lt;/LinearLayout&gt;

}
//ID: 465416
Intent.ACTION_VIEW
Intent.ACTION_GET_CONTENT

}
//ID: 1121482
class ScanHistoryAdapter(private var dataset: List&lt;Scan&gt;, private val db: AppDatabase) :
    RecyclerView.Adapter&lt;ScanHistoryAdapter.ViewHolder&gt;() {
    class ViewHolder(val historyItem: HistoryItem) : RecyclerView.ViewHolder(historyItem)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(HistoryItem(parent, db))


    fun updateDataset(scans: List&lt;Scan&gt;) {
        dataset = scans
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewItem = holder.historyItem
        val scan = dataset[position]
        viewItem.setScan(scan)
    }
}

}
//ID: 1121487
// gradle:

apply plugin: 'com.android.application'


android {
    compileSdkVersion 29
    buildToolsVersion '29.0.3'
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    defaultConfig {
        applicationId 'adwantay.studio.application'
        minSdkVersion 24
        targetSdkVersion 29
        versionCode 1
        versionName "1.0"


        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

}


dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])

    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    implementation 'com.google.firebase:firebase-ads:19.1.0'
    testImplementation 'junit:junit:4.13'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
    implementation 'androidx.cardview:cardview:1.0.0'
    implementation 'androidx.recyclerview:recyclerview:1.1.0'
    implementation 'com.squareup.retrofit2:retrofit:2.3.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
    implementation 'de.hdodenhof:circleimageview:3.1.0'
    implementation 'com.mikhaellopez:circularimageview:3.2.0'
    implementation 'com.google.android.gms:play-services-ads:19.1.0'

    implementation 'com.google.android.material:material:1.1.0'
    implementation 'androidx.coordinatorlayout:coordinatorlayout:1.1.0'
    implementation 'androidx.appcompat:appcompat:1.1.0'

    // butter knife

    implementation 'com.squareup.picasso:picasso:2.5.2'
    implementation 'com.google.android.material:material:1.1.0'
}
apply plugin: 'com.google.gms.google-services'

}
//ID: 636585
public void timerToConnect() {

        final CountDownTimer countDownTimer = new CountDownTimer(1000 * 60 * TIME_TO_REQUEST, 1000 * 60 * TIME_TO_REQUEST_INTERVAL) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                getDataFromServer();
            }
        };
        countDownTimer.start();
    }

}
//ID: 754305
apply plugin: 'com.android.application'

android {
    compileSdkVersion 27
    defaultConfig {
        applicationId "com.example.com.shcherbuk"
        minSdkVersion 16
        targetSdkVersion 27
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:27.0.2'
    compile 'com.android.support:customtabs:27.0.2'
    compile 'org.xwalk:xwalk_core_library:20.50.533.12'
    compile 'com.squareup.okhttp3:okhttp:3.7.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
}

}
//ID: 918711
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".NewsActivity"
    android:orientation="vertical"&gt;

    &lt;androidx.appcompat.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:background="@color/colorPrimary"
        app:title="26.06.2018"
        /&gt;

    &lt;androidx.drawerlayout.widget.DrawerLayout
        android:id="@+id/drawer"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:fitsSystemWindows="true"
        tools:openDrawer="start"&gt;

        &lt;com.google.android.material.navigation.NavigationView
            android:id="@+id/nav"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:fitsSystemWindows="true"
            android:layout_gravity="start"
            app:headerLayout="@layout/nav_header"
            app:menu="@menu/menu_main_drawer"/&gt;
    &lt;/androidx.drawerlayout.widget.DrawerLayout&gt;

&lt;/LinearLayout&gt;

}
//ID: 523920
public void setImageResource(@DrawableRes int resId) {
   ...
   mResource = resId;
   ...
}

}
//ID: 967865
&lt;androidx.core.widget.NestedScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent"&gt;
        &lt;LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical"&gt;
        &lt;androidx.constraintlayout.widget.ConstraintLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"&gt;
        &lt;!-- Any controls --&gt;
    &lt;/androidx.constraintlayout.widget.ConstraintLayout&gt; 
    &lt;com.google.android.material.tabs.TabLayout
        android:id="@+id/tabs"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:tabMode="scrollable" /&gt;

    &lt;androidx.viewpager.widget.ViewPager
        android:id="@+id/viewPager"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@android:color/white"
        app:layout_behavior="@string/appbar_scrolling_view_behavior" /&gt;
        &lt;/LinearLayout&gt;
&lt;/androidx.core.widget.NestedScrollView&gt;

}
//ID: 917838
 @Dao
 public interface TrainersDao { 

 @Query("SELECT * FROM trainers") 
 Flowable&lt;List&lt;Trainer&gt;&gt; getAll(); 
    //...
    }

}
//ID: 1078002
&lt;string-array name="lang_value"&gt;
    &lt;item name="english"&gt;en&lt;/item&gt;
    &lt;item name="russian"&gt;ru&lt;/item&gt;
    &lt;item name="ukrainian"&gt;uk&lt;/item&gt;
&lt;/string-array&gt;

}
//ID: 583606
public class VideoUpload {

@SerializedName("id")
@Expose
private String id;
@SerializedName("videoUrl")
@Expose
String videoUrl;
@SerializedName("user_portrait_huge")
@Expose
private String userPortraitHuge;
@SerializedName("url")
@Expose
private String url;

public String getUserPortraitHuge() {
    return userPortraitHuge;
}

public void setUserPortraitHuge(String userPortraitHuge) {
    this.userPortraitHuge = userPortraitHuge;
}

public String getUrl() {
    return url;
}

public void setUrl(String url) {
    this.url = url;
}

boolean loadImage = false;

public String getVideoUrl() {
    return videoUrl;
}

public void setVideoUrl(String videoUrl) {
    this.videoUrl = videoUrl;
}

public String getId() {
    return id;
}

public void setId(String id) {
    this.id = id;
}

public boolean isLoadImage() {
    return loadImage;
}

public void setLoadImage(boolean loadImage) {
    this.loadImage = loadImage;
}

}
//ID: 582417
@SerializedName("success")
@Expose
private Boolean success;
@SerializedName("output_metadata")
@Expose
private OutputMetadata outputMetadata;
@SerializedName("handler")
@Expose
private Integer handler;
@SerializedName("id")
@Expose
private String id;
private List&lt;VideoUpload&gt; videoUploads = new ArrayList&lt;&gt;();
@SerializedName("url")
@Expose
private String url;

}
//ID: 583058
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v7.widget.CardView
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"&gt;

    &lt;TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/currencyName"
        android:layout_alignParentTop="true"
        android:textSize="30sp" /&gt;

&lt;/android.support.v7.widget.CardView&gt;

}
//ID: 583058
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:activity=".View.NewCaptureActivity"&gt;

    &lt;SurfaceView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_centerInParent="true"
        android:id="@+id/preview_view"/&gt;

    &lt;FrameLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/fragment"/&gt;

&lt;/FrameLayout&gt;

}
//ID: 918856
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.2.1'


        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 585182
    &lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout android:id="@+id/drawer_layout"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start"&gt;

    &lt;include
        layout="@layout/app_bar_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/&gt;

    &lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"

        app:headerLayout="@layout/nav_header_main"
        app:menu="@menu/activity_main_drawer"/&gt;


    &lt;android.support.design.widget.BottomNavigationView
        android:id="@+id/bottom_navigation"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"


        app:itemBackground="@color/navigation"
        app:itemIconTint="@color/bottom_navigation_item_background_colors"
        app:itemTextColor="@color/bottom_navigation_item_background_colors"
        app:menu="@menu/bottomnavigationview" /&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 585182
&lt;RelativeLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;include
        layout="@layout/app_bar_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent" /&gt;

    &lt;android.support.design.widget.BottomNavigationView
        android:id="@+id/bottom_navigation"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"

        app:itemBackground="@color/navigation"
        app:itemIconTint="@color/bottom_navigation_item_background_colors"
        app:itemTextColor="@color/bottom_navigation_item_background_colors"
        app:menu="@menu/bottomnavigationview" /&gt;
&lt;/RelativeLayout&gt;


&lt;android.support.design.widget.NavigationView
    android:id="@+id/nav_view"
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:layout_gravity="start"
    android:fitsSystemWindows="true"
    app:headerLayout="@layout/nav_header_main"
    app:menu="@menu/activity_main_drawer" /&gt;

}
//ID: 637547
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:context="com.application.nikit.mydoctor.Activity.MedicamentActivity"
    android:orientation="vertical"&gt;

    &lt;android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="@style/AppTheme.AppBarOverlay"

        &gt;

        &lt;android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:popupTheme="@style/AppTheme.PopupOverlay"
            /&gt;

        &lt;TextView
            android:text="TextView"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:id="@+id/text_head"
            android:layout_weight="1" /&gt;

    &lt;/android.support.design.widget.AppBarLayout&gt;

    &lt;include layout="@layout/content_tablet_pager"
        android:layout_height="wrap_content"
        android:layout_width="wrap_content" /&gt;


&lt;/LinearLayout&gt;

}
//ID: 869775
 public void onResponse(@NonNull Call&lt;ViewMessage&gt; call, @NonNull Response&lt;ViewMessage&gt; response) {

                if (response.isSuccessful())
                {

              tvPerson.setText(Objects.requireNonNull(response.body()).getName());
                    tvTheme.setText(Objects.requireNonNull(response.body()).getSubject());
                    tvBody.setText(Objects.requireNonNull(response.body()).getBody());
                    tvDate.setText(Objects.requireNonNull(response.body()).getDate());
                } else {
                    ResponseBody errorBody = response.errorBody();
                    try {
                        if (Objects.requireNonNull(errorBody).string().contains("access_token_expired")) {
                            updateToken();
                        } else if (errorBody.string().contains("invalid_token")) {
                            logOut();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

}
//ID: 1078848
private val query = mutableLiveData("")
fun handleSearchQuery(text: String?) {
        query.value = text
}

fun &lt;T&gt; mutableLiveData(defaultValue: T? = null): MutableLiveData&lt;T&gt;{
    val data = MutableLiveData&lt;T&gt;()

    if(defaultValue != null){
        data.value = defaultValue
    }

    return data
}

}
//ID: 756162
apply plugin: 'com.android.application'

android {
    compileSdkVersion 27
    defaultConfig {
        applicationId "com.hodite.shcherbuk"
        minSdkVersion 16
        targetSdkVersion 27
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:27.0.2'
    compile 'com.android.support:customtabs:27.0.2'
    compile 'org.xwalk:xwalk_core_library:20.50.533.12'
    compile 'com.squareup.okhttp3:okhttp:3.7.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
}

}
//ID: 1079025
textViewPhoneValue.setOnClickListener {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phone")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

}
//ID: 1078536
class MainActivity : AppCompatActivity() {
  val url = "http://..."

   override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    webView.settings.javaScriptEnabled = true
    webView.webViewClient = webViewClient
    webView.loadUrl(url)
  }

  private val webViewClient = object : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }

    override fun shouldInterceptRequest(view: WebView?, url: String?): WebResourceResponse? {
        return getNewResponse(url!!)
    }

  fun getNewResponse(url: String): WebResourceResponse? {
    return try {
        val httpClient = OkHttpClient()
        val request: Request = Request.Builder()
            .url(url)
            .addHeader("Authorization", "value")
            .build()
        val response: Response = httpClient.newCall(request).execute()

        WebResourceResponse("","",response.body()?.byteStream())
    } catch (e: Exception) {
        null
    }
}

 }  

}
//ID: 756612
android {
    compileSdkVersion 26
    defaultConfig {
        applicationId "com.test.test"
        minSdkVersion 16
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:26.1.0'
    implementation 'com.google.android.gms:play-services-maps:11.6.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    implementation 'com.android.support:design:26.1.0'
    implementation 'com.android.support:support-v4:26.1.0'
    implementation 'com.android.support:support-vector-drawable:26.1.0'
    compile 'gun0912.ted:tedpermission:2.1.0'
    compile "com.google.android.gms:play-services-location:11.6.0"
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'

    // Addition library
    compile 'com.github.Kennyc1012:BottomSheet:2.4.0'



    compile 'com.squareup.retrofit2:retrofit:2.3.0'
    compile 'com.squareup.retrofit2:converter-gson:2.3.0'
    compile 'com.squareup.okhttp3:okhttp:3.9.1'

    implementation 'com.android.support:mediarouter-v7:26.1.0'
}

}
//ID: 919962
public class Envelope&lt;T&gt; {
@SerializedName("data")
@Expose
private T data;

@SerializedName("error")
@Expose
private JSONObject error;
...

}
//ID: 1125614
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;androidx.drawerlayout.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start"&gt;

    &lt;include
        layout="@layout/app_bar_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent" /&gt;

    &lt;com.google.android.material.navigation.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_main"
        app:menu="@menu/activity_main_drawer" /&gt;

&lt;/androidx.drawerlayout.widget.DrawerLayout&gt;

}
//ID: 1078400
dependencies {
implementation fileTree(dir: 'libs', include: ['*.jar'])
implementation 'androidx.appcompat:appcompat:1.1.0'
implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
testImplementation 'junit:junit:4.12'
androidTestImplementation 'androidx.test.ext:junit:1.1.1'
androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
implementation 'com.google.android.material:material:1.0.0'
implementation "androidx.cardview:cardview:1.0.0"
implementation "androidx.recyclerview:recyclerview:1.1.0"
implementation 'com.android.volley:volley:1.1.1'
}

}
//ID: 425187
buildscript {

repositories {
    mavenCentral()
}

dependencies {
    classpath 'com.android.tools.build:gradle:1.2.3'
 }
}
apply plugin: 'com.android.application'

repositories {
   mavenCentral()
   mavenLocal()
}
android {

lintOptions {
    abortOnError false
}

compileSdkVersion 21
buildToolsVersion "22.0.1"

defaultConfig {
    applicationId "com.blackmamba.gopgame"
    minSdkVersion 14
    targetSdkVersion 21
    versionCode 1
    versionName "1.0"
}

sourceSets {
    main {
        manifest.srcFile 'AndroidManifest.xml'
        resources.srcDirs = ['src']
        res.srcDirs = ['res']
    }
 }
  compileOptions {
    sourceCompatibility JavaVersion.VERSION_1_7
    targetCompatibility JavaVersion.VERSION_1_7
 }
}

dependencies {
 compile 'com.android.support:appcompat-v7:22.1.1'
 compile 'com.android.support:support-v13:22.1.1'
}

}
//ID: 756922
@Singleton
@Component(modules = ModelModule.class)
public interface AppComponent {

    void inject(AuthPresenter presenter);
}

}
//ID: 640011
&lt;resources&gt;
&lt;string name="app_name"&gt;noyify&lt;/string&gt;

&lt;string-array name="_names"&gt;
    &lt;item&gt;Pic1&lt;/item&gt;
    &lt;item&gt;Pic2&lt;/item&gt;
    &lt;item&gt;Pic3&lt;/item&gt;
    &lt;item&gt;Pic4&lt;/item&gt;
    &lt;item&gt;Pic5&lt;/item&gt;

&lt;/string-array&gt;

}
//ID: 469184
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity"&gt;
        &lt;fragment
            android:name="com.example.nikolai.viewpagerstartandroid.ButtonFragment"
            android:id="@+id/button_fragment"
            android:layout_height="wrap_content"
            android:layout_width="match_parent"
            tools:layout="@layout/button"&gt;
        &lt;/fragment&gt;
    &lt;android.support.v4.view.ViewPager
        android:id="@+id/pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true"&gt;
    &lt;/android.support.v4.view.ViewPager&gt;
&lt;/RelativeLayout&gt;

}
//ID: 1080125
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.5.3'
        classpath 'com.google.gms:google-services:4.3.3'
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 1080125
apply plugin: 'com.android.application'

android {
    compileSdkVersion 28
    defaultConfig {
        applicationId "myapp"
        minSdkVersion 19
        targetSdkVersion 28
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }

    packagingOptions{
        exclude 'META-INF/LICENSE'
        exclude 'META-INF/LICENSE-FIREBASE.txt'
        exclude 'META-INF/NOTICE'

    }
}

dependencies {
    def room_version = "2.1.0"
    def lifecycle_version = "2.2.0"
    def paging_version = "2.1.0"
    def recyclerview_version = "1.1.0"

    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    implementation 'com.google.android.material:material:1.0.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'androidx.navigation:navigation-fragment:2.2.0'
    implementation 'androidx.navigation:navigation-ui:2.2.0'
    implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'

    implementation 'com.github.bumptech.glide:glide:3.7.0'
    implementation 'com.google.firebase:firebase-core:17.2.2'
    implementation 'com.google.firebase:firebase-auth:19.2.0'
    implementation 'com.google.android.gms:play-services-auth:17.0.0'
}

apply plugin: 'com.google.gms.google-services'

}
//ID: 757164
public class Event 
{

   @SerializedName("id")
   @Expose
   private Integer id;
   @SerializedName("competitionId")
   @Expose
   private Integer competitionId;
   //.....
 }

}
//ID: 807536
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="wrap_content"
app:layout_behavior="@string/appbar_scrolling_view_behavior"
tools:context="com.rodgersdevelop.listsformagazine.ActivityMain"
tools:showIn="@layout/activity_main"&gt;


&lt;FrameLayout
    android:id="@+id/mainFrame"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" /&gt;
&lt;/LinearLayout&gt;

}
//ID: 587586
public class MyFragment extends Fragment {
    String s = getString(R.string.mystring);
    final private int n = getArguments().getInt(s, 0);
    //код
}

}
//ID: 759129
    &lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start"&gt;

    &lt;include
        layout="@layout/app_bar_main2"
        android:layout_width="match_parent"
        android:layout_height="match_parent" /&gt;

    &lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_profile"
        app:itemTextAppearance="@style/Navigation"
        app:menu="@menu/activity_main2_drawer" /&gt;/&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 759183
&lt;android.support.v4.widget.DrawerLayout
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
android:id="@+id/drawer_layout"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:openDrawer="start"&gt;

&lt;include
    layout="@layout/app_bar"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"/&gt;

&lt;android.support.design.widget.NavigationView
    android:id="@+id/nav_view"
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:layout_gravity="start"
    android:fitsSystemWindows="true"
    app:headerLayout="@layout/nav_header"
    app:menu="@menu/bar_drawer"/&gt;

}
//ID: 759183
&lt;android.support.design.widget.CoordinatorLayout
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context="com.example.raymaletdin.logview.view.StatusLog"&gt;

&lt;android.support.design.widget.AppBarLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:fitsSystemWindows="true"&gt;


    &lt;android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:background="?attr/colorPrimary"
        android:fitsSystemWindows="true" /&gt;
&lt;/android.support.design.widget.AppBarLayout&gt;

&lt;include layout="@layout/activity_status_log"/&gt;

}
//ID: 472279
&lt;string-array name="name"&gt;
        &lt;item&gt;0&lt;/item&gt;
        &lt;item&gt;3&lt;/item&gt;
        &lt;item&gt;4&lt;/item&gt;
        &lt;item&gt;5&lt;/item&gt;
    &lt;/string-array&gt;

}
//ID: 472297
&lt;string-array name="name"&gt;
        &lt;item&gt;0&lt;/item&gt;
        &lt;item&gt;3&lt;/item&gt;
        &lt;item&gt;4&lt;/item&gt;
        &lt;item&gt;5&lt;/item&gt;
    &lt;/string-array&gt;

}
//ID: 871249
android {
  defaultConfig {
    vectorDrawables.useSupportLibrary = true
  }
}

}
//ID: 528889
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout   xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
android:id="@+id/drawer_layout"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:fitsSystemWindows="true"
tools:openDrawer="start"&gt;

&lt;include
    layout="@layout/app_bar_mainsecond"
    android:layout_width="match_parent"
    android:layout_height="match_parent" /&gt;

&lt;android.support.design.widget.NavigationView
    android:id="@+id/nav_view"
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:layout_gravity="start"
    android:fitsSystemWindows="true"
    app:headerLayout="@layout/nav_header_mainsecond"
    app:menu="@menu/activity_therd_drawer" /&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 528468
&lt;intent-filter android:priority="2147483647"&gt;             
&lt;action android:name="android.intent.action.HEADSET_PLUG" /&gt;

}
//ID: 528468
&lt;uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" /&gt;

&lt;uses-permission android:name="android.permission.RECORD_AUDIO" /&gt;
&lt;uses-permission android:name="android.permission.MODIFY_AUDIO_SETTINGS" /&gt;
&lt;uses-permission android:name="android.permission.RECEIVE_HEADSET_PLUG" /&gt;

&lt;uses-permission android:name="android.permission.RECEIVE_SMS" /&gt;
&lt;uses-permission android:name="android.permission.READ_SMS" /&gt;
&lt;uses-permission android:name="android.permission.SEND_SMS" /&gt;

&lt;uses-permission android:name="android.permission.WAKE_LOCK" /&gt;


&lt;application
    android:allowBackup="true"
    android:icon="@mipmap/ic_launcher"
    android:label="@string/app_name"
    android:supportsRtl="true"
    android:theme="@style/AppTheme"&gt;
    &lt;activity android:name=".Main"&gt;
        &lt;intent-filter&gt;
            &lt;action android:name="android.intent.action.MAIN" /&gt;
            &lt;category android:name="android.intent.category.LAUNCHER" /&gt;
        &lt;/intent-filter&gt;
    &lt;/activity&gt;

    &lt;receiver
        android:name="com.example.kiril.micvol.SmsReceiver"
        android:enabled="true"
        android:exported="true"
        android:permission="android.permission.BROADCAST_SMS"
        android:process=":service"&gt;
        &lt;intent-filter android:priority="2147483647"&gt;
            &lt;action android:name="android.provider.Telephony.SMS_RECEIVED" /&gt;
            &lt;action android:name="android.provider.Telephony.SMS_DELIVER" /&gt;
            &lt;action android:name="android.intent.action.BOOT_COMPLETED" /&gt;
        &lt;/intent-filter&gt;
    &lt;/receiver&gt;

    &lt;receiver android:name="com.example.kiril.micvol.MyReceiver"
        android:enabled="true"
        android:exported="true"
        android:permission="android.permission.RECEIVE_HEADSET_PLUG"
        android:process=":service"&gt;
        &lt;intent-filter android:priority="2147483647"&gt;
            &lt;action android:name="android.intent.action.BOOT_COMPLETED" /&gt;
            &lt;action android:name="android.intent.action.HEADSET_PLUG" /&gt;
        &lt;/intent-filter&gt;
    &lt;/receiver&gt;

    &lt;service
        android:name="com.example.kiril.micvol.MyService"
        android:enabled="true"
        android:exported="true"
        android:process=":service"&gt;
        &lt;intent-filter&gt;
            &lt;action android:name="com.example.kiril.micvol" /&gt;
            &lt;action android:name="android.intent.action.BOOT_COMPLETED" /&gt;
        &lt;/intent-filter&gt;
    &lt;/service&gt;
&lt;/application&gt;

}
//ID: 1128814
class ChatovodFragment: Fragment() {

private lateinit var webView: WebView

companion object {
    fun newInstance(url: String): ChatovodFragment {
        val fragment = ChatovodFragment()
        val bundle = Bundle()
        bundle.putString(BUNDLE_CHATOVOD_URL, url)
        fragment.arguments = bundle
        return fragment
    }
}

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (webView.canGoBack()) {
                webView.goBack()
            } else {
                activity?.supportFragmentManager?.popBackStack()
            }
        }
    })
}

@SuppressLint("SetJavaScriptEnabled")
override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    val view = inflater.inflate(R.layout.fragment_chatovod, container, false)
    val url: String =
        if (arguments != null) {
            requireArguments().getString(BUNDLE_CHATOVOD_URL, "")
        } else
            ""
    webView = view.findViewById(R.id.webView)
    webView.settings.javaScriptEnabled = true
    webView.loadUrl(url)
    webView.webViewClient = MyWebViewClient()
    return view
}

class MyWebViewClient: WebViewClient() {
    override fun shouldOverrideUrlLoading(
        view: WebView?,
        request: WebResourceRequest?
    ): Boolean {
        view?.loadUrl(request?.url.toString())
        return true
    }
}

}
//ID: 588709
&lt;fragment android:id="@+id/map"
    android:name="com.google.android.gms.maps.MapFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:android="http://schemas.android.com/apk/res/android" /&gt;

}
//ID: 874688
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:id="@+id/drawer_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fitsSystemWindows="true"&gt;

        &lt;ListView
            android:id="@+id/listView1"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"&gt;
        &lt;/ListView&gt;

        &lt;android.support.design.widget.NavigationView
            android:id="@+id/nav_view"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_gravity="start"
            android:fitsSystemWindows="true"
            app:menu="@menu/menu_category"/&gt;
    &lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 1130858
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;
    &lt;TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_margin="10dp"
        android:text="test"
        /&gt;
&lt;/LinearLayout&gt;

}
//ID: 809787
apply plugin: 'com.android.application'

android {
    compileSdkVersion 27
        defaultConfig {
        applicationId "com.personal.anton.notes"
        minSdkVersion 17
        targetSdkVersion 27
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:27.1.0'
    implementation 'com.android.support:design:27.1.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    implementation 'com.android.support:recyclerview-v7:27.1.0'
    implementation 'com.github.bumptech.glide:glide:3.7.0'
    implementation 'com.android.volley:volley:1.0.0'
}

}
//ID: 926384
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        jcenter()
        maven {
            url "http://dl.bintray.com/lukaville/maven"
        }
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.2.1'
        classpath 'com.google.gms:google-services:4.2.0'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url "https://jitpack.io" }
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

configurations {
    cleanedAnnotations
    compile.exclude group: 'org.intellij.lang' , module:'annotations'
}

}
//ID: 1023346
&lt;string name="size" translatable="false"&gt;size&lt;/string&gt;
    &lt;string-array name="Set_string"&gt;
        &lt;item&gt;14&lt;/item&gt;
        ...................
        &lt;item&gt;24&lt;/item&gt;
        &lt;item&gt;26&lt;/item&gt;
    &lt;/string-array&gt;
    &lt;string-array name="Set_Values"&gt;
        &lt;item&gt;14&lt;/item&gt;
       .....................
        &lt;item&gt;24&lt;/item&gt;
        &lt;item&gt;26&lt;/item&gt;
    &lt;/string-array&gt;
}
//ID: 760757
&lt;android.support.design.widget.AppBarLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:theme="@style/AppTheme.AppBarOverlay"&gt;

    &lt;android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:layout_weight="1"
        android:background="?attr/colorPrimary"
        app:popupTheme="@style/AppTheme.PopupOverlay" /&gt;
&lt;/android.support.design.widget.AppBarLayout&gt;

&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start"&gt;

    &lt;include
        layout="@layout/app_bar_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent" /&gt;

    &lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_main"
        app:menu="@menu/activity_main_drawer" /&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 1023853
&lt;receiver android:name=".AlarmReceiver"
            android:enabled="true"
            android:exported="true"
            android:permission="android.permission.RECEIVE_BOOT_COMPLETED"&gt;
            &lt;intent-filter&gt;
                &lt;action android:name="android.intent.action.BOOT_COMPLETED"/&gt;
            &lt;/intent-filter&gt;
        &lt;/receiver&gt;

}
//ID: 761100
&lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_task_list"&gt;

        &lt;include layout="@layout/navigation_drawer_menu"/&gt;

    &lt;/android.support.design.widget.NavigationView&gt;

}
//ID: 875027
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:id="@+id/ui_commonLayout"
android:layout_width="match_parent"
android:layout_height="match_parent"&gt;

  &lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:tools="http://schemas.android.com/tools"
      android:id="@+id/ui_mainLayout"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:layout_centerInParent="true"
      tools:context=".PictureWorkActivity"&gt;

  &lt;/FrameLayout&gt;

  &lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
      android:id="@+id/ui_progressImageLayout"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:layout_centerInParent="true"
      tools:context=".PictureWorkActivity"&gt;

  &lt;/FrameLayout&gt;

  &lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
      android:id="@+id/ui_completeImageLayout"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:layout_centerInParent="true"
      tools:context=".PictureWorkActivity"&gt;

  &lt;/FrameLayout&gt;

&lt;/RelativeLayout&gt;

}
//ID: 704202
interface OnNumberChangeCallback{
    void onSuccess();
}

}
//ID: 645239
 &lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="match_parent"&gt;

        &lt;TextView
            android:id="@+id/nameFriend"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="TextView" /&gt;

    &lt;/LinearLayout&gt;

}
//ID: 928315
buildscript {
repositories {
    jcenter()
    google()
}
dependencies {
    classpath 'com.android.tools.build:gradle:3.2.1'
}
}
allprojects {
repositories {
    repositories {
    google()
    jcenter()
    }
}
}
apply plugin: 'com.android.application'
dependencies {
implementation fileTree(dir: 'libs', include: ['*.jar'])
}
android {
compileSdkVersion 28
buildToolsVersion '28.0.3'
defaultConfig {
    minSdkVersion 16
    targetSdkVersion 28
    applicationId 'com.AlexGame.Troubles'
    ndk {
        abiFilters 'x86'
    }
    versionCode 1
    versionName '0.1'
}

lintOptions {
    abortOnError false
}

aaptOptions {
    noCompress '.unity3d', '.ress', '.resource', '.obb'
}

buildTypes {
    debug {
        minifyEnabled true
        useProguard false
        proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-unity.txt'
        jniDebuggable true
    }
    release {
        minifyEnabled true
        useProguard false
        proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-unity.txt'
        signingConfig signingConfigs.debug
    }
}
packagingOptions {
    doNotStrip '*/x86/*.so'
}
}

}
//ID: 646361
    &lt;include
        layout="@layout/app_bar_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent" /&gt;

    &lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_main"
        app:menu="@menu/activity_main_drawer" /&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 646361
    &lt;?xml version="1.0" encoding="utf-8"?&gt;
    &lt;android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior"
        tools:context="by.ittech.test_chat.MainActivity"
        tools:showIn="@layout/app_bar_main"&gt;

        &lt;FrameLayout
            android:id="@+id/frame"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginBottom="8dp"
            app:layout_constraintBottom_toBottomOf="parent"
            tools:layout_editor_absoluteX="8dp"&gt;

        &lt;/FrameLayout&gt;

    &lt;/android.support.constraint.ConstraintLayout&gt;

}
//ID: 646361
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="by.ittech.test_chat.MainActivity"&gt;

    &lt;android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="@style/AppTheme.AppBarOverlay"&gt;

        &lt;android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:popupTheme="@style/AppTheme.PopupOverlay" /&gt;

    &lt;/android.support.design.widget.AppBarLayout&gt;

    &lt;include
        layout="@layout/content_main" /&gt;

&lt;/android.support.design.widget.CoordinatorLayout&gt;

}
//ID: 978009
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start"&gt;


    &lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:menu="@menu/navigate_menu.xml"
        /&gt;
&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 1131825
&lt;uses-permission android:name="android.permission.READ_SMS" /&gt;
&lt;uses-permission android:name="android.permission.WRITE_SMS" /&gt;

}
//ID: 762680
&lt;string-array name="opisanString"&gt;
    &lt;item&gt;текст &lt;b&gt;жирный шрифт&lt;/b&gt;&lt;/item&gt;
    &lt;item&gt;текст&lt;/item&gt;
&lt;/string-array&gt;

}
//ID: 762724
apply plugin: 'com.android.application'

android {
    compileSdkVersion 26
    defaultConfig {
        applicationId "com.example.zverek.myapplication"
        minSdkVersion 17
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}
allprojects {
    repositories {
        jcenter()
        mavenCentral()
        maven { url 'https://github.com/yandexmobile/yandexmapkit-android/raw/maven/' }
    }
}
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:26.1.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    implementation 'com.android.support:design:26.1.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
    compile 'ru.yandex:yandexmapkit:2.5.4@aar'
    compile 'de.hdodenhof:circleimageview:1.3.0'
}

}
//ID: 476076
Fragment fragment = SomeFragment()
Bundle bundle = Bundle()
bundle.putString(key, value)
fragment.arguments = bundle

}
//ID: 647426
&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
android:id="@+id/drawer_layout"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:fitsSystemWindows="true"
tools:openDrawer="start"&gt;

&lt;include
    layout="@layout/app_bar_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent" /&gt;

&lt;TextView
    android:id="@+id/test"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" /&gt;

&lt;android.support.design.widget.NavigationView
    android:id="@+id/nav_view"
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:layout_gravity="start"
    android:fitsSystemWindows="true"
    app:headerLayout="@layout/nav_header_main"
    app:menu="@menu/activity_main_drawer" /&gt;

}
//ID: 594025
&lt;LinearLayout 

    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    &gt;

    &lt;android.support.design.widget.AppBarLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    &gt;

    &lt;android.support.v7.widget.Toolbar
    android:id="@+id/toolbar"
    android:layout_width="match_parent"
    android:layout_height="?attr/actionBarSize"
    android:background="?attr/colorPrimary"
    /&gt;
    &lt;android.support.design.widget.TabLayout
    android:id="@+id/tabs"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:tabGravity="fill"
    app:tabMode="scrollable" /&gt;

    &lt;/android.support.design.widget.AppBarLayout&gt;

    &lt;android.support.v4.view.ViewPager
    android:id="@+id/viewpager"
    android:layout_width="match_parent"
    android:layout_height="match_parent" /&gt;

&lt;/LinearLayout&gt;

}
//ID: 592678
Caused by: java.lang.NoSuchMethodError: No static method encodeHexString([B)Ljava/lang/String; in class Lord/apache/commons/codec/binary/Hex; or its super classes (declaration of 'org.apache.commons.codec.binary.Hex' appears in /system/framework/ext.jar)
at org.apache.commons.codec.digest.DigestUtils.sha1Hex(DigestUtils.java:438)

}
//ID: 535371
&lt;android.support.design.widget.AppBarLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    &gt;

    &lt;android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_scrollFlags = "scroll|enterAlways"
        /&gt;
    &lt;android.support.design.widget.TabLayout
        android:id="@+id/tabs"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:tabMode="fixed"
        app:tabGravity="center"
        /&gt;
&lt;/android.support.design.widget.AppBarLayout&gt;
&lt;android.support.v4.view.ViewPager
    android:id="@+id/viewPager"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    /&gt;

}
//ID: 648017
&lt;include layout="@layout/toolbar" /&gt;

&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start"&gt;

    &lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"&gt;
        &lt;!-- MAIN Activity --&gt;
        &lt;include layout="@layout/main_activity_body" /&gt;
    &lt;/FrameLayout&gt;

    &lt;include layout="@layout/menu" /&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 648017
&lt;include layout="@layout/toolbar" /&gt;

&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start"&gt;

    &lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"&gt;
        &lt;!-- SECOND Activity --&gt;
        &lt;include layout="@layout/second_activity_body" /&gt;
    &lt;/FrameLayout&gt;

    &lt;include layout="@layout/menu" /&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 1085792
class MainActivity : AppCompatActivity() {

val SELECT_PHOTO = 1234

lateinit var prefernces: SharedPreferences

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    prefernces = getSharedPreferences("APP", Context.MODE_PRIVATE)

    val uriString = prefernces.getString("image_uri", "")

    if (uriString != null) {
        iv_test.setImageBitmap(bitmapFromUri(Uri.parse(uriString)))
    }

    btn_pick.setOnClickListener {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.setType("image/*")
        startActivityForResult(intent, SELECT_PHOTO)
    }
}

override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == SELECT_PHOTO &amp;&amp; resultCode == Activity.RESULT_OK) {
        iv_test.setImageURI(data?.data)
        prefernces.edit().putString("image_uri", data?.data.toString()).apply()
    }


}

private fun bitmapFromUri(uri: Uri): Bitmap? =
    contentResolver.openFileDescriptor(uri, "r")?.fileDescriptor?.let {
        BitmapFactory.decodeFileDescriptor(it)
    }
}

}
//ID: 812553
apply plugin: 'com.android.application'

android {
compileSdkVersion 27
defaultConfig {
    applicationId "com.personal.anton.notes"
    minSdkVersion 17
    targetSdkVersion 27
    versionCode 1
    versionName "1.0"
    testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
}
buildTypes {
    release {
        minifyEnabled false
        proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
    }
}
}

dependencies {
implementation fileTree(include: ['*.jar'], dir: 'libs')
implementation 'com.android.support:appcompat-v7:27.1.1'
implementation 'com.android.support:design:27.1.1'
implementation 'com.android.support.constraint:constraint-layout:1.0.2'
testImplementation 'junit:junit:4.12'
androidTestImplementation 'com.android.support.test:runner:1.0.1'
androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
implementation 'com.android.support:recyclerview-v7:27.1.1'
implementation 'com.github.bumptech.glide:glide:3.7.0'
implementation 'com.android.volley:volley:1.0.0'
}

}
//ID: 1133884
 @Insert(onConflict = OnConflictStrategy.REPLACE)
  public List&lt;Long&gt; insert(List&lt;TableTest&gt; tag);

}
//ID: 706901
    // Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.3.3'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}
allprojects {
    repositories {
        jcenter()
    }
}
task clean(type: Delete) {
    delete rootProject.buildDir
}

android {
    compileSdkVersion 26
    buildToolsVersion '26.0.1'
    dexOptions {
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_6
        targetCompatibility JavaVersion.VERSION_1_6
    }
}
dependencies {
}

}
//ID: 479055
&lt;fragment xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/map"
    android:name="com.google.android.gms.maps.SupportMapFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="napc.gmaps.MapsActivity" /&gt;

}
//ID: 535235
    &lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/drawerLayout"
    android:fitsSystemWindows="true"
    android:background="@color/colorWhitePrimary"
    &gt;


    &lt;android.support.design.widget.CoordinatorLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"&gt;

        &lt;android.support.design.widget.AppBarLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"&gt;


            &lt;android.support.v7.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                android:background="?attr/colorPrimary"
                android:theme="@style/ThemeOverlay.AppCompat.Dark"



                /&gt;

            &lt;android.support.design.widget.TabLayout
                android:id="@+id/tabLayout"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"

                app:tabIndicatorColor="@android:color/white"
                app:tabIndicatorHeight = "6dp"

                app:tabSelectedTextColor="@android:color/white"
                app:tabTextColor="@android:color/white"
                /&gt;

        &lt;/android.support.design.widget.AppBarLayout&gt;


        &lt;android.support.v4.view.ViewPager
            android:id="@+id/viewPager"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:layout_behavior="@string/appbar_scrolling_view_behavior"


            /&gt;

    &lt;/android.support.design.widget.CoordinatorLayout&gt;





    &lt;android.support.design.widget.NavigationView
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:id="@+id/navigationView"
        android:layout_gravity="start"
        app:headerLayout="@layout/navigation_header"
        app:menu="@menu/menu_navigation"

    /&gt;


&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 535242
&lt;include
    layout="@layout/app_bar_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent" /&gt;

&lt;android.support.design.widget.NavigationView
    android:id="@+id/nav_view"
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:layout_gravity="start"
    android:fitsSystemWindows="true"
    app:headerLayout="@layout/nav_header_main"
    app:menu="@menu/activity_main_drawer" /&gt;

}
//ID: 537305
@Override
public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    if (savedInstanceState == null) {
        fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Log.d(LOG_TAG, "Init new fragment");
        fragmentTransaction.replace(R.id.fragment_tab1, SearchFragment.newInstance(), getString(R.string.tag_tab_network));
        fragmentTransaction.commit();
    }
    else {
        Log.d(LOG_TAG, "Saved instance: " + savedInstanceState.toString());
    }
}

}
//ID: 595777
&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"

    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start"&gt;

    &lt;include
        layout="@layout/app_bar_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent" /&gt;

    &lt;android.support.design.widget.NavigationView
        android:layout_marginTop="5dp"
        android:id="@+id/nav_view"
        android:layout_width="250dp"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        android:padding="5dp"
        android:background="@color/colorBacgroundContent"
        app:headerLayout="@layout/nav_header_main"
        app:menu="@menu/activity_main_drawer" /&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 594733
&lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"&gt;

&lt;net.manualuser.calibr.TimeScale
    android:id="@+id/my_scale"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="@android:color/white" /&gt;

&lt;LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:paddingLeft="20dp"
    android:paddingTop="40dp"&gt;

    &lt;TextView
        android:id="@+id/counter"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" /&gt;

&lt;/LinearLayout&gt;

}
//ID: 482333
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
android:orientation="vertical"
android:layout_width="match_parent"
android:layout_height="match_parent"
&gt;

&lt;android.support.design.widget.AppBarLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:theme="@style/AppTheme.AppBarOverlay"
    android:id="@+id/appBarLayout"&gt;

    &lt;android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:background="?attr/colorPrimary"
        app:popupTheme="@style/AppTheme.PopupOverlay" /&gt;

&lt;/android.support.design.widget.AppBarLayout&gt;

&lt;android.support.v4.view.ViewPager xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/pager"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_above="@+id/btnNext"
    android:layout_below="@+id/appBarLayout" /&gt;


&lt;Button
    android:id="@+id/btnNext"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_alignParentBottom="true"
    android:layout_centerHorizontal="true" /&gt;

&lt;/RelativeLayout&gt;

}
//ID: 594533
&lt;GridLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:rowCount="4"
    android:columnCount="4" /&gt;

}
//ID: 481855
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"&gt;

    &lt;android.support.design.widget.TabLayout
        android:id="@+id/tabLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:tabMode="scrollable" /&gt;

    &lt;android.support.v4.view.ViewPager
        android:id="@+id/viewPager"
        android:layout_width="match_parent"
        android:layout_height="0px"
        android:layout_weight="1"
        android:background="@android:color/white" /&gt;

&lt;/LinearLayout&gt;

}
//ID: 537177
apply plugin: 'com.android.application'

android {
    compileSdkVersion 21
    buildToolsVersion "21.1.0"

    defaultConfig {
        applicationId "antonin.juliamusic"
        minSdkVersion 14
        targetSdkVersion 21
        versionCode 1
        versionName "1.0"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    compile 'com.android.support:design:+'
    compile 'com.android.support:support-v4:+'
    compile 'com.sothree.slidinguppanel:library:3.3.0'
    compile 'com.vk:androidsdk:1.6.7'
    compile 'com.android.support:cardview-v7:+'
    compile 'com.android.support:recyclerview-v7:+'
    compile 'com.android.support:appcompat-v7:+'
    compile 'com.android.support:design:+'

    compile 'com.wang.avi:library:1.0.5'
    compile 'com.nineoldandroids:library:2.4.0'

    compile 'org.greenrobot:eventbus:3.0.0'
    compile 'com.squareup:otto:1.3.8'
    compile 'com.orhanobut:dialogplus:1.11@aar'

    compile 'com.loopj.android:android-async-http:1.4.5'
    compile files ('libs/universal-image-loader-1.9.5.jar');

    compile fileTree(dir: 'libs', include: ['*.jar'])
}

}
//ID: 1032104
&lt;string-array name="basic_status_list"&gt;
       .....
    &lt;/string-array&gt;

}
//ID: 712619
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.design.widget.CoordinatorLayout 
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context="com.churkin.myapplication.MainActivity"&gt;

&lt;android.support.design.widget.AppBarLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:theme="@style/AppTheme.AppBarOverlay"&gt;

    &lt;android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:background="?attr/colorPrimary"
        app:popupTheme="@style/AppTheme.PopupOverlay"
        app:titleTextColor="@android:color/white" /&gt;

&lt;/android.support.design.widget.AppBarLayout&gt;

&lt;include layout="@layout/content_main" /&gt;

&lt;/android.support.design.widget.CoordinatorLayout&gt;

}
//ID: 712077
&lt;string-array name="firstList"&gt;
    &lt;item&gt;Один&lt;/item&gt;
    &lt;item&gt;Два&lt;/item&gt;
    &lt;item&gt;Три&lt;/item&gt;
    &lt;item&gt;Четыре&lt;/item&gt;
    &lt;item&gt;Пять&lt;/item&gt;
&lt;/string-array&gt;

&lt;string-array name="secondList"&gt;
    &lt;item&gt;One&lt;/item&gt;
    &lt;item&gt;Two&lt;/item&gt;
    &lt;item&gt;Three&lt;/item&gt;
    &lt;item&gt;Four&lt;/item&gt;
    &lt;item&gt;Five&lt;/item&gt;
&lt;/string-array&gt;

}
//ID: 540707
&lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="150dp"
    android:layout_height="150dp"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:padding="5dp"&gt;

    &lt;ImageView
        android:id="@+id/imageView"
        android:layout_width="150dp"
        android:layout_height="150dp"
        android:scaleType="centerCrop"&gt;

    &lt;/ImageView&gt;

    &lt;android.support.v7.widget.AppCompatCheckBox
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:buttonTint="@color/white"
        /&gt;

&lt;/RelativeLayout&gt;

}
//ID: 1137049
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;FrameLayout
        android:id="@+id/settings"
        android:layout_width="match_parent"
        android:layout_height="match_parent" /&gt;

&lt;/LinearLayout&gt;

}
//ID: 1136555
&lt;androidx.drawerlayout.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:id="@+id/dlMain"
android:fitsSystemWindows="true"
tools:context=".ui.activities.main.MainActivity"&gt;

&lt;androidx.constraintlayout.widget.ConstraintLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/main_background"&gt;

    ...

&lt;/androidx.constraintlayout.widget.ConstraintLayout&gt;

&lt;com.google.android.material.navigation.NavigationView
    android:id="@+id/nvMain"
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:layout_gravity="start"
    app:menu="@menu/main_menu"/&gt;

}
//ID: 1137523
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
    implementation 'com.google.android.material:material:1.1.0'
    implementation 'org.jsoup:jsoup:1.8.3'
    implementation 'com.sun.mail:android-mail:1.5.5'
    implementation 'com.sun.mail:android-activation:1.5.5'
} 

}
//ID: 540583
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:orientation="vertical"
              android:gravity="center"&gt;

    &lt;FrameLayout
        android:id="@+id/frgmCont"
        android:layout_width="match_parent"
        android:layout_height="479dp"&gt;
    &lt;/FrameLayout&gt;...

}
//ID: 818821
&lt;receiver
            android:name="packagename.ExecuteTaskReceiver"
            android:exported="true"
            android:enabled="true"&gt;

            &lt;intent-filter android:priority="1000"&gt;
                &lt;action android:name="my_custom_ection"&gt;&lt;/action&gt;
            &lt;/intent-filter&gt;
        &lt;/receiver&gt; 

}
//ID: 715352
public class Contact {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("remindDate")
    @Expose
    private String remindDate;

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @return The name
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return The email
     */
    public String getRemindDate() {
        return remindDate;
    }
}

}
//ID: 541979
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="ru.alexbykov.fragmentexampler.MainActivity"&gt;



    &lt;android.support.design.widget.CoordinatorLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/container"


       /&gt;


    &lt;android.support.design.widget.NavigationView
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:id="@+id/navigationView"
        android:layout_gravity="start"



        /&gt;


&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 541979
&lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    tools:context="ru.alexbykov.fragmentexampler.Fragment1"&gt;



    &lt;android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"&gt;


      &lt;include layout="@layout/toolbar"/&gt;

        &lt;android.support.design.widget.TabLayout
            android:id="@+id/tabLayout"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"

            app:tabIndicatorColor="@android:color/white"
            app:tabIndicatorHeight = "6dp"

            app:tabSelectedTextColor="@android:color/white"
            app:tabTextColor="@android:color/white"
            /&gt;

    &lt;/android.support.design.widget.AppBarLayout&gt;


    &lt;android.support.v4.view.ViewPager
        android:id="@+id/viewPager"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_behavior="@string/appbar_scrolling_view_behavior"


        /&gt;



&lt;/FrameLayout&gt;

}
//ID: 541979
    &lt;android.support.v7.widget.Toolbar
        xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:background="?attr/colorPrimary"
        android:theme="@style/ThemeOverlay.AppCompat.Dark"


        /&gt;

}
//ID: 1034143
dependencies {
implementation fileTree(dir: 'libs', include: ['*.jar'])
implementation 'androidx.appcompat:appcompat:1.1.0'
implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
implementation 'androidx.preference:preference:1.1.0'
implementation 'androidx.cardview:cardview:1.0.0'
implementation 'androidx.recyclerview:recyclerview:1.0.0'
implementation 'com.google.android.material:material:1.0.0'
implementation 'com.google.android:flexbox:1.1.0'
implementation 'com.github.bumptech.glide:glide:4.9.0'
implementation 'com.readystatesoftware.sqliteasset:sqliteassethelper:2.0.1'
testImplementation 'junit:junit:4.12'
androidTestImplementation 'androidx.test:runner:1.2.0'
androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'

}
//ID: 484265
 &lt;receiver    android:name="ru.diskrim.nebo.AlarmReceiver"
          android:enabled="true"
          android:exported="true"
      &gt;

}
//ID: 541323
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        jcenter()
        maven {
            url "https://jitpack.io"
            mavenCentral()
        }
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.1.2'
        classpath 'com.google.gms:google-services:3.0.0'
       // classpath 'com.android.tools.build:gradle:2.0.0-alpha6'
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        jcenter()
        maven {
            url "https://jitpack.io"
        }
    }
}

}
//ID: 1036386
@Query("SELECT * from word_table WHERE parent_id = 0")
LiveData&lt;List&lt;Word&gt;&gt; getAlphabetizedWords();

}
//ID: 1036886
&lt;string-array name="Themes"&gt;
            &lt;item&gt;@string/Pink_White_rus&lt;/item&gt;
            &lt;item&gt;@string/Pink_Dark_rus&lt;/item&gt;
            &lt;item&gt;@string/Pink_VeryDark_rus&lt;/item&gt;

        &lt;/string-array&gt;

}
//ID: 716537
@Component(modules = {RetrofitModule.class})
@Singleton
public interface AppComponent {

    void inject(MainActivity mainActivity);

}

}
//ID: 1139520
class UserRepository {
private val webservice: Webservice = TODO()
fun getUser(userId: String): LiveData&lt;User&gt; {
    val data = MutableLiveData&lt;User&gt;()
    webservice.getUser(userId).enqueue(object : Callback&lt;User&gt; {
        override fun onResponse(call: Call&lt;User&gt;, response: Response&lt;User&gt;) {
            data.value = response.body()
        }
       override fun onFailure(call: Call&lt;User&gt;, t: Throwable) {
           TODO()
        }
     })
   return data
   } 
}

}
//ID: 1140807
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_view_pager_fragment, R.id.nav_web_view_fragment,
                R.id.nav_recycler_view_fragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}
//ID: 541678
&lt;string-array name="planet"&gt;
    &lt;item&gt;Марс&lt;/item&gt;
    &lt;item&gt;Земля&lt;/item&gt;
    &lt;item&gt;Юпитер&lt;/item&gt;
    &lt;item&gt;Меркурий&lt;/item&gt;
&lt;/string-array&gt;

}
//ID: 543379
....

&lt;android.support.design.widget.NavigationView
    android:id="@+id/nav_view"
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:layout_gravity="start"
    android:fitsSystemWindows="true"
    app:headerLayout="@layout/nav_header_main_second"
    app:menu="@menu/activity_third_drawer" /&gt;

....

}
//ID: 544532
private boolean addPermission(List&lt;String&gt; permissionsList, String permission) {
    if (!isPermissionGranted(permission)) {
        permissionsList.add(permission);
        // Check for Rationale Option
        if (Build.VERSION.SDK_INT &gt;= Build.VERSION_CODES.M) {
            if (!shouldShowRequestPermissionRationale(permission)) {
                return false;
            }
        }
    }
    return true;
}

}
//ID: 544532
private boolean addPermission(List&lt;String&gt; permissionsList, String permission) {
    if (isPermissionGranted(permission)) {
        return true;
    }

    permissionsList.add(permission);
    // Check for Rationale Option
    if (Build.VERSION.SDK_INT &gt;= Build.VERSION_CODES.M) {
        if (!shouldShowRequestPermissionRationale(permission)) {
            return false;
        }
    }

    return true;
}

}
//ID: 716272
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/content"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;android.support.design.widget.AppBarLayout
        android:id="@+id/appbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        &gt;

        &lt;android.support.v7.widget.Toolbar

            android:id="@+id/bar"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            &gt;&lt;/android.support.v7.widget.Toolbar&gt;

    &lt;/android.support.design.widget.AppBarLayout&gt;

&lt;/RelativeLayout&gt;

        &lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:context="com.example.kombo.eplog.activity.MainActivity"&gt;

    &lt;android.support.design.widget.AppBarLayout

        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="@style/AppTheme.AppBarOverlay"&gt;

        &lt;android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:popupTheme="@style/AppTheme.PopupOverlay" /&gt;

    &lt;/android.support.design.widget.AppBarLayout&gt;

    &lt;include layout="@layout/content_main" /&gt;





&lt;/android.support.design.widget.CoordinatorLayout&gt;

}
//ID: 1147954
private var list = mutableListOf&lt;Book&gt;()

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false);
    log(list.size.toString())
    return ViewHolder(view);
}

override fun getItemCount(): Int = list.size

override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(list[position])
}

fun set(l: MutableList&lt;Book&gt;) {
    this.list.clear()
    this.list.addAll(l);
    notifyDataSetChanged()
}

}
//ID: 1147954
val ad = listOf&lt;Book&gt;(
        Book(&quot;1&quot;,&quot;11&quot;,&quot;12&quot;),
        Book(&quot;2&quot;, &quot;21&quot;,&quot;22&quot;)
    ) as MutableList&lt;Book&gt;

    recyclerRecommended.apply {
        layoutManager = LinearLayoutManager(context)
        adapter = DataAdapter(ad)
    }

}
//ID: 12271
&lt;receiver android:name=".Receiver"&gt;
    &lt;intent-filter&gt;
        &lt;action android:name="android.intent.action.PACKAGE_ADDED" /&gt;
        &lt;data android:scheme="package" /&gt;
    &lt;/intent-filter&gt;
&lt;/receiver&gt;

}
//ID: 18922
&lt;FrameLayout &gt;
    &lt;Button /&gt;
    &lt;TextView /&gt;
&lt;/FrameLayout&gt;

}
//ID: 46610
&lt;TableRow xmlns:android="http://schemas.android.com/apk/res/android"&gt;
    &lt;TextView android:text="" /&gt;
    &lt;TextView android:text="" /&gt;
&lt;/TableRow&gt;

}
//ID: 68120
&lt;intent-filter . . . &gt;
&lt;data android:mimeType="video/mpeg" android:scheme="http" . . . /&gt; 
&lt;data android:mimeType="audio/mpeg" android:scheme="http" . . . /&gt;
. . .
&lt;/intent-filter&gt;

}
//ID: 207490
    &lt;intent-filter&gt;
            &lt;action android:name="android.intent.action.VIEW"/&gt;
            &lt;category android:name="android.intent.category.DEFAULT"/&gt;
            &lt;data android:mimeType="*/*"/&gt;
&lt;/intent-filter&gt;

image.setImageURI(getIntent().getData());

}
//ID: 249780
&lt;activity android:name=".2GisActivity"&gt;
  &lt;intent-filter&gt;               
    &lt;action android:name="android.intent.action.VIEW" /&gt;
    &lt;category android:name="android.intent.category.DEFAULT"/&gt;
    &lt;data android:scheme="geo"/&gt;            
  &lt;/intent-filter&gt;
&lt;/activity&gt;

}
//ID: 249870
&lt;TableRow xmlns:android="http://schemas.android.com/apk/res/android"&gt;
  &lt;TextView android:id="@+id/col1" android:text="" /&gt;
  &lt;TextView android:id="@+id/col2" android:text="" /&gt;
  &lt;TextView android:id="@+id/col3" android:text="" /&gt;
  &lt;TextView android:id="@+id/col4" android:text="" /&gt;
  &lt;TextView android:id="@+id/col5" android:text="" /&gt;
  &lt;TextView android:id="@+id/col6" android:text="" /&gt;
&lt;/TableRow&gt;

}
//ID: 292398
 &lt;receiver android:name=".SmsReceiver"
              android:permission="android.permission.BROADCAST_SMS"
              android:enabled="true"
              android:exported="true"
            &gt;
        &lt;intent-filter android:priority="999"&gt;
            &lt;action android:name="android.provider.Telephony.SMS_RECEIVED"/&gt;
        &lt;/intent-filter&gt;
  &lt;/receiver&gt;

}
//ID: 321924
db.query(
    "mytable" /* table */,
     new String[] { "name" } /* columns */,
    "id = ?" /* where or selection */,
    new String[] { "john" } /* selectionArgs i.e. value to replace ? */,
    null /* groupBy */,
    null /* having */,
    null /* orderBy */
);

}
//ID: 329361
try {
    Log.d(LOG_TAG, "Bright: " + Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS));
} catch (SettingNotFoundException e) {
    e.printStackTrace();
}

}
//ID: 330547
  &lt;receiver android:name=".SmsReceiver"
              android:enabled="true"
              android:exported="true"
            &gt;
        &lt;!-- 999 is highest system priority, so it's hack 2147483647 --&gt;
        &lt;intent-filter android:priority="2147483647"&gt; 
            &lt;action android:name="android.provider.Telephony.SMS_RECEIVED"/&gt;
        &lt;/intent-filter&gt;
    &lt;/receiver&gt;

}
//ID: 330547
    &lt;receiver
            android:name=".SmsReceiver"
            android:permission="android.permission.BROADCAST_SMS"
            android:enabled="true"
            android:exported="true"
            &gt;
        &lt;intent-filter
            &lt;!-- 999 is highest system priority, so it's hack 2147483647 --&gt;
            android:priority="2147483647"&gt; 
            &lt;action android:name="android.provider.Telephony.SMS_RECEIVED"/&gt;
            &lt;action android:name="android.provider.Telephony.SMS_DELIVER"/&gt;
        &lt;/intent-filter&gt;
    &lt;/receiver&gt;

}
//ID: 363708
&lt;string-array name="images"&gt;
    &lt;item&gt;image1&lt;/item&gt;
    &lt;item&gt;image2&lt;/item&gt;
    &lt;item&gt;image3&lt;/item&gt;
    &lt;item&gt;image4&lt;/item&gt;
    &lt;item&gt;image5&lt;/item&gt;
    &lt;item&gt;image6&lt;/item&gt;
    &lt;item&gt;image7&lt;/item&gt;
&lt;/string-array&gt;

}
//ID: 383618
public class MyActivity ... implements HandlerInterface{

   @Override
   public Handler getHandler(){
      return myHandler;
   }
}

}
//ID: 414329
    |\ - Main.java
    | | - foo
    | |\
    | | | - A.java
    | | | - B.Jjava
    | | | - C.java
    | |
    | | - bar
    | |\
    | | | - D.java

}
//ID: 423720
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:scaleType="centerCrop"

}
//ID: 424891
Intent intent = new Intent(this, MyActivity.class);
intent.putExtra("playPause", false);
startActivity(intent)

}
//ID: 426161
&lt;string-array name="contacts" formatted="false" &gt;
    &lt;item&gt;текст&lt;/item&gt;
&lt;/string-array&gt;

}
//ID: 427841
&lt;ImageView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:scaleType="centerCrop" /&gt;

}
//ID: 428326
    &lt;fragment
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:name="com.packagename.ClassFragment0"
                    tools:layout="@layout/you_fragment_layout0" /&gt;
    &lt;fragment
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:name="com.packagename.ClassFragment1"
                    tools:layout="@layout/you_fragment_layout1" /&gt;
...

}
//ID: 434232
&lt;android.support.v4.widget.DrawerLayout
    android:id="@+id/drawer_layout"
    android:layout_width="match_parrent"
    android:layout_height="match_parent"&gt;

   &lt;!-- другие виджеты --&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 435539
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;resources&gt;
    &lt;string-array name="your_array"&gt;
        &lt;item&gt;Mercury&lt;/item&gt;
        &lt;item&gt;Venus&lt;/item&gt;
        &lt;item&gt;Earth&lt;/item&gt;
        &lt;item&gt;Mars&lt;/item&gt;
    &lt;/string-array&gt;
&lt;/resources&gt;

}
//ID: 447054
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
          android:layout_width="match_parent"
          android:layout_height="match_parent"&gt;

    &lt;ImageView
        android:layout_width="match_parent"
        android:layout_height="match_parent"/&gt;

    &lt;LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"&gt;
        &lt;!-- Остальной контент --&gt;
    &lt;/LinearLayout&gt;
&lt;/RelativeLayout&gt;

}
//ID: 451350
your_name.xml:

&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
   android:layout_width="match_parent"
   android:layout_height="match_parent"
   android:orientation="vertical" &gt;

   &lt;FrameLayout
      android:id="@+id/custom_action_bar_main"
      android:layout_width="match_parent"
      android:layout_height="?android:attr/actionBarSize" /&gt;

   &lt;FrameLayout
      android:id="@+id/custom_action_bar_tab"
      android:layout_width="match_parent"
      android:layout_height="48dp"/&gt;

&lt;/LinearLayout&gt; 

}
//ID: 453155
        &lt;receiver android:name=".MyBroadCast"&gt;   
           &lt;intent-filter android:priority="999999"&gt;
             &lt;action android:name="android.provider.Telephony.SMS_RECEIVED"/&gt;
           &lt;/intent-filter&gt;
       &lt;/receiver&gt;

}
//ID: 453171
public String getAndroidVersion() {
    String release = Build.VERSION.RELEASE;
    int sdkVersion = Build.VERSION.SDK_INT;
    return "Android SDK: " + sdkVersion + " (" + release +")";
}
//ответ "Android SDK: 19 (4.4.4)"

}
//ID: 461433
android {
    compileSdkVersion 23
    buildToolsVersion "23.0.1"
}
dependencies {
compile 'com.android.support:appcompat-v7:23.1.0'
//materialDialogs
compile('com.afollestad.material-dialogs:core:0.8.4.2@aar') {
    transitive = true
}

//materialDialogs
repositories {
    maven { url "https://jitpack.io" }
}

}
//ID: 463812
private class HoldTimer extends CountDownTimer {

    public HoldTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        //...
    }

    @Override
    public void onFinish() {
        // do something
    }
}

}
//ID: 464518
&lt;application
    android:allowBackup="true"
    android:icon="@mipmap/ic_launcher"
    android:label="LikeMobile"
    android:supportsRtl="true"
    android:theme="@style/AppTheme"&gt;
    &lt;activity android:name=".MainActivity"
        android:windowSoftInputMode="adjustPan"
        android:label="СОВЕТЫ"
        android:icon="@drawable/ic_advice"&gt;
        &lt;intent-filter android:label="LikeMobile"&gt;
            &lt;action android:name="android.intent.action.MAIN" /&gt;
            &lt;category android:name="android.intent.category.LAUNCHER" /&gt;
        &lt;/intent-filter&gt;
    &lt;/activity&gt;
    &lt;activity android:name=".Data"/&gt;
    &lt;activity android:name=".Settings"/&gt;
&lt;/application&gt;

}
//ID: 466750
&lt;activity
        android:name="com.example.MainActivity"
        android:windowSoftInputMode="adjustResize"&gt;
        &lt;intent-filter&gt;
            &lt;category android:name="android.intent.category.DEFAULT"/&gt;
        &lt;/intent-filter&gt;
        &lt;/activity&gt;

}
//ID: 470598
googleMap = ((SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map)).getMap();
if (googleMap != null) {}

}
//ID: 473406
&lt;android.support.design.widget.TabLayout
    ...
    style="@style/MyTabWidgetStyle"
/&gt;

}
//ID: 474370
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:1.3.0'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        jcenter()
    }
}

}
//ID: 474370
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:1.3.0'
        classpath 'com.google.gms:google-services:1.5.0-beta2'
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        jcenter()
    }
}

}
//ID: 476518
&lt;receiver android:name=".AppListener"&gt;
    &lt;intent-filter android:priority="100"&gt;
         &lt;action android:name="android.intent.action.PACKAGE_INSTALL"/&gt;
         &lt;action android:name="android.intent.action.PACKAGE_ADDED"/&gt;  
         &lt;action android:name="android.intent.action.PACKAGE_REMOVED"/&gt;
         &lt;data android:scheme="package"/&gt; 
    &lt;/intent-filter&gt;
&lt;/receiver&gt;

}
//ID: 479934
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;layout xmlns:android="http://schemas.android.com/apk/res/android"&gt;
   &lt;data&gt;
       &lt;variable name="handlers" type="com.example.Handlers"/&gt;
   &lt;/data&gt;
   &lt;LinearLayout
       android:orientation="vertical"
       android:layout_width="match_parent"
       android:layout_height="match_parent"&gt;
       &lt;Button android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:text="@{user.lastName}"
           android:onClick="@{handlers.onClickMyButton}"/&gt;
   &lt;/LinearLayout&gt;
&lt;/layout&gt;

}
//ID: 483705
        &lt;intent-filter&gt;
            &lt;action android:name="android.intent.action.MAIN"/&gt;
            &lt;category android:name="android.intent.category.HOME"/&gt;
            &lt;category android:name="android.intent.category.DEFAULT"/&gt;
            &lt;category android:name="android.intent.category.MONKEY"/&gt;
        &lt;/intent-filter&gt;

}
//ID: 487624
&lt;intent-filter&gt;
    &lt;action android:name="android.intent.action.VIEW"/&gt;
    &lt;action android:name="android.intent.action.SENDTO"/&gt;
    &lt;data android:scheme="mailto"/&gt;
    &lt;category android:name="android.intent.category.DEFAULT"/&gt;
    &lt;category android:name="android.intent.category.BROWSABLE"/&gt;
&lt;/intent-filter&gt;
&lt;intent-filter android:label="@string/app_name"&gt;
    &lt;action android:name="android.intent.action.SEND"/&gt;
    &lt;data android:mimeType="*/*"/&gt;
    &lt;category android:name="android.intent.category.DEFAULT"/&gt;
&lt;/intent-filter&gt;
&lt;intent-filter android:label="@string/app_name"&gt;
    &lt;action android:name="android.intent.action.SEND_MULTIPLE"/&gt;
    &lt;data android:mimeType="*/*"/&gt;
    &lt;category android:name="android.intent.category.DEFAULT"/&gt;
&lt;/intent-filter&gt;

}
//ID: 488036
 &lt;FrameLayout 
   xmlns:android="http://schemas.android.com/apk/res/android"
   android:layout_width="match_parent"
   android:layout_height="match_parent"&gt;

    &lt;ImageView
        android:src="@drawable/your_background"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" /&gt;

    &lt;RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"&gt;

    &lt;/RelativeLayout&gt;
&lt;/FrameLayout&gt;

}
//ID: 489273
&lt;!-- стиль для заднего фона --&gt;
&lt;style name="MyActionBar" parent="@android:style/Widget.Holo.Light.ActionBar"&gt;
    &lt;item name="android:background"&gt;@color/actionbar_background&lt;/item&gt;
    &lt;item name="android:titleTextStyle"&gt;@style/MyTextAppearance&lt;/item&gt;
&lt;/style&gt;
&lt;!-- стиль для текста --&gt;
    &lt;style name="MyTextAppearance" parent="@android:style/TextAppearance"&gt;
    &lt;item name="android:textColor"&gt;@color/actionbar_text_color&lt;/item&gt;
&lt;/style&gt;
&lt;style name="AppTheme" parent="@android:style/Theme.Light"&gt;
    &lt;item name="android:actionBarStyle"&gt;@style/MyActionBar&lt;/item&gt;
&lt;/style&gt;

}
//ID: 489394
&lt;FrameLayout
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context=".MainActivity"&gt;

&lt;android.support.v4.view.ViewPager
    android:padding="5dp"
    android:id="@+id/pager"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;
&lt;/android.support.v4.view.ViewPager&gt;
&lt;/FrameLayout&gt;

}
//ID: 490900
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:context="ua.complife.cl_office.MainActivity"&gt;
    &lt;android.support.v4.widget.DrawerLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/drawer_layout"
        tools:openDrawer="start"&gt;
        &lt;LinearLayout   
             android:layout_width="match_parent"
             android:layout_height="match_parent"/&gt;
        &lt;android.support.design.widget.NavigationView
            android:id="@+id/nav_view"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_gravity="start"
            android:fitsSystemWindows="true"
            app:headerLayout="@layout/header_menu_drawer"
            app:menu="@menu/menu_drawer_items" /&gt;
    &lt;/android.support.v4.widget.DrawerLayout&gt;
&lt;/android.support.design.widget.CoordinatorLayout&gt;

}
//ID: 491319
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;LinearLayout
xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="fill_parent"
android:layout_height="fill_parent"
android:orientation="vertical"&gt;
&lt;android.support.design.widget.AppBarLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:theme="@style/AppTheme.AppBarOverlay"&gt;
                &lt;android.support.v7.widget.Toolbar
                    android:id="@+id/toolbar"
                    android:layout_width="match_parent"
                    android:layout_height="?attr/actionBarSize"
                    android:background="?attr/colorPrimary"
                    app:popupTheme="@style/AppTheme.PopupOverlay"&gt;
                &lt;/android.support.v7.widget.Toolbar&gt;
            &lt;/android.support.design.widget.AppBarLayout&gt;
&lt;TabHost
    android:id="@android:id/tabhost"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;
    &lt;LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"&gt;
        &lt;TabWidget
            android:id="@android:id/tabs"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"&gt;
        &lt;/TabWidget&gt;
        &lt;FrameLayout
            android:id="@android:id/tabcontent"
            android:layout_width="match_parent"
            android:layout_height="match_parent"&gt;
        &lt;/FrameLayout&gt;
    &lt;/LinearLayout&gt;
&lt;/TabHost&gt;
&lt;/LinearLayout&gt;

}
//ID: 497379
View yourView = findViewById(R.id.your_view);

if (Build.VERSION.SDK_INT &gt;= Build.VERSION_CODES.M) {
    if (yourView != null) {
        yourView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}

}
//ID: 497797
&lt;LinearLayout
        android:orientation="vertical"
        ...&gt;
        &lt;android.support.v4.view.ViewPager
            ...
        /&gt;
        &lt;android.support.design.widget.TabLayout
           ...
        /&gt;
 &lt;/LinearLayout&gt;

}
//ID: 500291
if (Build.VERSION.SDK_INT &gt;= Build.VERSION_CODES.M) {
    ...getResources().getColor(color, theme);
}else {
    ...getResources().getColor(color);
}

}
//ID: 500824
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    compile 'com.github.cdflynn:crossview:v1.0.1'
}

}
//ID: 503442
&lt;android.support.v4.view.ViewPager
 android:id="@+id/pager"
 android:layout_width="match_parent"
 android:layout_height="match_parent"&gt;
&lt;/android.support.v4.view.ViewPager&gt;

}
//ID: 507411
public class DetailsAdapter extends RecyclerView.Adapter&lt;RecyclerView.ViewHolder&gt;{
public static final int TYPE_HEADER = 0;
public static final int TYPE_ITEM = 1; ..........

}
//ID: 512249
public static final int TYPE_ITEM = 0;
public static final int TYPE_FOOTER = 1;

public boolean isFooter(int position) {
        return mIsFooterEnabled &amp;&amp; getItemCount() == position + (mIsFooterEnabled ? 1 : 0);
    }

}
//ID: 512881
android {
    defaultConfig {
        vectorDrawables.useSupportLibrary = true
    }
}

}
//ID: 517199
 &lt;FrameLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_weight="0.25"&gt;

        &lt;RelativeLayout
               .....&gt;
        &lt;Button
            android:id="@+id/button_auth"
            android:layout_width="match_parent"
            android:layout_height="match_parent" /&gt;
        &lt;/RelativeLayout&gt;
        &lt;RelativeLayout
               .....&gt;
        &lt;FrameLayout
            android:layout_width="36dp"
            android:layout_height="36dp"
            android:layout_gravity="center"
            android:background="@drawable/ic_refresh_36dp_1" /&gt;
         &lt;/RelativeLayout&gt;
    &lt;/FrameLayout&gt;

}
//ID: 521470
dependencies {

    ...

    //materialDialogs
    compile('com.afollestad.material-dialogs:core:0.8.5.4@aar') {
        transitive = true
    }
    //materialDialogs
    repositories {
        maven { url "https://jitpack.io" }
    }
}

}
//ID: 521784
&lt;android.support.design.widget.NavigationView
    ...
    app:itemIconTint="@android:color/black" 
    ... /&gt;

}
//ID: 522797
allprojects {
    repositories {
    }
}


compile fileTree(dir: 'libs', include: '*.jar')

}
//ID: 526235
   &lt;FrameLayout
                android:id="@+id/frame_container"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
        &lt;/RelativeLayout&gt;

}
//ID: 526655
&lt;application
    android:allowBackup="true"
    android:icon="@mipmap/ic_launcher"
    android:label="@string/app_name"
    android:supportsRtl="true"
    android:theme="@style/AppTheme"&gt;
    &lt;activity android:name=".MainActivity"&gt;
        &lt;intent-filter&gt;
            &lt;action android:name="android.intent.action.MAIN" /&gt;
            &lt;category android:name="android.intent.category.LAUNCHER" /&gt;
        &lt;/intent-filter&gt;
    &lt;/activity&gt;

&lt;activity
    android:name=".SecondActivity"
    android:label="SA"
       &lt;intent-filter&gt;
         &lt;action android:name="android.intent.action.VIEW"/&gt;
         &lt;category android:name="android.intent.category.DEFAULT"/&gt;
   &lt;/intent-filter&gt;--&gt;

}
//ID: 533771
&lt;TableLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;
    &lt;TableRow&gt;
        &lt;TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="example"/&gt;
    &lt;/TableRow&gt;

    &lt;TableRow&gt;
        &lt;Button
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="button"/&gt;
    &lt;/TableRow&gt;

    &lt;TableRow&gt; 
        &lt;Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="button1"/&gt;
    &lt;/TableRow&gt;

    &lt;TableRow&gt;
        &lt;TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="example1"/&gt;
        &lt;TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="example2"/&gt;
    &lt;/TableRow&gt;
    &lt;TableRow&gt;
        &lt;TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="example3"/&gt;
        &lt;TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="example4"/&gt;
    &lt;/TableRow&gt;
    &lt;/TableLayout&gt;

}
//ID: 535633
&lt;intent-filter&gt;
    &lt;action android:name="com.google.android.gms.actions.SEARCH_ACTION"/&gt;
    &lt;category android:name="android.intent.category.DEFAULT"/&gt;
    &lt;category android:name="android.intent.category.VOICE"/&gt;
&lt;/intent-filter&gt;

}
//ID: 537164
buildscript {
repositories {
    mavenCentral()
    maven { url 'https://maven.fabric.io/public' }
    maven { url 'URL РЕПОЗИТОРИЯ ПРИЛОЖЕНИЯ'}
}
...

}
//ID: 541540
android {
  defaultConfig {
    vectorDrawables.useSupportLibrary = true
  }
}

}
//ID: 541690
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;resources&gt;
    &lt;string-array name="planet"&gt;
        &lt;item&gt;Марс&lt;/item&gt;
        &lt;item&gt;Земля&lt;/item&gt;
        &lt;item&gt;Юпитер&lt;/item&gt;
        &lt;item&gt;Меркурий&lt;/item&gt;
    &lt;/string-array&gt;
&lt;/resources&gt;

}
//ID: 545170
repositories {
    maven { url "https://jitpack.io" }
}

}
//ID: 548438
class BaseActivity extends Activity
{
    ...

    @Override
    public void onBackPressed() {
        ...
    }
}

}
//ID: 548438
class FirstActivity extends BaseActivity
{
    ...
}

class SecondActivity extends BaseActivity
{
    ...
}

}
//ID: 549431
productFlavors {
    lite {
        packageName = 'com.project.test.app'
        versionCode 1
        versionName '1.0.0'
    }
    pro {
        packageName = 'com.project.testpro.app'
        versionCode 1
        versionName '1.0.0'
    }
}

}
//ID: 550245
 if( Build.VERSION.SDK_INT &gt;= Build.VERSION_CODES.HONEYCOMB ) {
        new third(getActivity(), view ).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    } else {
        new third(getActivity(), view ).execute();
    }

}
//ID: 553815
&lt;android.support.v4.widget.DrawerLayout

    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
        tools:openDrawer="start"&gt;

        &lt;include
            layout="@layout/app_bar_main"
            android:layout_width="match_parent"
            android:layout_height="match_parent"/&gt;

        &lt;android.support.design.widget.NavigationView
            android:id="@+id/nav_view"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_gravity="start"
            android:fitsSystemWindows="true"
            app:headerLayout="@layout/nav_header_main"
            app:menu="@menu/activity_main_drawer"/&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

&lt;FrameLayout
    android:id="@+id/linGroupButtonAdd"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:visibility="gone"
    android:background="@color/colorBackgroud"&gt;
    &lt;LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|right"
        android:orientation="vertical"&gt;
        &lt;LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="right"
            android:orientation="horizontal"&gt;
            &lt;TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center_vertical"
                android:text="@string/box"/&gt;
            &lt;android.support.design.widget.FloatingActionButton
                android:id="@+id/fabAddBox"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                app:backgroundTint="@color/colorPrimaryDark"
                android:layout_margin="@dimen/fab_margin" /&gt;
        &lt;/LinearLayout&gt;
        &lt;LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="right"
            android:orientation="horizontal"&gt;
            &lt;TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center_vertical"
                android:text="@string/thing"/&gt;
            &lt;android.support.design.widget.FloatingActionButton
                android:id="@+id/fabAddThing"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_margin="@dimen/fab_margin" /&gt;
        &lt;/LinearLayout&gt;
    &lt;/LinearLayout&gt;
&lt;/FrameLayout&gt;

}
//ID: 554704
&lt;android.support.v4.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/nav_drawer"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"&gt;

    &lt;!-- This LinearLayout represents the contents of the screen  --&gt;
    &lt;LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"&gt;

        &lt;!-- The ActionBar displayed at the top --&gt;
        &lt;include layout="@layout/toolbar"/&gt;

        &lt;!-- The main content view where fragments are loaded --&gt;
        &lt;FrameLayout
            android:id="@+id/flContent"
            android:layout_width="match_parent"
            android:layout_height="match_parent" /&gt;

    &lt;/LinearLayout&gt;

    &lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/drawer_header"
        app:menu="@menu/drawer"/&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 561989
&lt;string-array name="texts"&gt;
  &lt;item&gt;Биография&lt;/item&gt;
  &lt;item&gt;Русский Язык&lt;/item&gt;
  &lt;item&gt;......&lt;/item&gt;
  ...
&lt;/string-array&gt;

}
//ID: 563732
&lt;android.support.v4.view.ViewPager
   android:id="@+id/viewPager"
   android:layout_width="match_parent"
   android:layout_height="match_parent"
   app:layout_behavior="@string/appbar_scrolling_view_behavior"/&gt;

}
//ID: 568512
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}

}
//ID: 568626
&lt;android.support.v4.view.ViewPager 
   ...
   /&gt;

}
//ID: 571245
   &lt;string-array name="opisanString"&gt;
    &lt;item&gt;@string/stringa1&lt;/item&gt;
    &lt;item&gt;@string/stringa2 &lt;/item&gt;
   &lt;/string-array&gt;

}
//ID: 576736
&lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context="com.бла-бла.бла.КлассФрейма"&gt;

&lt;TextView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:text="@string/hello_blank_fragment" /&gt;

}
//ID: 578330
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="150dp"
android:layout_height="150dp"
android:backgroynd="#FAFAFA"
android:elevation="5dp"
android:gravity="center"&gt;

&lt;TextView
    android:text="Hello, AlertDialog!"
    android:id="@+id/ad_tv"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" /&gt;

&lt;/LinearLayout&gt;

}
//ID: 583335
fun &lt;T&gt; foo(t: T) {
    // isMarkedNullable == false for t's type, but t can be null here 
}

}
//ID: 583800
&lt;android.support.v4.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start"&gt;

}
//ID: 583800
&lt;android.support.design.widget.NavigationView
    android:id="@+id/nav_view"
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:layout_gravity="start"
    android:fitsSystemWindows="true"
    app:headerLayout="@layout/nav_header_main"
    app:menu="@menu/activity_main_drawer"/&gt;

}
//ID: 585000
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:text="123"/&gt;

&lt;/FrameLayout&gt;

}
//ID: 585322
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.DrawerLayout android:id="@+id/drawer_layout"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start"&gt;

    &lt;RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"&gt;

        &lt;include
            layout="@layout/app_bar_main"
            android:layout_width="match_parent"
            android:layout_height="match_parent"/&gt;

        &lt;android.support.design.widget.BottomNavigationView
            android:id="@+id/bottom_navigation"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentBottom="true"
            app:itemBackground="@color/navigation"
            app:itemIconTint="@color/bottom_navigation_item_background_colors"
            app:itemTextColor="@color/bottom_navigation_item_background_colors"
            app:menu="@menu/bottomnavigationview" /&gt;

    &lt;/RelativeLayout&gt;

    &lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_main"
        app:menu="@menu/activity_main_drawer"/&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 714485
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    ext.kotlin_version = '1.1.4-3'
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.3.3'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 714943
interface MyInterface {
  val prop: Int // abstract

  val propertyWithImplementation: String
    get() = "foo"
}
class Child : MyInterface {
  override val prop: Int = 29
}

}
//ID: 714943
val child = Child()
val propValue = child.prop

}
//ID: 716900
class MainActivity : AppCompatActivity() {
lateinit var click:Button
lateinit var data:TextView


override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    click = findViewById(R.id.button) as Button
    data = findViewById(R.id.fetchedata) as TextView

    click.setOnClickListener(object:View.OnClickListener{
        override fun onClick(view:View) {
            fetchData(data).execute()
        }
    })
  }
}

}
//ID: 716900
class fetchData(val textView: TextView) : AsyncTask&lt;Void, Void, String&gt;() {
var data: String = ""
private var dataParsed = ""
private var singleParsed = ""
override fun doInBackground(vararg p0: Void?): String? {

    try {
        val url = URL("https://api.myjson.com/bins/b1kxl")

        val httpURLConnection = url.openConnection() as HttpURLConnection
        val inputStream = httpURLConnection.inputStream
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))

        for (line in bufferedReader.readLine()){
            data+=line;
        }

        val JA = JSONArray(data)
        for (i in 0 until JA.length()) {
            val JO = JA.get(i) as JSONObject
            singleParsed = "Name:" + JO.get("name") + "\n" +
                    "Password:" + JO.get("password") + "\n" +
                    "Contact:" + JO.get("contact") + "\n" +
                    "Country:" + JO.get("country") + "\n"

            dataParsed += singleParsed
        }
    } catch (e: MalformedURLException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: JSONException) {
        e.printStackTrace()
    }
    return dataParsed
}


override fun onPostExecute(aVoid: String) {
    super.onPostExecute(aVoid)
    textView.text = aVoid
    }
} 

}
//ID: 720557
        val dialog = object : Dialog("window") {
            init {
                ...
            }
        }
        dialog.show()
        dialog.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                if (...) {
                    dialog.hide()
                }
                return true
            }
        })

}
//ID: 721134
package com.example.sfp.json

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.github.kittinunf.fuel.httpGet

internal var GLOBAL_VAR:String? = ""
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startAsync = ParseTask()
         startAsync.execute(GLOBAL_VAR)

    }

    fun jdun(view: View){
        Toast.makeText(applicationContext,"$GLOBAL_VAR",Toast.LENGTH_LONG).show()
        Log.d("&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;","$GLOBAL_VAR")
    }

   class ParseTask : AsyncTask&lt;String, Void, String&gt;() {
        override fun doInBackground(vararg p0: String?): String? {
            "https://api.myjson.com/bins/19u5lp".httpGet().responseObject(User.Deserializer()) { req, res, result -&gt;
                val (user, err) = result
                GLOBAL_VAR = user!!.firstName

                println(user!!.firstName)
                println(user!!.lastName)
            }

            return GLOBAL_VAR
        }
    }
}

}
//ID: 721134
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.sfp.json.MainActivity"&gt;
&lt;FrameLayout
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    tools:layout_editor_absoluteY="0dp"
    tools:layout_editor_absoluteX="0dp" /&gt;

    &lt;Button
        android:layout_width="368dp"
        android:layout_height="wrap_content"
        android:text="GO"
        android:id="@+id/btn"
        android:onClick="jdun"
        tools:layout_editor_absoluteY="0dp"
        tools:layout_editor_absoluteX="8dp" /&gt;
&lt;/android.support.constraint.ConstraintLayout&gt;

}
//ID: 721134
package com.example.sfp.json

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class User(val firstName: String = "", val lastName: String = "") {
    class Deserializer : ResponseDeserializable&lt;User&gt; {
        override fun deserialize(content: String) = Gson().fromJson(content, User::class.java)!!

    }
}

}
//ID: 721184
data class Distance(var text: String, var value: Int)

data class Duration(var text: String, var value: Int)

data class Element(var distance: Distance, var duration: Duration, var status: String)

data class Row(var elements: List&lt;Element&gt;)   

data class Response(var destination_addresses: List&lt;String&gt;, var origin_addresses: List&lt;String&gt;, var rows: List&lt;Row&gt;, var status: String) 

}
//ID: 723196
   &lt;include
        layout="@layout/app_bar_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent" /&gt;

    &lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        app:headerLayout="@layout/menus"
        style="@style/AppTheme"
        android:background="@drawable/mfon"
        /&gt;

    &lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 725131
&lt;activity
    android:name="ru.test.MainActivity"
    &gt;
    &lt;intent-filter android:autoVerify="true"&gt;
        &lt;action android:name="android.intent.action.VIEW"/&gt;

        &lt;category android:name="android.intent.category.DEFAULT"/&gt;
        &lt;category android:name="android.intent.category.BROWSABLE"/&gt;

        &lt;data
            android:host="test.ru"
            android:scheme="myapp"/&gt;
    &lt;/intent-filter&gt;
&lt;/activity&gt;

}
//ID: 728628
&lt;resources&gt;
    &lt;string-array name="planets_array"&gt;
        &lt;item&gt;Mercury&lt;/item&gt;
        &lt;item&gt;Venus&lt;/item&gt;
        &lt;item&gt;Earth&lt;/item&gt;
        &lt;item&gt;Mars&lt;/item&gt;
    &lt;/string-array&gt;
&lt;/resources&gt;

}
//ID: 729995
    buildscript {
        repositories {
            jcenter()
        }
        dependencies {
            classpath 'com.android.tools.build:gradle:2.3.3'
            classpath 'com.google.gms:google-services:3.1.0'
        }
    }

allprojects {
    repositories {
        maven { url "https://maven.google.com" }
        jcenter()
    }
}

}
//ID: 734516
 override fun onBindViewHolder(holder: TeamViewHolder?, position: Int) {
        val test = items[position] 
        holder.itemView.tv_name.text = test.name

  }

}
//ID: 734595
package com.komdosh.testapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import java.net.URL

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    async(CommonPool){
      val result = URL("http://komdosh.github.io").readText()
      texttest.text = result
      Toast.makeText(this@MainActivity, result, Toast.LENGTH_LONG).show()
    }
  }
}

}
//ID: 734595
apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'

android {
    compileSdkVersion 26
    buildToolsVersion '26.0.2'
    defaultConfig {
        applicationId "com.komdosh.testapp"
        minSdkVersion 19
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:26.1.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'

    implementation"org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlin_version"
    compile "org.jetbrains.kotlinx:kotlinx-coroutines-core:0.12"
}
kotlin {
    experimental {
        coroutines "enable"
    }
}

}
//ID: 735072
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

}
//ID: 735109
fun kVmCA(args: Array&lt;String&gt;) {
    processStickyEvents&lt;FirstClass&gt;{ onFirstEvent(it) }
    processStickyEvents&lt;SecondClass&gt;{ onSecondEvent(it) }
    processStickyEvents&lt;AnotherClass&gt;{ onAnotherEvent(it) }
}

inline fun &lt;reified T : Any&gt; processStickyEvents(lambda: (T) -&gt; Unit) {
    EventBus.getDefault().getStickyEvent(T::class.java)?.let { lambda(it)  }
    EventBus.getDefault().removeStickyEvent(T::class.java)
}

//ID: 744183
&lt;string-array name="test"&gt;
        &lt;item&gt;test&lt;/item&gt;
    &lt;/string-array&gt;

}
//ID: 744212
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;ProgressBar
        android:id="@+id/loading_progress_bar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"/&gt;

    &lt;FrameLayout
        android:id="@+id/content_frame_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:visibility="gone"&gt;

    &lt;/FrameLayout&gt;

&lt;/FrameLayout&gt;

}
//ID: 745120
android {  
   defaultConfig {  
     vectorDrawables.useSupportLibrary = true  
   }  
}

}
//ID: 750094
class MyActivity : AppCompatActivity(), OnDialogItemSelected {

    // other methods..

    override fun onItemSelected(category: String) {
        textView.text = category
    }
}

}
//ID: 751477
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginBottom="10dp"&gt;

    &lt;ImageView
        android:id="@+id/image_iv"
        android:layout_width="150dp"
        android:layout_height="150dp"
        android:layout_alignParentStart="true"
        android:layout_alignParentTop="true" /&gt;

    &lt;TextView
        android:id="@+id/title_txv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentTop="true"
        android:layout_toEndOf="@+id/image_iv"
        android:text="TextView" /&gt;

    &lt;TextView
        android:id="@+id/description_txv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/title_txv"
        android:layout_toEndOf="@+id/image_iv"
        android:text="TextView" /&gt;
&lt;/RelativeLayout&gt;

}
//ID: 751477
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"&gt;

    &lt;ImageView
        android:id="@+id/image_iv"
        android:layout_width="150dp"
        android:layout_height="150dp"
        android:layout_alignParentEnd="true"
        android:layout_alignParentTop="true" /&gt;

    &lt;TextView
        android:id="@+id/title_txv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentTop="true"
        android:layout_toStartOf="@+id/image_iv"
        android:text="TextView" /&gt;

    &lt;TextView
        android:id="@+id/description_txv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/title_txv"
        android:layout_toStartOf="@+id/image_iv"
        android:text="TextView" /&gt;

&lt;/RelativeLayout&gt;

}
//ID: 756106

    &lt;resources&gt;
       &lt;string-array name="arrayWithStrings"&gt;
        &lt;item&gt;comedy&lt;/item&gt;
        &lt;item&gt;europa&lt;/item&gt;
        &lt;item&gt;hit&lt;/item&gt;
        &lt;item&gt;maximum&lt;/item&gt;
        &lt;item&gt;newradio&lt;/item&gt;
        &lt;item&gt;nrj&lt;/item&gt;
        &lt;item&gt;radiotnt&lt;/item&gt;
        &lt;item&gt;record&lt;/item&gt;
        &lt;item&gt;russkyirock&lt;/item&gt;
        &lt;item&gt;silverrain&lt;/item&gt;
       &lt;/string-array&gt;
    &lt;/resources&gt;

}
//ID: 757653
&lt;android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"&gt;
    &lt;android.support.v4.widget.DrawerLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/drawer_layout"
        tools:openDrawer="start"&gt;
        &lt;RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:id="@+id/ActivityLayout"&gt;

        &lt;/RelativeLayout&gt;
        &lt;android.support.design.widget.NavigationView
            android:id="@+id/nav_view"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_gravity="start"
            android:fitsSystemWindows="true"
            app:headerLayout="@layout/header_menu_drawer"
            app:menu="@menu/menu_drawer_items" /&gt;
    &lt;/android.support.v4.widget.DrawerLayout&gt;
&lt;/android.support.design.widget.CoordinatorLayout&gt;

}
//ID: 759756
&lt;receiver android:name=".ConsoleReceiver"
    android:enabled="true"&gt;
    &lt;intent-filter android:priority="999"&gt;
        &lt;action android:name="com.myapp.app.intent.TEST" /&gt;
    &lt;/intent-filter&gt;
&lt;/receiver&gt;

}
//ID: 759764
if(savedInstanceState != null) {
    checkBox.setChecked(savedInstanceState.getBooleanExtra("checked"));
}

}
//ID: 761738
&lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_task_list"&gt;

        &lt;include layout="@layout/navigation_drawer_menu"/&gt;

    &lt;/android.support.design.widget.NavigationView&gt;

}
//ID: 762207
...

public static MyActivity getInstance() {
    return ...
}

....

}
//ID: 762802
&lt;string-array name="opisanString"&gt;
&lt;item&gt;текст &amp;lt;b&gt;жирный шрифт&amp;lt;/b&gt;&lt;/item&gt;
&lt;item&gt;текст&lt;/item&gt;
&lt;/string-array&gt;

}
//ID: 764510
String reqString = Build.MANUFACTURER
            + " " + Build.MODEL + " " + Build.VERSION.RELEASE
            + " " + Build.VERSION_CODES.class.getFields()[android.os.Build.VERSION.SDK_INT].getName();

}
//ID: 767576
&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start"&gt;
&lt;android.support.design.widget.CoordinatorLayout
        android:layout_height="match_parent"
        android:layout_width="match_parent"&gt;
&lt;android.support.design.widget.AppBarLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:theme="@style/AppTheme.AppBarOverlay"&gt;

    &lt;android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:layout_weight="1"
        android:background="?attr/colorPrimary"
        app:popupTheme="@style/AppTheme.PopupOverlay" /&gt;
&lt;/android.support.design.widget.AppBarLayout&gt;
&lt;include
        layout="@layout/app_bar_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent" /&gt;
  &lt;/android.support.design.widget.CoordinatorLayout&gt;
   &lt;android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_main"
        app:menu="@menu/activity_main_drawer" /&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 769260
fun loadHouseList(limit: Int, offset: Int, query: String): Single&lt;List&lt;House&gt;&gt; {
    return api.loadHouseList(limit, offset, query)
            .flatMap { list -&gt; RealmProvider.queryListFromRealm {realm -&gt; realm.copyToRealmOrUpdate(list) } }
}

}
//ID: 770043
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"&gt;

    &lt;android.support.design.widget.AppBarLayout
        android:id="@+id/appbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"&gt;

        &lt;android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"&gt;&lt;/android.support.v7.widget.Toolbar&gt;

    &lt;/android.support.design.widget.AppBarLayout&gt;

    &lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/drawer_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:openDrawer="start"&gt;

        &lt;FrameLayout
            android:id="@+id/content_frame"
            android:layout_width="match_parent"
            android:layout_height="match_parent" /&gt;

        &lt;FrameLayout
            android:id="@+id/navigation_frame"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_gravity="start"/&gt;
    &lt;/android.support.v4.widget.DrawerLayout&gt;
&lt;/LinearLayout&gt;

}
//ID: 771211
&lt;FrameLayout
xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"&gt;

&lt;android.support.v4.view.ViewPager
    android:id="@+id/viewPager"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="8dp"&gt;

    &lt;android.support.design.widget.TabLayout
        android:id="@+id/tabLayout"
        style="@style/TabLayoutStyle"
        android:layout_width="match_parent"
        android:layout_height="32dp"/&gt;

&lt;/android.support.v4.view.ViewPager&gt;

}
//ID: 774157
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;com.google.android.gms.maps.MapView
            android:id="@+id/map"
            android:layout_width="match_parent"
            android:layout_height="match_parent" /&gt;

&lt;/LinearLayout&gt;

}
//ID: 774185
        if (U&lt;=-30 &amp;&amp; U&gt;-500)
        {

            if (mProgressBar.getVisibility() == View.VISIBLE) return;
            else
                {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBarLeft.setVisibility(View.INVISIBLE);
                }

        }

        if (U&gt;=30 &amp;&amp; U&lt;500)
        {

            if (mProgressBarLeft.getVisibility() == View.VISIBLE) return;
            else
            {
                mProgressBarLeft.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);
            }

        }

        if (U&lt;30 &amp;&amp; U&gt;-30) {
            mProgressBar.setVisibility(View.INVISIBLE);
            mProgressBarLeft.setVisibility(View.INVISIBLE);
        }

}
//ID: 781307
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.constraint.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="shwarz.senddatabetweenfragments.MainActivity"&gt;

    &lt;FrameLayout
        android:id="@+id/frame_fragment_spot"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/&gt;

    &lt;Button
        android:id="@+id/btn_show_1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="8dp"
        android:layout_marginStart="8dp"
        android:text="First Fragment"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"/&gt;

    &lt;Button
        android:id="@+id/btn_show_2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="8dp"
        android:layout_marginEnd="8dp"
        android:text="Second Fragment"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"/&gt;

&lt;/android.support.constraint.ConstraintLayout&gt;

}
//ID: 783720
val a = arrayOf(1, 2, 3)
val list = asList(-1, 0, *a, 4)

}
//ID: 783781
&lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;VideoView
        android:id="@+id/video_player_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_gravity="center" /&gt;

    &lt;ImageView
        android:id="@+id/video_player_preview"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:adjustViewBounds="true"
        android:scaleType="centerCrop" /&gt;

    &lt;ProgressBar
        android:id="@+id/video_player_progressbar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:visibility="gone" /&gt;
&lt;/FrameLayout&gt;

}
//ID: 784946
android {
    compileSdkVersion 27
    buildToolsVersion "27.0.2"
    defaultConfig {
        resConfigs "en"
        minSdkVersion 19
        targetSdkVersion 27
        ......

}
//ID: 784966
    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean checkPermissions() {
        switch (App.getCurFragContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            case PackageManager.PERMISSION_DENIED:
                fileLoadingListener.requestPermissions();
                return true;
            default:
                return false;
        }
    }

}
//ID: 785689
 &lt;activity
            android:name=".MainActivity"
            android:label="@string/app_name"
            android:theme="@style/AppTheme.MyTheme" &gt;

            &lt;intent-filter&gt;

                &lt;action android:name="android.intent.action.SEND"/&gt;
                &lt;category android:name="android.intent.category.DEFAULT"/&gt;
                &lt;category android:name="android.intent.category.BROWSABLE"/&gt;
                &lt;data android:mimeType="text/plain"/&gt;

            &lt;/intent-filter&gt;

        &lt;/activity&gt;

}
//ID: 785952
defaultConfig {
    vectorDrawables.useSupportLibrary = true
}

}
//ID: 788871
 @ElementsIntoSet
@Provides
@Named(AREA_TAG)
Set&lt;String&gt; getDebugArea() {
    return new HashSet&lt;&gt;(Arrays.asList(mService.getResources().getStringArray(R.array.debugArea)));
}

@ElementsIntoSet
@Provides
Set&lt;String&gt; getDebugType() {
    return new HashSet&lt;&gt;(Arrays.asList(mService.getResources().getStringArray(R.array.debugType)));
}

}
//ID: 793296
&lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/fragment_container"&gt;

&lt;/FrameLayout&gt;

}
//ID: 797083
kapt {
    generateStubs = true
}

}
//ID: 801629
&lt;receiver android:name="SMSReceiver"&gt;
    &lt;intent-filter android:priority="100"&gt;
        &lt;action android:name="android.provider.Telephony.SMS_RECEIVED"/&gt;
    &lt;/intent-filter&gt;
&lt;/receiver&gt;

}
//ID: 806250
class RegistrationActivity : AppCompatActivity() {


private lateinit var calendarsPresenter: SignInPresenter

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_registration);
   calendarsPresenter = SignInPresenter(this);
    calendarsPresenter.onErrorCancel()



    ActionModeCallback().onDestroyActionMode()

}


inner class ActionModeCallback : ActionMode.Callback {
    override fun onDestroyActionMode(p0: ActionMode?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
        return false
    }

    override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.email_sign_in_button -&gt; {
                calendarsPresenter.onErrorCancel()
                mode.finish()
                true
            }

            else -&gt; false
        }
    }

     fun onDestroyActionMode() {
        calendarsPresenter.onErrorCancel()
    }
}}

}
//ID: 809909
data class Device(val device: BluetoothDevice) : Parcelable {

enum class ConnectionState {
    DISCONNECTED,
    SCAN,
    CONNECTED,
    DISCOVERED
}

var connectionState: ConnectionState = ConnectionState.DISCONNECTED


constructor(parcel: Parcel) : this(parcel.readParcelable(BluetoothDevice::class.java.classLoader) as BluetoothDevice)

fun getName(): String {
    return device.name
}

fun getAddress(): String {
    return device.address
}

override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeParcelable(device, flags)
}

override fun describeContents(): Int {
    return 0
}

companion object CREATOR : Parcelable.Creator&lt;Device&gt; {
    override fun createFromParcel(parcel: Parcel): Device {
        return Device(parcel)
    }

    override fun newArray(size: Int): Array&lt;Device?&gt; {
        return arrayOfNulls(size)
    }
}}

}
//ID: 811256
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class Exclude

}
//ID: 815457
class MyActivity : Activity() {

    private lateinit var uri: Uri
    private lateinit var button: Button

    public override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)

        button = findViewById&lt;View&gt;(R.id.my_button) as Button

        button.setOnClickListener {
            startActivity(Intent().putExtra("uri", uri.toString()))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

            uri = // получите uri
    }
}

}
//ID: 815780
buildscript {
   .....
   dependencies {
      .....
      classpath "org.jetbrains.kotlin:kotlin-android-extensions:$kotlin_version"
      .....
   }
}

}
//ID: 820038
&lt;fragment
  android:id="@+id/map"
  android:layout_width="wrap_content"
  android:layout_height="match_parent"
  class="com.google.android.gms.maps.SupportMapFragment" /&gt;

}
//ID: 823027
&lt;android.support.design.widget.TabLayout
    android:id="@android:id/tabs"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" /&gt;

&lt;android.support.v4.view.ViewPager
    android:id="@+id/pager"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" /&gt;

}
//ID: 823199
allprojects {
    repositories {
        jcenter()
        maven { url "https://maven.google.com" }
        // или google() для Android Studio 3.x
    }
}

dependencies {
    compile 'com.google.android.gms:play-services-ads:15.0.0'
}

}
//ID: 823828
&lt;!-- ВЫБОР ШРИФТА --&gt;
&lt;string-array name="entries"&gt;
    &lt;item&gt;1&lt;/item&gt;
    &lt;item&gt;2&lt;/item&gt;
    &lt;item&gt;3&lt;/item&gt;
&lt;/string-array&gt;
&lt;string-array name="entry_values"&gt;
    &lt;item&gt;Мелкий&lt;/item&gt;
    &lt;item&gt;Средний&lt;/item&gt;
    &lt;item&gt;Крупный&lt;/item&gt;
&lt;/string-array&gt;

}
//ID: 824709
    &lt;receiver
        android:name=".BootReceiver"
        android:enabled="true"&gt;

        &lt;intent-filter&gt;
            &lt;action android:name="android.intent.action.BOOT_COMPLETED"/&gt;
            &lt;action android:name="android.intent.action.QUICKBOOT_POWERON"/&gt;
            &lt;action android:name="com.htc.intent.action.QUICKBOOT_POWERON"/&gt;
            &lt;action android:name="android.intent.action.REBOOT"/&gt;

            &lt;category android:name="android.intent.category.DEFAULT"/&gt;
        &lt;/intent-filter&gt;
    &lt;/receiver&gt;

}
//ID: 825919
     editText.addTextChangedListener(object : TextWatcher {

        override fun afterTextChanged(s: Editable) {  }
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) { }
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            // set cursor
            editText.setSelection(start)
        }
    })

}
//ID: 829587
  &lt;string-array name="colors"&gt;
        &lt;item&gt;#dd3b7fec&lt;/item&gt;
        &lt;item&gt;#dded4545&lt;/item&gt;
        &lt;item&gt;#dd6de947&lt;/item&gt;
        &lt;item&gt;#ddf9e640&lt;/item&gt;
    &lt;/string-array&gt;

}
//ID: 831172
public class ResponseData {

   @SerializedName("data")
   @Expose
   public List&lt;Salon&gt; data;

}

}
//ID: 832523
&lt;!-- ВЫБОР ШРИФТА --&gt;
&lt;string-array name="entries"&gt;
    &lt;item&gt;1&lt;/item&gt;
    &lt;item&gt;2&lt;/item&gt;
    &lt;item&gt;3&lt;/item&gt;
&lt;/string-array&gt;
&lt;string-array name="entry_values"&gt;
    &lt;item&gt;Мелкий&lt;/item&gt;
    &lt;item&gt;Средний&lt;/item&gt;
    &lt;item&gt;Крупный&lt;/item&gt;
&lt;/string-array&gt;

}
//ID: 833508
override fun getItemViewType(position: Int): Int {
    val item = mItems.get(position);
    if (item.visible == true) {
       return 1
    }
    return 0
}

}
//ID: 833508
override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseVH? {
    val inflater: LayoutInflater = LayoutInflater.from(mContext)
    if (viewType == 1) {
        return CustomVH(inflater.inflate(R.layout.your_layout_here, parent, false))
    }
    return null
}

}
//ID: 979913
receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                workingWithBtn(intent.extras.getInt("url"))
            }
        }

}
//ID: 982013
val filter = IntentFilter("item_position")
    receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            deleteAttachedItem(intent.extras.getInt("position"))
        }
    }

}
//ID: 982127
@PrimaryKey(autoGenerate = true)
private int id;

}
//ID: 998027
open class AbstractDto @JvmOverloads constructor(
    var id: Long? = null,
    var created: LocalDateTime? = null,
    var updated: LocalDateTime? = null
)

}
//ID: 998027
open class AbstractDto {
    var id: Long? = null
    var created: LocalDateTime? = null
    var updated: LocalDateTime? = null
}

}
//ID: 998027
open class AbstractDto(
    var id: Long?,
    var created: LocalDateTime?,
    var updated: LocalDateTime?)
{
    constructor() : this(null, null, null)
}

}
//ID: 998836
val bundle = Bundle()
bundle.putStringArrayList();("key", array)
fragment.arguments = bundle

}
//ID: 1001746
@Entity
class Category(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    .......

    )

}
//ID: 1002836
class MyAdapter(private var items: List&lt;MyObject&gt;,
                      val listener: (MyObject) -&gt; Unit) : RecyclerView.Adapter&lt;MyAdapter.ViewHolder&gt;() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindData(items[position])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(mObject: MyObject) {
             itemView.setOnClickListener { listener(gist) }
        }

    }
}

}
//ID: 1004179
 override fun onResume() {
        super.onResume()
       Intent k = new Intent(Activity_1.this, Activity_2.class);
startActivity(k);
    }

}
//ID: 1006320
class TestModel(val name: String?, val age: Int) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readString(), parcel.readInt())

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
        dest?.writeInt(age)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator&lt;TestModel&gt; {
        override fun createFromParcel(parcel: Parcel): TestModel {
            return TestModel(parcel)
        }

        override fun newArray(size: Int): Array&lt;TestModel?&gt; {
            return arrayOfNulls(size)
        }
    }

}

}
//ID: 1007788
/**
 * Represents a range of [Comparable] values.
 */
private open class ComparableRange&lt;T : Comparable&lt;T&gt;&gt;(
    override val start: T,
    override val endInclusive: T
) : ClosedRange&lt;T&gt; {

    override fun equals(other: Any?): Boolean {
        return other is ComparableRange&lt;*&gt; &amp;&amp; (isEmpty() &amp;&amp; other.isEmpty() ||
                start == other.start &amp;&amp; endInclusive == other.endInclusive)
    }

    override fun hashCode(): Int {
        return if (isEmpty()) -1 else 31 * start.hashCode() + endInclusive.hashCode()
    }

    override fun toString(): String = "$start..$endInclusive"
}

}
//ID: 1007788
/**
 * A range of values of type `Int`.
 */
public class IntRange(start: Int, endInclusive: Int) : IntProgression(start, endInclusive, 1), ClosedRange&lt;Int&gt; {
    override val start: Int get() = first
    override val endInclusive: Int get() = last

    override fun contains(value: Int): Boolean = first &lt;= value &amp;&amp; value &lt;= last

    override fun isEmpty(): Boolean = first &gt; last

    override fun equals(other: Any?): Boolean =
            other is IntRange &amp;&amp; (isEmpty() &amp;&amp; other.isEmpty() ||
                    first == other.first &amp;&amp; last == other.last)

    override fun hashCode(): Int =
            if (isEmpty()) -1 else (31 * first + last)

    override fun toString(): String = "$first..$last"

    companion object {
        /** An empty range of values of type Int. */
        public val EMPTY: IntRange = IntRange(1, 0)
    }
}

}
//ID: 1008516
class CustomEditText @JvmOverloads constructor(
context: Context,
attrs: AttributeSet? = null,
defStyle: Int = R.style.Widget_AppCompat_EditText_Custom,
defStyleRes: Int = R.style.Widget_AppCompat_EditText_Custom
) : EditText(context, attrs, defStyle, defStyleRes) {

private var onTextChangedListener: OnTextChangedListener? = null
fun setOnTextChangedListener(onTextChangedListener: OnTextChangedListener) {
    this.onTextChangedListener = onTextChangedListener
}

init {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            validation(s)
            onTextChangedListener?.onTextChangedListener(s)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    })
}

private fun validation(s: Editable) {
    //универсальный валидатор
}

interface OnTextChangedListener {
    fun onTextChangedListener(s: Editable?)
}
}


etCustom.setOnTextChangedListener(object : CustomEditText.OnTextChangedListener {
        override fun onTextChangedListener(s: Editable?) {
            //дополнительный валидатор
        }
    })

}
//ID: 1008619
public class TESTCLSS
{
    constructor()  {
        for (i in 1..20)
        {
            print(i.toString() + " ");
        }
    }
}

fun yfEBa() {
    TESTCLSS()
}

//ID: 1008804
fun calculateEvenDigits(input: String): Int {
    var sum = 0
    for(c in input) {
        val digit = c - '0'
        if (digit % 2 == 0) {
            sum += digit
            println("c = $c, sum = $sum")
        }
    }
    return sum
}

}
//ID: 1009585
@Database(entities = arrayOf(User::class), version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

}
//ID: 1009750
allprojects {
   repositories {
       ...
       maven { url 'https://jitpack.io' }
   }
}

}
//ID: 1009909
    &lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;androidx.drawerlayout.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".view.home.HomeActivity"
    android:fitsSystemWindows="true"
    &gt;
    &lt;androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"&gt;
    &lt;/androidx.recyclerview.widget.RecyclerView&gt;
    &lt;com.google.android.material.navigation.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        &gt;
        &lt;ExpandableListView
            android:id="@+id/navigationmenu"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_marginTop="192dp"
            android:background="@android:color/white"
            &gt;
        &lt;/ExpandableListView&gt;
    &lt;/com.google.android.material.navigation.NavigationView&gt;
&lt;/androidx.drawerlayout.widget.DrawerLayout&gt;

}
//ID: 1012593
&lt;string-array name="my_string_array"&gt;
    &lt;item&gt;one&lt;/item&gt;
    &lt;item&gt;two&lt;/item&gt;
    &lt;item&gt;three&lt;/item&gt;
&lt;/string-array&gt;

}
//ID: 1016135
 var timer = object : CountDownTimer(TIMER_WAITING, TIMER_INTERVAL) {
    override fun onTick(millisUntilFinished: Long) {

    }

    override fun onFinish() {
        )
    }
}

}
//ID: 1016215
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;RelativeLayout
    android:id="@+id/main_layout"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity"&gt;

    &lt;com.google.android.material.tabs.TabLayout
        android:id="@+id/tab_layout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:elevation="6dp" /&gt;

    &lt;androidx.viewpager.widget.ViewPager

        android:id="@+id/view_pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent" /&gt;

&lt;/RelativeLayout&gt;

}
//ID: 1016538
var editTextFlag = true
MyEditText.addTextChangedListener(object : TextWatcher
    {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int)
        {
            when
            {
                (editTextFlag &amp;&amp; after == 0) -&gt; {
                    editTextFlag = false
                    MyEditText.setText("")
                }

                (editTextFlag &amp;&amp; HabitIconEditText.text.isNotEmpty()) -&gt; {
                    editTextFlag = false
                    MyEditText.setText(s)
                    MyEditText.setSelection(s.length)
                }

                else -&gt; {
                    editTextFlag = true
                }
            }
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable) {}
    })

}
//ID: 1016943
open class A(protected var a: Int, private var b: Int) {

    constructor(): this(1, 2)  // Вызываем первичный конструктор

    fun fa(): Int {
        return a  // this не обязательно
    }

    fun fb(): Int {
        return b  // this не обязательно
    }
}


class B(a: Int, b: Int, private var c: Int) : A(a, b) {

    constructor(): this(1,2,3)  // Вызываем первичный конструктор

    fun fsum(): Int {
        return c + a // this не обязательно
    }
}

}
//ID: 1024252
val intent = Intent(this@MainActivity, Activity_Information::class.java)
startActivity(intent)

}
//ID: 1025924
val common = AtomicReference&lt;Map&lt;String, Int&gt;&gt;()

fun JLGlJ() {
    common.set(mapOf("foo" to 1))
    common.set(common.get().plus("bar" to 2))
    thread {
        common.set(common.get().plus("baz" to 3))
    }
    sleep(1000)

    println(common.get())
}

//ID: 1027301
&lt;!-- SMS handling permissions --&gt;
&lt;uses-permission android:name="android.permission.RECEIVE_SMS" /&gt;
&lt;uses-permission android:name="android.permission.READ_SMS" /&gt;

}
//ID: 1031067
fun onRequestPermissionsResult(requestCode: Int, permissions: Array&lt;String&gt;, grantResults: IntArray) {
    presenter.checkPermissionsResult(requestCode, permissions, grantResults)
}

}
//ID: 1032121
   class SpinnerAdapter(var ctx: Context, var title: List&lt;String&gt;,var count:List&lt;Int&gt;): BaseAdapter() {
override fun getView(i: Int, p1: View?, p2: ViewGroup?): View {
    val inflater = LayoutInflater.from(ctx)
    val view1 = inflater.inflate(R.layout.spinner_item, null) as View
    val titles = view1.findViewById(R.id.title) as TextView
    val counts = view1.findViewById(R.id.number) as TextView
    titles.text = title[i]
    counts.text = count[i].toString()

    return view1
}

override fun getItem(p0: Int): Any? {
    return null
}

override fun getItemId(p0: Int): Long {
    return 0
}

override fun getCount(): Int {
    return title.size
}

}
//ID: 1040415
&lt;android.support.v4.widget.DrawerLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
     android:id="@+id/drawer_layout"
     android:layout_width="match_parent"
     android:layout_height="match_parent"&gt;
      &lt;!-- The main content view --&gt;
     &lt;FrameLayout
             android:id="@+id/content_frame"
             android:layout_width="match_parent"
              android:layout_height="match_parent" /&gt;
      &lt;!-- The navigation drawer --&gt;
        &lt;ListView android:id="@+id/left_drawer"
             android:layout_width="240dp"
                android:layout_height="match_parent"
                android:layout_gravity="start"
             android:choiceMode="singleChoice"
                android:divider="@android:color/transparent"
                android:dividerHeight="0dp"
                android:background="#111"/&gt;
&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 1042566
buildscript {
    ext.kotlin_version = '1.2.71'
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath 'com.android.tools.build:gradle:3.2.1'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        classpath 'com.google.gms:google-services:4.2.0'
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

rootProject.buildDir = '../build'
subprojects {
    project.buildDir = "${rootProject.buildDir}/${project.name}"
}
subprojects {
    project.evaluationDependsOn(':app')
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

}
//ID: 1046307
// In an Activity, you can retrieve a ViewModel scoped to the Activity
// by using viewModels
val viewModel: MyVewModel by viewModels { myFactory }

}
//ID: 1048063
val array = arrayOf(1, 2, 3)

array.toList().zipWithNext { a, b -&gt; 
    println("$a compare $b")
}

array.asIterable().zipWithNext { a, b -&gt; 
    println("$a compare $b")
}

val zipSum = array.asIterable().zipWithNext { a, b -&gt; 
    a + b
}

}
//ID: 1055718
import kotlin.math.pow

fun sqrt(a:Int):Double {
    return a.toDouble().pow(1/2.toDouble())
}

fun wdNyd() {
    println(sqrt(25))
}

//ID: 1059415
import kotlin.system.measureTimeMillis

fun mineFun(num: Int): Int {
    var numTemp = num
    var res = 0

    while (numTemp != 0) {
        when (numTemp % 10) {
            3 -&gt; res = res + 3
            6 -&gt; res = res + 6
            9 -&gt; res = res + 9
        }
        numTemp = numTemp / 10
    }
    return res
}

fun notMineFun(num: Int): Int {
    var c = num
    var res = 0
    val numArray = arrayListOf&lt;Int&gt;()
    while (c != 0) {
        numArray.add(c % 10)
        c /= 10
    }
    for (i in numArray) {
        if (i % 3 == 0) {
            res += i
        }
    }
    return res
}

fun JJSuy() {
    val limit = 10_000_000
    var x = 0
    val t1 = measureTimeMillis {
        for (n in 1..limit) {
            x = mineFun(n)
        }
    }

    val t2 = measureTimeMillis {
        for (n in 1..limit) {
            x = notMineFun(n)
        }
    }

    println(t1)
    println(t2)
}

//ID: 1060376
override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_one, container, false)
            val list:RecyclerView = rootView.findViewById(R.id.recyclerView)
            return rootView
    }

}
//ID: 1062585
import java.util.* 

val scan = Scanner(System.`in`) 

fun ZBnGW(args: Array&lt;String&gt;) { 
   var sum = 0 
   val n = scan.nextInt() 
   for (i in 1..n) { //запрашиваем n чисел
      sum+=scan.nextInt() 
   }
   print(sum) 
}

//ID: 1066300
data class Contacts(
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("notes")
    val notes: String,
    @SerializedName("images")
    val images: String?
) : Serializable 

}
//ID: 848697
&lt;RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;ImageView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:scaleType="centerCrop"
        app:srcCompat="@drawable/background" /&gt;
&lt;/RelativeLayout&gt;

}
//ID: 851032
 override fun onResume() {
        super.onResume()
        adapter.clearAdapter()
    }

}
//ID: 856570
fun getItem(position: Int): Fragment? {
    return when (position) {
        0 -&gt; Login()
        1 -&gt; Registration()
        else -&gt; null
    }
}

}
//ID: 857802
@Transaction
@Query("SELECT * FROM User WHERE email = :emailId")
abstract fun getAccount(emailId: String): Maybe&lt;Account&gt;

}
//ID: 857802
  override fun getAccount(email: String): Maybe&lt;Account&gt; {
        return MaybeUtils&lt;Account&gt;().getMaybe(database.accountDao().getAccount(email))
    }

}
//ID: 860816
 &lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/layout_container"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

   &lt;fragment class="com.example.mycompany.mycontainer"
        android:id="@+id/fragment_container"
        android:layout_width="match_parent"
        android:layout_height="match_parent" &gt;
    &lt;/fragment&gt;
 &lt;FrameLayout/&gt;

}
//ID: 864407
release {
    minifyEnabled false
    proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.txt'
    signingConfig signingConfigs.config
}

}
//ID: 864451
override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                          savedInstanceState: Bundle?): View? {

}
//ID: 869373
apply plugin: 'kotlin-kapt'
...
implementation "android.arch.persistence.room:runtime:1.1.1"
kapt "android.arch.persistence.room:compiler:1.1.1"

}
//ID: 871130
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    loginView.setOnFocusChangeListener { _, hasFocus -&gt;
        if (hasFocus) {
            topView.visibility = View.GONE
        } else {
            topView.visibility = View.VISIBLE
        }
    }
}

}
//ID: 874742
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    override fun onMapReady(googleMap: GoogleMap) { 
        mMap = googleMap

        mMap.setOnMapClickListener(GoogleMap.OnMapClickListener(
            { p0 -&gt; Log.e("map", p0.toString()) }) ) 

    }
}

}
//ID: 881169
data class Source(
        var id : Int?,

        var name : String?
)

}
//ID: 882367
View yourView = findViewById(R.id.your_view);

if (Build.VERSION.SDK_INT &gt;= Build.VERSION_CODES.M) {
    if (yourView != null) {
        yourView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}

}
//ID: 882820
internal var menuFlag = false
internal var menuFlag2 = false
val fragment = PrinterFragment()
val manager = supportFragmentManager

}
//ID: 884003
data class A(
    var a: String?,
    var b: Boolean?,
    var c: Int?
)

}
//ID: 884828
@Entity (tableName = "article")
open class Article {
    ...

    @Embedded
    @ColumnInfo(name = "source")
    var sources: Sources? = null
}

}
//ID: 886290
fun &lt;T&gt; getRetrofitService(clazz: Class&lt;T&gt;): T {
    return Retrofit.Builder()
                .baseUrl(baseDomain)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(clazz)
}

}
//ID: 890102
&lt;uses-permission android:name="android.permission.RECEIVE_SMS" /&gt;
&lt;uses-permission android:name="android.permission.READ_SMS" /&gt;
&lt;uses-permission android:name="android.permission.SEND_SMS" /&gt;

}
//ID: 891389
class IntAttribute(value : Int) : Attribute&lt;Int&gt;(value) {
    override fun add(otherValue : Number) {
        value += otherValue as Int
    }
}

class DoubleAttribute(value : Double) : Attribute&lt;Double&gt;(value) {
    override fun add(otherValue : Number) {
        value += otherValue as Double
    }
}

}
//ID: 892483
viewModel.codeRequestSuccess.observe(this, object  : Observer&lt;Boolean&gt; {
    override fun onChanged(t: Boolean?) {
        if (t != null &amp;&amp; t == true) {
            view?.findNavController()?.navigate(R.id.action_forgotPasswordFragment_to_resetCodeFragment)
        }
    }
})

}
//ID: 894714
@Suppress("UNCHECKED_CAST")
fun &lt;T : Number&gt; T?.fixNull(): T = this ?: 0 as T

}
//ID: 902534
inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  val title1 = itemView.findViewById(R.id.title) as TextView
  val content1 = itemView.findViewById(R.id.content) as TextView
    init {
         itemView.setOnClickListener {
            if (adapterPosition == RecyclerView.NO_POSITION) {
                return@setOnClickListener
            }
           val template = itemsList[adapterPosition]
        }
}

}
//ID: 904288
 &lt;?xml version="1.0" encoding="utf-8"?&gt;
    &lt;android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        xmlns:app="http://schemas.android.com/apk/res-auto"&gt;

        &lt;ImageView
            android:layout_width="match_parent"
            android:layout_height="match_parent" 
            android:transitionName="imageTag" //указываем тег для анимирования перехода

/&gt;
    &lt;/android.support.constraint.ConstraintLayout&gt;

}
//ID: 905282
 defaultConfig {
        vectorDrawables.useSupportLibrary = true
    }

}
//ID: 919977
if (response.isSuccessful()) {

                    } else {
                        ResponseBody errorBody = response.errorBody();
                        try {
                            if (Objects.requireNonNull(errorBody).string().contains("refresh_token_expired")) {
                                logOut();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

}
//ID: 924270
@Dao
public interface BludoDao {

    @Query("SELECT * FROM bludo")
    public LiveData&lt;List&lt;Bludo&gt;&gt; getAll();
}

}
//ID: 928258
&lt;string-array name="medParams&gt;
     &lt;item&gt;Имя#Доза#Группа#Рецепт#Информация&lt;/item&gt;
     ...
 &lt;/string-array&gt;

}
//ID: 934449
   companion object {
    fun start(context: Context) {
        val intent = Intent(context, Activity2::class.java)
        context.startActivity(intent)
    }
}

}
//ID: 934831
 public class ModelFactory extends ViewModelProvider.NewInstanceFactory {

   private String name;

   public ModelFactory(String name) {
       super();
       this.name = name;
   }

   @NonNull
   @Override
   public &lt;T extends ViewModel&gt; T create(@NonNull Class&lt;T&gt; modelClass) {
       if (modelClass == MyViewModel.class) {
           return (T) new MyViewModel(name);
       }
       return null;
   }
}

}
//ID: 939312
class ExponentialMovingAverage(private val alpha : Double) {
    companion object {
        const val ALPHA_FILTER_INDEX : Double = 0.05
    }

    private var oldValue: Double? = null

    fun average(value: Double): Double {
        if (oldValue == null) {
            oldValue = value
            return value
        }

        val newValue = oldValue!! + alpha * (value - oldValue!!)
        oldValue = newValue
        return newValue
    }
}

}
//ID: 948849
interface AbstractFactory&lt;T&gt; {
    fun create(): T
}

}
//ID: 949862
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.notes.observe(this, Observer&lt;ArrayList&lt;Note&gt;&gt; {
            it?.let(::initAdapter)
        })
    }


    private fun initAdapter(list: ArrayList&lt;Note&gt;) {
        recyclerView.run {
            adapter = RegistrationAdapter(this@RegistrationFragment)
            layoutManager = LinearLayoutManager(context)
            (adapter as RegistrationAdapter).setData(list)
        }
    }

}
//ID: 950464
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gridlayoutManager = GridLayoutManager(activity, 4)

        gridlayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (adapter.getItemViewType(position)) {
                    EnumPHOTOS.PHOTOS.id -&gt; if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
                       2 else 1
                    else -&gt; 4
                }
            }
        }
        recyclerView.layoutManager = gridlayoutManager
    }

}
//ID: 950587
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    gridlayoutManager = GridLayoutManager(activity, 12)

    gridlayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return when (adapter.getItemViewType(position)) {
                EnumPHOTOS.PHOTOS.id -&gt; if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
                   4 else 3
                else -&gt; 12
            }
        }
    }
    recyclerView.layoutManager = gridlayoutManager
}

}
//ID: 951102
 interface View extends MvpView {    
        void showAnimalPack(AnimalPack animalPack);
 }
 ...
        //в onCreate
        findViewById(R.id.show_animal_pack_button).setOnClickListener(v -&gt; {
        presenter.setAnimalPackById(1);
    });

}
//ID: 951862
val intent = Intent(this@MainActivity, Donation::class.java)
startActivity(intent)

}
//ID: 952220
abstract class BaseAdapter&lt;E,
    H : BaseListViewHolder&lt;E&gt;&gt;(val context: Context?, val listener: BaseItemClickListener&lt;E&gt;?)
: RecyclerView.Adapter&lt;H&gt;() {

val items = mutableListOf&lt;E&gt;()

override fun getItemCount(): Int {
    return items.size
}

fun addItem(item: E) {
    items.add(item)
    notifyItemInserted(itemCount - 1)
}

fun addItems(items: List&lt;E&gt;) {
    this.items.addAll(items)
    notifyItemInserted(itemCount - 1)
}

fun updateItems(items: List&lt;E&gt;) {
    if (this.items != items) {
        clearAdapter()
        addItems(items)
    }
}

fun clearAdapter() {
    items.clear()
    notifyDataSetChanged()

   }

        interface BaseItemClickListener&lt;E&gt; {
            fun onItemClick(item: E)
        }
    }

}
//ID: 952220
abstract class BaseListViewHolder&lt;T&gt;(itemView: View, private val listener: BaseItemClickListener&lt;T&gt;?)
: RecyclerView.ViewHolder(itemView), View.OnClickListener {

val context = itemView.context

@SuppressWarnings("unchecked")
override fun onClick(view: View) {
    listener?.onItemClick(view.tag as T)
}

   open fun bindData(item: T) {
        itemView.tag = item
        itemView.setOnClickListener(this)
    }
}

}
//ID: 957523
&lt;android.support.v4.view.ViewPager
android:id="@+id/photos_viewpager"
android:layout_width="match_parent"
android:layout_height="match_parent"&gt;

&lt;android.support.design.widget.TabLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/&gt;

}
//ID: 959401
public fun &lt;T&gt; Sequence&lt;T&gt;.firstOrNull(): T? {
    val iterator = iterator()
    if (!iterator.hasNext())
        return null
    return iterator.next()
}

}
//ID: 959401
open class Center
class CenterChild: Center() {
    val k = 1
}

fun xTXBy(args: Array&lt;String&gt;) {
    val items = arrayListOf&lt;Center&gt;(Center(), CenterChild())
    val position = 10

    val item: Center? = items
        .filterIndexed { index, any -&gt;
            index == position &amp;&amp; any is CenterChild
        }
        .map { it as CenterChild }
        .firstOrNull()
}

//ID: 959401
public fun &lt;T&gt; List&lt;T&gt;.firstOrNull(): T? {
    return if (isEmpty()) null else this[0]
}

}
//ID: 959401
open class Center
class CenterChild: Center() {
    val k = 1
}

fun OhTJG(args: Array&lt;String&gt;) {
    var items = listOf(Center(), CenterChild(), Object())
    val position = 10
    val item: Any?= items.getOrNull(position)
    if (item != null &amp;&amp; item is CenterChild) {
        /* code here */
    }
}

//ID: 959401
public fun &lt;T&gt; List&lt;T&gt;.getOrNull(index: Int): T? {
    return if (index &gt;= 0 &amp;&amp; index &lt;= lastIndex) get(index) else null
}

}
//ID: 959849
private class LoadDataAsyncTask constructor(private val onPreExec: () -&gt; Unit, 
                                            private val onCancel: () -&gt; Unit,
                                            private val onPostExec: (data: List&lt;Task&gt;?) -&gt; Unit):
            AsyncTask&lt;Unit, Unit, List&lt;Task&gt;&gt;() {

        private var threadRepo: TaskRepository
        private val waitTymeout = 1000

        override fun onPreExecute() {
            onPreExec()
        }

        override fun doInBackground(vararg params: Unit): List&lt;Task&gt;? {
            Thread.sleep(waitTymeout);
            return threadRepo.getTasks()
        }

        override fun onCancelled() {
            onCancel()
        }

        override fun onPostExecute(data: List&lt;Task&gt;?) {
            onPostExec(data)
        }
    }
}

}
//ID: 959877
val map: Map&lt;Int, Map&lt;Int, Map&lt;Int, Int&gt;&gt;&gt; = mapOf()
val result = map.get(10)?.get(20)?.get(30) ?: 42
assertEquals(42, result)

}
//ID: 960474
override fun loadLocalItems(): Flowable&lt;MutableList&lt;Item&gt;&gt; =
    itemDao.getAllItems()
            .map { list -&gt;
                list.map {
                    mapper.mapDetailItem(it, itemDao.getAllDetailItemsById(it.idItem))
                }
            }

}
//ID: 960672
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity"&gt;
    &lt;android.support.v4.view.ViewPager
        android:id="@+id/pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"&gt;
    &lt;/android.support.v4.view.ViewPager&gt;
&lt;/RelativeLayout&gt;

}
//ID: 961749
val listener = object: OnPackClickListener {
    override fun onPackClicked(packId: Int) {
        TODO("not implemented")
    }
}

}
//ID: 962647
&lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"&gt;

    &lt;fragment
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:name="com.example.app.AppSettings$PrefsFragment"
        android:id="@+id/pref_fragment" /&gt;
&lt;/FrameLayout&gt;

}
//ID: 606471
&lt;RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;com.your.package.MainGamePanel
        android:id="@+id/main_game_panel"
        xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/&gt;

&lt;/RelativeLayout&gt;

}
//ID: 607320
 &lt;intent-filter&gt;
            &lt;action android:name="test"/&gt;
            &lt;category android:name="android.intent.category.DEFAULT"/&gt;
 &lt;/intent-filter&gt;

}
//ID: 612320
&lt;FrameLayout
    android:id="@+id/fmContent"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/&gt;

}
//ID: 612492
if (BuildConfig.DEBUG) {
     //Код
}

}
//ID: 616482
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/container"
    android:layout_width="match_parent"
    android:layout_height="match_parent" /&gt;

}
//ID: 619731
android {
    ...
    lintOptions {
        abortOnError true
        fatal 'StopShip'
    }
}

}
//ID: 622352
public void MakeItFullscreen() {
    if(Build.VERSION.SDK_INT &gt; 11 &amp;&amp; Build.VERSION.SDK_INT &lt; 19) { // lower api
        View v = this.getWindow().getDecorView();
        v.setSystemUiVisibility(View.GONE);
    } else if(Build.VERSION.SDK_INT &gt;= 19) {          
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }
}

}
//ID: 622507
&lt;string-array name="countryCodes"&gt;
&lt;item&gt;93,AF&lt;/item&gt;
&lt;item&gt;355,AL&lt;/item&gt;
&lt;item&gt;213,DZ&lt;/item&gt;
&lt;item&gt;376,AD&lt;/item&gt;
&lt;item&gt;244,AO&lt;/item&gt;
&lt;item&gt;672,AQ&lt;/item&gt;
&lt;item&gt;54,AR&lt;/item&gt;
&lt;item&gt;374,AM&lt;/item&gt;
&lt;item&gt;297,AW&lt;/item&gt;
&lt;item&gt;61,AU&lt;/item&gt;
&lt;item&gt;43,AT&lt;/item&gt;
&lt;item&gt;994,AZ&lt;/item&gt;
&lt;item&gt;973,BH&lt;/item&gt;
&lt;item&gt;880,BD&lt;/item&gt;
&lt;item&gt;375,BY&lt;/item&gt;
&lt;item&gt;32,BE&lt;/item&gt;
&lt;item&gt;501,BZ&lt;/item&gt;
&lt;item&gt;229,BJ&lt;/item&gt;
&lt;item&gt;975,BT&lt;/item&gt;
&lt;item&gt;591,BO&lt;/item&gt;
&lt;item&gt;387,BA&lt;/item&gt;
&lt;item&gt;267,BW&lt;/item&gt;
&lt;item&gt;55,BR&lt;/item&gt;
&lt;item&gt;673,BN&lt;/item&gt;
&lt;item&gt;359,BG&lt;/item&gt;
&lt;item&gt;226,BF&lt;/item&gt;
&lt;item&gt;95,MM&lt;/item&gt;
&lt;item&gt;257,BI&lt;/item&gt;
&lt;item&gt;855,KH&lt;/item&gt;
&lt;item&gt;237,CM&lt;/item&gt;
&lt;item&gt;1,CA&lt;/item&gt;
&lt;item&gt;238,CV&lt;/item&gt;
&lt;item&gt;236,CF&lt;/item&gt;
&lt;item&gt;235,TD&lt;/item&gt;
&lt;item&gt;56,CL&lt;/item&gt;
&lt;item&gt;86,CN&lt;/item&gt;
&lt;item&gt;61,CX&lt;/item&gt;
&lt;item&gt;61,CC&lt;/item&gt;
&lt;item&gt;57,CO&lt;/item&gt;
&lt;item&gt;269,KM&lt;/item&gt;
&lt;item&gt;242,CG&lt;/item&gt;
&lt;item&gt;243,CD&lt;/item&gt;
&lt;item&gt;682,CK&lt;/item&gt;
&lt;item&gt;506,CR&lt;/item&gt;
&lt;item&gt;385,HR&lt;/item&gt;
&lt;item&gt;53,CU&lt;/item&gt;
&lt;item&gt;357,CY&lt;/item&gt;
&lt;item&gt;420,CZ&lt;/item&gt;
&lt;item&gt;45,DK&lt;/item&gt;
&lt;item&gt;253,DJ&lt;/item&gt;
&lt;item&gt;670,TL&lt;/item&gt;
&lt;item&gt;593,EC&lt;/item&gt;
&lt;item&gt;20,EG&lt;/item&gt;
&lt;item&gt;503,SV&lt;/item&gt;
&lt;item&gt;240,GQ&lt;/item&gt;
&lt;item&gt;291,ER&lt;/item&gt;
&lt;item&gt;372,EE&lt;/item&gt;
&lt;item&gt;251,ET&lt;/item&gt;
&lt;item&gt;500,FK&lt;/item&gt;
&lt;item&gt;298,FO&lt;/item&gt;
&lt;item&gt;679,FJ&lt;/item&gt;
&lt;item&gt;358,FI&lt;/item&gt;
&lt;item&gt;33,FR&lt;/item&gt;
&lt;item&gt;689,PF&lt;/item&gt;
&lt;item&gt;241,GA&lt;/item&gt;
&lt;item&gt;220,GM&lt;/item&gt;
&lt;item&gt;995,GE&lt;/item&gt;
&lt;item&gt;49,DE&lt;/item&gt;
&lt;item&gt;233,GH&lt;/item&gt;
&lt;item&gt;350,GI&lt;/item&gt;
&lt;item&gt;30,GR&lt;/item&gt;
&lt;item&gt;299,GL&lt;/item&gt;
&lt;item&gt;502,GT&lt;/item&gt;
&lt;item&gt;224,GN&lt;/item&gt;
&lt;item&gt;245,GW&lt;/item&gt;
&lt;item&gt;592,GY&lt;/item&gt;
&lt;item&gt;509,HT&lt;/item&gt;
&lt;item&gt;504,HN&lt;/item&gt;
&lt;item&gt;852,HK&lt;/item&gt;
&lt;item&gt;36,HU&lt;/item&gt;
&lt;item&gt;91,IN&lt;/item&gt;
&lt;item&gt;62,ID&lt;/item&gt;
&lt;item&gt;98,IR&lt;/item&gt;
&lt;item&gt;964,IQ&lt;/item&gt;
&lt;item&gt;353,IE&lt;/item&gt;
&lt;item&gt;44,IM&lt;/item&gt;
&lt;item&gt;972,IL&lt;/item&gt;
&lt;item&gt;39,IT&lt;/item&gt;
&lt;item&gt;225,CI&lt;/item&gt;
&lt;item&gt;81,JP&lt;/item&gt;
&lt;item&gt;962,JO&lt;/item&gt;
&lt;item&gt;7,KZ&lt;/item&gt;
&lt;item&gt;254,KE&lt;/item&gt;
&lt;item&gt;686,KI&lt;/item&gt;
&lt;item&gt;965,KW&lt;/item&gt;
&lt;item&gt;996,KG&lt;/item&gt;
&lt;item&gt;856,LA&lt;/item&gt;
&lt;item&gt;371,LV&lt;/item&gt;
&lt;item&gt;961,LB&lt;/item&gt;
&lt;item&gt;266,LS&lt;/item&gt;
&lt;item&gt;231,LR&lt;/item&gt;
&lt;item&gt;218,LY&lt;/item&gt;
&lt;item&gt;423,LI&lt;/item&gt;
&lt;item&gt;370,LT&lt;/item&gt;
&lt;item&gt;352,LU&lt;/item&gt;
&lt;item&gt;853,MO&lt;/item&gt;
&lt;item&gt;389,MK&lt;/item&gt;
&lt;item&gt;261,MG&lt;/item&gt;
&lt;item&gt;265,MW&lt;/item&gt;
&lt;item&gt;60,MY&lt;/item&gt;
&lt;item&gt;960,MV&lt;/item&gt;
&lt;item&gt;223,ML&lt;/item&gt;
&lt;item&gt;356,MT&lt;/item&gt;
&lt;item&gt;692,MH&lt;/item&gt;
&lt;item&gt;222,MR&lt;/item&gt;
&lt;item&gt;230,MU&lt;/item&gt;
&lt;item&gt;262,YT&lt;/item&gt;
&lt;item&gt;52,MX&lt;/item&gt;
&lt;item&gt;691,FM&lt;/item&gt;
&lt;item&gt;373,MD&lt;/item&gt;
&lt;item&gt;377,MC&lt;/item&gt;
&lt;item&gt;976,MN&lt;/item&gt;
&lt;item&gt;382,ME&lt;/item&gt;
&lt;item&gt;212,MA&lt;/item&gt;
&lt;item&gt;258,MZ&lt;/item&gt;
&lt;item&gt;264,NA&lt;/item&gt;
&lt;item&gt;674,NR&lt;/item&gt;
&lt;item&gt;977,NP&lt;/item&gt;
&lt;item&gt;31,NL&lt;/item&gt;
&lt;item&gt;599,AN&lt;/item&gt;
&lt;item&gt;687,NC&lt;/item&gt;
&lt;item&gt;64,NZ&lt;/item&gt;
&lt;item&gt;505,NI&lt;/item&gt;
&lt;item&gt;227,NE&lt;/item&gt;
&lt;item&gt;234,NG&lt;/item&gt;
&lt;item&gt;683,NU&lt;/item&gt;
&lt;item&gt;850,KP&lt;/item&gt;
&lt;item&gt;47,NO&lt;/item&gt;
&lt;item&gt;968,OM&lt;/item&gt;
&lt;item&gt;92,PK&lt;/item&gt;
&lt;item&gt;680,PW&lt;/item&gt;
&lt;item&gt;507,PA&lt;/item&gt;
&lt;item&gt;675,PG&lt;/item&gt;
&lt;item&gt;595,PY&lt;/item&gt;
&lt;item&gt;51,PE&lt;/item&gt;
&lt;item&gt;63,PH&lt;/item&gt;
&lt;item&gt;870,PN&lt;/item&gt;
&lt;item&gt;48,PL&lt;/item&gt;
&lt;item&gt;351,PT&lt;/item&gt;
&lt;item&gt;1,PR&lt;/item&gt;
&lt;item&gt;974,QA&lt;/item&gt;
&lt;item&gt;40,RO&lt;/item&gt;
&lt;item&gt;7,RU&lt;/item&gt;
&lt;item&gt;250,RW&lt;/item&gt;
&lt;item&gt;590,BL&lt;/item&gt;
&lt;item&gt;685,WS&lt;/item&gt;
&lt;item&gt;378,SM&lt;/item&gt;
&lt;item&gt;239,ST&lt;/item&gt;
&lt;item&gt;966,SA&lt;/item&gt;
&lt;item&gt;221,SN&lt;/item&gt;
&lt;item&gt;381,RS&lt;/item&gt;
&lt;item&gt;248,SC&lt;/item&gt;
&lt;item&gt;232,SL&lt;/item&gt;
&lt;item&gt;65,SG&lt;/item&gt;
&lt;item&gt;421,SK&lt;/item&gt;
&lt;item&gt;386,SI&lt;/item&gt;
&lt;item&gt;677,SB&lt;/item&gt;
&lt;item&gt;252,SO&lt;/item&gt;
&lt;item&gt;27,ZA&lt;/item&gt;
&lt;item&gt;82,KR&lt;/item&gt;
&lt;item&gt;34,ES&lt;/item&gt;
&lt;item&gt;94,LK&lt;/item&gt;
&lt;item&gt;290,SH&lt;/item&gt;
&lt;item&gt;508,PM&lt;/item&gt;
&lt;item&gt;249,SD&lt;/item&gt;
&lt;item&gt;597,SR&lt;/item&gt;
&lt;item&gt;268,SZ&lt;/item&gt;
&lt;item&gt;46,SE&lt;/item&gt;
&lt;item&gt;41,CH&lt;/item&gt;
&lt;item&gt;963,SY&lt;/item&gt;
&lt;item&gt;886,TW&lt;/item&gt;
&lt;item&gt;992,TJ&lt;/item&gt;
&lt;item&gt;255,TZ&lt;/item&gt;
&lt;item&gt;66,TH&lt;/item&gt;
&lt;item&gt;228,TG&lt;/item&gt;
&lt;item&gt;690,TK&lt;/item&gt;
&lt;item&gt;676,TO&lt;/item&gt;
&lt;item&gt;216,TN&lt;/item&gt;
&lt;item&gt;90,TR&lt;/item&gt;
&lt;item&gt;993,TM&lt;/item&gt;
&lt;item&gt;688,TV&lt;/item&gt;
&lt;item&gt;971,AE&lt;/item&gt;
&lt;item&gt;256,UG&lt;/item&gt;
&lt;item&gt;44,GB&lt;/item&gt;
&lt;item&gt;380,UA&lt;/item&gt;
&lt;item&gt;598,UY&lt;/item&gt;
&lt;item&gt;1,US&lt;/item&gt;
&lt;item&gt;998,UZ&lt;/item&gt;
&lt;item&gt;678,VU&lt;/item&gt;
&lt;item&gt;39,VA&lt;/item&gt;
&lt;item&gt;58,VE&lt;/item&gt;
&lt;item&gt;84,VN&lt;/item&gt;
&lt;item&gt;681,WF&lt;/item&gt;
&lt;item&gt;967,YE&lt;/item&gt;
&lt;item&gt;260,ZM&lt;/item&gt;
&lt;item&gt;263,ZW&lt;/item&gt;

}
//ID: 623102
&lt;string-array name="array_of_strings"&gt;

    &lt;item&gt;one&lt;/item&gt;
    &lt;item&gt;two&lt;/item&gt;
    &lt;item&gt;three&lt;/item&gt;

&lt;/string-array&gt;

}
//ID: 624625
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;ImageView
        android:id="@+id/ivMain"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/&gt;

    &lt;LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignBottom="@+id/ivMain"
        android:layout_alignLeft="@+id/ivMain"
        android:layout_alignRight="@+id/ivMain"
        android:layout_alignTop="@+id/ivMain"
        android:gravity="center"&gt;

        &lt;ImageView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginRight="16dp"/&gt;

        &lt;ImageView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginLeft="16dp" /&gt;

    &lt;/LinearLayout&gt;

&lt;/RelativeLayout&gt;

}
//ID: 625622
public class MyClass {

    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("item")
    @Expose
    public String item;

    @SerializedName("itemId")
    @Expose
    public String itemId;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("img")
    @Expose
    public String img;
}

}
//ID: 631657
 &lt;activity android:label="TestBrowser" android:name="WebActivity"&gt;
    &lt;intent-filter&gt;
        &lt;action android:name="your_custom_id"/&gt;
        &lt;category android:name="android.intent.category.DEFAULT"/&gt;
    &lt;/intent-filter&gt;
&lt;/activity&gt;

}
//ID: 634044
fun onCreateView (inflater: LayoutInflater, 
            container: ViewGroup?, 
            savedInstanceState: Bundle?): View?

}
//ID: 635254
&lt;resources&gt;

    &lt;string-array name="a0"&gt;
        &lt;item&gt;One 1&lt;/item&gt;
        &lt;item&gt;One 2&lt;/item&gt;
    &lt;/string-array&gt;

    &lt;string-array name="a1"&gt;
        &lt;item&gt;Two 1&lt;/item&gt;
        &lt;item&gt;Two 2&lt;/item&gt;
    &lt;/string-array&gt;
&lt;/resources&gt;

}
//ID: 635636
&lt;resources&gt;
    &lt;string-array name="people"&gt;
        &lt;name&gt;denis&lt;/name&gt;
        &lt;name&gt;ivan&lt;/name&gt;
    &lt;/string-array&gt;
&lt;/resources&gt;

}
//ID: 638711
&lt;FrameLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;LinearLayout
        &lt;!-- нижняя разметка --&gt;
    &lt;/LinearLayout&gt;

     &lt;LinearLayout
         &lt;!-- верхняя разметка --&gt;
    &lt;/LinearLayout&gt;

&lt;/FrameLayout&gt;

}
//ID: 639721
&lt;receiver
    android:name="MyReceiver"
    android:enabled="true"&gt;
    &lt;intent-filter android:priority="999"&gt;
         &lt;action android:name="android.intent.action.PHONE_STATE"/&gt;           
         &lt;action android:name="android.intent.action.NEW_OUTGOING_CALL"/&gt; 
    &lt;/intent-filter&gt;
&lt;/receiver&gt;

}
//ID: 640897
@Override
 protected void onActivityResult(final int requestCode, final int resultCode, final Intent intent)
 {
     if (resultCode == Activity.RESULT_OK &amp;&amp; requestCode == 3)
     {
          Uri toneUri = intent.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);

          if (toneUri != null)
          {
              this.chosenRingtone = toneUri.toString();
          }
          else
          {
              this.chosenRingtone = null;
          }
      }            
  }

}
//ID: 645105
&lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context="by.ittech.test_chat.Profile"&gt;

}
//ID: 646278
public class zTextWatcher : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(editable: Editable?) {}
}

}
//ID: 652577
&lt;android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start" &gt;

    &lt;android.support.design.widget.CoordinatorLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fitsSystemWindows="true" &gt;

        &lt;android.support.design.widget.AppBarLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:theme="@style/AppTheme.AppBarOverlay" &gt;

            &lt;android.support.v7.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                android:background="@drawable/toolbar_bg"
                android:theme="@style/ToolbarStyle"
                app:popupTheme="@style/AppTheme.PopupOverlay" /&gt;
        &lt;/android.support.design.widget.AppBarLayout&gt;

       &lt;RelativeLayout
            xmlns:tools="http://schemas.android.com/tools"
            android:id="@+id/LinearLayout1"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layout_behavior="@string/appbar_scrolling_view_behavior"
            tools:context="${relativePackage}.${activityClass}" &gt;

           // ...
&lt;/RelativeLayout&gt;

    &lt;/android.support.design.widget.CoordinatorLayout&gt;

    &lt;android.support.design.widget.NavigationView
        android:id="@+id/navigationView1"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/navside_main_new"
        app:menu="@menu/menu_navside_main" /&gt;

&lt;/android.support.v4.widget.DrawerLayout&gt;

}
//ID: 654512
 &lt;!-- Стандартная тема для всего приложения. --&gt;
&lt;style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar"&gt;
        &lt;item name="colorPrimary"&gt;@color/colorPrimary&lt;/item&gt;
        &lt;item name="colorPrimaryDark"&gt;@color/colorPrimaryDark&lt;/item&gt;
        &lt;item name="colorAccent"&gt;@color/colorAccent&lt;/item&gt;
    &lt;/style&gt;
 &lt;!-- Тема для вашего активити --&gt;
    &lt;style name="Name" parent="Theme.AppCompat.Light.DarkActionBar"&gt;
        &lt;!-- Customize your theme here. --&gt;
        &lt;item name="colorPrimary"&gt;#36a438&lt;/item&gt;
        &lt;item name="colorPrimaryDark"&gt;#cf8c34&lt;/item&gt;
        &lt;item name="colorAccent"&gt;#fe2ee6&lt;/item&gt;
 &lt;/style&gt;

}
//ID: 661044
val volunteers = SortedHashMap&lt;Volunteer&gt;()
(0..json.length() - 1)
        .map {
            try {
                Volunteer(json.getJSONObject(it))
            } catch (e: Exception) {
                null
            }
        }
        .filterNotNull()
        .forEach { volunteers.put(it.id, it) }

}
//ID: 661594
public class MyFragment extends Fragment {
   //blah-blah

   public interface MyFragmentListener {
       public void onFragmentSomethingHappens();
       public void onFragmentYetAnotherHappens();
   } 
}

}
//ID: 662394
val list = listOf(1, 2, 3, 1, 2, 3)
val result = list - 2
println(result) // [1, 3, 1, 2, 3]

}
//ID: 664391
&lt;FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="center"
    &gt;

    &lt;ImageView
        android:id="@+id/image"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="10dp"
        /&gt;

    &lt;ImageView
        android:id="@+id/letter"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        /&gt;

&lt;/FrameLayout&gt;

}
//ID: 664404
&lt;FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    &gt;

    &lt;ImageView
        android:id="@+id/image"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        /&gt;

    &lt;ImageView
        android:id="@+id/letter"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        /&gt;
&lt;/FrameLayout&gt;

}
//ID: 664627
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"&gt;
 &lt;ImageView
    android:id ="@+id/backgroun"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:scaleType="fitXY"/&gt;

 &lt;RelativeLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;
            &lt;!-- твой код --&gt;
 &lt;/RelativeLayout&gt;
&lt;/FrameLayout&gt;

}
//ID: 666605
if(savedInstanceState == null)
   setFragment(0);

}
//ID: 666823
public class MyActivity extends Activity {    
    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        //blah-blah
    }
}

}
//ID: 666825
public class MyFragment extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.name2, container, false);
        View view_name2_1=v.findViewById(R.id.name2_1); 
    }

}
//ID: 668445
public class RetainMapFragment : MapFragment
{
    public override void OnActivityCreated(Bundle savedInstanceState)
    {
        base.OnActivityCreated(savedInstanceState);
        RetainInstance = true;
    }
}

}
//ID: 672229
override fun onListItemClick(itemIndex: Int, itemCode: String) {
    try {
        presenter.onItemClick(adapter.getItem(itemIndex))
    } catch (e: Exception) { }
}

}
//ID: 678700
@SuppressWarnings("unchecked")
public &lt;T extends View&gt; T fvbi(@IdRes int resId) {
    return (T) findViewById(resId);
}

}
//ID: 680800
android {
    lintOptions {
        abortOnError false
    }
}

}
//ID: 682477
LocationListener locationListener = new LocationListener() {
    ...
};

}
//ID: 682888
&lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"&gt;

        &lt;!--тут все остальные ваши вью--&gt;
    &lt;/RelativeLayout&gt;

    &lt;FrameLayout
        android:id="@+id/black_screen"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#000000"/&gt;
&lt;/RelativeLayout&gt; 

}
//ID: 1074829
private fun showFileChooser() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"      //all files
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        try {
            startActivityForResult(Intent.createChooser(intent, resources.getString(R.string.attachment_dialog)), 1)
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show()
        }
    }

}
//ID: 1075577
    override fun onCreate(savedInstanceState: Bundle?) {
    val numberList = ArrayList&lt;Int&gt;()
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fifth)
    val recyclerView = findViewById&lt;RecyclerView&gt;(R.id.recycler_view)
    val recyclerAdapter = RecyclerAdapter()
    recyclerView.adapter = recyclerAdapter
    for (i in 1..100){
        numberList.add(i)
        recyclerAdapter.setValue(numberList)
    }
}

}
//ID: 1079061
private fun callPhone(number: String = this.phone) {
    val intent = Intent(Intent.ACTION_CALL).apply {
        data = Uri.parse("tel:$number")
    }
    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}

}
//ID: 1079061
companion object{
    private const val MY_PERMISSIONS_REQUEST_CALL_PHONE = 1
}

}
//ID: 1079359
data class MusicItem(val wrapperType: String, val map: Map&lt;String, Any?&gt;)

data class Collection(val map: Map&lt;String, Any?&gt;) {
    val collection: String by map
    val collectionType: String by map
}

data class Track(val map: Map&lt;String, Any?&gt;){
    val kind: String by map
    val artistId: String by map
}


fun List&lt;MusicItem&gt;.getTracks(): List&lt;Track&gt; {
    return this.filter { it.wrapperType == "track" }.map { Track(it.map) }.toList()
}

fun List&lt;MusicItem&gt;.getCollections(): List&lt;Collection&gt; {
return this.filter { it.wrapperType == "collection" }.map { Collection(it.map) }.toList()
}

data class BaseResponse&lt;T&gt;(
val resultCount : Int,
val results : List&lt;MusicItem&gt;)

}
//ID: 1079470
    kapt {
        correctErrorTypes = true
    }

}
//ID: 1080859
class NoteViewModelFactory(val application: Application) : ViewModelProvider.Factory {
    override fun &lt;T : ViewModel?&gt; create(modelClass: Class&lt;T&gt;): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

}
//ID: 1082039
class MyClass { 
  companion object {
    val info = "This is info"    
    fun getMoreInfo():String { return "This is more fun" }
  } 
}

MyClass.info             // This is info
MyClass.getMoreInfo()    // This is more fun

}
//ID: 1082861
val filter = IntentFilter("identificator")
receiver = object : BroadcastReceiver() {
   override fun onReceive(context: Context, intent: Intent) {
      // your action
    }
}

registerReceiver(receiver, filter)

}
//ID: 1083953
fun MhCdu(args: Array&lt;String&gt;) {
    println("Hello, World!")
}

//ID: 1085415
@CheckResult
fun View.clicks(): Observable&lt;Unit&gt; {
  return ViewClickObservable(this)
}

private class ViewClickObservable(
  private val view: View
) : Observable&lt;Unit&gt;() {

  override fun subscribeActual(observer: Observer&lt;in Unit&gt;) {
    if (!checkMainThread(observer)) {
      return
    }
    val listener = Listener(view, observer)
    observer.onSubscribe(listener)
    view.setOnClickListener(listener)
  }

  private class Listener(
    private val view: View,
    private val observer: Observer&lt;in Unit&gt;
  ) : MainThreadDisposable(), OnClickListener {

    override fun onClick(v: View) {
      if (!isDisposed) {
        observer.onNext(Unit)
      }
    }

    override fun onDispose() {
      view.setOnClickListener(null)
    }
  }
}

}
//ID: 1086714
val timer = object: CountDownTimer(20000, 1000) {
    override fun onTick(millisUntilFinished: Long) {...}

    override fun onFinish() {...}
}
timer.start()

}
//ID: 1087945
fun mapToJson(map: HashMap&lt;String, String&gt;): String {
    return Gson().toJson(map)
}

}
//ID: 1089375
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/white"&gt;

    &lt;Button
        android:id="@+id/button_1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Button 1"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toStartOf="@+id/button_2"
        app:layout_constraintHorizontal_chainStyle="packed"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" /&gt;

    &lt;Button
        android:id="@+id/button_2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Button 2"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_chainStyle="packed"
        app:layout_constraintStart_toEndOf="@id/button_1"
        app:layout_constraintTop_toTopOf="parent" /&gt;

&lt;/androidx.constraintlayout.widget.ConstraintLayout&gt;

}
//ID: 1089864
class FilterScreen : DialogFragment(), View.OnClickListener {
    val TAG = FilterScreen::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_fragment, container, false)


        return view
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            Objects.requireNonNull&lt;Window&gt;(dialog.window).setLayout(width, height)
        }
    }

}

}
//ID: 1089864
class JobsList : Fragment(){

override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                          savedInstanceState: Bundle?): View? {

val rootView = inflater.inflate(R.layout.fragment_jobs_list, container, false)
 }
}

}
//ID: 1090809
 &lt;androidx.appcompat.widget.Toolbar
    android:layout_width="match_parent"
    android:layout_height="?attr/actionBarSize"
    android:background="?attr/colorPrimary"
    app:contentInsetStart="0dp"
    app:navigationIcon="@null"&gt;

    &lt;LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"&gt;

        &lt;!--     your spinner       --&gt;
        &lt;Spinner
            android:layout_gravity="center_vertical"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:entries="@array/spinner_entries"
            android:theme="@style/SpinnerTheme" /&gt;

    &lt;/LinearLayout&gt;

&lt;/androidx.appcompat.widget.Toolbar&gt;

}
//ID: 1091723
var id: Long = 0L
open var accountNonExpired: Boolean = true
open var accountNonLocked: Boolean = true
open var credentialsNonExpired: Boolean = true
open var enabled: Boolean = true

@OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])

open var roles: Set&lt;Role&gt; = HashSet()

}
//ID: 1092725
fun test(view: View) {
    var num = 0
    for (i in 0..4){
        num++
        val testSay = Toast.makeText(this, num.toString(), Toast.LENGTH_SHORT)
        testSay.show()
    }
}

}
//ID: 1093097
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val liveData = DataController.getData()

        liveData?.observe(this, object : Observer,
            androidx.lifecycle.Observer&lt;String&gt; {

            override  fun onChanged(@Nullable value: String) {
                textView.text = value
            }

            override fun update(o: Observable?, arg: Any?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    fun refresh(view: View) {
        DataController.refresh()
    }

    object DataController {
        private val liveData = MutableLiveData&lt;String&gt;()

        fun getData(): LiveData&lt;String&gt;? {
            return liveData
        }

        fun getCurrentDateTime(): Date {
            return Calendar.getInstance().time
        }

        fun refresh() {
            val date = getCurrentDateTime()
            val dateInString = date.toString()
            liveData.value = dateInString
        }
    }
}

}
//ID: 1093996
fun ageDescription(age: Int): String =
    when (age % 100) {
        in 11..14 -&gt; "$age лет"
        else -&gt; when (age % 10) {
            1 -&gt; "$age год"
            in 2..4 -&gt; "$age года"
            else -&gt; "$age лет"
        }
    }

}
//ID: 1094138
class SubChapterBottomSheet private constructor() : Fragment(){
}

}
//ID: 1095305
interface TypeConverter&lt;T&gt; {

    val clazz: Type

    @TypeConverter
    fun fromType(model: T): String =
        Gson().toJson(model)

    @TypeConverter
    fun fromString(value: String): T {
        return Gson().fromJson&lt;T&gt;(value, clazz)
    }
}

}
//ID: 1095305
class StringListConverter : TypeConverter&lt;List&lt;String&gt;&gt; {

    override val clazz: Type
        get() = object : TypeToken&lt;List&lt;String&gt;&gt;() {}.type
}

class IntListConverter : TypeConverter&lt;List&lt;Int&gt;&gt; {

    override val clazz: Type
        get() = object : TypeToken&lt;List&lt;Int&gt;&gt;() {}.type
}

class ImagesConfigConverter : TypeConverter&lt;ImagesConfigModel&gt; {

    override val clazz: Type
        get() = object : TypeToken&lt;ImagesConfigModel&gt;() {}.type
}

}
//ID: 1097589
val wrongArray = listOf(1, 5, 2, 7, 93, 8, 9, 3)
val result = mutableListOf&lt;Int&gt;()
for (i in wrongArray.indices){
    when {
        i == 0 -&gt; result.add(wrongArray[i])
        i == wrongArray.size - 1 -&gt; result.add(wrongArray[i])
        wrongArray[i-1] + wrongArray[i+1] &gt;= wrongArray[i] -&gt; result.add(wrongArray[i])
    }
}
println(result)

}
//ID: 1097589
fun filter(wrongArray: List&lt;Int&gt;): Pair&lt;List&lt;Int&gt;, Boolean&gt; {
    val result = mutableListOf&lt;Int&gt;()
    for (i in wrongArray.indices) {
        when {
            i == 0 -&gt; result.add(wrongArray[i])
            i == wrongArray.size - 1 -&gt; result.add(wrongArray[i])
            wrongArray[i - 1] + wrongArray[i + 1] &gt;= wrongArray[i] -&gt; result.add(wrongArray[i])
        }
    }
    return Pair(result, result.size == wrongArray.size)
}

}
//ID: 1104041
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    ...
    val args = arguments
    if(args != null){
        ...
        finishButton.setOnClickListener { 
            Toast.makeText(context, score.toString(), Toast.LENGTH_SHORT).show()
        }
    } else {
        Toast.makeText(context, "Args are null!", Toast.LENGTH_SHORT).show()
    }
}

}
//ID: 1104041
    finishButton.setOnClickListener { 
        View.OnClickListener { Toast.makeText(context, score.toString(), Toast.LENGTH_SHORT).show() }
    }

}
//ID: 1104195
const val APP_PREFERENCES = "mysettings"
const val APP_PREFS_SCORE = "my_settings_score"
private lateinit var prefs: SharedPreferences 

....

override fun onCreate(savedInstanceState: Bundle?){
    super.onCreate(savedInstanceState)
    prefs = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
    .....
}

fun changeScore(addition: Int){
    val editor = prefs.edit()
    val oldValue = prefs.getInt(APP_PREFS_SCORE, 0) ?: 0
    val newValue = if(oldValue + addition &lt; 0) 0
    else oldValue + addition
    editor.putInt(APP_PREFS_SCORE, newValue)
    editor.apply()
}

fun getScore(): Int {
    return prefs.getInt(APP_PREFS_SCORE, 0) ?: 0
}

}
//ID: 1104197
var score = 0
        radioGroup.setOnCheckedChangeListener { _, isChecked -&gt;
            when (isChecked) {
                R.id.radio_one-&gt; when {
                    correctAnswerNumber == "1" -&gt; score++
                }
                R.id.radio_two -&gt; when {
                    correctAnswerNumber == "2" -&gt; score++
                }
                R.id.radio_three -&gt; when {
                    correctAnswerNumber == "3" -&gt; score ++
                }
                R.id.radio_four -&gt; when {
                    correctAnswerNumber == "4" -&gt; score++
                }
                else -&gt; when {
                    score &lt; 1 -&gt; score = 0
                    else -&gt; score--
                }
            }
        }

}
//ID: 1109672
class DialogFrg: DialogFragment() {
    val TAG: String = DialogFrg::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialogFrg, container, false)

        return view
    }

}

}
//ID: 1110820
class BluetoothConnect : AppCompatActivity() {
    private var bluetooth: BluetoothAdapter? = null
    private val REQUEST_ENABLE_BT = 1
    private lateinit var pairedDevises: Set&lt;BluetoothDevice&gt;
    private var listView: ListView? = null
    private var pairedDeviceArrayList: java.util.ArrayList&lt;String&gt;? = null
    private var pairedDeviceAdapter: ArrayAdapter&lt;String&gt;? = null
    private var bluetoothAdapter: BluetoothAdapter? = null
    var clientSocket: BluetoothSocket? = null

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
     ....
    }
}

}
//ID: 1130603
companion object {
    private const val TYPE_ITEM = 0
    private const val TYPE_DATE = 1
}

}
//ID: 1130603
override fun getItemViewType(position: Int): Int {
    return when (withdraws[position].source_id) {
        DATE_ITEM_ID -&gt; TYPE_DATE
        else -&gt; TYPE_ITEM
    }
}

}
//ID: 1130603
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): 
RecyclerView.ViewHolder {
    return when (viewType) {
        TYPE_ITEM -&gt; {
            WithdrawsViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_withdraw,
                    parent,
                    false
                )
            )
        }
        TYPE_DATE -&gt; {
            DateViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_withdraw_date,
                    parent,
                    false
                )
            )
        }
        else -&gt; WithdrawsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_withdraw,
                parent,
                false
            )
        )
    }
}

}
//ID: 1131892
@Query("SELECT * FROM Recept WHERE check = 1")
LiveData&lt;List&lt;Recept&gt;&gt; getLike();

}
//ID: 1132524
class SharedViewModel : ViewModel() {
    val selected = MutableLiveData&lt;Item&gt;()

    fun select(item: Item) {
        selected.value = item
    }
}

class MasterFragment : Fragment() {

    private lateinit var itemSelector: Selector

    private lateinit var model: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = activity?.run {
            ViewModelProviders.of(this).get(SharedViewModel::class.java)
        } : throw Exception("Invalid Activity")
        itemSelector.setOnClickListener { item -&gt;
            // Update the UI
        }
    }
}

class DetailFragment : Fragment() {

    private lateinit var model: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = activity?.run {
            ViewModelProviders.of(this).get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        model.selected.observe(this, Observer&lt;Item&gt; { item -&gt;
            // Update the UI
        })
    }
}

}
//ID: 1132913
&lt;androidx.drawerlayout.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"&gt;

    &lt;LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"&gt;

        &lt;include
            layout="@layout/toolbar"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" /&gt;

        &lt;FrameLayout
            android:id="@+id/flContent"
            app:layout_behavior="@string/appbar_scrolling_view_behavior"
            android:layout_width="match_parent"
            android:layout_height="match_parent" /&gt;
    &lt;/LinearLayout&gt;


    &lt;com.google.android.material.navigation.NavigationView
        android:id="@+id/nvView"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:background="@android:color/white"
        app:menu="@menu/drawer_view" /&gt;
&lt;/androidx.drawerlayout.widget.DrawerLayout&gt;

}
//ID: 1135873
var name = ""
var size: Long? = null

}
//ID: 1137926
main(arrayOf&lt;String&gt;("hello from script"))

fun nFpBb(args: Array&lt;String&gt;) {
    println(args[0])
    println("Hello World!")
}

//ID: 1140179
class BootReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, &quot;BOOT_COMPLETED&quot;, Toast.LENGTH_LONG).show()
    }
}

}
//ID: 1141166
fun install(filePath: String) {
        var inputStream: InputStream? = null
        try { 
            val sb = StringBuilder()
            sb.append(&quot;pm install -r &quot;)
            sb.append(filePath)
            val proc = Runtime.getRuntime().exec(sb.toString())
            inputStream = proc.inputStream
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            var line: String
            val log = StringBuilder()
            while (bufferedReader.readLine().also { line = it } != null) {
                log.append(line + &quot;\n&quot;)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

}
//ID: 1141548
sealed class Result&lt;out T&gt; {
    object Loading: Result&lt;Nothing&gt;()
    data class Success&lt;out T&gt;(val data: T) : Result&lt;T&gt;()
    data class Error(val exception: Exception) : Result&lt;Nothing&gt;()
} 

}
//ID: 1141658
data class Result&lt;out T&gt;(
    val status : Status,
    val data: T?,
    val error: String?
){
    enum class Status{
        SUCCESS,
        LOADING,
        ERROR
    }

    companion object{
        fun &lt;T&gt; success(data: T) : Result&lt;T&gt; = Result(Status.SUCCESS, data, null)
        fun &lt;T&gt; error(error: String?, data: T? = null): Result&lt;T&gt; = Result(Status.ERROR, data, error)
        fun &lt;T&gt; loading(data: T? = null): Result&lt;T&gt; = Result(Status.LOADING, data, null)
    }

}

}
//ID: 1141854
class MessageEvent(
        val user: User? = null,
        val key: String? = null,
        val pos: Int? = null
    ) 

}
//ID: 1141854
class MessageEvent() {
        var user: User? = null,
        var key: String? = null,
        var pos: Int? = null
    
        constructor(user: User, key: String, pos: Int) : this() {
            this.user = user,
            this.key = key,
            this.pos = pos
        }
    }

}
//ID: 1142475
class App : Application() {
companion object{
    private var instance:App? = null

    fun applicationContext(): Context {
        return instance!!.applicationContext
    }
}

init {
    instance = this
}

override fun onCreate() {
    super.onCreate()
    PreferencesRepository.getAppTheme().also {
        AppCompatDelegate.setDefaultNightMode(it)
    }
  }
}

}
//ID: 1143109
class ExpandableAdapter(
items: List&lt;Item&gt;,
private val nestedClickListener: (String) -&gt; Unit
) : RecyclerView.Adapter&lt;ExpandableAdapter.ExpandableViewHolder&gt;() {

val items = mutableListOf&lt;Item&gt;()

init {
    this.items.addAll(items)
}

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ExpandableViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
)

override fun getItemCount() = items.size

override fun onBindViewHolder(holder: ExpandableViewHolder, position: Int) {
    val item = items[position]

    with(holder) {
        text.text = item.text
        itemView.setOnClickListener {
            list.visibility = VISIBLE
            list.adapter = NestedAdapter(item.nestedItems) { nestedClickListener(it) }
        }
    }
}

class ExpandableViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val text: TextView = view.text
    val list: RecyclerView = view.recycler
}
}

}
//ID: 1143109
class NestedAdapter(
items: List&lt;String&gt;,
private val clickListener: (String) -&gt; Unit
) : RecyclerView.Adapter&lt;NestedAdapter.NestedViewHolder&gt;() {

val items = mutableListOf&lt;String&gt;()

init {
    this.items.addAll(items)
}

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NestedViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
)

override fun getItemCount() = items.size

override fun onBindViewHolder(holder: NestedViewHolder, position: Int) {
    val item = items[position]

    with(holder) {
        text.text = item
        itemView.setOnClickListener { clickListener(item) }
    }
}

class NestedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val text: TextView = view.text
}
}

}
//ID: 1144026
&lt;fragment android:name=&quot;ru.user.test.ItemFragment&quot;&quot;
        android:id=&quot;@+id/item_fragment&quot;
        android:layout_width=&quot;match_parent&quot;
        android:layout_height=&quot;match_parent&quot; /&gt;

}
//ID: 1145111
override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       
        welcomeMessage.text = &quot;Hello Kotlin!&quot;
    }
}

}
//ID: 1145111
class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: Data) {
            itemView.welcomeMessage.text = &quot;Hello Kotlin!&quot;
            }
        }
    }

}
//ID: 693417
interface IFoo {
  fun bar()
}

open class Foo: IFoo {
  override fun bar() {
    println("Foo")
  }
}

fun WyMbO(args: Array&lt;String&gt;) {
  accept(object: Foo() {
    override fun bar() {
        super.bar()
        println("Anonymous")
    }
  })
}

fun accept(foo: IFoo) {
  foo.bar()
}

//ID: 694006
android {  
   defaultConfig {  
     vectorDrawables.useSupportLibrary = true  
    }  
} 

}
//ID: 697739
if (Build.VERSION.SDK_INT &gt;= Build.VERSION_CODES.M) {
    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
}

}
//ID: 703183
allprojects {
    repositories {
        maven { url "https://maven.google.com" }
    }
}

}
//ID: 703296
 &lt;intent-filter &gt;
     &lt;action android:name="android.intent.action.VIEW"/&gt;
     &lt;category android:name="android.intent.category.DEFAULT"/&gt;
     &lt;category android:name="android.intent.category.BROWSABLE"/&gt;
     &lt;data android:host="myhost" android:scheme="myapp"/&gt;
 &lt;/intent-filter&gt;

}
//ID: 704044
&lt;android.support.design.widget.NavigationView
    android:id="@+id/nav_view"
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:layout_gravity="start"
    android:fitsSystemWindows="true"
    app:menu="@menu/activity_main_drawer" &gt;

    &lt;ImageView
        android:id="@+id/ivIcon"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@mipmap/ic_launcher_round"
        android:layout_gravity="bottom|left"/&gt;

&lt;/android.support.design.widget.NavigationView&gt;

}
//ID: 704219
public class MyFragment extends Fragment implements OnNumberChangeCallback {

    @Override
    public void onSuccess() {
        // TODO
    }

}

}
//ID: 705146
annotation class Description(val value: String)

class Test {

    @field:Description(value="My field")
    var myfield: String? = null

    fun getMyFieldDescription(): String {

        return this.javaClass.getDeclaredField("myfield")
                   .getAnnotation(Description::class.java)
                   .value
    }
}

fun KnyfU(args: Array&lt;String&gt;) {

    var test = Test()

    println(test.getMyFieldDescription())
}

//ID: 708764
class AccountModelNode private constructor(val key: String, val isLeaf: Boolean) {

    constructor(key: String, value: AccountCardViewModel) : this(key, true) {
        this.value = value
    }

    constructor(key: String, list: List&lt;AccountModelNode&gt;) : this(key, false) {
        this.list = list
    }

    private var value: AccountCardViewModel? = null

    val isAutomatic: Boolean? = value?.isAutomatic

    val currencyCode: String? = value?.currencyCode

    val description: String? = value?.description

    var list: List&lt;AccountModelNode&gt;? = null
        private set
}

}
//ID: 711355
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/root_frame_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/&gt;

}