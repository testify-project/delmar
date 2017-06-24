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

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import org.jvnet.hk2.annotations.Service;

/**
 * NetworkUtil is a utility class that provides methods for discovering network
 * information about the machine at runtime.
 * @author saden
 */
@Service
public class NetworkUtil {

    public NavigableMap<String, LocalNetwork> findAddresses() {
        //we use reverse order comperator to preserve private network order
        //192.168.0.0 - 192.168.255.255 (local router network)
        //172.16.0.0 - 172.31.255.255 (bridge networks)
        //10.0.0.0 - 10.255.255.255 (corporate networks)
        NavigableMap<String, LocalNetwork> addresses = new ConcurrentSkipListMap<>(Collections.reverseOrder());

        // iterate over the known network interfaces known
        List<NetworkInterface> networkInterfaces;

        try {
            networkInterfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
        } catch (SocketException e) {
            //TODO: log exception
            return Collections.emptyNavigableMap();
        }

        networkInterfaces.parallelStream().forEach(networkInterface -> {
            try {
                if (networkInterface.isUp()
                        && !networkInterface.isLoopback()
                        && !networkInterface.isVirtual()
                        && networkInterface.supportsMulticast()) {

                    List<InetAddress> inetAddresses = Collections.list(networkInterface.getInetAddresses());

                    inetAddresses.parallelStream().forEach(inetAddress -> {
                        try {
                            if (inetAddress instanceof Inet4Address && inetAddress.isReachable(3000)) {
                                //TODO: should we try to see if we can connect to the internet?
                                //SocketChannel socket = SocketChannel.open();
                                //socket.socket().setSoTimeout(3000);
                                //socket.bind(new InetSocketAddress(inetAddress, 0));
                                //socket.connect(new InetSocketAddress("google.com", 80));
                                //socket.close();

                                //TODO: should we ignore bridge networks?
                                LocalNetwork network = new LocalNetwork(networkInterface, inetAddress);
                                addresses.put(inetAddress.getHostAddress(), network);
                            }
                        } catch (IOException e) {
                            //TODO: log exception
                        }

                    });
                }
            } catch (IOException e) {
                //TODO: log exception
            }
        });

        return addresses;
    }

    public Optional<LocalNetwork> findDefaultAddress() {
        NavigableMap<String, LocalNetwork> addresses = findAddresses();

        return addresses.isEmpty()
                ? Optional.empty()
                : Optional.of(addresses.firstEntry().getValue());
    }

}
