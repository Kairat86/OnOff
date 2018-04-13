package off.on.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import off.on.App
import off.on.entity.Time

/**
 * Created by kairatdoshekenov on 2/28/18.
 */
class OnOffReceiver : BroadcastReceiver() {

    companion object {
        val TAG: String = OnOffReceiver::class.java.simpleName
        const val BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED"
    }

    override fun onReceive(context: Context, intent: Intent) {
        val time = Time(time = System.currentTimeMillis())
        when (intent.action) {
            BOOT_COMPLETED -> time.on = true
        }
        val app = context.applicationContext as App
        val boxStore = app.getBoxStore()
        val box = boxStore.boxFor(Time::class.java)
        box.put(time)
        if (time.on) {
           app.publishEvent(time);
        }
    }
}