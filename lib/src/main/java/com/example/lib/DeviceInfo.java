package com.example.lib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;

import java.io.File;
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
        return Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    private static List<AppPackage> newPackageList(Context context) {
        PackageManager package_manager = context.getPackageManager();
        List<PackageInfo> package_info_list = package_manager.getInstalledPackages(0);

        int package_count = package_info_list.size();
        List<AppPackage> app_package_list = new ArrayList<>();

        for (int i = 0; i < package_count; i++) {
            PackageInfo package_info = package_info_list.get(i);
            ApplicationInfo app_info = package_info.applicationInfo;

            String package_name = package_info.packageName;
            // size in bytes
            long apk_size = packageApkSize(app_info);
            boolean is_system = ((app_info.flags & ApplicationInfo.FLAG_SYSTEM) != 0);

            AppPackage app_package = new AppPackage(package_name, apk_size, is_system);
            app_package_list.add(app_package);
        }
        return app_package_list;
    }

    private static Long packageApkSize(ApplicationInfo app_info) {
        try {
            File file = new File(app_info.sourceDir);
            return file.length();
        } catch (Exception e) {
            e.printStackTrace();
            return (long) 0;
        }
    }

    private static native String getAndroidIdFromJNI(Context context);
    private static native List<AppPackage> getInstalledPackagesFromJNI(Context context);
}
