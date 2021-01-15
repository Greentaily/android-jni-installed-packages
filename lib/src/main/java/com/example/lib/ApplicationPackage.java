package com.example.lib;

public class ApplicationPackage {
    public ApplicationPackage(String name, int size, boolean isSystem) {
        this.name = name;
        this.size = size;
        this.isSystem = isSystem;
    }

    public final String name;
    public final int size;
    public final boolean isSystem;
}
