package com.hu.presentation.features.main

import androidx.activity.viewModels
import com.hu.presentation.base.BaseActivity
import com.hu.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor(): BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()

    override fun observerViewModel() {
    }

}