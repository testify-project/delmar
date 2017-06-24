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
package org.testifyproject.delmar.discovery.message;

import java.util.Map;
import javax.inject.Inject;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;
import org.testifyproject.delmar.discovery.network.NetworkSetting;
import org.testifyproject.guava.common.collect.ImmutableMap;

/**
 * DiscoveryMessageBuilder.
 * @author saden
 */
@Service
@PerLookup
public class DiscoveryMessageBuilder {

    private final NetworkSetting networkSetting;
    private String address;
    private final ImmutableMap.Builder<String, Object> properties = ImmutableMap.builder();

    @Inject
    DiscoveryMessageBuilder(NetworkSetting networkSetting) {
        this.networkSetting = networkSetting;
    }

    public DiscoveryMessageBuilder address(String address) {
        this.address = address;

        return this;
    }

    public DiscoveryMessageBuilder property(String name, Object value) {
        this.properties.put(name, value);

        return this;
    }

    public DiscoveryMessageBuilder properties(Map<String, Object> properties) {
        this.properties.putAll(properties);

        return this;
    }

    public DiscoveryMessage build(DiscoveryType type) {
        if (address == null || address.isEmpty()) {
            address = networkSetting.getLocalInetAddress().getHostAddress();
        }

        return new DiscoveryMessage(type, address, properties.build());
    }
}
