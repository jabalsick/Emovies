package com.blaja.movies_data


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
@ExperimentalCoroutinesApi
abstract class BaseUnitTest {
    @get:Rule
    var instantTaskExecutorRule : TestRule = InstantTaskExecutorRule()

    @get: Rule
    var testCoroutineRule = TestCoroutineRule()

    abstract fun init()

    @Before
    open fun setUp() {
        init()
    }
}