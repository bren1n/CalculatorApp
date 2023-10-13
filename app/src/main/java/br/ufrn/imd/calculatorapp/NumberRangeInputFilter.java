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
            // Concatena o texto atual com o texto de origem (source)
            String newText = dest.toString().substring(0, dstart) +
                    source.toString().substring(start, end) +
                    dest.toString().substring(dend);

            // Converte o texto para um número decimal
            double inputVal = Double.parseDouble(newText);

            // Verifica se o número está dentro do intervalo desejado
            if (inputVal >= minValue && inputVal <= maxValue) {
                return null; // Aceita o valor inserido
            }
        } catch (NumberFormatException e) {
            // A entrada não é um número válido, não faz nada
        }

        // Rejeita a entrada se estiver fora do intervalo ou não for um número válido
        return "";
    }
}
