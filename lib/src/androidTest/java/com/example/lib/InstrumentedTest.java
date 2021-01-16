package com.example.lib;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {
    @Test
    public void useAppContext() {
        Context app_context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.lib.test", app_context.getPackageName());
    }

    @Test
    public void assertLibAndroidIdEqualsNormalAndroidId() {
        Context app_context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String id_java = Settings.Secure.getString(app_context.getContentResolver(), "android_id");
        assertEquals(id_java, DeviceInfo.getAndroidId(app_context));
    }
}