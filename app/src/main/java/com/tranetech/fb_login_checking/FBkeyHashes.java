package com.tranetech.fb_login_checking;

/**
 * Created by HIREN AMALIYAR on 08/06/2016.
 * <p/>
 * n order to generate key hash you need to follow some easy steps.
 * <p/>
 * 1) Download Openssl from: here     https://code.google.com/archive/p/openssl-for-windows/downloads
 * <p/>
 * Using OpenSSL and command prompt
 * Download openssl from Google code (If you have a 64 bit machine you must download openssl-0.9.8e X64 not the latest version)
 * Extract it. Create a folder- OpenSSL in C: / and copy all files here
 * Find “debug.keystore” file path. Most likely it will be inside “C:\Users\\.android” folder. However, if you still don’t find then perform a search. I am sure you are lucky enough to get it.
 * Find keytool.exe path. It will be inside your java/bin directory. In my system it is under “C:\Program Files\Java\jdk1.6.0_30\bin”
 * Open command prompt (Run-> cmd->start) and go to java /bin folder (cd “C:\Program Files\Java\jdk1.6.0_30\bin” command will do it for you)
 * Now you run the below command.
 * <p/>
 * keytool -exportcert -alias androiddebugkey -keystore "C:\\Users\\.android\\debug.keystore" | "C:\\OpenSSL\\bin\\openssl" sha1 -binary |"C:\\OpenSSL\bin\\openssl" base64
 * <p/>
 * Run your application. Go to the activity where you pasted the above code. In the logcat search for "KeyHash". You may found a key hash. Copy the key hash and go to Facebook application dashboard page.
 * Run your application. Go to the activity where you pasted the above code. In the logcat search for "KeyHash". You may found a key hash. Copy the key hash and go to Facebook application dashboard page.
 * try {
 * PackageInfo info = getPackageManager().getPackageInfo(
 * "com.tranetech.fb_login_checking",
 * PackageManager.GET_SIGNATURES);
 * for (Signature signature : info.signatures) {
 * MessageDigest md = MessageDigest.getInstance("SHA");
 * md.update(signature.toByteArray());
 * Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
 * }
 * } catch (PackageManager.NameNotFoundException e) {
 * <p/>
 * } catch (NoSuchAlgorithmException e) {
 * <p/>
 * }
 * Run your application. Go to the activity where you pasted the above code. In the logcat search for "KeyHash". You may found a key hash. Copy the key hash and go to Facebook application dashboard page.
 * try {
 * PackageInfo info = getPackageManager().getPackageInfo(
 * "com.tranetech.fb_login_checking",
 * PackageManager.GET_SIGNATURES);
 * for (Signature signature : info.signatures) {
 * MessageDigest md = MessageDigest.getInstance("SHA");
 * md.update(signature.toByteArray());
 * Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
 * }
 * } catch (PackageManager.NameNotFoundException e) {
 * <p/>
 * } catch (NoSuchAlgorithmException e) {
 * <p/>
 * }
 */

//Simple way TO GET HASH KEY BY PROGRAMATICALLY because above steps gave has key for first time only after it gave error invallid or mismatch hash key so i prefer below method to get FB hash key

/**Run your application. Go to the activity where you pasted the above code. In the logcat search for "KeyHash". You may found a key hash. Copy the key hash and go to Facebook application dashboard page. */


/**
 try {
 PackageInfo info = getPackageManager().getPackageInfo(
 "com.tranetech.fb_login_checking",
 PackageManager.GET_SIGNATURES);
 for (Signature signature : info.signatures) {
 MessageDigest md = MessageDigest.getInstance("SHA");
 md.update(signature.toByteArray());
 Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
 }
 } catch (PackageManager.NameNotFoundException e) {

 } catch (NoSuchAlgorithmException e) {

 }*/
