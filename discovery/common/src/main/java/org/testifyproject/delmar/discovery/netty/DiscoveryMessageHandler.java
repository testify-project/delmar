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
package org.testifyproject.delmar.discovery.netty;

import com.google.gson.JsonSyntaxException;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.SimpleChannelInboundHandler;
import javax.inject.Inject;
import javax.inject.Provider;
import org.jvnet.hk2.annotations.ContractsProvided;
import org.jvnet.hk2.annotations.Service;
import org.testifyproject.delmar.discovery.NodeCache;
import org.testifyproject.delmar.discovery.message.DiscoveryMessage;
import org.testifyproject.delmar.discovery.message.DiscoveryMessageBuilder;
import org.testifyproject.delmar.discovery.message.DiscoveryType;

/**
 * DiscoveryMessageHandler.
 * @author saden
 */
@Service
@ContractsProvided({DiscoveryMessageHandler.class, ChannelInboundHandler.class})
public class DiscoveryMessageHandler extends SimpleChannelInboundHandler<DiscoveryMessage> {

    private final NodeCache nodeCache;
    private final Provider<DiscoveryMessageBuilder> messageBuilderProvider;

    @Inject
    DiscoveryMessageHandler(NodeCache nodeCache, Provider<DiscoveryMessageBuilder> messageBuilderProvider) {
        this.nodeCache = nodeCache;
        this.messageBuilderProvider = messageBuilderProvider;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DiscoveryMessage msg) throws Exception {
        try {
            switch (msg.getType()) {
                case BROADCAST:
                    nodeCache.addBroadcaster(msg.getAddress());
                    DiscoveryMessage nodeMessage = messageBuilderProvider.get()
                            .build(DiscoveryType.MONITOR);

                    ctx.channel().writeAndFlush(nodeMessage);

                    break;
                case MONITOR:
                    nodeCache.addMonitor(msg.getAddress());
                    break;
            }
        } catch (JsonSyntaxException e) {
        }
    }

}
