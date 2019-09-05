package kulonprogokab.go.id.loginambyar

import android.content.Context

class SharedPrefManager private constructor(private val mCtx: Context) {

    val isLogin: Boolean
    get() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("success", false) != false
    }

    val loginResponse: LoginResponse
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return LoginResponse(
                sharedPreferences.getBoolean("success", false)!!,
                sharedPreferences.getString("message", null)!!,
                sharedPreferences.getString("token", null)!!
            )
        }

    fun saveUser(loginResponse: LoginResponse) {

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putBoolean("success", loginResponse.success)
        editor.putString("message", loginResponse.message)
        editor.putString("token", loginResponse.token)
        editor.apply()

    }

    fun clear() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private val SHARED_PREF_NAME = "my_shared_preff"
        private var mInstance: SharedPrefManager? = null
        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }
}