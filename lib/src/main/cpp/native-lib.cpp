#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_lib_DeviceInfo_getDeviceSerialFromJNI(JNIEnv* env, jclass, jobject context) {
    std::string id = "123";
    return env->NewStringUTF(id.c_str());
}

extern "C" JNIEXPORT jobject JNICALL
Java_com_example_lib_DeviceInfo_getInstalledPackagesFromJNI(JNIEnv* env, jclass, jobject context) {
    /* Use PackageManager to retrieve list of installed packages */
    /* Context.getPackageManager() */
    jclass context_class = env->FindClass("android/content/Context");
    jmethodID get_pm = env->GetMethodID(context_class, "getPackageManager", "()Landroid/content/pm/PackageManager;");
    /* PackageManager.getInstalledPackages() */
    jclass pm_class = env->FindClass("android/content/pm/PackageManager");
    jmethodID pm_get_installed_packages = env->GetMethodID(pm_class, "getInstalledPackages","(I)Ljava/util/List;");
    /* Java List methods */
    jclass java_list_class = env->FindClass("java/util/List");
    jclass java_array_list_class = env->FindClass("java/util/ArrayList");
    jmethodID list_constructor = env->GetMethodID(java_array_list_class, "<init>", "()V");
    jmethodID list_size = env->GetMethodID(java_list_class, "size", "()I");
    jmethodID list_get = env->GetMethodID(java_list_class, "get", "(I)Ljava/lang/Object;");
    jmethodID list_add = env->GetMethodID(java_list_class, "add", "(Ljava/lang/Object;)Z");
    /* Class PackageInfo */
    jclass pi_class = env->FindClass("android/content/pm/PackageInfo");
    jfieldID pi_package_name = env->GetFieldID(pi_class, "packageName", "Ljava/lang/String;");
    /* Class lib.Package */
    jclass lib_package_class = env->FindClass("com/example/lib/ApplicationPackage");
    jmethodID lib_package_constructor = env->GetMethodID(lib_package_class, "<init>","(Ljava/lang/String;IZ)V");

    jobject pm = env->CallObjectMethod(context, get_pm);
    jobject package_info_list = env->CallObjectMethod(pm, pm_get_installed_packages, 0);
    jobject lib_package_list = env->NewObject(java_array_list_class, list_constructor);
    jint size = env->CallIntMethod(package_info_list, list_size);

    for (jint i = 0; i < size; ++i) {
        jobject package_info = env->CallObjectMethod(package_info_list, list_get, i);

        jobject package_name = env->GetObjectField(package_info, pi_package_name);
        jint package_size = 10;
        jboolean package_is_system = false;
        jobject lib_package = env->NewObject(
                lib_package_class,
                lib_package_constructor,
                package_name,
                package_size,
                package_is_system);
        env->CallBooleanMethod(lib_package_list, list_add, lib_package);
    }
    return lib_package_list;
}
