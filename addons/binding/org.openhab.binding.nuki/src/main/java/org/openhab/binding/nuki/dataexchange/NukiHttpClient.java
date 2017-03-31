/**
 * Copyright (c) 2014-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.nuki.dataexchange;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpResponseException;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.smarthome.config.core.Configuration;
import org.openhab.binding.nuki.NukiBindingConstants;
import org.openhab.binding.nuki.dto.BridgeApiInfoDto;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 2a4e8a643... Implemented NukiSmartLockHandler handleCommand OFF/ON
import org.openhab.binding.nuki.dto.BridgeApiLockActionDto;
import org.openhab.binding.nuki.dto.BridgeApiLockStateDto;
=======
>>>>>>> 0a5308483... Implemented NukiBridgeHandler initialize
=======
import org.openhab.binding.nuki.dto.BridgeApiLockStateDto;
>>>>>>> a3d389951... Implemented NukiSmartLockHandlerHandler initialize
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * The {@link NukiHttpClient} class is responsible for getting data from the Nuki Bridge.
 *
 * @author Markus Katter - Initial contribution
 */
public class NukiHttpClient {

    private final Logger logger = LoggerFactory.getLogger(NukiHttpClient.class);

    private HttpClient httpClient;
    private Configuration configuration;

    public NukiHttpClient(Configuration configuration) {
<<<<<<< HEAD
<<<<<<< HEAD
        logger.trace("Instantiating NukiHttpClient({})", configuration);
=======
>>>>>>> 0a5308483... Implemented NukiBridgeHandler initialize
=======
        logger.trace("Instantiating NukiHttpClient({})", configuration);
>>>>>>> 9964fbb2e... Tweaked Logging
        this.configuration = configuration;
        this.httpClient = new HttpClient();
        long connectTimeout = NukiBindingConstants.CLIENT_CONNECT_TIMEOUT;
        httpClient.setConnectTimeout(connectTimeout);
    }

    public void startClient() {
        logger.trace("Starting HttpClient");
        try {
            httpClient.start();
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
            logger.debug("Started httpClient[{}]", httpClient);
=======
>>>>>>> 0a5308483... Implemented NukiBridgeHandler initialize
=======
            logger.debug("Started httpClient[{}]", httpClient);
>>>>>>> 9964fbb2e... Tweaked Logging
=======
            logger.trace("Started httpClient[{}]", httpClient);
>>>>>>> c32d3861e... Using unique instance of NukiHttpClient for each request
        } catch (Exception e) {
            logger.error("Could not start NukiHttpClient! ERROR: {}", e.getMessage(), e);
        }
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public void stopClient() {
        logger.trace("Stopping HttpClient");
        try {
            if (httpClient.isStarted()) {
                httpClient.stop();
                logger.trace("Stopped NukiHttpClient[{}]", httpClient);
            }
        } catch (Exception e) {
<<<<<<< HEAD
            logger.error("Could not stop NukiHttpClient! ERROR: {}", e.getMessage());
            e.printStackTrace();
=======
    public void stop() {
        if (httpClient.isStarted()) {
            try {
=======
    public void stopClient() {
        try {
            if (httpClient.isStarted()) {
>>>>>>> 3662262e1... Implemented NukiHttpServer for Nuki Bridge callbacks
                httpClient.stop();
            }
<<<<<<< HEAD
>>>>>>> 0a5308483... Implemented NukiBridgeHandler initialize
=======
        } catch (Exception e) {
            logger.error("Could not stop NukiHttpClient! ERROR: {}", e.getMessage());
            e.printStackTrace();
>>>>>>> 3662262e1... Implemented NukiHttpServer for Nuki Bridge callbacks
=======
            logger.error("Could not stop NukiHttpClient! ERROR: {}", e.getMessage(), e);
>>>>>>> d79dc40ae... Incorporated various pull request review comments (#2019).
        }
    }

    public BridgeInfoResponse getBridgeInfo() {
        logger.debug("NukiHttpClient:getBridgeInfo");
<<<<<<< HEAD
<<<<<<< HEAD
=======
        BridgeInfoResponse bridgeInfoResponse = new BridgeInfoResponse();
>>>>>>> 0a5308483... Implemented NukiBridgeHandler initialize
=======
>>>>>>> a3d389951... Implemented NukiSmartLockHandlerHandler initialize
        String configIp = (String) configuration.get(NukiBindingConstants.CONFIG_IP);
        BigDecimal configPort = (BigDecimal) configuration.get(NukiBindingConstants.CONFIG_PORT);
        String configApiToken = (String) configuration.get(NukiBindingConstants.CONFIG_API_TOKEN);
        String uri = String.format(NukiBindingConstants.URI_INFO, configIp, configPort, configApiToken);
<<<<<<< HEAD
<<<<<<< HEAD
        logger.trace("uri[{}]", uri);
        try {
            ContentResponse contentResponse = httpClient.GET(uri);
            String contentResponseAsString = contentResponse.getContentAsString();
            logger.trace("contentResponseAsString[{}]", contentResponseAsString);
            BridgeApiInfoDto bridgeApiInfoDto = new Gson().fromJson(contentResponseAsString, BridgeApiInfoDto.class);
            BridgeInfoResponse bridgeInfoResponse = new BridgeInfoResponse(contentResponse.getStatus(), "");
            bridgeInfoResponse.setBridgeInfo(bridgeApiInfoDto);
            return bridgeInfoResponse;
        } catch (Exception e) {
            logger.error("Could not get Bridge Info! ERROR: {}", e.getMessage());
            if (e instanceof ExecutionException && e.getCause() instanceof HttpResponseException) {
                return new BridgeInfoResponse(((HttpResponseException) e.getCause()).getResponse().getStatus(),
                        ((HttpResponseException) e.getCause()).getResponse().getReason());
            } else {
                return new BridgeInfoResponse(500, e.getMessage());
            }
        }
    }

    public BridgeLockStateResponse getBridgeLockState(String nukiId) {
        logger.debug("NukiHttpClient:getBridgeLockState({})", nukiId);
        String configIp = (String) configuration.get(NukiBindingConstants.CONFIG_IP);
        BigDecimal configPort = (BigDecimal) configuration.get(NukiBindingConstants.CONFIG_PORT);
        String configApiToken = (String) configuration.get(NukiBindingConstants.CONFIG_APITOKEN);
        String uri = String.format(NukiBindingConstants.URI_LOCKSTATE, configIp, configPort, configApiToken, nukiId);
        logger.trace("uri[{}]", uri);
        try {
            ContentResponse contentResponse = httpClient.GET(uri);
            String contentResponseAsString = contentResponse.getContentAsString();
            logger.trace("contentResponseAsString[{}]", contentResponseAsString);
            int status = contentResponse.getStatus();
            if (status == 200) {
                BridgeApiLockStateDto bridgeApiLockStateDto = new Gson().fromJson(contentResponseAsString,
                        BridgeApiLockStateDto.class);
                BridgeLockStateResponse bridgeLockStateResponse = new BridgeLockStateResponse(status, "");
                bridgeLockStateResponse.setBridgeLockState(bridgeApiLockStateDto);
                return bridgeLockStateResponse;
            } else {
                logger.error("Nuki Smart Lock with NukiID[{}] not found!", nukiId);
                return new BridgeLockStateResponse(status, "Nuki Smart Lock with NukiID[" + nukiId + "] not found!");
            }
        } catch (Exception e) {
            logger.error("Could not get Bridge Lock State! ERROR: {}", e.getMessage());
            if (e instanceof ExecutionException && e.getCause() instanceof HttpResponseException) {
                return new BridgeLockStateResponse(((HttpResponseException) e.getCause()).getResponse().getStatus(),
                        ((HttpResponseException) e.getCause()).getResponse().getReason());
            } else {
                return new BridgeLockStateResponse(500, e.getMessage());
            }
        }
    }

    public BridgeLockActionResponse getBridgeLockAction(String nukiId, int lockAction) {
        logger.debug("NukiHttpClient:getBridgeLockAction({}, {})", nukiId, lockAction);
        String configIp = (String) configuration.get(NukiBindingConstants.CONFIG_IP);
        BigDecimal configPort = (BigDecimal) configuration.get(NukiBindingConstants.CONFIG_PORT);
        String configApiToken = (String) configuration.get(NukiBindingConstants.CONFIG_APITOKEN);
        String uri = String.format(NukiBindingConstants.URI_LOCKACTION, configIp, configPort, configApiToken, nukiId,
                lockAction);
        logger.trace("uri[{}]", uri);
        try {
            ContentResponse contentResponse = httpClient.GET(uri);
            String contentResponseAsString = contentResponse.getContentAsString();
            logger.trace("contentResponseAsString[{}]", contentResponseAsString);
            int status = contentResponse.getStatus();
            if (status == 200) {
                BridgeApiLockActionDto bridgeApiLockActionDto = new Gson().fromJson(contentResponseAsString,
                        BridgeApiLockActionDto.class);
                BridgeLockActionResponse bridgeLockActionResponse = new BridgeLockActionResponse(status, "");
                bridgeLockActionResponse.setBridgeLockAction(bridgeApiLockActionDto);
                return bridgeLockActionResponse;
            } else {
                logger.error("Nuki Smart Lock with NukiID[{}] not found!", nukiId);
                return new BridgeLockActionResponse(status, "Nuki Smart Lock with NukiID[" + nukiId + "] not found!");
            }
        } catch (Exception e) {
            logger.error("Could not execute lockAction[{}] on NukiID[{}]! ERROR: {}", lockAction, nukiId,
                    e.getMessage());
            if (e instanceof ExecutionException && e.getCause() instanceof HttpResponseException) {
                return new BridgeLockActionResponse(((HttpResponseException) e.getCause()).getResponse().getStatus(),
                        ((HttpResponseException) e.getCause()).getResponse().getReason());
            } else {
                return new BridgeLockActionResponse(500, e.getMessage());
            }
        }
=======
        logger.debug("uri[{}]", uri);
=======
        logger.trace("uri[{}]", uri);
>>>>>>> 9964fbb2e... Tweaked Logging
        try {
            startClient();
            ContentResponse contentResponse = httpClient.GET(uri);
            stopClient();
            String contentResponseAsString = contentResponse.getContentAsString();
            logger.trace("contentResponseAsString[{}]", contentResponseAsString);
            BridgeApiInfoDto bridgeApiInfoDto = new Gson().fromJson(contentResponseAsString, BridgeApiInfoDto.class);
            BridgeInfoResponse bridgeInfoResponse = new BridgeInfoResponse(contentResponse.getStatus(), "");
            bridgeInfoResponse.setBridgeInfo(bridgeApiInfoDto);
            return bridgeInfoResponse;
        } catch (Exception e) {
            logger.error("Could not get Bridge Info! ERROR: {}", e.getMessage());
            if (e instanceof ExecutionException && e.getCause() instanceof HttpResponseException) {
                return new BridgeInfoResponse(((HttpResponseException) e.getCause()).getResponse().getStatus(),
                        ((HttpResponseException) e.getCause()).getResponse().getReason());
            } else {
                return new BridgeInfoResponse(500, e.getMessage());
            }
        }
    }

    public BridgeLockStateResponse getBridgeLockState(String nukiId) {
        logger.debug("NukiHttpClient:getBridgeLockState({})", nukiId);
        String configIp = (String) configuration.get(NukiBindingConstants.CONFIG_IP);
        BigDecimal configPort = (BigDecimal) configuration.get(NukiBindingConstants.CONFIG_PORT);
        String configApiToken = (String) configuration.get(NukiBindingConstants.CONFIG_API_TOKEN);
        String uri = String.format(NukiBindingConstants.URI_LOCKSTATE, configIp, configPort, configApiToken, nukiId);
        logger.trace("uri[{}]", uri);
        try {
            startClient();
            ContentResponse contentResponse = httpClient.GET(uri);
            stopClient();
            String contentResponseAsString = contentResponse.getContentAsString();
            logger.trace("contentResponseAsString[{}]", contentResponseAsString);
            int status = contentResponse.getStatus();
            if (status == 200) {
                BridgeApiLockStateDto bridgeApiLockStateDto = new Gson().fromJson(contentResponseAsString,
                        BridgeApiLockStateDto.class);
                BridgeLockStateResponse bridgeLockStateResponse = new BridgeLockStateResponse(status, "");
                bridgeLockStateResponse.setBridgeLockState(bridgeApiLockStateDto);
                return bridgeLockStateResponse;
            } else {
                logger.error("Nuki Smart Lock with NukiID[{}] not found!", nukiId);
                return new BridgeLockStateResponse(status, "Nuki Smart Lock with NukiID[" + nukiId + "] not found!");
            }
        } catch (Exception e) {
            logger.error("Could not get Bridge Lock State! ERROR: {}", e.getMessage());
            if (e instanceof ExecutionException && e.getCause() instanceof HttpResponseException) {
                return new BridgeLockStateResponse(((HttpResponseException) e.getCause()).getResponse().getStatus(),
                        ((HttpResponseException) e.getCause()).getResponse().getReason());
            } else {
                return new BridgeLockStateResponse(500, e.getMessage());
            }
        }
<<<<<<< HEAD
        return bridgeInfoResponse;
>>>>>>> 0a5308483... Implemented NukiBridgeHandler initialize
=======
>>>>>>> a3d389951... Implemented NukiSmartLockHandlerHandler initialize
    }

    public BridgeLockActionResponse getBridgeLockAction(String nukiId, int lockAction) {
        logger.debug("NukiHttpClient:getBridgeLockAction({}, {})", nukiId, lockAction);
        String configIp = (String) configuration.get(NukiBindingConstants.CONFIG_IP);
        BigDecimal configPort = (BigDecimal) configuration.get(NukiBindingConstants.CONFIG_PORT);
        String configApiToken = (String) configuration.get(NukiBindingConstants.CONFIG_API_TOKEN);
        String uri = String.format(NukiBindingConstants.URI_LOCKACTION, configIp, configPort, configApiToken, nukiId,
                lockAction);
        logger.trace("uri[{}]", uri);
        try {
            startClient();
            ContentResponse contentResponse = httpClient.GET(uri);
            stopClient();
            String contentResponseAsString = contentResponse.getContentAsString();
            logger.trace("contentResponseAsString[{}]", contentResponseAsString);
            int status = contentResponse.getStatus();
            if (status == 200) {
                BridgeApiLockActionDto bridgeApiLockActionDto = new Gson().fromJson(contentResponseAsString,
                        BridgeApiLockActionDto.class);
                BridgeLockActionResponse bridgeLockActionResponse = new BridgeLockActionResponse(status, "");
                bridgeLockActionResponse.setBridgeLockAction(bridgeApiLockActionDto);
                return bridgeLockActionResponse;
            } else {
                logger.error("Nuki Smart Lock with NukiID[{}] not found!", nukiId);
                return new BridgeLockActionResponse(status, "Nuki Smart Lock with NukiID[" + nukiId + "] not found!");
            }
        } catch (Exception e) {
            logger.error("Could not execute lockAction[{}] on NukiID[{}]! ERROR: {}", lockAction, nukiId,
                    e.getMessage());
            if (e instanceof ExecutionException && e.getCause() instanceof HttpResponseException) {
                return new BridgeLockActionResponse(((HttpResponseException) e.getCause()).getResponse().getStatus(),
                        ((HttpResponseException) e.getCause()).getResponse().getReason());
            } else {
                return new BridgeLockActionResponse(500, e.getMessage());
            }
        }
    }

}
