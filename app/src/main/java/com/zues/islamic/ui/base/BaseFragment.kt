package com.raza.android.videocompressor.fragments

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import kotlinx.android.synthetic.*

/*
* Created by Khawar on 10/12/2016.
 */

abstract class BaseFragment : Fragment() {

    var helper: FragmentNavigationHelper? = null
    var sharedPreferences: SharedPreferences? = null
    var parentView: View? = null

    abstract val layoutId: Int

    open fun getTitle(): String {
        return ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        try {
            helper = activity as FragmentNavigationHelper?
        } catch (e: Exception) {
        }

        sharedPreferences = activity?.getSharedPreferences("dfg", Context.MODE_PRIVATE)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        parentView = inflater.inflate(layoutId, container, false)
        parentView!!.isClickable = true

        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(parentView!!, savedInstanceState)

    }

    fun setParentView() {
        this.parentView = null
    }


    open fun initViews(parent: View, savedInstanceState: Bundle?) {

    }

    open fun showBackButton(): Boolean {
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.clearFindViewByIdCache()
        this.hideKeyboard()
    }

    fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return false
    }

    fun hideKeyboard() {
        if (view != null) {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view!!.windowToken, 0)
        }
    }

    fun showKeyboard() {
        val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun showKeyboard(view: View) {
        val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun hideKeyboard(input: EditText) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(input.windowToken, 0)
    }

    /**
     * An interface to load and make navigation. The parent activity must provide an implementation for this interface.
     *
     * @author khawarraza
     */
    interface FragmentNavigationHelper {

        fun addFragment(f: BaseFragment, clearBackStack: Boolean, addToBackstack: Boolean)

        fun addFragment(f: BaseFragment, layoutId: Int, clearBackStack: Boolean, addToBackstack: Boolean)

        fun replaceFragment(f: BaseFragment, clearBackStack: Boolean, addToBackstack: Boolean)

        fun replaceFragment(f: BaseFragment, layoutId: Int, clearBackStack: Boolean, addToBackstack: Boolean)

        fun goBack()

        fun showLoading(msg: String)

        fun hideLoading()

        fun showToast(msg: String)
    }

//    fun askPermissionIfRequired(permission: String, requestCode: Int): Boolean {
//        if (!canMakeSmores()) {
//            return true
//        }
//        // Here, thisActivity is the current activity
//        if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(activity,
//                    arrayOf(permission),
//                    requestCode)
//            return false
//        }
//        return true
//    }

    private fun canMakeSmores(): Boolean {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1

    }

    companion object {

        private val ANIMATION_DURATION: Long = 700
    }

    open fun onBackPressed() {

    }

}
