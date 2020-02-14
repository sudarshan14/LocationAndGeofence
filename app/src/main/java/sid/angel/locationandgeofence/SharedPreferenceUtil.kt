package sid.angel.locationandgeofence

import android.content.Context
import androidx.preference.PreferenceManager

class SharedPreferenceUtil(context: Context) {

    val PREF_LAT_KEY = "lat"
    val PREF_LON_KEY = "lon"
    private val pref = PreferenceManager.getDefaultSharedPreferences(context)


    fun saveData(key: String, value: String) {
        pref.edit().putString(key, value).apply()
    }


    fun getData(key: String): String? {

        return pref.getString(key, "")
    }
}