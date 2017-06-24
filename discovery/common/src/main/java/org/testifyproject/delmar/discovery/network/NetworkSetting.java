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
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * NetworkSetting.
 * @author saden
 */
@ToString
@EqualsAndHashCode
public class NetworkSetting {

    private final InetSocketAddress localSocketAddress;
    private final InetSocketAddress multicastSocketAddress;
    private final NetworkInterface networkInterface;
    private final Integer port;

    public NetworkSetting(InetSocketAddress localAddress,
            InetSocketAddress multicastAddress,
            NetworkInterface networkInterface,
            Integer port) {
        this.localSocketAddress = localAddress;
        this.multicastSocketAddress = multicastAddress;
        this.networkInterface = networkInterface;
        this.port = port;
    }

    public InetSocketAddress getLocalSocketAddress() {
        return localSocketAddress;
    }

    public InetSocketAddress getMulticastSocketAddress() {
        return multicastSocketAddress;
    }

    public InetAddress getLocalInetAddress() {
        return localSocketAddress.getAddress();
    }

    public InetAddress getMulticastInetAddress() {
        return multicastSocketAddress.getAddress();
    }

    public NetworkInterface getNetworkInterface() {
        return networkInterface;
    }

    public Integer getPort() {
        return port;
    }

}
