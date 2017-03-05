/**
 * Copyright (c) 2014-2016 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.nuki.handler;

<<<<<<< HEAD
import java.util.List;

import org.eclipse.smarthome.core.library.types.OnOffType;
import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.Channel;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusDetail;
import org.eclipse.smarthome.core.thing.binding.BaseBridgeHandler;
import org.eclipse.smarthome.core.types.Command;
import org.eclipse.smarthome.core.types.State;
import org.openhab.binding.nuki.NukiBindingConstants;
import org.openhab.binding.nuki.dataexchange.BridgeInfoResponse;
import org.openhab.binding.nuki.dataexchange.NukiHttpClient;
import org.openhab.binding.nuki.dataexchange.NukiHttpServer;
import org.openhab.binding.nuki.dataexchange.NukiHttpServerListener;
import org.openhab.binding.nuki.dto.BridgeApiLockStateRequestDto;
=======
import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusDetail;
import org.eclipse.smarthome.core.thing.binding.BaseBridgeHandler;
import org.eclipse.smarthome.core.types.Command;
<<<<<<< HEAD
>>>>>>> 2a58c752c... Nuki NoOp Implementation
=======
import org.openhab.binding.nuki.dataexchange.BridgeInfoResponse;
import org.openhab.binding.nuki.dataexchange.NukiHttpClient;
>>>>>>> 0a5308483... Implemented NukiBridgeHandler initialize
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link NukiBridgeHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Markus Katter - Initial contribution
 */
<<<<<<< HEAD
public class NukiBridgeHandler extends BaseBridgeHandler implements NukiHttpServerListener {

    private final static Logger logger = LoggerFactory.getLogger(NukiBridgeHandler.class);

    private NukiHttpClient nukiHttpClient;
    private NukiHttpServer nukiHttpServer;

    public NukiBridgeHandler(Bridge bridge) {
        super(bridge);
        logger.trace("Instantiating NukiBridgeHandler({})", bridge);
    }

    public NukiHttpClient getNukiHttpClient() {
        return nukiHttpClient;
=======
public class NukiBridgeHandler extends BaseBridgeHandler {

    private final static Logger logger = LoggerFactory.getLogger(NukiBridgeHandler.class);

    private NukiHttpClient nukiHttpClient;

    public NukiBridgeHandler(Bridge bridge) {
        super(bridge);
>>>>>>> 2a58c752c... Nuki NoOp Implementation
    }

    public NukiHttpClient getNukiHttpClient() {
        return nukiHttpClient;
    }

    @Override
    public void initialize() {
<<<<<<< HEAD
        logger.debug("NukiBridgeHandler:initialize()");
        nukiHttpClient = new NukiHttpClient(this.getConfig());
        nukiHttpServer = NukiHttpServer.getInstance(this.getConfig(), this);
        BridgeInfoResponse bridgeInfoResponse = nukiHttpClient.getBridgeInfo();
        if (bridgeInfoResponse.getStatus() == 200) {
            updateStatus(ThingStatus.ONLINE, ThingStatusDetail.NONE,
                    "Found " + bridgeInfoResponse.getBridgeInfo().getScanResults().size() + " Nuki Smart Locks.");
        } else {
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR, bridgeInfoResponse.getMessage());
        }
=======
        logger.debug("NukiBridgeHandler:initialize");
<<<<<<< HEAD
        updateStatus(ThingStatus.ONLINE);
>>>>>>> 2a58c752c... Nuki NoOp Implementation
=======
        nukiHttpClient = new NukiHttpClient(this.getConfig());
        BridgeInfoResponse bridgeInfoResponse = nukiHttpClient.getBridgeInfo();
        if (bridgeInfoResponse.getStatus() == 200) {
            updateStatus(ThingStatus.ONLINE, ThingStatusDetail.NONE,
                    "Found " + bridgeInfoResponse.getBridgeInfo().getScanResults().size() + " Nuki Smart Locks.");
        } else {
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR, bridgeInfoResponse.getMessage());
        }
>>>>>>> 0a5308483... Implemented NukiBridgeHandler initialize
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        logger.debug("NukiBridgeHandler:handleCommand({}, {})", channelUID, command);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    @Override
    public void dispose() {
        logger.debug("NukiBridgeHandler:dispose");
        nukiHttpClient.stopClient();
        nukiHttpServer.stopServer();
    }

    @Override
    public void handleBridgeLockStateChange(BridgeApiLockStateRequestDto bridgeApiLockStateRequestDto) {
        logger.debug("NukiBridgeHandler:handleBridgeLockStateChange({})", bridgeApiLockStateRequestDto);
        String nukiId = String.valueOf(bridgeApiLockStateRequestDto.getNukiId());
        Bridge bridge = this.getThing();
        List<Thing> things = bridge.getThings();
        for (Thing thing : things) {
            String nukiIdThing = (String) thing.getConfiguration().get(NukiBindingConstants.CONFIG_NUKIID);
            if (nukiId.equals(nukiIdThing)) {
                Channel channel = thing.getChannel(NukiBindingConstants.CHANNEL_SMARTLOCKOPENCLOSE);
                State state = bridgeApiLockStateRequestDto.getState() == 1 ? OnOffType.ON : OnOffType.OFF;
                thing.getHandler().handleUpdate(channel.getUID(), state);
                logger.trace("Updated Nuki Smart Lock[{}] to state[{}]", nukiId, state);
                return;
            }
        }
        logger.error("Could not find and update Smart Lock[{}]", nukiId);
    }

=======
>>>>>>> 2a58c752c... Nuki NoOp Implementation
=======
    @Override
    public void dispose() {
        logger.debug("NukiBridgeHandler:dispose");
        nukiHttpClient.stop();
    }

>>>>>>> 0a5308483... Implemented NukiBridgeHandler initialize
}