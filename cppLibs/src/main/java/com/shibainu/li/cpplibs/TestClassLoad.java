package com.shibainu.li.cpplibs;

import android.util.Log;

import java.lang.reflect.Field;
import java.time.Period;

public class TestClassLoad {

    public void test(){
        try {
//
            Thread thread = Thread.currentThread();
            Class<Thread> threadClass = Thread.class;
            Field nativePeer = threadClass.getDeclaredField("nativePeer");
            nativePeer.setAccessible(true);
            long o = (long) nativePeer.get(thread);
            Log.d("lhh", "nativePeer:"+o);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
