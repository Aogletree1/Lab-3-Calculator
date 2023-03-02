package com.example.cs408lab3;

public enum CalculatorFunction {
    ADD("Add"),
    SUBTRACT("Subtract"),
    MULTIPLY("Multiply"),
    DIVIDE("Divide"),
    SIGN("Sign"),
    SQRT("Sqrt"),
    PERCENT("Percent"),
    CLEAR("Clear"),
    EQUALS("Equals");

    private String message;

    private CalculatorFunction(String msg) {message = msg;}

    @Override
    public String toString() {return message;}

}
