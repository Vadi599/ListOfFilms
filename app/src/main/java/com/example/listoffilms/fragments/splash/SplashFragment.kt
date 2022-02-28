package com.example.listoffilms.fragments.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.listoffilms.R
import com.example.listoffilms.databinding.FragmentSplashBinding
import com.example.listoffilms.fragments.films.FilmsFragment

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(
            inflater, container, false
        )
        // Inflate the layout for this fragment
        val view = binding.root
        binding.rootSplash.run {
            Handler(Looper.getMainLooper()).postDelayed({
                fragmentManager
                    ?.beginTransaction()
                    ?.replace(
                        R.id.container,
                        FilmsFragment()
                    )
                    ?.commit()
            }, 2000L)
        }
        return view
    }
}