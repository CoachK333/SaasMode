package com.boot.data.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

/**
 * Created by 98548 on 2019/8/29.
 */
public interface CLibrary extends Library {

    CLibrary INSTANCE = (CLibrary) Native.loadLibrary((Platform.isWindows() ? "msvcrt" : "c"), CLibrary.class);

    void printf(String format, Object... args);
}
