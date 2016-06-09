package com.tranetech.fb_login_checking;


import java.util.concurrent.Callable;

public class Application {
    private static Application ourInstance = new Application();

    public static Application getInstance() {
        return ourInstance;
    }

    private Callable<Void> mLogoutCallable;

    private Application() {
    }

    public void setLogoutCallable(Callable<Void> callable) {
        mLogoutCallable = callable;
    }
}
