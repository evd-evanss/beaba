package com.sugarspoon.beaba.base

import com.sugarspoon.beaba.extensions.createAppSettingsIntent
import com.sugarspoon.beaba.utils.helpers.PermissionsHelper

/**
 * Created by Evandro Costa
 */
abstract class BasePermissionActivity : BaseActivity(), PermissionsHelper.OnPermissionResult {

    private var permissionHelper: PermissionsHelper? = null
    private lateinit var actionToExecute: (() -> Unit)

    /**
     * Initialize the request permissions flow.
     *
     * @param action the action that must be executed after getting the permissions.
     * @param permissions a vararg of the permissions that must be requested to execute the action.
     */
    protected fun askPermissions(action: (() -> Unit), vararg permissions: String) {
        permissionHelper = PermissionsHelper(
            activity = this,
            requestCode = 0,
            permissions = permissions.toList().toTypedArray(),
            onPermissionResult = this
        )

        actionToExecute = action
        permissionHelper?.dispatchPermissions()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        permissionHelper?.onRequestPermissionsResult(requestCode, permissions, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onAllPermissionsGranted(requestCode: Int) {
        actionToExecute.invoke()
    }

    override fun onPermissionsDenied(
        requestCode: Int,
        deniedPermissions: List<String>,
        deniedPermissionsWithNeverAskAgainOption: List<String>
    ) {
        if (deniedPermissionsWithNeverAskAgainOption.isNotEmpty())
            startActivity(createAppSettingsIntent())
    }
}
