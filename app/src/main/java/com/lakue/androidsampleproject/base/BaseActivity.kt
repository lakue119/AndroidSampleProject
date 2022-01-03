package com.lakue.androidsampleproject.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelLazy
import com.lakue.androidsampleproject.BR
import com.lakue.androidsampleproject.extension.showToast
import com.lakue.androidsampleproject.utils.Event
import java.lang.reflect.ParameterizedType

open class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutResId: Int,
) : AppCompatActivity() {
    val Tag = BaseActivity::class.java.name

    protected lateinit var binding: B

    private val viewModelClass = ((javaClass.genericSuperclass as ParameterizedType?)
        ?.actualTypeArguments
        ?.get(1) as Class<VM>).kotlin

    protected open val viewModel by ViewModelLazy(
        viewModelClass,
        { viewModelStore },
        { defaultViewModelProviderFactory }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        binding {
            lifecycleOwner = this@BaseActivity
            setVariable(BR.vm, viewModel)
            setVariable(BR.activity, this@BaseActivity)
        }
        viewModel {
            liveToast.observe(this@BaseActivity) { this@BaseActivity.showToast(it) }
        }
    }

    protected fun binding(action: B.() -> Unit) {
        binding.run(action)
    }

    protected fun viewModel(action: VM.() -> Unit) {
        viewModel.run(action)
    }

    protected infix fun <T> LiveData<T>.observe(action: (T) -> Unit) {
        observe(this@BaseActivity, action)
    }

    protected infix fun <T> LiveData<Event<T>>.eventObserve(action: (T) -> Unit) {
        observe(this@BaseActivity) {
            it.get(action)
        }
    }

}