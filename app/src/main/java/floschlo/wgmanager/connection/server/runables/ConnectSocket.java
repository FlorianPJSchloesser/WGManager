package floschlo.wgmanager.connection.server.runables;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Florian Schlösser on 20.10.2016.
 */
public class ConnectSocket extends Runnable {

    public final static String TAG = ConnectSocket.class.getSimpleName();

    @Override
    public void run() {
        try {
            //here you must put your computer's IP address.
            mServerAddr = InetAddress.getByName(TEST_IP);
            Log.e("TCP Client", "C: Connecting...");
            //create a socket to make the connection with the server

            mServerSocket = new Socket(mServerAddr, SERVER_PORT);

            try {

                Log.e("TCP Client", "C: Sent.");

                //send the message to the server
                mServerOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mServerSocket.getOutputStream())), true);

                Log.e("TCP Client", "C: Done.");

            } catch (Exception e) {

                Log.e("TCP", "S: Error", e);

            }
        } catch (Exception e) {

            Log.e("TCP", "C: Error", e);

        }
    }

    public interface OnConnectionFinished {
        void onConnectionSucceed(Socket socket, PrintWriter printWriter);

        void onConnectionFailed(int errorCode);
    }
}
