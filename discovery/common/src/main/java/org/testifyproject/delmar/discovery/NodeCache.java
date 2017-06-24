/*
 * Copyright 2016-2017 Testify Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testifyproject.delmar.discovery;

import java.util.Set;

/**
 * NodeCache.
 * @author saden
 */
public class NodeCache {

    private final Set<String> broadcasters;
    private final Set<String> monitors;

    NodeCache(Set<String> broadcasters, Set<String> monitors) {
        this.broadcasters = broadcasters;
        this.monitors = monitors;
    }

    public void addBroadcaster(String address) {
        broadcasters.add(address);
    }

    public void addMonitor(String address) {
        broadcasters.add(address);
    }

    public Set<String> getBroadcasters() {
        return broadcasters;
    }

    public Set<String> getMonitors() {
        return monitors;
    }

    public void clear() {
        monitors.clear();
        broadcasters.clear();
    }

}
