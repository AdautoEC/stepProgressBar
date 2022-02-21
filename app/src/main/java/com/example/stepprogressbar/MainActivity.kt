package com.example.stepprogressbar

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.Guideline
import androidx.core.content.res.ResourcesCompat
import com.example.stepprogressbar.databinding.ActivityMainBinding
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var stepProgressBar: StepProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        stepProgressBar = StepProgressBar(
            context = this,
            resources = resources,
            orientation = ConstraintLayout.LayoutParams.VERTICAL,
            stepsSize = 3,
            constraintLayout = binding.stepProgressBar.constraint,
            viewStart = binding.stepProgressBar.viewStart,
            viewEnd = binding.stepProgressBar.viewEnd,
            colorComplete = R.color.blue,
            colorUncomplete = R.color.black,
            colorBackgroudStep = R.color.white,
            backgroudStep = R.drawable.circle
        )

        binding.btnProximo.setOnClickListener {
            stepProgressBar.nextStep()
        }
        binding.btnAnterior.setOnClickListener {
            stepProgressBar.previousStep()
        }
    }
}