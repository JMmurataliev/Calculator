package com.example.calculator;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private int firstOperand, secondOperand;
    private boolean isOperationClick;
    private String operation = "";


    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.text_view);
    }

    public void onNumberCLick(View view) {

        String textButton = ((MaterialButton) view).getText().toString();

        animateButtonClick(view);

        if (textButton.equals("AC")) {
            textView.setText("0");
            firstOperand = 0;

        } else if (textView.getText().toString().equals("0") || isOperationClick) {
            textView.setText(textButton);
        } else {
            textView.append(textButton);
        }

        isOperationClick = false;
    }

    public void onOperationClick(View view) {
        Button button = (Button) view;
        animateButtonClick(view);
        if (view.getId() != R.id.btn_equal) {
            firstOperand = Integer.parseInt(textView.getText().toString());
            operation = button.getText().toString();
        } else {
            secondOperand = Integer.parseInt(textView.getText().toString());

            int result;

            switch (operation) {
                case "÷":
                    result = firstOperand / secondOperand;
                    break;
                case "x":
                    result = firstOperand * secondOperand;
                    break;
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                default:
                    result = firstOperand - secondOperand;
            }
            textView.setText(String.valueOf(result));

        }

        isOperationClick = true;
    }
    @SuppressLint("NewApi")
    private void animateButtonClick(View view) {
        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(view, "scaleX", 0.9f, 1f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(view, "scaleY", 0.9f, 1f);
        scaleDownX.setDuration(100);
        scaleDownY.setDuration(100);
        ObjectAnimator scalepUpX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.9f);
        ObjectAnimator scalepUpY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.9f);
        scalepUpX.setDuration(100);
        scalepUpY.setDuration(100);
        scaleDownX.start();
        scaleDownY.start();
        scalepUpX.start();
        scalepUpY.start();
    }


}