package com.aligmohammad.doctorappclient.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutines {
    /**
     * a function that will take the suspend function and launch it in the main thread
     */
    fun main(work: suspend (() -> Unit)) = CoroutineScope(Dispatchers.Main).launch {
        work()
    }

    /**
     *
     * @param work SuspendFunction0<Unit>
     * @return Job
     */
    fun readWrite(work: suspend (() -> Unit)) = CoroutineScope(Dispatchers.IO).launch {
        work()
    }
}