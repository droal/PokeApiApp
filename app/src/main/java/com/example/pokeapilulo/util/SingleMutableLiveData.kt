package com.example.pokeapilulo.util

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CopyOnWriteArraySet
import java.util.concurrent.atomic.AtomicBoolean

class SingleMutableLiveData<T> : MutableLiveData<T>() {

    private val observers = CopyOnWriteArraySet<ObserverWrapper<in T>>()

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        val wrapper = ObserverWrapper(observer)
        observers.add(wrapper)
        super.observe(owner, wrapper)
    }

    override fun removeObservers(owner: LifecycleOwner) {
        observers.clear()
        super.removeObservers(owner)
    }

    override fun removeObserver(observer: Observer<in T>) {
        observers.removeAll{ observer == it.observer}
        super.removeObserver(observer)
    }

    @MainThread
    override fun setValue(value: T?) {
        observers.forEach { it.newValue() }
        super.setValue(value)
    }

    private class ObserverWrapper<X>(val observer: Observer<X>): Observer<X>{

        private val pending = AtomicBoolean(false)

        override fun onChanged(t: X?) {
            if (pending.compareAndSet(true, false)){
                observer.onChanged(t)
            }
        }

        fun newValue(){
            pending.set(true)
        }

    }
}