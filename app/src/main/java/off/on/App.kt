package off.on

import android.app.Application
import android.util.Log
import io.objectbox.BoxStore
import off.on.activity.MainActivity
import off.on.adapter.OnOffAdapter
import off.on.entity.MyObjectBox
import off.on.entity.Time

/**
 * Created by kairatdoshekenov on 3/11/18.
 */
class App : Application() {

    companion object {
        val TAG: String = App::class.java.simpleName
    }

    private lateinit var boxStore: BoxStore;
    private var activity: MainActivity? = null

    override fun onCreate() {
        super.onCreate()
        boxStore = MyObjectBox.builder().androidContext(this).build()

    }

    fun getBoxStore(): BoxStore {
        return boxStore
    }

    fun publishEvent(time: Time) {
        val adapter = activity?.getAdapter()
        (adapter as OnOffAdapter).addItem(time);
        val position = adapter.itemCount - 1
        adapter.notifyItemInserted(position)
        activity?.getRV()?.scrollToPosition(position)
    }

    fun register(activity: MainActivity) {
        this.activity = activity;
    }
}