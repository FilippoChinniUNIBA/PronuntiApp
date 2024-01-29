package it.uniba.dib.sms2324.num15.PronuntiApp.models.restapi.cloudspeechtotextapi.cloud_actions;

import android.content.Context;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.restapi.cloudspeechtotextapi.CloudAction;

public class DownloadAction implements CloudAction {
    @Override
    public void startCloudAction(Context context, File filePath, String objName) throws IOException {
        if(!filePath.exists()) {
            InputStream inputStream = context.getAssets().open("google-cloud-credentials.json");
            GoogleCredentials credentials = GoogleCredentials.fromStream(inputStream);
            Storage storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId(projectId).build().getService();
            BlobId blobId = BlobId.of(bucketName, objName);
            Blob blob = storage.get(blobId);
            if (blob != null) {
                blob.downloadTo(Paths.get(filePath.getPath()));
            } else {
                throw new RuntimeException("Blob non esistente");
            }
        }else{
            throw new RuntimeException("File con lo stesso nome gia esistente nel dispositivo");
        }
    }
}
