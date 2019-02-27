/*
 * Copyright (c) 2019 Hai Zhang <dreaming.in.code.zh@gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.libselinux;

import android.system.ErrnoException;
import android.system.Os;

import java.io.FileDescriptor;

import androidx.annotation.NonNull;

public class SeLinux {

    private static final String LIBRARY_NAME = "selinux-jni";

    static {
        if (Os.getuid() != 0) {
            System.loadLibrary(LIBRARY_NAME);
        }
    }

    private SeLinux() {}

    @NonNull
    public static String getLibraryName() {
        return LIBRARY_NAME;
    }

    @NonNull
    public static native String getfilecon(@NonNull String path) throws ErrnoException;

    @NonNull
    public static native String fgetfilecon(@NonNull FileDescriptor fd) throws ErrnoException;

    public static native void fsetfilecon(@NonNull FileDescriptor fd, @NonNull String context)
            throws ErrnoException;

    public static native boolean is_selinux_enabled();

    @NonNull
    public static native String lgetfilecon(@NonNull String path) throws ErrnoException;

    public static native void lsetfilecon(@NonNull String path, @NonNull String context)
            throws ErrnoException;

    public static native boolean security_getenforce() throws ErrnoException;

    public static native void setfilecon(@NonNull String path, @NonNull String context)
            throws ErrnoException;
}
