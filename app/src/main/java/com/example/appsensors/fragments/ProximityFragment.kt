package com.example.appsensors.fragments

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.appsensors.R
import com.example.appsensors.databinding.FragmentProximityBinding

class ProximityFragment : Fragment(), SensorEventListener {

    lateinit var b: FragmentProximityBinding
    lateinit var sensorManager: SensorManager;
    var sensor: Sensor? = null;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_proximity, container, false)

        setHasOptionsMenu(true);
        setSensorStuff();

        return b.root;
    }

    fun setSensorStuff(){
        sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor?.type == Sensor.TYPE_PROXIMITY){
            val TAG = "Log_Proximidade"
            val distance = event.values[0]

            Log.i(TAG, "Distancia: $distance")

            b.txtProximity.text = "Distancia = ${distance.toInt()}";

        }
    }

    override fun onResume() {
        super.onResume()
        sensor?.also { proximity ->
            sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_FASTEST)
        }

    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this);
    }


}
