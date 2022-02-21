package com.example.stepprogressbar

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.Guideline
import androidx.core.content.res.ResourcesCompat
import kotlin.properties.Delegates

class StepProgressBar(
    private var context: Context,
    private var resources: Resources,
    private var orientation: Int,
    private var stepsSize: Int,
    private var constraintLayout: ConstraintLayout,
    private var viewStart: View,
    private var viewEnd: View,
    private var colorComplete: Int,
    private var colorUncomplete: Int,
    private var colorBackgroudStep: Int,
    private var backgroudStep: Int
) {
    private var currentStep = 0
    private var completedProgeress = false
    private lateinit var steps: ArrayList<ImageView>
    private lateinit var stepsInternals: ArrayList<ImageView>
    private lateinit var guideLineIds: ArrayList<Int>

    init{
        setGuidelines()
        setSteps()
        setStepsIntenals()
        setStepBar(currentStep)
        steps[currentStep].setColorFilter(ResourcesCompat.getColor(resources, colorComplete, null))
    }

    private fun getNewGuideline(): Guideline {
        val guideline = Guideline(context)
        guideline.id = Guideline.generateViewId()
        val lp = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        lp.orientation = orientation
        guideline.layoutParams = lp
        return guideline
    }

    private fun  setGuidelines(){
        var guideline: Guideline?
        val listGuidelineId: ArrayList<Int> = arrayListOf()
        for(i in 1 until stepsSize){
            guideline = getNewGuideline()
            constraintLayout.addView(guideline)
            guideline.setGuidelinePercent(i.toFloat().div(stepsSize))
            listGuidelineId.add(guideline.id)
        }
        this.guideLineIds = listGuidelineId
    }

    private fun setSteps(){
        var step = ImageView(context)
        var set = ConstraintSet()
        val listSteps: ArrayList<ImageView> = arrayListOf()
        step.setImageDrawable(ResourcesCompat.getDrawable(resources, backgroudStep, null))
        step.setColorFilter(ResourcesCompat.getColor(resources, colorComplete, null))
        step.minimumWidth = 30
        step.minimumHeight = 30
        step.translationZ = 90F
        step.id = View.generateViewId()
        constraintLayout.addView(step, 0)
        set.clone(constraintLayout)
        set.connect(step.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0)
        set.connect(step.id, ConstraintSet.END, guideLineIds[0], ConstraintSet.END, 0)
        set.connect(step.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
        set.connect(step.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
        set.applyTo(constraintLayout)
        listSteps.add(step)

        for (i in 1 until stepsSize-1){
            step = ImageView(context)
            step.setImageDrawable(ResourcesCompat.getDrawable(resources, backgroudStep, null))
            step.minimumWidth = 30
            step.minimumHeight = 30
            step.translationZ = 90F
            step.id = View.generateViewId()
            constraintLayout.addView(step, 0)
            set = ConstraintSet()
            set.clone(constraintLayout)
            set.connect(step.id, ConstraintSet.START, guideLineIds[i-1], ConstraintSet.START, 0)
            set.connect(step.id, ConstraintSet.END, guideLineIds[i], ConstraintSet.END, 0)
            set.connect(step.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
            set.connect(step.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
            set.applyTo(constraintLayout)
            listSteps.add(step)
        }

        step = ImageView(context)
        step.setImageDrawable(ResourcesCompat.getDrawable(resources, backgroudStep, null))
        step.minimumWidth = 30
        step.minimumHeight = 30
        step.translationZ = 90F
        step.id = View.generateViewId()
        constraintLayout.addView(step, 0)
        set = ConstraintSet()
        set.clone(constraintLayout)
        set.connect(step.id, ConstraintSet.START, guideLineIds[stepsSize-2], ConstraintSet.START, 0)
        set.connect(step.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)
        set.connect(step.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
        set.connect(step.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
        set.applyTo(constraintLayout)
        listSteps.add(step)

        this.steps = listSteps
    }

    private fun setStepsIntenals(){
        var step = ImageView(context)
        var set = ConstraintSet()
        val listSteps: ArrayList<ImageView> = arrayListOf()
        step.setImageDrawable(ResourcesCompat.getDrawable(resources, backgroudStep, null))
        step.setColorFilter(ResourcesCompat.getColor(resources, colorBackgroudStep, null))
        step.minimumWidth = 15
        step.minimumHeight = 15
        step.translationZ = 100F
        step.id = View.generateViewId()
        constraintLayout.addView(step, 0)
        set.clone(constraintLayout)
        set.connect(step.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0)
        set.connect(step.id, ConstraintSet.END, guideLineIds[0], ConstraintSet.END, 0)
        set.connect(step.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
        set.connect(step.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
        set.applyTo(constraintLayout)
        listSteps.add(step)

        for (i in 1 until stepsSize-1){
            step = ImageView(context)
            step.setImageDrawable(ResourcesCompat.getDrawable(resources, backgroudStep, null))
            step.minimumWidth = 15
            step.minimumHeight = 15
            step.translationZ = 100F
            step.id = View.generateViewId()
            constraintLayout.addView(step, 0)
            set = ConstraintSet()
            set.clone(constraintLayout)
            set.connect(step.id, ConstraintSet.START, guideLineIds[i-1], ConstraintSet.START, 0)
            set.connect(step.id, ConstraintSet.END, guideLineIds[i], ConstraintSet.END, 0)
            set.connect(step.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
            set.connect(step.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
            set.applyTo(constraintLayout)
            listSteps.add(step)
        }

        step = ImageView(context)
        step.setImageDrawable(ResourcesCompat.getDrawable(resources, backgroudStep, null))
        step.minimumWidth = 15
        step.minimumHeight = 15
        step.translationZ = 100F
        step.id = View.generateViewId()
        constraintLayout.addView(step, 0)
        set = ConstraintSet()
        set.clone(constraintLayout)
        set.connect(step.id, ConstraintSet.START, guideLineIds[stepsSize-2], ConstraintSet.START, 0)
        set.connect(step.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)
        set.connect(step.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
        set.connect(step.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
        set.applyTo(constraintLayout)
        listSteps.add(step)

        this.stepsInternals = listSteps
    }

    private fun setStepBar(step: Int){
        var stepBar = viewStart
        var set = ConstraintSet()
        val listStepsId: ArrayList<View> = arrayListOf()

        set.clone(constraintLayout)
        set.connect(stepBar.id, ConstraintSet.END, steps[step].id, ConstraintSet.END, 0)
        set.applyTo(constraintLayout)
        listStepsId.add(stepBar)

        stepBar = viewEnd
        set = ConstraintSet()

        set.clone(constraintLayout)
        set.connect(stepBar.id, ConstraintSet.START, steps[step].id, ConstraintSet.START, 0)
        set.applyTo(constraintLayout)
        listStepsId.add(stepBar)
    }

    private fun setCompletStepBar(layout: ConstraintLayout,){
        val stepBar = viewStart
        val set = ConstraintSet()
        val listStepsId: ArrayList<View> = arrayListOf()

        set.clone(layout)
        set.connect(stepBar.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)
        set.applyTo(layout)
        listStepsId.add(stepBar)

        viewEnd.visibility = View.GONE
        completedProgeress = true
    }

    fun nextStep(){
        if(currentStep < stepsSize-1){
            steps[currentStep].setColorFilter(ResourcesCompat.getColor(resources, colorComplete, null))
            stepsInternals[currentStep].setColorFilter(ResourcesCompat.getColor(resources, colorComplete, null))
            currentStep +=1
            setStepBar(currentStep)
            steps[currentStep].setColorFilter(ResourcesCompat.getColor(resources, colorComplete, null))
            stepsInternals[currentStep].setColorFilter(ResourcesCompat.getColor(resources, colorBackgroudStep, null))
        }
        else{
            completProgeress()
        }
    }

    fun previousStep(){
        if(currentStep-1>=0 && !completedProgeress){
            steps[currentStep].setColorFilter(ResourcesCompat.getColor(resources, colorUncomplete, null))
            stepsInternals[currentStep].setColorFilter(ResourcesCompat.getColor(resources, colorUncomplete, null))
            currentStep -=1
            setStepBar(currentStep)
            steps[currentStep].setColorFilter(ResourcesCompat.getColor(resources, colorComplete, null))
            stepsInternals[currentStep].setColorFilter(ResourcesCompat.getColor(resources, colorBackgroudStep, null))
        }
    }

    private fun completProgeress(){
        setCompletStepBar(constraintLayout)
        steps[currentStep].setColorFilter(ResourcesCompat.getColor(resources, colorComplete, null))
        stepsInternals[currentStep].setColorFilter(ResourcesCompat.getColor(resources, colorComplete, null))
    }
}