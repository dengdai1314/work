package com.obex.demo

import android.content.Context
import android.util.Log
 import java.io.*
import java.lang.Character.isSpace
import java.lang.Exception
import java.lang.StringBuilder

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by obex, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: ResourceUtils.java
 * Author: liuhai
 * Version: V100R001C01
 * Create: 2019/7/6 11:34
 * Changes (from 2019/7/6)
 * -----------------------------------------------------------------
 * 2019/7/6 : Create ResourceUtils.java (liuhai);
 * -----------------------------------------------------------------
 * description:
 */

class ResourceUtils {
    private val BUFFER_SIZE = 8192

    /**
     * Return the content of assets.
     *
     * @param assetsFilePath The path of file in assets.
     * @param charsetName    The name of charset.
     * @return the content of assets
     */

    companion object {
        fun  getJson(context: Context, fileName: String): String {
            var string: String = ""
            var insputStream: InputStream? = null
            var bos: ByteArrayOutputStream? = null
            try {
                insputStream = context.assets.open(fileName)
                bos = ByteArrayOutputStream()
                val bytes = ByteArray(1024)
                insputStream?.let {
                    while (true) {
                        val read = it.read(bytes)
                        if (read == -1) {
                            break
                        }
                        bos.write(bytes, 0, read)
                    }
                }

                string = String(bos.toByteArray())
            } catch (e: Exception) {
                Log.e("解析数据", string)
            }
            return string

        }
    }


}