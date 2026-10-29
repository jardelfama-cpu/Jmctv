package com.jmctv.app

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.Presenter

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ChannelBrowserFragment())
                .commit()
        }
    }
}

class ChannelBrowserFragment : BrowseSupportFragment() {
    private val channels = ChannelRepository.getChannels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        title = "Canais"

        val adapter = ArrayObjectAdapter(ListRowPresenter())
        val rowAdapter = ArrayObjectAdapter(ChannelPresenter())
        channels.forEach { rowAdapter.add(it) }

        val header = HeaderItem(0, "Lista de canais")
        adapter.add(ListRow(header, rowAdapter))
        this.adapter = adapter

        setOnItemViewClickedListener { _, item, _, _ ->
            val channel = item as? Channel ?: return@setOnItemViewClickedListener
            val intent = Intent(requireContext(), PlayerActivity::class.java).apply {
                putExtra("channel_name", channel.name)
                putExtra("channel_url", channel.url)
            }
            startActivity(intent)
        }
    }
}

class ChannelPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_channel, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val channel = item as Channel
        val view = viewHolder.view
        view.findViewById<TextView>(R.id.channel_name).text = channel.name
        view.findViewById<TextView>(R.id.channel_url).text = channel.description
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) = Unit
}
