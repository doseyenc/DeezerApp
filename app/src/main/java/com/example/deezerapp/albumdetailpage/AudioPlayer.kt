package com.example.deezerapp.albumdetailpage

import android.media.MediaPlayer

import android.media.AudioAttributes
import android.media.AudioManager

class AudioPlayer {

    private var mediaPlayer: MediaPlayer? = null

    fun playAudio(url: String) {
        mediaPlayer?.reset()
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build())
            setDataSource(url)
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setOnPreparedListener {
                it.start()
            }
            setOnErrorListener { mp, what, extra ->
                // Hata durumlarını yönetme
                false
            }
            setOnCompletionListener {
                // Şarkı tamamlandığında yapılacak işlemler
            }
            prepareAsync()
        }
    }

    fun stopAudio() {
        mediaPlayer?.stop()
        mediaPlayer?.reset()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
