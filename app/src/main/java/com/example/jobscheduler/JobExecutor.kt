package com.example.jobscheduler

import android.os.AsyncTask
import com.example.jobscheduler.MainActivity.Companion.counter

open class JobExecutor :AsyncTask<Void, Void, String>(){

    override fun doInBackground(vararg p0: Void?): String {
        MainActivity.countSeconds()
        return "Performing in Background $counter time"
    }
}