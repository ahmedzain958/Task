package com.zainco.task.presentation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zainco.task.R

class AdviceFragment : Fragment() {
    companion object {
        val FRAGMENT_NAME = AdviceFragment::class.java.name
        @JvmStatic
        fun newInstance() =
            AdviceFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_advice, container, false)
    }


}
