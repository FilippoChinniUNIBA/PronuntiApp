package it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.LOCALE_SERVICE;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.api.services.storage.Storage;
import com.google.firebase.Firebase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBPersonaggio;

public class TestFilePickerFragment extends Fragment {
    private Button buttonfilePicker;
    private Button buttonfileUploader;
    private Button buttonfileDownloader;
    private TextView filepath;
    private TextView risultatoTest;
    private ActivityResultLauncher<String> filePickerLauncher;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_file_picker_fragment, container, false);

        this.filePickerLauncher = createFilePickerLauncher();

        this.buttonfilePicker = view.findViewById(R.id.idfilepickerbutton);
        this.filepath = view.findViewById(R.id.filepathsceltoid);
        this.buttonfileUploader = view.findViewById(R.id.testButtonUploadFilePicker);
        this.buttonfileDownloader = view.findViewById(R.id.testButtonDownloadFilePicker);
        this.risultatoTest = view.findViewById(R.id.testFilePickerTextView);

        buttonfilePicker.setOnClickListener(v -> funzioneMaster());
        buttonfileUploader.setOnClickListener(v -> uploadFileToStorage(Uri.parse(filepath.getText().toString())));
        buttonfileDownloader.setOnClickListener(v -> getUrlFromStorageReference(FirebaseStorage.getInstance()));

        return view;
    }

    private void funzioneMaster() {
        openFile();
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
                    if (uri != null) {
                        filepath.setText(uri.toString());
                    } else {
                        filepath.setText("Nessun file selezionato!");
                    }
                }
        );
    }

    private CompletableFuture<String> uploadFileToStorage(Uri file) {
        CompletableFuture<String> future = new CompletableFuture<>();

        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference ref = storage.getReference().child(CostantiDBPersonaggio.TEXTURE_PERSONAGGIO);
        StorageReference fRef = ref.child(LocalDate.now().toString() + LocalTime.now().toString());

        fRef.putFile(file).addOnSuccessListener(taskSnapshot -> fRef.getDownloadUrl().addOnSuccessListener(uri -> {
            future.complete(uri.toString());
            Log.d("TestFilePickerFragment", "File uploaded: " + uri.toString());
            this.risultatoTest.setText(uri.toString());
        }).addOnFailureListener(e -> {
            future.completeExceptionally(e);
            Log.e("TestFilePickerFragment", "Errore nel getting del Download URL", e);
        })).addOnFailureListener(e -> {
            future.completeExceptionally(e);
            Log.e("TestFilePickerFragment", "Errore Upload File", e);
        });

        return future;
    }


    //QUESTA NON SERVE
    private CompletableFuture<String> getUrlFromStorageReference(FirebaseStorage storage) {
        CompletableFuture<String> future = new CompletableFuture<>();

        StorageReference ref = storage.getReference().child(CostantiDBPersonaggio.TEXTURE_PERSONAGGIO);
        ref.child("texture.png");

        ref.getDownloadUrl().addOnSuccessListener(uri -> {
            future.complete(uri.toString());
            Log.d("TestFilePickerFragment", "File downloaded: " + uri.toString());
            this.risultatoTest.setText(uri.toString());
        }).addOnFailureListener(e -> {
            future.completeExceptionally(e);
            Log.e("TestFilePickerFragment", "Error downloading file", e);
        });

        return future;
    }



}
