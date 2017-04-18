/*
 * In The Name Of God
 * ========================================
 * [] File Name : AppComponent.java
 *
 * [] Creation Date : 04-18-2017
 *
 * [] Created By : Parham Alvani (parham.alvani@gmail.com)
 * =======================================
 */
/*
 * Copyright 2014 Open Networking Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package home.parham.sdnfv101;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.onlab.packet.Ethernet;
import org.onosproject.app.ApplicationService;
import org.onosproject.core.ApplicationId;
import org.onosproject.net.DeviceId;
import org.onosproject.net.flow.DefaultFlowRule;
import org.onosproject.net.flow.DefaultTrafficSelector;
import org.onosproject.net.flow.DefaultTrafficTreatment;
import org.onosproject.net.flow.FlowRule;
import org.onosproject.net.flow.FlowRuleService;
import org.onosproject.net.flow.TrafficSelector;
import org.onosproject.net.flow.TrafficTreatment;
import org.onosproject.net.host.HostService;
import org.onosproject.net.packet.PacketPriority;
import org.onosproject.net.packet.PacketProcessor;
import org.onosproject.net.packet.PacketService;
import org.onosproject.net.topology.TopologyService;
import org.onosproject.net.PortNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * SDNFV101 application component.
 */
@Component(immediate = true)
	public class AppComponent {

		private final Logger log = LoggerFactory.getLogger(getClass());

		@Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
		private FlowRuleService flowRuleService;

		@Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
		private ApplicationService applicationService;

		@Activate
		protected void activate() {
			ApplicationId id = applicationService.getId("home.parham.sdnfv101");

			/*
			 * Let's build the rule for it's source.
			 */
			FlowRule.Builder fb = DefaultFlowRule.builder();

			/* General flow information */
			fb.forDevice(DeviceId.deviceId("of:00000000000000b0"));
			fb.makePermanent();
			fb.withPriority(10);
			fb.fromApp(id);

			/* Flow selection */
			TrafficSelector.Builder sb = DefaultTrafficSelector.builder();
			sb.matchInPort(PortNumber.portNumber(3));
			fb.withSelector(sb.build());

			/* Flow treatment */
			TrafficTreatment.Builder tb = DefaultTrafficTreatment.builder();
			tb.setOutput(PortNumber.NORMAL);
			fb.withTreatment(tb.build());

			/* Flow applying */
			FlowRule f = fb.build();
			this.flowRuleService.applyFlowRules(f);
		}

		@Deactivate
		protected void deactivate() {
			log.info("Stopped");
		}
	}
