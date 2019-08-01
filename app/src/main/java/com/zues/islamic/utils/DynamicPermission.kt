package com.zues.islamic.utils

import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity

import java.util.ArrayList

/**
 * Created by akhtarz on 4/26/17.
 */

class DynamicPermission {

    private var mContext: AppCompatActivity? = null
    private var mPermissionList: List<String>? = null
    val TAG_PERMISSION = 11

    val isCompatibleOS: Boolean
        get() = Build.VERSION.SDK_INT >= 23

    constructor(mContext: AppCompatActivity) {
        this.mContext = mContext
    }

    constructor(mContext: AppCompatActivity, permissionList: List<String>) {
        this.mContext = mContext
        this.mPermissionList = permissionList
    }

    fun checkAndRequestPermissions(): Boolean {
        if (isCompatibleOS) {
            val listPermissionsNeeded = ArrayList<String>()
            for (permission in this.mPermissionList!!) {
                if (!isPermissionGranted(permission)) {
                    listPermissionsNeeded.add(permission)
                }
            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(mContext!!, listPermissionsNeeded.toTypedArray(), TAG_PERMISSION)
                return false
            }
        }
        return true
    }

    fun isPermissionGranted(permission: String): Boolean {
        val permissionCAMERA = ContextCompat.checkSelfPermission(mContext!!,
                permission)
        return permissionCAMERA == PackageManager.PERMISSION_GRANTED

    }

    fun requestSinglePermission(permission: String) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(mContext!!,
                        permission)) {
            ActivityCompat.requestPermissions(mContext!!,
                    arrayOf(permission),
                    TAG_PERMISSION)
        } else {
            ActivityCompat.requestPermissions(mContext!!,
                    arrayOf(permission),
                    TAG_PERMISSION)
        }
    }
}
