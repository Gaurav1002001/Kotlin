package com.example.kotlinconcepts.ui

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.MediaController
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinconcepts.R
import com.example.kotlinconcepts.databinding.ActivityMediaBinding

class MediaPage : AppCompatActivity() {

    private lateinit var binding: ActivityMediaBinding
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var runnable: Runnable
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMediaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mediaController = MediaController(this)
        handler = Handler(Looper.getMainLooper())

        binding.apply {

            mediaController.setAnchorView(videoView)
            val uri: Uri = Uri.parse("android.resource://"+packageName+"/"+ R.raw.mountains)
            videoView.setMediaController(mediaController)
            videoView.setVideoURI(uri)
            videoView.requestFocus()
            videoView.start()

            icPlayBtn.setOnClickListener {
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(this@MediaPage, R.raw.tune)
                    val totalTimeInSec = mediaPlayer!!.duration / 1000
                    tvTotalTime.text = "${totalTimeInSec / 60}:${totalTimeInSec % 60}"
                    initializeSeekbar()
                }

                mediaPlayer?.start()
            }

            icPauseBtn.setOnClickListener {
                mediaPlayer?.pause()
            }

            icStopBtn.setOnClickListener {
                mediaPlayer?.stop()
                mediaPlayer?.reset()
                mediaPlayer?.release()
                mediaPlayer = null
            }
        }
    }

    private fun initializeSeekbar() {

        binding.apply {
            seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) mediaPlayer?.seekTo(progress)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            })

            seekBar.max = mediaPlayer!!.duration

            runnable = Runnable {
                val currentTimeInSec = mediaPlayer!!.currentPosition / 1000
                tvCurrentTime.text = "${currentTimeInSec / 60}:${currentTimeInSec % 60}"

                seekBar.progress = mediaPlayer!!.currentPosition
                handler.postDelayed(runnable, 1000)
            }

            handler.postDelayed(runnable, 1000)
        }
    }
}