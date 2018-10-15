package com.example.ekaterina_sarycheva_shop.presentation.base

abstract class LifecyclePresenter<TView> {

    var view: TView? = null
    open fun onCreateView(view: TView) {
        this.view = view
    }

    open fun onDestroyView() {
        this.view = null
    }

    open fun onAppear() {}
    open fun onDisappear() {}
}