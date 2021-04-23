package com.example.appsensors.fragments

import android.content.Context.SENSOR_SERVICE
import android.graphics.Color
import android.hardware.*
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.appsensors.R
import com.example.appsensors.databinding.FragmentAccelerometerBinding

class AccelerometerFragment : Fragment(), SensorEventListener  {

    lateinit var b: FragmentAccelerometerBinding
    private lateinit var sensorManager: SensorManager
    var sensor: Sensor? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_accelerometer, container, false)

        setUpSensorStuff()
        return b.root
    }

    private fun setUpSensorStuff() {
        sensorManager = activity?.getSystemService(SENSOR_SERVICE) as SensorManager

        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also { accelerometer ->
            sensorManager.registerListener(
                this,
                accelerometer,
                SensorManager.SENSOR_DELAY_FASTEST,
                SensorManager.SENSOR_DELAY_FASTEST
            )
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {

            val sides = event.values[0]
            val upDown = event.values[1]

            b.tvSquare.apply {
                rotationX = upDown * 3f
                rotationY = sides * 3f
                rotation = -sides
                translationX = sides * -10
                translationY = upDown * 10
            }

            val color = if (upDown.toInt() == 0 && sides.toInt() == 0) Color.rgb(24,255,255) else Color.WHITE
            b.tvSquare.setBackgroundColor(color)

            b.tvSquare.text = "Subir/Descer: ${upDown.toInt()}\nEsquerda/Direita ${sides.toInt()}"

        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this);
    }


}