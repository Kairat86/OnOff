package off.on.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View.VISIBLE
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import io.objectbox.Box
import off.on.App
import off.on.R
import off.on.adapter.OnOffAdapter
import off.on.entity.Time

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    private lateinit var box: Box<Time>

    private lateinit var adapter: RecyclerView.Adapter<OnOffAdapter.VH>

    private lateinit var rv: RecyclerView

    private lateinit var ad: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val app = application as App
        app.register(this);
        box = app.getBoxStore().boxFor(Time::class.java)
        rv = findViewById<RecyclerView>(R.id.rvTime)
        rv.setHasFixedSize(true)
        adapter = OnOffAdapter(box.all)
        rv.adapter = adapter
        val adView = findViewById<AdView>(R.id.adView)
        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                adView.visibility = VISIBLE
            }
        }

        adView.loadAd(AdRequest.Builder().build())

        ad = InterstitialAd(this)
        ad.adUnitId = getString(R.string.inter_id)
        ad.loadAd(AdRequest.Builder().build())
    }

    fun getAdapter(): RecyclerView.Adapter<OnOffAdapter.VH> {
        return adapter;
    }

    fun getRV(): RecyclerView {
        return rv
    }

    override fun onBackPressed() {
        if (ad.isLoaded) {
            ad.show()
        }
        super.onBackPressed()
    }
}
