package com.zainco.task.presentation.advice


import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.zainco.task.R
import com.zainco.task.databinding.FragmentAdviceBinding
import kotlinx.android.synthetic.main.fragment_advice.*
import org.koin.android.viewmodel.ext.android.viewModel

class AdviceFragment : Fragment() {
    private val adviceViewModel: AdviceViewModel by viewModel()
    private lateinit var fragmentAdviceBinding: FragmentAdviceBinding

    companion object {
        val FRAGMENT_NAME = AdviceFragment::class.java.name
        @JvmStatic
        fun newInstance() =
            AdviceFragment().apply {
                arguments = Bundle()
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adviceViewModel.fetchAdvice()
        val timer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.d("millisUntilFinished", (millisUntilFinished / 1000).toString())
            }
            override fun onFinish() {
                Log.d("millisUntilFinished", "finished")
            }
        }
        timer.start()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAdviceBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_advice, container, false)
        fragmentAdviceBinding.adviceViewModel = adviceViewModel
        return fragmentAdviceBinding.root
    }

    override fun onStart() {
        super.onStart()
        adviceViewModel.isLoad.observe(this, Observer {
            it?.let { visibility ->
                fragmentAdviceBinding.adviceProgressBar.visibility =
                    if (visibility) View.GONE else View.VISIBLE
                fragmentAdviceBinding.adviceTv.visibility =
                    if (visibility) View.VISIBLE else View.GONE
            }
        })

        adviceViewModel.getAdviceLiveData().observe(this, Observer {
            advice_tv.text = it
        })
    }
}
