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

import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.glassfish.hk2.api.Factory;
import org.jvnet.hk2.annotations.Service;

/**
 * LocalNetworkFactory.
 * @author saden
 */
@Service
public class NetworkSettingFactory implements Factory<NetworkSetting> {

    private static final String DEFAULT_MULTICAST_ADDRESS = "237.2.9.79";
    private static final Integer DEFAULT_MUTLICAST_PORT = 9109;

    private final NetworkUtil networkUtil;

    @Inject
    NetworkSettingFactory(NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;
    }

    @Singleton
    @Override
    public NetworkSetting provide() {
        LocalNetwork localNetwork = networkUtil.findDefaultAddress().get();

        InetSocketAddress multicastAddress
                = new InetSocketAddress(DEFAULT_MULTICAST_ADDRESS, DEFAULT_MUTLICAST_PORT);

        InetSocketAddress localAddress
                = new InetSocketAddress(localNetwork.getAddress(), DEFAULT_MUTLICAST_PORT);

        NetworkInterface networkInterface = localNetwork.getNetworkInterface();

        return new NetworkSetting(localAddress,
                multicastAddress,
                networkInterface,
                DEFAULT_MUTLICAST_PORT);
    }

    @Override
    public void dispose(NetworkSetting instance) {
    }

}
