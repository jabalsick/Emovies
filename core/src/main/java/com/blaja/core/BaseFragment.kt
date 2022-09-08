package com.blaja.core

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel


abstract class BaseFragment<VM : ViewModel> : Fragment() {
    protected abstract val mViewModel: VM


}