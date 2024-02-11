package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.utils_fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class DatePickerCustom extends AbstractFragmentWithNavigation {
    private TextInputEditText textInputEditTextDataNascitaProfiloPaziente;
    private TextInputLayout textInputLayoutDataNascitaProfiloPaziente;
    private DatePickerDialog datePickerDialog;
    private ImageButton imageButtonDataNascitaProfiloPaziente;
    private GridLayout gridLayout;
    private String date;
    private String time;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.date_picker, container, false);

        //parte del date picker
        textInputEditTextDataNascitaProfiloPaziente = view.findViewById(R.id.textInputEditTextDataNascitaProfiloPaziente);
        imageButtonDataNascitaProfiloPaziente = view.findViewById(R.id.imageButtonDataNascitaProfiloPaziente);
        textInputLayoutDataNascitaProfiloPaziente = view.findViewById(R.id.textInputLayoutDataNascitaProfiloPaziente);

        textInputEditTextDataNascitaProfiloPaziente.setOnClickListener(v -> showDatePickerDialog());
        textInputLayoutDataNascitaProfiloPaziente.setEndIconOnClickListener(v -> showDatePickerDialog());
        imageButtonDataNascitaProfiloPaziente.setOnClickListener(v -> showDatePickerDialog());

        //parte del time picker
        gridLayout = view.findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View child = gridLayout.getChildAt(i);

            if (child instanceof TextView) {
                final TextView textView = (TextView) child;
                textView.setOnClickListener(v -> handleTextViewSelection(textView));
            }
        }
        return view;
    }


    //metodi del time picker
    private void handleTextViewSelection(TextView selectedTextView) {
        selectedTextView.setBackground(getContext().getDrawable(R.drawable.rectangle_rounded_border_selector_bkg));
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View child = gridLayout.getChildAt(i);
            if (child instanceof TextView) {
                TextView textView = (TextView) child;
                if (textView != selectedTextView) {
                    textView.setBackground(getContext().getDrawable(R.drawable.rectangle_rounded_border_bkg));
                }
            }
        }
        String selectedText = selectedTextView.getText().toString();
        time = selectedText;
    }

    //metodi del date picker
    private void showDatePickerDialog() {
        LocalDate now = LocalDate.now();
        datePickerDialog = new DatePickerDialog(getContext(), (view, year, month, dayOfMonth) -> {
            String date = formatDate(year, month, dayOfMonth);
            textInputEditTextDataNascitaProfiloPaziente.setText(date);
            this.date = date;
        }, now.getYear(), now.getMonthValue() - 1, now.getDayOfMonth());
        datePickerDialog.show();
    }

    private String formatDate(int year, int month, int dayOfMonth) {
        LocalDate selectedDate = LocalDate.of(year, month + 1, dayOfMonth);
        return selectedDate.toString();
    }

}