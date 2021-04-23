package com.example.appsensors.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.appsensors.R
import com.example.appsensors.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var b : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?): View? {

        b = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false)

        b.btnAccelerometer.setOnClickListener {
            Navigation.findNavController(it).navigate(HomeFragmentDirections.actionHomeFragmentToAccelerometerFragment())
        }

        b.btnProximity.setOnClickListener {
            Navigation.findNavController(it).navigate(HomeFragmentDirections.actionHomeFragmentToProximityFragment())
        }
        b.btnLight.setOnClickListener {
            Navigation.findNavController(it).navigate(HomeFragmentDirections.actionHomeFragmentToLightFragment())

        }
        b.btnGyroscope.setOnClickListener {
            Navigation.findNavController(it).navigate(HomeFragmentDirections.actionHomeFragmentToGyroscopeFragment())

        }

        setHasOptionsMenu(true);
        return b.root
    }

}