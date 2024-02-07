package it.uniba.dib.sms2324.num15.PronuntiApp.views;

import android.app.DatePickerDialog;
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
import java.util.Calendar;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class DatePickerCustom extends AbstractFragmentWithNavigation {

    private TextInputEditText textInputEditTextDataNascitaProfiloPaziente;
    private TextInputLayout textInputLayoutDataNascitaProfiloPaziente;
    private Calendar calendar;
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

        calendar = Calendar.getInstance();

        textInputEditTextDataNascitaProfiloPaziente.setOnClickListener(v -> showDatePickerDialog());
        textInputLayoutDataNascitaProfiloPaziente.setEndIconOnClickListener(v -> showDatePickerDialog());
        imageButtonDataNascitaProfiloPaziente.setOnClickListener(v -> showDatePickerDialog());

        //parte del time picker
        gridLayout = view.findViewById(R.id.gridLayout);
        // Loop through each child view in the GridLayout
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View child = gridLayout.getChildAt(i);

            // Check if the child view is a TextView
            if (child instanceof TextView) {
                final TextView textView = (TextView) child;

                // Set OnClickListener for the TextView
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle the selection of the TextView here
                        handleTextViewSelection(textView);
                    }
                });
            }
        }
        return view;
    }


    //metodi del time picker
    private void handleTextViewSelection(TextView selectedTextView) {
        selectedTextView.setBackground(getContext().getDrawable(R.drawable.rectangle_rounded_border_bkg_colored));
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
    private void updateDateInView() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = simpleDateFormat.format(calendar.getTime());
        textInputEditTextDataNascitaProfiloPaziente.setText(formattedDate);
        date = formattedDate;
    }

    private void showDatePickerDialog() {
        datePickerDialog = new DatePickerDialog(getActivity(), (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDateInView();
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}