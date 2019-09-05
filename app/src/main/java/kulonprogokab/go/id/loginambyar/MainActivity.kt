package kulonprogokab.go.id.loginambyar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Login Aplikasi"

        btnLogin?.setOnClickListener {
            login()
        }
    }

    override fun onStart() {
        super.onStart()

        if(SharedPrefManager.getInstance(this).isLogin){
            val intent = Intent(applicationContext, Dashboard::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }

    private fun login() {
        val username = username.text.toString().trim()
        val password = password.text.toString().trim()

        if (username.isEmpty()) {
            Toast.makeText(applicationContext, "Username masih kosong", Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            Toast.makeText(applicationContext, "Password masih kosong", Toast.LENGTH_SHORT).show()
        } else {
            try {
                val json = JSONObject()
                json.put("usernames", username)
                json.put("passwords", password)
                val requestBody: RequestBody = RequestBody.create(MediaType.parse("application/json"), json.toString())

                // Api api =
                val call: Call<LoginResponse> =  RetrofitClient.instance.userLogin(requestBody)
                call.enqueue(
                    object : Callback<LoginResponse> {
                        override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {

                            Log.d("loginAuthendication", response.toString())
                            Log.d("loginAuthendication", response!!.body().toString())
                            if (response!!.body()?.success!!) {
                                Toast.makeText(applicationContext, "Login berhasil", Toast.LENGTH_LONG).show()

                                SharedPrefManager.getInstance(applicationContext).saveUser(response.body()?.copy()!!)

                                val intent = Intent(applicationContext, Dashboard::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                                startActivity(intent)
                            } else {
                                Toast.makeText(applicationContext, "Login gagal", Toast.LENGTH_LONG).show()
                            }
                        }
                        override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                            Log.d("loginAuthendication", "failure--" + t.toString())

                        }
                    })
            } catch (e: Exception) {
                Log.d("loginAuthendication", e.toString())
            }
        }
    }
}
