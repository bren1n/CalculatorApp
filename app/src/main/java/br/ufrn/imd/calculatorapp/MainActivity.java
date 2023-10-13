package br.ufrn.imd.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private NoteCalculatorFragment note_fragment;
    private SimpleCalculatorFragment calculator_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstInitNoteFragment();
    }

    public void firstInitNoteFragment() {
        note_fragment = new NoteCalculatorFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment, note_fragment);
        transaction.commit();
    }

    public void initNoteFragment(View view) {
        note_fragment = new NoteCalculatorFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, note_fragment);
        transaction.commit();
    }

    public void initCalculatorFragment(View view) {
        calculator_fragment = new SimpleCalculatorFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, calculator_fragment);
        transaction.commit();
    }
}