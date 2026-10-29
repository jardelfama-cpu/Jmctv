package com.jmctv.app

object ChannelRepository {
    fun getChannels(): List<Channel> = listOf(
        Channel(
            id = "tv1",
            name = "Canal Demo 1",
            url = "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
            description = "Vídeo de demonstração"
        ),
        Channel(
            id = "tv2",
            name = "Canal Demo 2",
            url = "https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            description = "Vídeo de demonstração"
        ),
        Channel(
            id = "tv3",
            name = "Canal Demo 3",
            url = "https://storage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            description = "Vídeo de demonstração"
        )
    )
}
