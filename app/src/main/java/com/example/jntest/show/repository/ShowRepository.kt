package com.example.jntest.show.repository

import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ShowRepository @Inject constructor(@ApplicationContext val context: ApplicationContext) {
}