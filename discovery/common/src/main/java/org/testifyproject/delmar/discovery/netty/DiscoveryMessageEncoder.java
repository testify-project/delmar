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

import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;
import static io.netty.util.CharsetUtil.UTF_8;
import java.net.InetSocketAddress;
import java.util.List;
import javax.inject.Inject;
import org.jvnet.hk2.annotations.Service;
import org.testifyproject.delmar.discovery.message.DiscoveryMessage;
import org.testifyproject.delmar.discovery.network.NetworkSetting;

/**
 * DiscoveryMessageEncoder.
 * @author saden
 */
@Service
public class DiscoveryMessageEncoder extends MessageToMessageEncoder<DiscoveryMessage> {

    private final Gson gson;
    private final NetworkSetting networkSetting;

    @Inject
    DiscoveryMessageEncoder(Gson gson, NetworkSetting networkSetting) {
        this.gson = gson;
        this.networkSetting = networkSetting;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, DiscoveryMessage msg, List<Object> out) throws Exception {
        String json = gson.toJson(msg);

        ByteBuf data = ctx.alloc()
                .buffer(json.length())
                .writeBytes(json.getBytes(UTF_8));

        InetSocketAddress recipient = networkSetting.getMulticastSocketAddress();

        out.add(new DatagramPacket(data, recipient));
    }

}
