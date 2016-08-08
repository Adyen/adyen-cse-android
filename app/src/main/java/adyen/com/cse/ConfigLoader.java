package adyen.com.cse;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Created by andrei on 11/10/15.
 */
public class ConfigLoader {

    private String tag = ConfigLoader.class.getSimpleName();

    public ConfigLoader() {

    }

    public JSONObject loadJsonConfiguration() {
        JSONObject configurationJson;
        InputStream inputStream = CseApplication.getInstance().getGlobalContext().getResources().openRawResource(R.raw.config);

        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }

            configurationJson = new JSONObject(responseStrBuilder.toString());

            return configurationJson;
        } catch (IOException e) {
            Log.e(tag, e.getMessage(), e);
        } catch (JSONException e) {
            Log.e(tag, e.getMessage(), e);
        }

        return null;
    }

}
