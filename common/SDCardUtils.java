package com.cny.common;

import java.io.File;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.os.StatFs;

public class SDCardUtils {

    private SDCardUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);

    }

    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator;
    }

    @SuppressLint("NewApi")
    public static long getSDCardAllSize() {
        if (isSDCardEnable()) {
            StatFs stat = new StatFs(getSDCardPath());
            long availableBlocks = stat.getAvailableBlocksLong() - 4;
            long freeBlocks = stat.getFreeBlocksLong();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }

    @SuppressLint("NewApi")
    public static long getFreeBytes(String filePath) {
        if (filePath.startsWith(getSDCardPath())) {
            filePath = getSDCardPath();
        } else {
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocksLong() - 4;
        return stat.getBlockSizeLong() * availableBlocks;
    }

    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }

    public static String getFileName(String pathAndName) {
        int start = pathAndName.lastIndexOf("/");
        int end = pathAndName.lastIndexOf(".");
        if (start != -1 && end != -1) {
            return pathAndName.substring(start + 1, end);
        } else {
            return null;
        }
    }


}
