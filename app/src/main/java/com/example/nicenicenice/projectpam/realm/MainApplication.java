package com.example.nicenicenice.projectpam.realm;

/**
 * Created by capri on 5/13/2017.
 */
import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;
/**
 * Created by Roy Deddy Tobing on 4/4/2017.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
// The RealmConfiguration is created using the builder pattern.
// The Realm file will be located in Context.getFilesDir() with name "myrealm.realm"
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("realmsaya.realm")
                .schemaVersion(1)
                .build();
// Use the config
        Realm realm = Realm.getInstance(config);
    }
}
