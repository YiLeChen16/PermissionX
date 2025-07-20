package com.permission.yiledev

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment
//给回调函数类型变量起别名
typealias PermissionCallback = ((Boolean, List<String>) -> Unit)

/**
 * @description: 隐藏的Fragment，用于封装运行时权限请求API
 * @author YL Chen
 * @date 2025/7/20 14:39
 * @version 1.0
 */
class InvisibleFragment : Fragment() {

    //回调函数变量
    private var callback: PermissionCallback? = null

    /**
     * 对外开放的请求权限方法
     * @param cb PermissionCallback 注册的回调，权限请求结果会通过此回调通知回去
     * @param permission Array<out String> 需求请求的所有权限
     */
    fun requestNow(cb:PermissionCallback,vararg permission:String){
        this.callback = cb
        //调用系统方法请求权限
        requestPermissions(permission,1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    //将被拒绝的权限加入deniedList集合中
                    deniedList.add(permissions[index])
                }
            }
            //若deniedList为空则说明全部权限都请求成功
            val allGranted = deniedList.isEmpty()
            //将权限处理结果通知回注册的回调
            callback?.let {
                it(allGranted, deniedList)
            }
        }

    }
}