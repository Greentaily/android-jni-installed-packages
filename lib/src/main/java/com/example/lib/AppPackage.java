package com.example.lib;

public class AppPackage {
    public AppPackage(String name, long size, boolean isSystem) {
        this.name = name;
        this.size = size;
        this.isSystem = isSystem;
    }

    public final String name;
    public final long size;
    public final boolean isSystem;
}
