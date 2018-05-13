package abanoub.johnny.development.moviesapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import abanoub.johnny.development.moviesapp.utils.events.NetworkSpeedObserver;
import com.facebook.network.connectionclass.ConnectionClassManager;
import com.facebook.network.connectionclass.ConnectionQuality;
import com.facebook.network.connectionclass.DeviceBandwidthSampler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Abanoub Maher on 10/29/17.
 */

public class NetworkUtil {
    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;
    public static final int NETWORK_STATUS_NOT_CONNECTED=0,NETWORK_STAUS_WIFI=1,NETWORK_STATUS_MOBILE=2;

    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    public static int getConnectivityStatusString(Context context) {
        int conn = NetworkUtil.getConnectivityStatus(context);
        int status = 0;
        if (conn == NetworkUtil.TYPE_WIFI) {
            status = NETWORK_STAUS_WIFI;
        } else if (conn == NetworkUtil.TYPE_MOBILE) {
            status =NETWORK_STATUS_MOBILE;
        } else if (conn == NetworkUtil.TYPE_NOT_CONNECTED) {
            status = NETWORK_STATUS_NOT_CONNECTED;
        }
        return status;
    }

    private static String mURL = "https://i2.wp.com/beebom.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg?resize=640%2C426";
    private static ConnectionClassManager mConnectionClassManager;
    private static ConnectionChangedListener mListener;
    private static ConnectionQuality mConnectionClass = ConnectionQuality.UNKNOWN;
    private static DeviceBandwidthSampler mDeviceBandwidthSampler;
    private static DownloadImage downloadImage;
    private static Handler handler ;
    private static Runnable runnable ;
    private static final int delayMillisecons = 5000;
    private static NetworkSpeedObserver myNetworkSpeedObserver;

    public static void StartAnalizing(NetworkSpeedObserver networkSpeedObserver){
        myNetworkSpeedObserver = networkSpeedObserver;
        mConnectionClass = ConnectionQuality.UNKNOWN;
        mConnectionClassManager = ConnectionClassManager.getInstance();
        mDeviceBandwidthSampler = DeviceBandwidthSampler.getInstance();
        mListener = new ConnectionChangedListener();
        mConnectionClassManager.register(mListener);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                downloadImage = new DownloadImage();
                downloadImage.execute(mURL);

            }
        };
        handler.postDelayed(runnable,delayMillisecons);
    }
    private static class ConnectionChangedListener
            implements ConnectionClassManager.ConnectionClassStateChangeListener {

        @Override
        public void onBandwidthStateChange(ConnectionQuality bandwidthState) {

            mConnectionClass = bandwidthState;
            if (bandwidthState.equals(ConnectionQuality.EXCELLENT)||bandwidthState.equals(ConnectionQuality.GOOD )){
                handler.removeCallbacks(runnable);
                myNetworkSpeedObserver.setAvailable(true);
            }
        }
    }
    /**
     * AsyncTask for handling downloading and making calls to the timer.
     */
    private static class DownloadImage extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            mDeviceBandwidthSampler.startSampling();
        }

        @Override
        protected Void doInBackground(String... url) {
            String imageURL = url[0];
            try {
                // Open a stream to download the image from our URL.
                URLConnection connection = new URL(imageURL).openConnection();
                connection.setUseCaches(false);
                connection.connect();
                InputStream input = connection.getInputStream();
                try {
                    byte[] buffer = new byte[1024];

                    // Do some busy waiting while the stream is open.
                    while (input.read(buffer) != -1) {
                    }
                } finally {
                    input.close();
                }
            } catch (IOException e) {
                Log.e("Networkcheck", "Error while downloading image.");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            // Retry for up to 10 times until we find a ConnectionClass.

            mDeviceBandwidthSampler.stopSampling();
            if (!mConnectionClass.equals(ConnectionQuality.EXCELLENT)&&!mConnectionClass.equals(ConnectionQuality.GOOD )){
                handler.postDelayed(runnable,delayMillisecons);
            }
        }
    }
}
