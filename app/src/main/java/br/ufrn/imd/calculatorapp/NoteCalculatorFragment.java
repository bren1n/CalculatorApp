package br.ufrn.imd.calculatorapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class NoteCalculatorFragment extends Fragment {

    public NoteCalculatorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_note_calculator, container, false);

        EditText n1_input = view.findViewById(R.id.note1);
        EditText n2_input = view.findViewById(R.id.note2);
        EditText n3_input = view.findViewById(R.id.note3);

        int minValue = 0;
        int maxValue = 10;

        n1_input.setFilters(new InputFilter[]{new NumberRangeInputFilter(minValue, maxValue)});
        n2_input.setFilters(new InputFilter[]{new NumberRangeInputFilter(minValue, maxValue)});
        n3_input.setFilters(new InputFilter[]{new NumberRangeInputFilter(minValue, maxValue)});

        Button note_btn = (Button) view.findViewById(R.id.note_btn);
        note_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showAvg(view);
            }
        });

        return view;
    }

    private String getAvgResults(String n1, String n2, String n3) {
        float approved_by_avg;
        float approved_by_note;
        String result_text;

        if (!TextUtils.isEmpty(n1) && TextUtils.isEmpty(n2) && TextUtils.isEmpty(n3)) {
            approved_by_avg = (21 - Float.parseFloat(n1)) / 2;
            approved_by_note = (15 - Float.parseFloat(n1)) / 2;

            result_text = String.format(getString(R.string.note_result1) + "\n - %.1f " +
                    getString(R.string.note_result2_by_note) + "\n - %.1f " +
                    getString(R.string.note_result2_by_avg), approved_by_note, approved_by_avg);

        } else if (!TextUtils.isEmpty(n1) && !TextUtils.isEmpty(n2) && TextUtils.isEmpty(n3)) {
            approved_by_avg = 21 - Float.parseFloat(n1) - Float.parseFloat(n2);
            approved_by_note = 15 - Float.parseFloat(n1) - Float.parseFloat(n2);

            result_text = String.format(getString(R.string.note_result1) + "\n - %.1f " +
                    getString(R.string.note_result3_by_note) + "\n - %.1f " +
                    getString(R.string.note_result3_by_avg), approved_by_note, approved_by_avg);
        } else {
            float avg = (Float.parseFloat(n1) + Float.parseFloat(n2) + Float.parseFloat(n3)) / 3;
            if (avg >= 7) {
                result_text = getString(R.string.aprooved) + " " + avg;
            } else if (avg < 7 && avg >= 5) {
                result_text = getString(R.string.aprooved_by_note) + " " + avg;
            } else {
                result_text = getString(R.string.disapproved) + " " + avg;
            }
        }

        return result_text;
    }


    public void showAvg(View view) {
        EditText n1_text = (EditText) view.findViewById(R.id.note1);
        EditText n2_text = (EditText) view.findViewById(R.id.note2);
        EditText n3_text = (EditText) view.findViewById(R.id.note3);

        String n1 = n1_text.getText().toString();
        String n2 = n2_text.getText().toString();
        String n3 = n3_text.getText().toString();

        String avg_result_text = getAvgResults(n1, n2, n3);

        if (!TextUtils.isEmpty(n1) && !TextUtils.isEmpty(n2) && !TextUtils.isEmpty(n3)) {
            Toast.makeText(getActivity(), avg_result_text, Toast.LENGTH_LONG).show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setMessage(avg_result_text);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }
}