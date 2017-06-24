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

import io.netty.channel.socket.DatagramChannel;
import javax.inject.Inject;
import javax.inject.Provider;
import org.jvnet.hk2.annotations.Service;
import org.testifyproject.delmar.discovery.message.DiscoveryMessage;
import org.testifyproject.delmar.discovery.message.DiscoveryMessageBuilder;
import org.testifyproject.delmar.discovery.message.DiscoveryType;

/**
 * DiscoveryBroadcaster.
 * @author saden
 */
@Service
public class DiscoveryBroadcaster {

    private final DatagramChannel datagramChannel;
    private final Provider<DiscoveryMessageBuilder> messageBuilderProvider;

    @Inject
    DiscoveryBroadcaster(DatagramChannel datagramChannel,
            Provider<DiscoveryMessageBuilder> messageBuilderProvider) {
        this.datagramChannel = datagramChannel;
        this.messageBuilderProvider = messageBuilderProvider;
    }

    public void broadcast() {
        DiscoveryMessage nodeMessage = messageBuilderProvider.get()
                .build(DiscoveryType.BROADCAST);

        broadcast(nodeMessage);
    }

    public void broadcast(DiscoveryMessage nodeMessage) {
        datagramChannel.writeAndFlush(nodeMessage).awaitUninterruptibly();
    }

}
