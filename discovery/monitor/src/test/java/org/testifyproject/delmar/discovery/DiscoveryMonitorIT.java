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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.testifyproject.annotation.Real;
import org.testifyproject.annotation.Scan;
import org.testifyproject.di.hk2.HK2Properties;
import org.testifyproject.junit4.integration.HK2IntegrationTest;

/**
 *
 * @author saden
 */
@Scan(HK2Properties.DEFAULT_DESCRIPTOR)
@RunWith(HK2IntegrationTest.class)
public class DiscoveryMonitorIT {

    @Real
    DiscoveryMonitor sut;
    
    @Real
    DiscoveryBroadcaster broadcaster;

    @Test
    public void verlidateInjection() throws Exception {
        Thread advertiserThread = new Thread(sut);
        advertiserThread.start();
        Thread.sleep(5000);
        broadcaster.broadcast();

        advertiserThread.interrupt();

    }

}
