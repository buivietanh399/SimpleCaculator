package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.calculator.R

class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val str = "+-x/"
        val CE: Button = findViewById(R.id.btCE)
        val C: Button = findViewById(R.id.btC)
        val BS: Button = findViewById(R.id.btBS)
        val bt0: Button = findViewById(R.id.bt0)
        val bt1: Button = findViewById(R.id.bt1)
        val bt2: Button = findViewById(R.id.bt2)
        val bt3: Button = findViewById(R.id.bt3)
        val bt4: Button = findViewById(R.id.bt4)
        val bt5: Button = findViewById(R.id.bt5)
        val bt6: Button = findViewById(R.id.bt6)
        val bt7: Button = findViewById(R.id.bt7)
        val bt8: Button = findViewById(R.id.bt8)
        val bt9: Button = findViewById(R.id.bt9)
        val btNp: Button = findViewById(R.id.btNp)
        val btPlus: Button = findViewById(R.id.btPlus)
        val btMinus: Button = findViewById(R.id.btMinus)
        val btDiv: Button = findViewById(R.id.btDiv)
        val btMulti: Button = findViewById(R.id.btMul)
        val btSum: Button = findViewById(R.id.btSum)
        val text = StringBuilder()
        val textview: TextView = findViewById(R.id.textCalculator)
        CE.setOnClickListener {
            text.clear()
            textview.setText("0")
        }
        C.setOnClickListener {
            if (text.isNotEmpty()) {
                for (k in text.length - 1 downTo 0) {
                    if (str.contains(text.get(k))) {
                        text.delete(k, text.length)
                        textview.setText(text.toString())
                        break
                    }
                }
            }
        }
        BS.setOnClickListener {
            if (text.isNotEmpty()) {
                text.deleteCharAt(text.length - 1)
                if (text.isNotEmpty()) {
                    textview.text = text.toString()
                } else textview.text = "0"

            }
        }
        bt0.setOnClickListener {
            clickButton('0',text,textview)
        }
        bt1.setOnClickListener {
            clickButton('1',text,textview)
        }
        bt2.setOnClickListener {
            clickButton('2',text,textview)
        }
        bt3.setOnClickListener {
            clickButton('3',text,textview)
        }
        bt4.setOnClickListener {
            clickButton('4',text,textview)
        }
        bt5.setOnClickListener {
            clickButton('5',text,textview)
        }
        bt6.setOnClickListener {
            clickButton('6',text,textview)
        }
        bt7.setOnClickListener {
            clickButton('7',text,textview)
        }
        bt8.setOnClickListener {
            clickButton('8',text,textview)
        }
        bt9.setOnClickListener {
            clickButton('9',text,textview)
        }
        btNp.setOnClickListener {
            if (text.isNotEmpty()) {
                var parts = text.toString()
                    .split(Regex("(?<=\\+)|(?=\\+)|(?<=-)|(?=-)|(?<=x)|(?=x)|(?<=/)|(?=/)"))
                    .filter { it.isNotEmpty() }
                if (parts.size == 2) {
                    if (parts[0] == "+") {
                        text.setCharAt(0, '-')
                        textview.text = text.toString()
                    }
                    if (parts[0] == "-") {
                        text.setCharAt(0, '+')
                        textview.text = text.toString()
                    }
                }
                if (parts.size == 1) {
                    text.insert(0, '+')
                    textview.text = text.toString()
                }
            }
        }
        btPlus.setOnClickListener {
            if (text.isNotEmpty()) {
                if (!str.contains(text.last())) {
                    text.append("+")
                    textview.text = text.toString()
                } else {
                    text.setCharAt(text.length - 1, '+')
                    textview.text = text.toString()
                }
            }
        }
        btDiv.setOnClickListener {
            if (text.isNotEmpty()) {
                if (!str.contains(text.last())) {
                    text.append("/")
                    textview.text = text.toString()
                } else {
                    text.setCharAt(text.length - 1, '/')
                    textview.text = text.toString()
                }
            }
        }
        btMinus.setOnClickListener {
            if (text.isNotEmpty()) {
                if (!str.contains(text.last())) {
                    text.append("-")
                    textview.text = text.toString()
                } else {
                    text.setCharAt(text.length - 1, '-')
                    textview.text = text.toString()
                }
            }
        }
        btMulti.setOnClickListener {
            if (text.isNotEmpty()) {
                if (!str.contains(text.last())) {
                    text.append("x")
                    textview.text = text.toString()
                } else {
                    text.setCharAt(text.length - 1, 'x')
                }
            }
        }
        btSum.setOnClickListener {
            if (text.isNotEmpty() && text.toString().length > 1 && !str.contains(text.last())) {
                var parts = text.toString()
                    .split(Regex("(?<=\\+)|(?=\\+)|(?<=-)|(?=-)|(?<=x)|(?=x)|(?<=/)|(?=/)"))
                    .filter { it.isNotEmpty() }
                var i = 0
                while (i < parts.size) {
                    when (parts[i]) {
                        "/", "x" -> {
                            val operator = parts[i].single()
                            val num1 = parts[i - 1].toLong()
                            val num2 = parts[i + 1].toLong()
                            if (operator == '/' && num2 == 0L) {
                                text.clear()
                                textview.setText("N/A")
                                return@setOnClickListener
                            }
                            val result = when (operator) {
                                'x' -> num1 * num2
                                '/' -> num1 / num2
                                else -> throw IllegalAccessError("Invalid operator")
                            }
                            if (i + 2 < parts.size) {
                                parts = parts.subList(
                                    0,
                                    i - 1
                                ) + listOf(result.toString()) + parts.subList(i + 2, parts.size)
                            } else {
                                parts = parts.subList(0, i - 1) + listOf(result.toString())
                            }
                            for (hehe in parts) Log.e("loi1", hehe);
                        }

                        else -> {
                            i++
                        }
                    }
                }
                var sum: Long = 0
                var j = 0
                while (j < parts.size) {
                    when (parts[j]) {
                        "+" -> {
                            sum += parts[++j].toLong()
                            j++
                        }

                        "-" -> {
                            sum -= parts[++j].toLong()
                        }

                        else -> sum += parts[j].toLong()
                    }
                    j += 1
                }
                text.clear()
                text.append(sum)
                textview.setText(text.toString())
            }
        }


    }
    fun clickButton(char: Char,text: StringBuilder,textview: TextView) {
        if (text.isNotEmpty()) {
            if (text.get(0) != '0') {
                text.append(char)
                textview.text = text.toString()
            } else {
                text.setCharAt(0, char)
                textview.text = text.toString()
            }
        }else{
            text.append(char)
            textview.text = text.toString()
        }
    }
}