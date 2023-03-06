package com.example.cs408lab3;

import java.util.HashMap;

public class CalculatorController extends AbstractController
{
    public static final String KEY_PROPERTY = "Key";

    public static final String SCREEN_PROPERTY = "Screen";

    public static final String FUNCTION_PROPERTY = "Function";

    private final HashMap<String, Character> keyMap;

    private final HashMap<String, CalculatorFunction> functionMap;

    public CalculatorController() {
        super();

        keyMap = createKeyMap();
        functionMap = createFunctionMap();

    }



    public void changeFunction(CalculatorFunction newFunction) {
        setModelProperty(FUNCTION_PROPERTY, newFunction);

    }

    public void changeScreen(String newText) {setModelProperty(SCREEN_PROPERTY, newText);}

    public void processInput(String tag) {
        switch (tag) {
            case "btnPlus": case "btnClear": case "btnDivide":
            case "btnEquals": case "btnMultiply": case "btnMinus":
            case "btnPercent": case "btnSrt": case "btnSignSwitch":

                changeFunction(functionMap.get(tag));
                break;

            default:

                changeKey (keyMap.get(tag));
                break;

        }

    }

    public void changeKey(Character newText) {

        setModelProperty(KEY_PROPERTY, newText);

    }

    private HashMap createKeyMap() {
        HashMap<String, Character> map = new HashMap<>();

        map.put("btnZero", '0');
        map.put("btnOne", '1');
        map.put("btnTwo", '2');
        map.put("btnThree", '3');
        map.put("btnFour", '4');
        map.put("btnFive", '5');
        map.put("btnSix", '6');
        map.put("btnSeven", '7');
        map.put("btnEight", '8');
        map.put("btnNine", '9');
        map.put("btnDecimal", '.');

        return map;

    }

    private HashMap createFunctionMap() {
        HashMap<String, CalculatorFunction> map = new HashMap<>();

        map.put("btnPlus", CalculatorFunction.ADD);
        map.put("btnDivide", CalculatorFunction.DIVIDE);
        map.put("btnEquals", CalculatorFunction.EQUALS);
        map.put("btnMultiply", CalculatorFunction.MULTIPLY);
        map.put("btnPercent", CalculatorFunction.PERCENT);
        map.put("btnSignSwitch", CalculatorFunction.SIGN);
        map.put("btnClear", CalculatorFunction.CLEAR);
        map.put("btnSrt", CalculatorFunction.SQRT);
        map.put("btnMinus", CalculatorFunction.SUBTRACT);

        return map;

    }

}
