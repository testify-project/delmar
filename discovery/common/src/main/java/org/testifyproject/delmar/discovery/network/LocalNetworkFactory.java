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

import javax.inject.Inject;
import javax.inject.Singleton;
import org.glassfish.hk2.api.Factory;
import org.jvnet.hk2.annotations.Service;

/**
 * LocalNetworkFactory.
 * @author saden
 */
@Service
public class LocalNetworkFactory implements Factory<LocalNetwork> {

    private final NetworkUtil networkUtil;

    @Inject
    LocalNetworkFactory(NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;
    }

    @Singleton
    @Override
    public LocalNetwork provide() {
        return networkUtil.findDefaultAddress().get();
    }

    @Override
    public void dispose(LocalNetwork instance) {
    }

}
