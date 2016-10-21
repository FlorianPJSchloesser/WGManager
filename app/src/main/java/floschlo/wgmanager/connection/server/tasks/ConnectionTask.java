package floschlo.wgmanager.connection.server.tasks;

import android.os.AsyncTask;

import java.net.Socket;

/**
 * Created by Florian Schlösser on 20.10.2016.
 */
public class ConnectionTask extends AsyncTask<Void, Void, Void> {

    public final static String TAG = ConnectionTask.class.getSimpleName();

    public ConnectionTask (String serverIp, int serverPort) {

    }

    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    public interface ConnectionTaskListener {
        void onSuccess (Socket socket);
        void onFailure (int errorCode);
    }
}
