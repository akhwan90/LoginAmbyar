package kulonprogokab.go.id.loginambyar

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.auth0.android.jwt.JWT
import kotlinx.android.synthetic.main.activity_dashboard.*



class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        supportActionBar?.title = "Dashboard Login"
        val token_get = SharedPrefManager.getInstance(applicationContext).loginResponse.token
        token.text = token_get

        val jwt = JWT(token_get)

        Log.d("loginAuthendication", jwt.toString())
    }
}
