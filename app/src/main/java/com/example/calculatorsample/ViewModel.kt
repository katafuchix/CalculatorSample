package com.example.calculatorsample

import androidx.databinding.ObservableField
import androidx.lifecycle.*
import java.text.DecimalFormat
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.rxkotlin.Observables

class MainViewModel: ViewModel() {

    // price監視
    val priceSubject = BehaviorSubject.create<String>()

    // 結果TextViewに表示用
    val priceValueField: ObservableField<String> = ObservableField()

    // EditTextと結合するString  値が入ったらpriceSubjectに流す
    var price: String = ""
        set(value) {
            priceSubject.onNext(value)
        }

    // 左入力欄
    var leftOperand : String  = ""
        set(value) {
            leftOperandSubject.onNext(value)
        }
    // 左入力欄の値を流す
    private val leftOperandSubject  = BehaviorSubject.create<String>()

    // 右入力欄
    var rightOperand : String = ""
        set(value) {
            rightOperandSubject.onNext(value)
        }
    // 右入力欄の値を流す
    private val rightOperandSubject = BehaviorSubject.create<String>()

    // 左右の合計値をTextViewに表示するための変数
    val plusValueField: ObservableField<String> = ObservableField()

    init {

        // priceSubjectに流れてきた値をpriceValueFieldにセットして画面に表示
        priceSubject.subscribe {
            val valuel = if (it?.toString()?.isNotBlank()  == true) it.toString().toInt() else 0
            val value =  DecimalFormat("#,###").format(valuel)
            priceValueField.set(value)
        }

        // combine系はRxKotlinの方が簡単に書ける & 型推論が効く
        // 左右の入力欄の合計を表示
        Observables.combineLatest(leftOperandSubject, rightOperandSubject)
            {l , r ->
                val valuel = if (l?.toString()?.isNotBlank()  == true) l.toString().toInt() else 0
                val valuer = if (r?.toString()?.isNotBlank()  == true) r.toString().toInt() else 0
                valuel + valuer
            }
            .subscribe({
                plusValueField.set(it.toString())
            })

    }
}