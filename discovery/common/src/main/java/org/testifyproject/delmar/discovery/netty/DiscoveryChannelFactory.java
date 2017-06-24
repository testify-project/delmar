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

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFactory;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.socket.nio.NioDatagramChannel;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.IterableProvider;
import org.jvnet.hk2.annotations.Service;
import org.testifyproject.delmar.discovery.network.NetworkSetting;
import org.testifyproject.guava.common.base.Throwables;

/**
 * LocalNetworkFactory.
 * @author saden
 */
@Service
public class DiscoveryChannelFactory implements Factory<DatagramChannel> {

    private final NetworkSetting networkSetting;
    private final DiscoveryMessageDecoder messageDecoder;
    private final DiscoveryMessageEncoder messageEncoder;
    private final IterableProvider<ChannelInboundHandler> inboundHandlers;

    @Inject
    DiscoveryChannelFactory(NetworkSetting networkSetting,
            DiscoveryMessageDecoder messageDecoder,
            DiscoveryMessageEncoder messageEncoder,
            IterableProvider<ChannelInboundHandler> inboundHandlers) {
        this.networkSetting = networkSetting;
        this.messageDecoder = messageDecoder;
        this.messageEncoder = messageEncoder;
        this.inboundHandlers = inboundHandlers;
    }

    @Singleton
    @Override
    public DatagramChannel provide() {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        NioDatagramChannel channel = new NioDatagramChannel(InternetProtocolFamily.IPv4);

        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(eventLoopGroup)
                    .channelFactory((ChannelFactory<NioDatagramChannel>) () -> channel)
                    .localAddress(networkSetting.getLocalInetAddress(), networkSetting.getPort())
                    .option(ChannelOption.IP_MULTICAST_IF, networkSetting.getNetworkInterface())
                    .option(ChannelOption.IP_MULTICAST_TTL, 30)
                    .option(ChannelOption.IP_MULTICAST_LOOP_DISABLED, true)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .option(ChannelOption.SO_RCVBUF, 2048)
                    .handler(new ChannelInitializer<NioDatagramChannel>() {
                        @Override
                        public void initChannel(NioDatagramChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(messageDecoder);
                            pipeline.addLast(messageEncoder);
                            inboundHandlers.forEach(pipeline::addLast);
                        }
                    });

            bootstrap.bind(networkSetting.getPort()).sync();
            channel.joinGroup(networkSetting.getMulticastSocketAddress(), networkSetting.getNetworkInterface()).sync();
        } catch (InterruptedException e) {
            throw Throwables.propagate(e);
        }

        return channel;
    }

    @Override
    public void dispose(DatagramChannel instance) {
        instance.close();
        instance.eventLoop().shutdownGracefully();
    }

}
