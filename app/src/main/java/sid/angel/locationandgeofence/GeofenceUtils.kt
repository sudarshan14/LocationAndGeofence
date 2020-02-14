package sid.angel.locationandgeofence

import android.content.Context
import com.google.android.gms.location.GeofenceStatusCodes
import com.google.android.gms.maps.model.LatLng
import sid.angel.locationandgeofence.GeofencingConstants.LANDMARK_DATA
import java.util.concurrent.TimeUnit

internal object GeofencingConstants {

    /**
     * Used to set an expiration time for a geofence. After this amount of time, Location services
     * stops tracking the geofence. For this sample, geofences expire after one hour.
     */

    val GEOFENCE_EXPIRATION_IN_MILLISECONDS: Long = TimeUnit.HOURS.toMillis(1)
    val LANDMARK_DATA = arrayOf(
        LandmarkDataObject(
            "home",
            R.string.home_location_hint.toString(),
            R.string.home_location.toString(),
            LatLng(37.819927, -122.478256)
        ),
        LandmarkDataObject(
            "office",
            R.string.office_location_hint.toString(),
            R.string.office_location.toString(),
            LatLng(37.819927, -122.478256)
        )
    )
}

val NUM_LANDMARKS = LANDMARK_DATA.size
const val GEOFENCE_RADIUS_IN_METERS = 100f
const val EXTRA_GEOFENCE_INDEX = "GEOFENCE_INDEX"
data class LandmarkDataObject(val id: String, val hint: String, val name: String, val latLong: LatLng)

fun errorMessage(context: Context, errorCode: Int): String {
    val resources = context.resources
    return when (errorCode) {
        GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE -> resources.getString(
            R.string.geofence_not_available
        )
        GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES -> resources.getString(
            R.string.geofence_too_many_geofences
        )
        GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS -> resources.getString(
            R.string.geofence_too_many_pending_intents
        )
        else -> resources.getString(R.string.unknown_geofence_error)
    }
}