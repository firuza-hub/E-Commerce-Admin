package az.red.e_commerce_admin_android.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.utils.UIEvent
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import kotlin.reflect.KClass

abstract class BaseFragment<Binding : ViewDataBinding, ViewModel : BaseViewModel> :
    Fragment() {

    protected abstract val layoutId: Int

    lateinit var binding: Binding

    protected abstract val kClass: KClass<ViewModel>
    val viewModel: ViewModel by lazy { getViewModel(kClass) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews.invoke(binding)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEventFlow.collect { event ->
                    when (event) {
                        is UIEvent.Error -> {
                            Snackbar.make(binding.root, event.message!!, Snackbar.LENGTH_LONG)
                                .show()
                        }
                        is UIEvent.Navigate -> {
                            findNavController().navigate(event.path)
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    protected open val bindViews: Binding.() -> Unit = {}

}