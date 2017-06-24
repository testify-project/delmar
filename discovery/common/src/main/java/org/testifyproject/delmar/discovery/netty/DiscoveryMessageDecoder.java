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
import com.google.gson.JsonSyntaxException;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.List;
import javax.inject.Inject;
import org.jvnet.hk2.annotations.Service;
import org.testifyproject.delmar.discovery.message.DiscoveryMessage;

/**
 * DiscoveryMessageDecoder.
 * @author saden
 */
@Service
public class DiscoveryMessageDecoder extends MessageToMessageDecoder<DatagramPacket> {

    private final Gson gson;

    @Inject
    DiscoveryMessageDecoder(Gson gson) {
        this.gson = gson;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket msg, List<Object> out) throws Exception {
        try {
            ByteBuf data = msg.content();
            String json = data.toString(UTF_8);
            DiscoveryMessage nodeMessage = gson.fromJson(json, DiscoveryMessage.class);
            out.add(nodeMessage);
        } catch (JsonSyntaxException e) {
            //TODO: note the exception though we should never rethrow it because
            //we could be sent an invalid message.
        }

    }

}
