package com.develrm.metronomoemportugues.util

import android.content.Context
import android.media.MediaPlayer
import com.develrm.metronomoemportugues.R
import com.develrm.metronomoemportugues.data.model.enum.SubdivisionEnum
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Singleton
class MediaUtil(@ApplicationContext private val context: Context) {

    private var tickMediaPlayer: MediaPlayer? = null
    private var voiceMediaPlayer: MediaPlayer? = null
    fun emitTickSound() {
        if (tickMediaPlayer == null) {
            tickMediaPlayer = MediaPlayer.create(context, R.raw.tick)
            tickMediaPlayer?.setOnCompletionListener {
                stopTickSound()
            }
        }
        tickMediaPlayer?.start()
    }

    fun emitVoiceSound(
        subdivision: SubdivisionEnum,
        beats: Int,
        tick: Int,
        subdivisionBeat: Int
    ) {
        val sound = chooseSound(subdivision,beats,tick,subdivisionBeat)
        if (voiceMediaPlayer == null && sound != 0) {
            voiceMediaPlayer = MediaPlayer.create(context, sound)
            voiceMediaPlayer?.setOnCompletionListener {
                stopVoiceSound()
            }
        }
        voiceMediaPlayer?.start()
    }
    fun stopTickSound() {
        tickMediaPlayer?.release()
        tickMediaPlayer = null
    }

    fun stopVoiceSound() {
        voiceMediaPlayer?.release()
        voiceMediaPlayer = null
    }
}