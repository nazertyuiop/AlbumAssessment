package com.leboncoin.assessment.utils

import androidx.core.util.Consumer
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.ArrayList
import java.util.Arrays
import java.util.Collections
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class TestObserver<T> private constructor() : Observer<T> {
    private val valueHistory = ArrayList<T>()
    private val onChanged = ArrayList<Consumer<T>>()
    private var valueLatch = CountDownLatch(1)

    override fun onChanged(value: T) {
        valueHistory.add(value)
        valueLatch.countDown()

        for (consumer in onChanged) {
            consumer.accept(value)
        }
    }

    /**
     * Returns a unmodifiable list of received values.
     *
     * @return a list of received values
     */
    fun valueHistory(): List<T> {
        return Collections.unmodifiableList(valueHistory)
    }


    companion object {

        fun <T> test(liveData: LiveData<T>): TestObserver<T> {
            val observer = TestObserver<T>()
            liveData.observeForever(observer)
            return observer
        }
    }
}

fun <T> LiveData<T>.test(): TestObserver<T> {
    return TestObserver.test(this)
}
