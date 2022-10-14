package com.lhl.apackage;

public interface PackageListener {
    void onAdd(String packageName);

    void onRemove(String packageName);
}
