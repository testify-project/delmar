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
package org.testifyproject.delmar.discovery.network;

import java.net.InetAddress;
import java.net.NetworkInterface;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * LocalNetwork.
 * @author saden
 */
@ToString
@EqualsAndHashCode
public class LocalNetwork {

    private final NetworkInterface networkInterface;
    private final InetAddress address;

    public LocalNetwork(NetworkInterface networkInterface, InetAddress address) {
        this.networkInterface = networkInterface;
        this.address = address;
    }

    public NetworkInterface getNetworkInterface() {
        return networkInterface;
    }

    public InetAddress getAddress() {
        return address;
    }

}
