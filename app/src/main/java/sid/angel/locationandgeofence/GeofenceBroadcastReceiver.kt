package sid.angel.locationandgeofence

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent
import sid.angel.locationandgeofence.MainActivity.Companion.ACTION_GEOFENCE_EVENT

class GeofenceBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent?.action == ACTION_GEOFENCE_EVENT) {

            val geofencingEvent = GeofencingEvent.fromIntent(intent)

            if (geofencingEvent.hasError()) {
                val errorMessage = context?.let { errorMessage(it, geofencingEvent.errorCode) }
                return
            }

            if (geofencingEvent.geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {

                val fenceId = when {

                    geofencingEvent.triggeringGeofences.isEmpty() -> geofencingEvent.triggeringGeofences[0].requestId
                    else -> {
                        return
                    }
                }


                val foundIndex = GeofencingConstants.LANDMARK_DATA.indexOfFirst {
                    it.id == fenceId
                }

                if (-1 == foundIndex) {
//                    Log.e(TAG, "Unknown Geofence: Abort Mission")
                    return
                }

                val notificationManager = context?.let {
                    ContextCompat.getSystemServiceName(
                        it,
                        NotificationManager::class.java
                    )
                } as NotificationManager
                notificationManager.sendGeoFenceEnterendNotification(
                    context,
                    GeofencingConstants.LANDMARK_DATA[foundIndex].hint
                )

                Toast.makeText(
                    context,
                    "Enter ${GeofencingConstants.LANDMARK_DATA[foundIndex].hint}",
                    Toast.LENGTH_LONG
                ).show()
            } else if (geofencingEvent.geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {

                Toast.makeText(context, "Exit", Toast.LENGTH_LONG).show()
            }


        }else{
            Toast.makeText(context, "Exit", Toast.LENGTH_LONG).show()
        }
    }
}