package zhaoxizhang.github.io.leethub;


import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static App sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Context getApplication() {
        return sContext;
    }
}
