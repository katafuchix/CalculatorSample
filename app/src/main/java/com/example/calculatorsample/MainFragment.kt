package com.example.calculatorsample

import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.calculatorsample.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // DataBidingを行う場合はこうする
        activity?.let {
            viewModel = ViewModelProviders.of(
                this
            ).get(MainViewModel::class.java)
        }

        binding = DataBindingUtil.inflate(
                        inflater,
                        R.layout.fragment_main,
                        container, false)
        // xmlで定義したもの
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // インスタンス生成
    // 他クラスから利用する場合にこのメソッドを呼ぶ
    companion object {
        fun newInstance(): MainFragment {
            // Fragemnt01 インスタンス生成
            val fragment = MainFragment()

            return fragment
        }
    }
}
