/*
 *******************************************************************************
 * Copyright (c) 2020 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package org.eclipse.microprofile.fault.tolerance.tck.config.fallback;

import org.eclipse.microprofile.fault.tolerance.tck.config.TestConfigExceptionA;
import org.eclipse.microprofile.fault.tolerance.tck.config.TestConfigExceptionB;
import org.eclipse.microprofile.faulttolerance.Fallback;

import javax.enterprise.context.Dependent;

/**
 * Bean with methods to help test configuration of parameters of {@link Fallback}
 */
@Dependent
public class FallbackConfigBean {

    /**
     * Throws TestConfigExceptionA unless Fallback.applyOn is configured to cause it to fall back
     */
    @Fallback(fallbackMethod = "theFallback", applyOn = TestConfigExceptionB.class)
    public String applyOnMethod() {
        throw new TestConfigExceptionA();
    }

    /**
     * TestConfigExceptionA will cause fallback to run, unless skipOn is configured
     */
    @Fallback(fallbackMethod = "theFallback")
    public String skipOnMethod() {
        throw new TestConfigExceptionA();
    }

    public String theFallback() {
        return "FALLBACK";
    }
}
