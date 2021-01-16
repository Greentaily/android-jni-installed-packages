#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_lib_DeviceInfo_getAndroidIdFromJNI(JNIEnv* env, jclass jcl, jobject context) {
    jmethodID resolve_android_id = env->GetStaticMethodID(jcl, "resolveAndroidId","(Landroid/content/Context;)Ljava/lang/String;");
    jobject android_id = env->CallStaticObjectMethod(jcl, resolve_android_id, context);
    // not dynamic_cast as the cast is valid regardless
    return static_cast<jstring>(android_id);
}

extern "C" JNIEXPORT jobject JNICALL
Java_com_example_lib_DeviceInfo_getInstalledPackagesFromJNI(JNIEnv* env, jclass jcl, jobject context) {
    jmethodID new_package_list = env->GetStaticMethodID(jcl, "newPackageList", "(Landroid/content/Context;)Ljava/util/List;");
    jobject package_list = env->CallStaticObjectMethod(jcl, new_package_list, context);
    return package_list;
}
