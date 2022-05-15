package com.example.jntest.show.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShowViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
}