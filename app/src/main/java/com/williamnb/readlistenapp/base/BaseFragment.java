package com.williamnb.readlistenapp.base;

import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

public abstract class BaseFragment<VB extends ViewBinding, VM extends BaseViewModel>
        extends Fragment implements BaseView{

}
