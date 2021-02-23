package com.aligmohammad.doctorappclient.util

import kotlinx.coroutines.*

/**
 * a customized generic function to use Coroutines with DI.lazy
 * @see lazy
 * @see CoroutineScope
 * @return Lazy<Deferred<T>> which is a generic job
 */
fun <T> lazyCall(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}