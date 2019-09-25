package com.zainco.task.presentation.advice


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.zainco.task.R
import kotlinx.android.synthetic.main.fragment_advice.*
import org.koin.android.viewmodel.ext.android.viewModel

class AdviceFragment : Fragment() {
    private val adviceViewModel: AdviceViewModel by viewModel()

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
        adviceViewModel.fetchAdvice()
        return inflater.inflate(R.layout.fragment_advice, container, false)
    }

    override fun onStart() {
        super.onStart()
        adviceViewModel.getAdviceLiveData().observe(this, Observer {
            tvAdvice.text = it
        })
    }
}
