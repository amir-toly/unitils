/*
 * Copyright 2013,  Unitils.org
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
package org.unitils.mock.core.proxy.impl;

import org.unitils.mock.core.BehaviorDefiningInvocations;
import org.unitils.mock.core.Scenario;
import org.unitils.mock.core.matching.MatchingInvocationBuilder;
import org.unitils.mock.core.proxy.ProxyInvocation;
import org.unitils.mock.core.util.CloneService;
import org.unitils.mock.mockbehavior.MockBehavior;
import org.unitils.mock.mockbehavior.impl.OriginalBehaviorInvokingMockBehavior;

public class PartialMockInvocationHandler<T> extends MockInvocationHandler<T> {


    public PartialMockInvocationHandler(BehaviorDefiningInvocations behaviorDefiningInvocations1, Scenario scenario, CloneService cloneService, MatchingInvocationBuilder matchingInvocationBuilder) {
        super(behaviorDefiningInvocations1, scenario, cloneService, matchingInvocationBuilder);
    }


    @Override
    protected MockBehavior getDefaultMockBehavior(ProxyInvocation proxyInvocation) {
        return new OriginalBehaviorInvokingMockBehavior();
    }
}