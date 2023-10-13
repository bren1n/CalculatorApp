package br.ufrn.imd.calculatorapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class SimpleCalculatorFragment extends Fragment {

    Button n0, n1, n2, n3, n4, n5, n6, n7, n8, n9;
    Button plus, minus, times, divided_by, equals, del;

    String bufferText = "";

    TextView result;
    TextView operation;

    boolean needsBufferReset = false;
    public SimpleCalculatorFragment() {
        // Required empty public constructor
    }

    private void assignIdDigit(Button btn, int id, View view) {
        btn = view.findViewById(id);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (needsBufferReset) {
                    bufferText = "";
                    needsBufferReset = false;
                }

                Button button = (Button) v;
                bufferText += button.getText();
                operation.setText(bufferText);
            }
        });
    }

    private void assignIdDel(Button btn, int id, View view) {
        btn = view.findViewById(id);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                result.setText("");
                operation.setText("");
                bufferText = "";
            }
        });
    }

    private void assignIdEquals(Button btn, int id, View view) {
        btn = view.findViewById(id);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try {
                    Context context = Context.enter();
                    context.setOptimizationLevel(-1);
                    Scriptable scriptable = context.initStandardObjects();
                    String finalResult = context.evaluateString(scriptable, operation.getText().toString(), "Javascript", 1, null).toString();
                    result.setText(finalResult);

                    if (finalResult.equals("Infinity")) {
                        Toast.makeText(getActivity(), R.string.invalid_operation, Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), R.string.invalid_operation, Toast.LENGTH_LONG).show();
                }

                needsBufferReset = true;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_simple_calculator, container, false);

        result = view.findViewById(R.id.result);
        operation = view.findViewById(R.id.operation);

        assignIdDigit(n0, R.id.n0, view);
        assignIdDigit(n1, R.id.n1, view);
        assignIdDigit(n2, R.id.n2, view);
        assignIdDigit(n3, R.id.n3, view);
        assignIdDigit(n4, R.id.n4, view);
        assignIdDigit(n5, R.id.n5, view);
        assignIdDigit(n6, R.id.n6, view);
        assignIdDigit(n7, R.id.n7, view);
        assignIdDigit(n8, R.id.n8, view);
        assignIdDigit(n9, R.id.n9, view);

        assignIdDigit(plus, R.id.plus, view);
        assignIdDigit(minus, R.id.minus, view);
        assignIdDigit(times, R.id.times, view);
        assignIdDigit(divided_by, R.id.divide, view);

        assignIdDel(del, R.id.del, view);

        assignIdEquals(equals, R.id.equal, view);

        return view;
    }
}