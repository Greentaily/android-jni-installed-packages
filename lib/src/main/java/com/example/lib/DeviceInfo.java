package com.example.lib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;

import java.util.ArrayList;
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

    @SuppressLint("HardwareIds")
    private static String resolveAndroidId(Context context) {
        // android_id is stored inside Settings.Secure
        // accessed via Secure.getString(ContentResolver, Secure.ANDROID_ID)
        return Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    private static List<AppPackage> newPackageList(Context context) {
        PackageManager package_manager = context.getPackageManager();
        List<PackageInfo> package_info_list = package_manager.getInstalledPackages(0);

        int package_count = package_info_list.size();
        List<AppPackage> app_package_list = new ArrayList<>();

        for (int i = 0; i < package_count; i++) {
            PackageInfo package_info = package_info_list.get(i);
            boolean is_system = ((package_info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
            AppPackage app_package = new AppPackage(package_info.packageName, 10, is_system);
            app_package_list.add(app_package);
        }
        return app_package_list;
    }

    private static native String getAndroidIdFromJNI(Context context);
    private static native List<AppPackage> getInstalledPackagesFromJNI(Context context);
}
