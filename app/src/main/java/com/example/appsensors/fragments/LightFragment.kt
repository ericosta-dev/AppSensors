package com.example.appsensors.fragments

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.appsensors.R
import com.example.appsensors.databinding.FragmentLightBinding

class LightFragment : Fragment(), SensorEventListener {
    lateinit var sensorManager: SensorManager
    var lights: Sensor? = null

    lateinit var b : FragmentLightBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_light, container, false)

        setHasOptionsMenu(true);

        setSensorStuff()

        return b.root
    }

    private fun setSensorStuff(){
        sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lights = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }


    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
            val TAG = "Log_Luz"
            val light = event.values[0]
            Log.i(TAG, "Nivel Luz: $light")

            b.txtNivel.text = "Nivel: $light"
        }
    }


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, lights, SensorManager.SENSOR_DELAY_FASTEST)
    }


    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

}