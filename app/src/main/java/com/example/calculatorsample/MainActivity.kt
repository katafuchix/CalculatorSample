package com.example.calculatorsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            // BackStackを設定
            fragmentTransaction.addToBackStack(null)

            // MainFragmentのインスタンスを生成
            val fragment = MainFragment.newInstance()

            // idがcontainerの領域にフラグメントを表示
            fragmentTransaction.replace(R.id.container, fragment)
            fragmentTransaction.commit()
        }
    }
}
