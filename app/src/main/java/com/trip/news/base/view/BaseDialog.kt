package com.trip.news.base.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.trip.news.R
import com.trip.news.databinding.LayoutDialogBinding

class BaseDialog(
    context: Context,
    private val contents:String
) : Dialog(context) {
    lateinit var binding : LayoutDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_dialog,null,false)
        setContentView(binding.root)
        settingDialog()
        settingLayout()
    }

    private fun settingLayout() {
        //window 세팅
        window?.apply {
            setGravity(Gravity.CENTER)
            attributes.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
            attributes.dimAmount = 0.8f

            // Dialog 사이즈 조절 하기
//            attributes.width = WindowManager.LayoutParams.WRAP_CONTENT
//            attributes.height = WindowManager.LayoutParams.WRAP_CONTENT
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    private fun settingDialog() {
        setCancelable(true)
        binding.contents = contents
    }
}