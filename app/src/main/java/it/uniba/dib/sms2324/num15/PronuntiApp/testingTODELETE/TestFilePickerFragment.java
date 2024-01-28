package it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.io.File;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class TestFilePickerFragment extends Fragment {
    private Button buttonfilePicker;
    private TextView filepath;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_file_picker_fragment, container, false);

        this.buttonfilePicker = view.findViewById(R.id.idfilepickerbutton);
        this.filepath = view.findViewById(R.id.filepathsceltoid);

        this.buttonfilePicker.setOnClickListener(v -> openFile());
        return view;
    }
    private void openFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent=Intent.createChooser(intent,"choose a file");
        sActivityLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> sActivityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();

                //Log.d("data",data.getData().getPath().toString());

                Uri uri = data.getData();
                String path = uri.getPath();
                filepath.setText(path);
            }
        }
    });



}
