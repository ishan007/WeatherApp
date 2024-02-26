package com.example.weatherapp

import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
abstract class BaseUnitTest{

    @Before
    open fun setup(){
        MockitoAnnotations.initMocks(this)
    }

}