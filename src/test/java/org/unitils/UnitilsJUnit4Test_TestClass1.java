/*
 * Copyright 2006 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.unitils;

import org.junit.*;
import static org.unitils.TracingTestListener.*;

/**
 * JUnit 4 test class containing 2 active and 1 ignored test method
 */
public class UnitilsJUnit4Test_TestClass1 extends UnitilsJUnit4 {


    private static TracingTestListener tracingTestListener;

    public static void setTracingTestListener(TracingTestListener testListener) {
        tracingTestListener = testListener;
    }


    @BeforeClass
    public static void beforeClass() {
        addTestInvocation(TEST_BEFORE_CLASS, UnitilsJUnit4Test_TestClass1.class, null);
    }

    @AfterClass
    public static void afterClass() {
        addTestInvocation(TEST_AFTER_CLASS, UnitilsJUnit4Test_TestClass1.class, null);
    }

    @Before
    public void setUp() {
        addTestInvocation(TEST_SET_UP, this, null);
    }

    @After
    public void tearDown() {
        addTestInvocation(TEST_TEAR_DOWN, this, null);
    }

    @Test
    public void test1() {
        addTestInvocation(TEST_METHOD, this, "test1");
    }

    @Test
    public void test2() {
        addTestInvocation(TEST_METHOD, this, "test2");
    }

    @Ignore
    @Test
    public void test3() {
        addTestInvocation(TEST_METHOD, this, "test3");
    }

    private static void addTestInvocation(String invocation, Object test, String testMethodName) {
        if (tracingTestListener != null) {
            tracingTestListener.addTestInvocation(invocation, test, testMethodName);
        }
    }
}