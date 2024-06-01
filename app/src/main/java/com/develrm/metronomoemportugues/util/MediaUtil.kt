package com.develrm.metronomoemportugues.util

import android.content.Context
import android.media.MediaPlayer
import com.develrm.metronomoemportugues.R
import com.develrm.metronomoemportugues.data.model.enum.SubdivisionEnum
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Singleton
class MediaUtil(@ApplicationContext private val context: Context) {

    private val mediaPlayerPool = mutableListOf<MediaPlayer?>()
    private val maxPoolSize = 4

    init {
        repeat(maxPoolSize) { mediaPlayerPool.add(null) }
    }

    private fun acquireMediaPlayer(sound : Int): MediaPlayer {
        val availablePlayer = mediaPlayerPool.indexOfFirst { it == null }
        return if (availablePlayer != -1) {
            val mediaPlayer = MediaPlayer.create(context,sound)
            mediaPlayerPool[availablePlayer] = mediaPlayer
            mediaPlayer
        } else {
            throw IllegalStateException("MediaPlayer pool is full.")
        }
    }

    private fun releaseMediaPlayer(mediaPlayer: MediaPlayer) {
        mediaPlayer.release()
        val index = mediaPlayerPool.indexOf(mediaPlayer)
        if (index != -1) {
            mediaPlayerPool[index] = null
        }
    }
    fun emitTickSound() {
        playSound(R.raw.tick)
    }

    fun emitVoiceSound(
        subdivision: SubdivisionEnum,
        beats: Int,
        tick: Int,
        subdivisionBeat: Int
    ) {
        playSound(chooseSound(subdivision,beats,tick,subdivisionBeat))
    }

    private fun playSound(soundResourceId: Int) {
        val mediaPlayer = acquireMediaPlayer(soundResourceId)
        mediaPlayer.setOnCompletionListener {
            releaseMediaPlayer(mediaPlayer)
        }
        mediaPlayer.start()
    }
}