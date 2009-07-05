/*
 * Copyright 2006-2009,  Unitils.org
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
package org.unitils.mock.core;

import org.unitils.mock.proxy.ProxyInvocation;

import java.util.ArrayList;
import java.util.List;

public class BehaviorDefiningInvocations {


    protected List<BehaviorDefiningInvocation> behaviorDefiningInvocations = new ArrayList<BehaviorDefiningInvocation>();


    public void addBehaviorDefiningInvocation(BehaviorDefiningInvocation behaviorDefiningInvocation) {
        behaviorDefiningInvocations.add(behaviorDefiningInvocation);
    }


    public void clear() {
        behaviorDefiningInvocations.clear();
    }


    public BehaviorDefiningInvocation getUnusedMatchingBehaviorDefiningInvocation(ProxyInvocation proxyInvocation) {
        for (BehaviorDefiningInvocation behaviorDefiningInvocation : behaviorDefiningInvocations) {
            if (behaviorDefiningInvocation.isUsed()) {
                continue;
            }
            if (behaviorDefiningInvocation.matches(proxyInvocation)) {
                return behaviorDefiningInvocation;
            }
        }
        return null;
    }

    public BehaviorDefiningInvocation getMatchingBehaviorDefiningInvocation(ProxyInvocation proxyInvocation) {
        for (BehaviorDefiningInvocation behaviorDefiningInvocation : behaviorDefiningInvocations) {
            if (behaviorDefiningInvocation.matches(proxyInvocation)) {
                return behaviorDefiningInvocation;
            }
        }
        return null;
    }


}