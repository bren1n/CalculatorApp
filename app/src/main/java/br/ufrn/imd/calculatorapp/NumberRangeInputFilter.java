package br.ufrn.imd.calculatorapp;

import android.text.InputFilter;
import android.text.Spanned;

public class NumberRangeInputFilter implements InputFilter {
    private int minValue;
    private int maxValue;

    public NumberRangeInputFilter(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {

        try {
            String newText = dest.toString().substring(0, dstart) +
                    source.toString().substring(start, end) +
                    dest.toString().substring(dend);

            double inputVal = Double.parseDouble(newText);

            if (inputVal >= minValue && inputVal <= maxValue) {
                return null;
            }
        } catch (NumberFormatException e) {}

        return "";
    }
}
