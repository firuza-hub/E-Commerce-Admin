package az.red.e_commerce_admin_android.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import kotlin.reflect.KClass

abstract class BaseFragment<Binding : ViewDataBinding, ViewModel : BaseViewModel> :
    Fragment() {
    protected abstract val bindingCallBack: (LayoutInflater, ViewGroup?, Boolean) -> Binding

    lateinit var binding: Binding

    protected abstract val kClass: KClass<ViewModel>
    val viewModel: ViewModel by lazy { getViewModel(kClass) { parametersOf(arguments) } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingCallBack.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews.invoke(binding)
    }

    protected open val bindViews: Binding.() -> Unit = {}

}