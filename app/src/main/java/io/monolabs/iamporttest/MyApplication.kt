package io.monolabs.iamporttest

import android.app.Application
import com.iamport.sdk.domain.core.Iamport

class MyApplication:Application() {
  override fun onCreate() {
    super.onCreate()

    Iamport.create(this)
  }
}