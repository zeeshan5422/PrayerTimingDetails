package com.raza.android.videocompressor.activities

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.raza.android.videocompressor.fragments.BaseFragment
import com.zues.islamic.R
import com.zues.islamic.views.LoadingDialog

import java.util.Stack

abstract class BaseActivity : AppCompatActivity(), BaseFragment.FragmentNavigationHelper {

    var currentFragment: BaseFragment? = null
    var prefs: SharedPreferences? = null
        private set
    private val mFragments = Stack<Fragment>()

    var loadingDialog : LoadingDialog? = null

    abstract val layoutId: Int

    val fragmentMinStackSize: Int
        get() = 1

    val fragmetnStackSize: Int
        get() = mFragments.size

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = getSharedPreferences("", Context.MODE_PRIVATE)

        setContentView(layoutId)

        initViews(savedInstanceState)
    }

    open fun initViews(savedInstanceState: Bundle?) {
    }

    override fun onCreateView(name: String?, context: Context?, attrs: AttributeSet?): View? {
//        loadingDialog = LoadingDialog(context!!)
        return super.onCreateView(name, context, attrs)
    }

    override fun showLoading(msg: String){
        if (loadingDialog == null){
            loadingDialog = LoadingDialog(baseContext)
        }
        loadingDialog = LoadingDialog(this)
        loadingDialog?.setMessage(msg)
        loadingDialog?.show()
    }


    override fun hideLoading(){
        loadingDialog?.dismiss()
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun addFragment(f: BaseFragment, clearBackStack: Boolean, addToBackstack: Boolean) {
        addFragment(f, R.id.layout_container, clearBackStack, addToBackstack)
    }

    override fun addFragment(f: BaseFragment, layoutId: Int, clearBackStack: Boolean, addToBackstack: Boolean) {
        try {
            if (clearBackStack) {
                clearFragmentBackStack()
            }

            val transaction = supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(
                    R.anim.enter_from_left, R.anim.exit_to_left, R.anim.enter_from_right, R.anim.exit_to_right)
            transaction.add(layoutId, f)
            if (addToBackstack) {
                transaction.addToBackStack(null)
            }
            transaction.commit()

            currentFragment = f
            mFragments.push(f)

            onFragmentBackStackChanged()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun replaceFragment(f: BaseFragment, clearBackStack: Boolean, addToBackstack: Boolean) {
        replaceFragment(f, R.id.layout_container, clearBackStack, addToBackstack)
    }

    override fun replaceFragment(f: BaseFragment, layoutId: Int, clearBackStack: Boolean, addToBackstack: Boolean) {
        try {
            if (clearBackStack) {
                clearFragmentBackStack()
            }

            val transaction = supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(
                    R.anim.enter_from_left, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
            transaction.replace(layoutId, f)
            if (addToBackstack) {
                transaction.addToBackStack(null)
            }

            transaction.commit()

            currentFragment = f
            mFragments.push(f)

            onFragmentBackStackChanged()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun goBack() {
        if (supportFragmentManager.backStackEntryCount <= fragmentMinStackSize) {
            finish()
            return
        }
        if (currentFragment != null) {
            currentFragment?.onBackPressed()
        }
        supportFragmentManager.popBackStack()
        if (!mFragments.isEmpty()) {
            mFragments.pop()
        }
        currentFragment = (if (mFragments.isEmpty()) null else if (mFragments.peek() is BaseFragment) mFragments.peek() else null) as BaseFragment

        onFragmentBackStackChanged()
    }

    fun clearFragmentBackStack() {
        val fm = supportFragmentManager
        for (i in 0 until fm.backStackEntryCount) {
            fm.popBackStack()
        }

        if (!mFragments.isEmpty()) {
            val homeFragment = mFragments[0]
            mFragments.clear()
            mFragments.push(homeFragment)
        }

    }

    open fun onFragmentBackStackChanged() {

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (supportFragmentManager.backStackEntryCount <= fragmentMinStackSize) {
                finish()
                return true
            } else {
                goBack()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    fun hideKeyboard(v: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }

    fun showKeyboard() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun changeStatusBarColor(color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = color
        }
    }

    fun askPermissionIfRequired(permission: String, requestCode: Int): Boolean {
        if (!canMakeSmores()) {
            return true
        }
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(permission),
                    requestCode)
            return false
        }
        return true
    }

    private fun canMakeSmores(): Boolean {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1

    }
}
