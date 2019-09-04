package com.kenny.factorytest;
/*
 *
 * File: Util.java
 * Author: 29003
 * Create: 2019/8/12 10:55
 * Changes (from 2019/8/12)
 * -----------------------------------------------------------------
 * 2019/8/12 : Create Util.java (29003);
 * -----------------------------------------------------------------
 * description:assets文件复制
 */
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Util {
        /**
         * 从assets目录下拷贝整个文件夹，不管是文件夹还是文件都能拷贝
         *
         * @param context           上下文
         * @param rootDirFullPath   文件目录，要拷贝的目录如assets目录下有一个tessdata文件夹：
         * @param targetDirFullPath 目标文件夹位置如：/Download/tessdata
         */
        public static void copyFolderFromAssets(Context context, String rootDirFullPath, String targetDirFullPath) {
            Log.d("Tag", "copyFolderFromAssets " + "rootDirFullPath-" + rootDirFullPath + " targetDirFullPath-" + targetDirFullPath);
            try {
                String[] listFiles = context.getAssets().list(rootDirFullPath);// 遍历assets文件夹下目标目录下的文件和文件夹
                for (String string : listFiles) {                              // 判断目录是文件还是文件夹，这里只好用.做区分了
                    Log.d("Tag", "name-" + rootDirFullPath + "/" + string);
                    if (isFileByName(string)) {                                // 文件
                        copyFileFromAssets(context, rootDirFullPath + "/" + string, targetDirFullPath + "/" + string);
                    } else {                                                   // 文件夹
                        String childRootDirFullPath = rootDirFullPath + "/" + string;//拼接
                        String childTargetDirFullPath = targetDirFullPath + "/" + string;
                        new File(childTargetDirFullPath).mkdirs();             //创建文件夹
                        copyFolderFromAssets(context, childRootDirFullPath, childTargetDirFullPath);
                    }
                }
            } catch (IOException e) {
                Log.d("Tag", "copyFolderFromAssets " + "IOException-" + e.getMessage());
                Log.d("Tag", "copyFolderFromAssets " + "IOException-" + e.getLocalizedMessage());
                e.printStackTrace();
            }
        }

        private static boolean isFileByName(String string) {
            if (string.contains(".")) {
                return true;
            }
            return false;
        }

        /**
         * 从assets目录下拷贝文件
         *
         * @param context            上下文
         * @param assetsFilePath     文件的路径名如：SBClock/0001cuteowl/cuteowl_dot.png
         * @param targetFileFullPath 目标文件路径如：/sdcard/SBClock/0001cuteowl/cuteowl_dot.png
         */
        public static void copyFileFromAssets(Context context, String assetsFilePath, String targetFileFullPath) {
            Log.d("Tag", "copyFileFromAssets ");
            InputStream assestsFileImputStream;
            try {
                assestsFileImputStream = context.getAssets().open(assetsFilePath);
                copyFile(assestsFileImputStream, targetFileFullPath);
            } catch (IOException e) {
                Log.d("Tag", "copyFileFromAssets " + "IOException-" + e.getMessage());
                e.printStackTrace();
            }
        }

        private static void copyFile(InputStream in, String targetPath) {
            try {
                FileOutputStream fos = new FileOutputStream(new File(targetPath));
                byte[] buffer = new byte[1024];
                int byteCount = 0;
                while ((byteCount = in.read(buffer)) != -1) {// 循环从输入流读取
                    // buffer字节
                    fos.write(buffer, 0, byteCount);// 将读取的输入流写入到输出流
                }
                fos.flush();// 刷新缓冲区
                in.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }