package floschlo.wgmanager.connection.server;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Florian Schlösser on 20.10.2016.
 */
public class SocketService extends Service {

    public final static String TAG = SocketService.class.getSimpleName();

    private static final String TEST_IP = "192.168.178.30";

    private static final int SERVER_PORT = 30284;

    private PrintWriter mServerOut;
    private Socket mServerSocket;
    private InetAddress mServerAddr;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        System.out.println("I am in Ibinder onBind method");
        return serviceBinder;
    }

    private final IBinder serviceBinder = new LocalBinder();
    //TCPClient mTcpClient = new TCPClient();

    public class LocalBinder extends Binder {
        public SocketService getService() {
            System.out.println("I am in Localbinder ");
            return SocketService.this;

        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Runnable connect = new connectSocket();
        new Thread(connect).start();
        System.out.println("I am in on create");
    }

    public void IsBoundable(){
        Toast.makeText(this,"I bind like butter", Toast.LENGTH_LONG).show();
    }

    public void sendMessage(String message){
        if (mServerOut != null && !mServerOut.checkError()) {
            System.out.println("in sendMessage"+message);
            mServerOut.println(message);
            mServerOut.flush();
        }
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId){
        super.onStartCommand(intent, flags, startId);
        System.out.println("I am in on start");
        //  Toast.makeText(this,"Service created ...", Toast.LENGTH_LONG).show();
        return START_NOT_STICKY;
    }


    class connectSocket implements Runnable {

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

                }
                catch (Exception e) {

                    Log.e("TCP", "S: Error", e);

                }
            } catch (Exception e) {

                Log.e("TCP", "C: Error", e);

            }

        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            mServerSocket.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mServerSocket = null;
    }
}
