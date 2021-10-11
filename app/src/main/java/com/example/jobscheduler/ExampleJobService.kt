package com.example.jobscheduler

import android.annotation.SuppressLint
import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

class ExampleJobService: JobService() {

    private val TAG = "ExampleJobService"
    private var jobCancelled :Boolean = false
    private lateinit var jobExecutor: JobExecutor

    override fun onStartJob(param: JobParameters?): Boolean {
        Log.d(TAG, "Job Started")
       // doBackgroundWork(param)

        jobExecutor = @SuppressLint("StaticFieldLeak")
        object  : JobExecutor(){

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                Log.d(TAG, "Job Finished")
                jobFinished(param, false)
            }
        }
        jobExecutor.execute()
        return true
    }

    private fun doBackgroundWork(param: JobParameters?) {


      /*  runBlocking {

            for (i in 0..10){
                Log.d(TAG, "run $i")
                delay(1000L)

            }
            Log.d(TAG, "Job Finished")
        }*/
    /*
        var newThread = Thread{
            for (i in 0..10){
                Log.d(TAG, "run $i")
                if(jobCancelled) return@Thread
                Thread.sleep(1000L)
            }
            Log.d(TAG, "Job Finished")
            jobFinished(param,false)
        }

        newThread.start()*/


    }

    override fun onStopJob(p0: JobParameters?): Boolean {

        Log.d(TAG, "Job cancelled before completion: ")
         jobCancelled = true
        return true
    }
}