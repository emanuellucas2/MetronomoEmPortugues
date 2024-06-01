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
    public fun emitTickSound() {
        if (tickMediaPlayer == null) {
            tickMediaPlayer = MediaPlayer.create(context, R.raw.tick)
            tickMediaPlayer?.setOnCompletionListener {
                stopTickSound()
            }
        }
        tickMediaPlayer?.start()
    }

    public fun emitVoiceSound(subdivision: SubdivisionEnum, beats:Int, tick:Int) {
        val sound = chooseSound(subdivision,beats,tick)
        if (voiceMediaPlayer == null && sound != 0) {
            voiceMediaPlayer = MediaPlayer.create(context, sound)
            voiceMediaPlayer?.setOnCompletionListener {
                stopVoiceSound()
            }
        }
        voiceMediaPlayer?.start()
    }
    public fun stopTickSound() {
        tickMediaPlayer?.release()
        tickMediaPlayer = null
    }

    public fun stopVoiceSound() {
        voiceMediaPlayer?.release()
        voiceMediaPlayer = null
    }
}