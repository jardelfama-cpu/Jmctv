package com.jmctv.app

object ChannelRepository {
    // Lista extraída do M3U enviado pelo usuário.
    // Ajuste os canais e URLs conforme necessário.
    private const val RAW_M3U = """
#EXTM3U
#EXTINF:-1 tvg-id="TVClube.br@SD" tvg-logo="https://i.imgur.com/CpXjcyE.png" group-title="Undefined",TV Clube (720p)
https://5c483b9d1019c.streamlock.net/8186/8186/playlist.m3u8
#EXTINF:-1 tvg-id="TVComunitaria.br@SD" tvg-logo="https://i.imgur.com/uxefHY3.png" group-title="General",TV Comunitaria (360p)
https://5b7f3c45ab7c2.streamlock.net/8008/smil:8008.smil/playlist.m3u8?DVR=
#EXTINF:-1 tvg-id="TVGloboBrasilia.br@SD" tvg-logo="" group-title="Undefined",TV Globo Brasilia (1080p)
http://79.127.238.228:14173/
#EXTINF:-1 tvg-id="TVMaisMarica.br@SD" tvg-logo="https://i.imgur.com/lgCRX7q.png" group-title="General",TV Mais Marica (1080p)
http://wz3.dnip.com.br:1935/novaeratv/novaeratv.sdp/live.m3u8
"""

    fun getChannels(): List<Channel> = parseM3u(RAW_M3U)

    private fun parseM3u(rawM3u: String): List<Channel> {
        val channels = mutableListOf<Channel>()
        val lines = rawM3u.lineSequence().map { it.trim() }.filter { it.isNotEmpty() }

        var currentName: String? = null
        var currentUrl: String? = null

        for (line in lines) {
            if (line.startsWith("#EXTINF")) {
                val info = line.substringAfter(",")
                currentName = info.ifBlank { "Canal sem nome" }
                continue
            }

            if (line.startsWith("http://") || line.startsWith("https://")) {
                currentUrl = line
            }

            if (currentName != null && currentUrl != null) {
                channels.add(
                    Channel(
                        id = currentName.replace("[\\s\\W]+".toRegex(), "_").lowercase(),
                        name = currentName,
                        url = currentUrl,
                        description = "Stream do canal"
                    )
                )
                currentName = null
                currentUrl = null
            }
        }

        return if (channels.isNotEmpty()) channels else listOf(
            Channel(
                id = "tv_1",
                name = "TV Clube (720p)",
                url = "https://5c483b9d1019c.streamlock.net/8186/8186/playlist.m3u8",
                description = "Canal de teste"
            )
        )
    }
}
