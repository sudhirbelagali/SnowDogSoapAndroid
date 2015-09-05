package sportee.sudhir.snowdogsoapandroid;

import android.app.Activity;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends Activity {

    private static final String MAGENTO_NAMESPACE = "urn:Magento";
    private static final String MAGENTO_URL = "http://192.168.56.1/magento/api/soap/";
    private static final String MAGENTO_METHOD_NAME = "login";

    public static void getLogin(String username, String apiKey) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //Create request
        SoapObject request = new SoapObject(MAGENTO_NAMESPACE, MAGENTO_METHOD_NAME);
        //Property which holds input parameters
        request.addProperty("username", username);
        request.addProperty("apiKey", apiKey);

        //Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.xsd = SoapSerializationEnvelope.XSD;
        envelope.enc = SoapSerializationEnvelope.ENC;
        //Set output SOAP object
        envelope.setOutputSoapObject(request);
        //Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(MAGENTO_URL);
        androidHttpTransport.debug = true;
        try {
            //Invole web service (synchronous)
            androidHttpTransport.call("", envelope);
            //Get the response
            String loginResponse = envelope.getResponse().toString();

            Log.d("Session ID",loginResponse);

        } catch (Exception e) {
            //it's nasty to catch Exception. Catch some specific like IOException
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        getLogin("cats","sudhir123");
    }

}
