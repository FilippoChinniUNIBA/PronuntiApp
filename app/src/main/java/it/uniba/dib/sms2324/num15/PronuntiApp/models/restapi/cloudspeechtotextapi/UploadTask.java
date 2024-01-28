package it.uniba.dib.sms2324.num15.PronuntiApp.models.restapi.cloudspeechtotextapi;

import android.content.Context;
import android.os.AsyncTask;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class UploadTask extends AsyncTask<Void, Void, Void> {

    private static final String projectId = "braided-case-412215";
    private static final String bucketName = "pronunti-app-bucket";
    private File filePath;
    private Context context;
    private String objectName;

    public UploadTask(File audioFile, Context context, String objectName) {
        this.filePath = audioFile;
        this.context = context;
        this.objectName = objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            uploadObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        // Puoi eseguire azioni post-caricamento qui se necessario
    }

    public void execute() {
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void uploadObject() throws IOException {
        InputStream inputStream = context.getAssets().open("google-cloud-credentials.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(inputStream);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId(projectId).build().getService();

        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        Storage.BlobWriteOption precondition;

        if (storage.get(bucketName, objectName) == null) {
            precondition = Storage.BlobWriteOption.doesNotExist();
        } else {
            precondition = Storage.BlobWriteOption.generationMatch();
        }
        storage.createFrom(blobInfo, filePath.toPath(), precondition);
    }
}