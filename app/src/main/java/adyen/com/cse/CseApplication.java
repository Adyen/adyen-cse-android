package adyen.com.cse;

import android.app.Application;
import android.content.Context;

/**
 * Created by andrei on 8/8/16.
 */
public class CseApplication extends Application {

    private static CseApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized CseApplication getInstance() {
        return mInstance;
    }

    public Context getGlobalContext() {
        return getApplicationContext();
    }

}
