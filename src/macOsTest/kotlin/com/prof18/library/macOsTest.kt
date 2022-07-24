package com.prof18.library

import kotlin.test.Test
import kotlin.test.assertTrue

class MacOsGreetingTest {

    @Test
    fun testExample() {
        assertTrue(Greeting().greeting().contains("macOS"), "Check macOS is mentioned")
    }
}