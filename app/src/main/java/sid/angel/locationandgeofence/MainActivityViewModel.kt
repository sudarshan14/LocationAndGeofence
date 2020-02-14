package sid.angel.locationandgeofence

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {


    fun getGeoFenceClient(application: Application): GeofencingClient {
        return LocationServices.getGeofencingClient(application)
    }

    val prefs = SharedPreferenceUtil(getApplication())


    fun getCurrentLatitude() :Double {

        return prefs.getData("PREF_LAT_KEY").toString().toDouble()
    }

    fun getCurrentLongitude():Double {

        return prefs.getData("PREF_LON_KEY").toString().toDouble()
    }


    fun getGeofenceData() :Array<LandmarkDataObject> {
       val  LANDMARK_DATA = arrayOf(
            LandmarkDataObject(
                "home",
                R.string.home_location_hint.toString(),
                R.string.home_location.toString(),
                LatLng(getCurrentLatitude(), getCurrentLongitude())
            ),
            LandmarkDataObject(
                "office",
                R.string.office_location_hint.toString(),
                R.string.office_location.toString(),
                LatLng(37.819927, -122.478256)
            )
        )

        return LANDMARK_DATA
    }
}