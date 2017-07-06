package com.kotlin1

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

/**
 * 此处是主要设计到的是如何做点击的处理
 * 其次是when的用处
 * 还有中就是实现的点击的事件
 *  var是一个可变变量，这是一个可以通过重新分配来更改为另一个值的变量。这种声明变量的方式和Java中声明变量的方式一样
 *  val是一个只读变量，这种声明变量的方式相当于java中的final变量。一个val创建的时候必须初始化，因为以后不能被改变
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * 最原始的是设置点击事件
         * 每个都需要设置点击事件
         */
//        var btn1 = findViewById(R.id.button1) as Button
//        var btn2 = findViewById(R.id.button2) as Button
//        var btn3 = findViewById(R.id.button3) as Button
//
//        btn1.setOnClickListener {
//            val intent = Intent()
//            intent.setClass(this,TextTabActivity::class.java)
//            startActivity(intent)
//        }
//        btn2.setOnClickListener {
//            val intent2 = Intent();
//            intent2.setClass(this,CustomTabItemActivity::class.java)
//            startActivity(intent2)
//        }
//        btn3.setOnClickListener {
//            val intent3 = Intent()
//            intent3.setClass(this,ImageTabActivity::class.java)
//            startActivity(intent3)
//        }
    }

    /***
     * onClick需要在布局里面设置一下点击的事件 android:onClick="onClick"
     */
     fun onClick(view: View) {
        val id = view.id;
        when(id) {
            R.id.button1 -> {
                val intent = Intent()
                intent.setClass(this,TextTabActivity::class.java)
                startActivity(intent)
            }
            R.id.button2 -> {
                val intent2 = Intent();
                intent2.setClass(this,CustomTabItemActivity::class.java)
                startActivity(intent2)
            }
            R.id.button3 -> {
                val intent3 = Intent()
                intent3.setClass(this,ImageTabActivity::class.java)
                startActivity(intent3)
            }

        }
    }

 }
