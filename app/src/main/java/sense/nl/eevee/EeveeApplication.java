package sense.nl.eevee;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by ahmadmuhsin on 8/2/16.
 */
public class EeveeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
