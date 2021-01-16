package com.example.lib;

import android.content.Context;

import java.util.List;

public class DeviceInfo {
    static {
        System.loadLibrary("native-lib");
    }
    public static String getAndroidId(Context context) {
        return getAndroidIdFromJNI(context);
    }

    public static List<AppPackage> getPackages(Context context) {
        return getInstalledPackagesFromJNI(context);
    }

    private static native String getAndroidIdFromJNI(Context context);
    private static native List<AppPackage> getInstalledPackagesFromJNI(Context context);
}
