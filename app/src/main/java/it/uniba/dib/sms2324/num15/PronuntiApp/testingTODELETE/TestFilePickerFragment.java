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

import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class TestFilePickerFragment extends Fragment {
    private Button buttonfilePicker;
    private TextView filepath;
    private ActivityResultLauncher<String> filePickerLauncher;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_file_picker_fragment, container, false);

        this.filePickerLauncher = createFilePickerLauncher();

        this.buttonfilePicker = view.findViewById(R.id.idfilepickerbutton);
        this.filepath = view.findViewById(R.id.filepathsceltoid);

        this.buttonfilePicker.setOnClickListener(v -> openFile());

        return view;
    }

    private void openFile() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        String[] mimetypes = {"image/*", "audio/*"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
        filePickerLauncher.launch(intent.getType());
    }

    private ActivityResultLauncher<String> createFilePickerLauncher() {
        return registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    // This is called when a file is picked
                    if (uri != null) {
                        filepath.setText(uri.toString());
                    } else {
                        filepath.setText("No file selected");
                    }
                }
        );
    }


    private CompletableFuture<String> getUrlFromStorageReference(StorageReference reference) {
        CompletableFuture<String> future = new CompletableFuture<>();

        reference.getDownloadUrl().addOnSuccessListener(uri -> {
            String imageUrl = uri.toString();
            future.complete(imageUrl);
        }).addOnFailureListener(e -> {
            future.completeExceptionally(e);
        });

        return future;
    }

    /*private CompletableFuture<Void> uploadFileToStorage(File file, StorageReference storageReference, Activity activity) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        StorageReference fileReference = storageReference.child(file.getName());

        try {
            FileInputStream stream = new FileInputStream(file);
            UploadTask uploadTask = fileReference.putStream(stream);

            uploadTask.addOnSuccessListener(taskSnapshot -> {
                future.complete(null);
            }).addOnFailureListener(exception -> {
                future.completeExceptionally(exception);
            });
        } catch (IOException e) {
            future.completeExceptionally(e);
        }
        return future;
    }*/



}
