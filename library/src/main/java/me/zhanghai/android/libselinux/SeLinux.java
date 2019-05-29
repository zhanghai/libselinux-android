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
    public static native byte[] fgetfilecon(@NonNull FileDescriptor fd) throws ErrnoException;

    public static native void fsetfilecon(@NonNull FileDescriptor fd, @NonNull byte[] context)
            throws ErrnoException;

    @NonNull
    public static native byte[] getfilecon(@NonNull byte[] path) throws ErrnoException;

    public static native boolean is_selinux_enabled();

    @NonNull
    public static native byte[] lgetfilecon(@NonNull byte[] path) throws ErrnoException;

    public static native void lsetfilecon(@NonNull byte[] path, @NonNull byte[] context)
            throws ErrnoException;

    public static native boolean security_getenforce() throws ErrnoException;

    public static native void setfilecon(@NonNull byte[] path, @NonNull byte[] context)
            throws ErrnoException;
}
