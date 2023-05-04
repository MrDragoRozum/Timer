package com.example.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.timer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var second = 0
        val timer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                second = millisUntilFinished.toInt() / 1000
                second++
                binding.textViewTimer.text = "$second"
            }

            override fun onFinish() {
                Toast.makeText(
                    this@MainActivity,
                    R.string.timer_worked,
                    Toast.LENGTH_SHORT
                ).show()
                binding.textViewTimer.text = "0"
            }
        }
        listeners(second, timer)
    }

    private fun listeners(second: Int, timer: CountDownTimer) {
        binding.buttonStart.setOnClickListener {
            timer.start()
        }
        binding.buttonStop.setOnClickListener {
            timer.cancel()
        }
        binding.buttonRestart.setOnClickListener {
            timer.cancel()
            timer.start()
        }
    }
}