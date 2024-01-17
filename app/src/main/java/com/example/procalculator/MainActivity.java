package com.example.procalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView resultTv , solutionTv;
    MaterialButton btnC , btnOpenBrac , btnCloseBrac;
    MaterialButton btnDiv, btnAdd , btnSub , btnMul , btnEqual;
    MaterialButton btn1 ,btn2 , btn3 , btn4 , btn5 , btn6 , btn7 , btn8 , btn9 , btn0;
    MaterialButton btnAc , btnDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);
        assignId(btnC , R.id.btn_c);
        assignId(btnOpenBrac , R.id.btn_open_brac);
        assignId(btnCloseBrac , R.id.btn_close_brac);
        assignId(btnDiv , R.id.btn_div);
        assignId(btnAdd , R.id.btn_add);
        assignId(btnSub , R.id.btn_minus);
        assignId(btnMul , R.id.btn_mul);
        assignId(btnEqual , R.id.btn_equals);
        assignId(btn1 , R.id.btn_1);
        assignId(btn2 , R.id.btn_2);
        assignId(btn3 , R.id.btn_3);
        assignId(btn4 , R.id.btn_4);
        assignId(btn5 , R.id.btn_5);
        assignId(btn6 , R.id.btn_6);
        assignId(btn7 , R.id.btn_7);
        assignId(btn8 , R.id.btn_8);
        assignId(btn9 , R.id.btn_9);
        assignId(btn0 , R.id.btn_0);
        assignId(btnAc , R.id.btn_ac);
        assignId(btnDot , R.id.btn_dot);

    }
    void assignId(MaterialButton btn , int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String Calculation = solutionTv.getText().toString();
        if (buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }if (buttonText.equals("C")){
            Calculation = Calculation.substring(0 , Calculation.length()-1);
        }else {
            Calculation = Calculation + buttonText;
        }
        String finalResult = getResult(Calculation);
        if (!finalResult.equals("Error")){
            resultTv.setText(finalResult);
        }

        solutionTv.setText(Calculation);

    }
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable , data , "Javascript" , 1 , null ).toString();
            return finalResult;
        }catch (Exception e){
            return "Error";
        }
    }
}