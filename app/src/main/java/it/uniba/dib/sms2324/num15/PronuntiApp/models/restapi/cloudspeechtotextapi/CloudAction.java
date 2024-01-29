package it.uniba.dib.sms2324.num15.PronuntiApp.models.restapi.cloudspeechtotextapi;

import android.content.Context;

import java.io.File;
import java.io.IOException;

public interface CloudAction {
    public static final String projectId = "braided-case-412215";
    public static final String bucketName = "pronunti-app-bucket";
    void startCloudAction(Context context, File filePath, String objName) throws IOException;
}
