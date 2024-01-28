package it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.io.File;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class TestFilePickerFragment extends Fragment {
    private Button buttonfilePicker;

    private TextView filepath;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_file_picker_fragment, container, false);

        buttonfilePicker = view.findViewById(R.id.idfilepickerbutton);


        this.buttonfilePicker.setOnClickListener(v -> openFile());
        return view;
    }
    public void onActivityResult(int requestcode, int resultcode, Intent data) {
        super.onActivityResult(requestcode,resultcode,data);
         Uri uri=data.getData();
         String path= uri.getPath();
         filepath.setText(path);
    }

    private void openFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, 1);
    }



}
