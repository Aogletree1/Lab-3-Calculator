package com.example.cs408lab3;

import static com.example.cs408lab3.CalculatorFunction.CLEAR;
import static com.example.cs408lab3.CalculatorFunction.SIGN;

import android.util.Log;

import java.math.BigDecimal;
import java.math.MathContext;

public class CalculatorModel extends AbstractModel{

    public static final String TAG = "CalculatorModel";

    private final String START_VALUE = "0";
    private final Character DECIMAL_POINT = '.';
    private final Character SIGN_SWITCH = '±';

    private boolean hasDecimalPoint = false;
    private boolean hasSignSwitch = false;

    private StringBuilder screen;

    private BigDecimal lhs, rhs;

    private CalculatorState state;

    private CalculatorFunction operator;

    public CalculatorModel() {
        screen = new StringBuilder();

    }

    public void calcStart(){
        state = CalculatorState.CLEAR;
        lhs = rhs = null;
        operator = null;
        hasDecimalPoint = false;
        setScreen("0");

        state = CalculatorState.LHS;

    }

    public void setKey(Character key) {
        if (state.equals(CalculatorState.CLEAR)){
            changeState(CalculatorState.LHS);
            setScreen("0");
        }

        if (state.equals(CalculatorState.OP_SELECTED)) {
            changeState(CalculatorState.RHS);
            setScreen("0");

        }

        if (state.equals(CalculatorState.LHS) || state.equals(CalculatorState.RHS)) {
            if (key == DECIMAL_POINT) {
                if (!hasDecimalPoint) {
                    appendDigit(key);
                    hasDecimalPoint = true;
                }


                if (key == SIGN_SWITCH){
                    if(!hasSignSwitch){
                    lhs.negate();
                    rhs.negate();
                    appendSign(lhs);
                    hasSignSwitch = true;
                }}

            }



            else {
                if (screen.toString().equals(START_VALUE)) {
                    screen.setLength(0);
                }
                appendDigit(key.charValue());
            }
        }
    }

    public void setFunction(CalculatorFunction function) {
        Log.i(TAG, "Function Change: " + function.toString());

        try {
            switch (function) {
                case ADD: case SUBTRACT: case MULTIPLY: case DIVIDE: case EQUALS: case SQRT:
                case PERCENT:
                    changeState(CalculatorState.OP_SELECTED);
                    operator = function;
                    break;

                case CLEAR:
                    calcStart();


            }
        }
        catch (Exception e) {
            e.toString();

        }

    }

    private void setScreen(String newText){
        String oldText = screen.toString();
        screen.setLength(0);
        screen.append(newText);

        Log.i(TAG, "Screen Change: " + newText);

        firePropertyChange(CalculatorController.SCREEN_PROPERTY, oldText, newText);

    }

    private void changeState(CalculatorState newState) {
        if (newState.equals(CalculatorState.CLEAR)){
            calcStart();

        }
        else {
            switch (state) {
                case LHS:
                    lhs = new BigDecimal(screen.toString());
                    rhs = null;
                    break;

                case RHS:
                    rhs = new BigDecimal(screen.toString());
                    evaluate();
                    break;

            }

        }

        state = newState;
    }

    private void evaluate() {
        BigDecimal result = new BigDecimal("0");
        if (lhs == null) {
            lhs = result;
        }
        if (rhs == null) {
            rhs = lhs;
        }

        if (operator != null) {
            switch (operator) {
                case ADD:
                    result = lhs.add(rhs);
                    break;
                case SUBTRACT:
                    result = lhs.subtract(rhs);
                    break;
                case MULTIPLY:
                    result = lhs.multiply(rhs);
                    break;
                case DIVIDE:
                    result = lhs.divide(rhs);
                    break;
                case SQRT:
                    lhs.doubleValue();
                    result = lhs.pow(1/2);
                    break;
                case SIGN:
                    result = lhs.negate();
                    break;
                case PERCENT:
                    //Load lhs into percent.
                    BigDecimal percent = null;
                    percent = lhs;
                    BigDecimal divisor = new BigDecimal(100);

                    //Make rhs divided by 100.
                    rhs.divide(divisor);

                    //Multiply the rhs by the lhs to get the percent.
                    percent.multiply(rhs);
                    result = percent;
                    break;

            }

            lhs = result;
            rhs = null;
            setScreen(result.toString());

        }

    }

    public void appendDigit(char digit) {

        String oldText = screen.toString();
        screen.append(digit);
        String newText = screen.toString();

        Log.i(TAG, "Screen Change: " + digit);

        firePropertyChange(CalculatorController.SCREEN_PROPERTY, oldText, newText);

    }

    public void appendSign(BigDecimal r) {

        String oldText = screen.toString();
        screen.append(r);
        String newText = screen.toString();

        Log.i(TAG, "Screen Change: " + r);

        firePropertyChange(CalculatorController.SCREEN_PROPERTY, oldText, newText);

    }


    private enum CalculatorState {
        CLEAR, LHS, OP_SELECTED, RHS, RESULT, ERROR

    }
}
