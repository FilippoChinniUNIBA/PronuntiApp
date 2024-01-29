package it.uniba.dib.sms2324.num15.PronuntiApp.models.restapi.cloudspeechtotextapi;

import android.content.Context;
import android.os.AsyncTask;
import java.io.File;
import java.io.IOException;

public class CloudTask extends AsyncTask<Void, Void, Void> {
    private Context context;
    private File filePath;
    private String objName;
    private CloudAction cloudAction;

    public CloudTask(File filePath, Context context, String objName, CloudAction cloudAction) {
        this.context = context;
        this.filePath = filePath;
        this.objName = objName;
        this.cloudAction = cloudAction;
    }

    public void setFilePath(File filePath) {
        this.filePath = filePath;
    }

    public void setObjectName(String objectName) {
        this.objName = objectName;
    }

    public File getFilePath() {
        return filePath;
    }

    public String getObjName() {
        return objName;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            if (cloudAction != null) {
                cloudAction.startCloudAction(context,filePath,objName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void execute() {
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
