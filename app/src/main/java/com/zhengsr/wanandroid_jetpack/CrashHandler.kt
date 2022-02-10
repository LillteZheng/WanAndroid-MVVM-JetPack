package com.zhengsr.wanandroid_jetpack

import android.util.Log
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.system.exitProcess

/**
 * @author by zhengshaorui 2021/3/29 09:45
 * describe：
 */
class CrashHandler : Thread.UncaughtExceptionHandler {
    private var defaultHandler: Thread.UncaughtExceptionHandler? = null

    init {
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(t: Thread, e: Throwable) {
        val crashMsg = getCrashMsg(e)
        Log.e("zsr CrashHandler", "崩溃日志: $crashMsg")
        exitProcess(1)
        defaultHandler?.uncaughtException(t, e)
    }

    fun getCrashMsg(throwable: Throwable): String? {
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        throwable.printStackTrace(pw)
        return sw.toString()
    }

}