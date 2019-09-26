package com.boot.data.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.win32.StdCallLibrary;

/**
 * Created by 98548 on 2019/8/29.
 */
public interface StdCallDllLibrary extends StdCallLibrary {

    StdCallDllLibrary INSTANCE = (StdCallDllLibrary) Native.loadLibrary((Platform.isWindows() ? "msvcrt" : "c"), StdCallDllLibrary.class);

    void printf(String format, Object... args);
}
