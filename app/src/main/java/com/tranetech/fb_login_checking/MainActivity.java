package com.tranetech.fb_login_checking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Callable;

import static com.tranetech.fb_login_checking.R.*;

/**
 * Refer this link for fb login with fb sdk
 * https://auth0.com/blog/2016/02/08/how-to-authenticate-on-android-using-social-logins/
 */


public class MainActivity extends AppCompatActivity {
    private ProfileTracker mProfileTracker;
    private Profile profile;
    private CallbackManager mFacebookCallbackManager;
    private LoginButton mFacebookSignInButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(layout.activity_main);


        // TO GET HASH KEY BY PROGRAMATICALLY
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.tranetech.fb_login_checking",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        mFacebookCallbackManager = CallbackManager.Factory.create();
        mFacebookSignInButton = (LoginButton) findViewById(id.facebook_sign_in_button);
        mFacebookSignInButton.registerCallback(mFacebookCallbackManager,
                new FacebookCallback<LoginResult>() {

                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        handleSignInResult(new Callable<Void>() {
                            @SuppressLint("LongLogTag")
                            @Override
                            public Void call() throws Exception {
                                LoginManager.getInstance().logOut();
                                return null;
                            }
                        });
                    }

                    @Override
                    public void onCancel() {
                        handleSignInResult(null);
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d(MainActivity.class.getCanonicalName(), error.getMessage());
                        handleSignInResult(null);
                    }
                }
        );

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private void handleSignInResult(Callable<Void> logout) {

        if (logout == null) {
            /* Login error */
            Toast.makeText(getApplicationContext(), string.login_error, Toast.LENGTH_SHORT).show();
        } else {
            /* Login success */
            com.tranetech.fb_login_checking.Application.getInstance().setLogoutCallable(logout);
            startActivity(new Intent(this, MainActivity.class));
            if (Profile.getCurrentProfile() == null) {
                mProfileTracker = new ProfileTracker() {
                    @Override
                    protected void onCurrentProfileChanged(final Profile OldProfile, final Profile NewProfile) {

                        Log.e("facebook - profile", NewProfile.getFirstName());
                        Toast.makeText(MainActivity.this, "" + NewProfile.getFirstName().toString() + " " + NewProfile.getMiddleName().toString() + " " + NewProfile.getLastName().toString(), Toast.LENGTH_SHORT).show();
                        mProfileTracker.stopTracking();
                    }
                };
                // no need to call startTracking() on mProfileTracker
                // because it is called by its constructor, internally.
            } else {
                profile = Profile.getCurrentProfile();
                Log.e("facebook - profile", profile.getFirstName());
                Toast.makeText(MainActivity.this, "" + profile.getFirstName().toString() + " " + profile.getMiddleName().toString() + " " + profile.getLastName().toString(), Toast.LENGTH_SHORT).show();

            }

        }
    }


}
