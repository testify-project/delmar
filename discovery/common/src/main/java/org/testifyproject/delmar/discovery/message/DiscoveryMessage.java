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
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DiscoveryMessage request.
 * @author saden
 */
@ToString
@EqualsAndHashCode
public class DiscoveryMessage {

    private final DiscoveryType type;
    private final Map<String, Object> properties;
    private final String address;

    DiscoveryMessage(DiscoveryType type, String address, Map<String, Object> properties) {
        this.type = type;
        this.address = address;
        this.properties = properties;
    }

    public DiscoveryType getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

}
