package com.example.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    companion object{

        val JOB_ID: Int = 123
        var counter: Int = 0
        fun countSeconds(){
            counter++
        }
    }

    private lateinit var jobScheduler : JobScheduler
    private lateinit var jobInfo : JobInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val componentName: ComponentName = ComponentName(this, ExampleJobService::class.java)
        var builder: JobInfo.Builder = JobInfo.Builder(JOB_ID, componentName)
        builder.setRequiresCharging(false)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setPersisted(true)
            .setPeriodic(50000)

        jobInfo = builder.build()
        jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler

    }


    fun scheduleJob(view: View){

        val resultCode = jobScheduler.schedule(jobInfo)
        if(resultCode == JobScheduler.RESULT_SUCCESS){
            Log.d(TAG, "job scheduled ")

        } else {
            Log.d(TAG, "job scheduled failed")
        }
    }

    fun cancelJob(view: View){

        val jobScheduler: JobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.cancelAll()
        Log.d(TAG, "Job cancelled")
    }
}