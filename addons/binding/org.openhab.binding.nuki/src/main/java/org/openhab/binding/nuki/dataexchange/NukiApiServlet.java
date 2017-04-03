/**
 * Copyright (c) 2014-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.nuki.dataexchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.smarthome.core.library.types.OnOffType;
import org.eclipse.smarthome.core.thing.Channel;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingRegistry;
import org.eclipse.smarthome.core.types.State;
import org.openhab.binding.nuki.NukiBindingConstants;
import org.openhab.binding.nuki.dto.BridgeApiLockStateRequestDto;
import org.openhab.binding.nuki.dto.NukiHttpServerStatusResponseDto;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * The {@link NukiApiServlet} class is responsible for handling the callbacks from the Nuki Bridge.
 *
 * @author Markus Katter - Initial contribution
 */
public class NukiApiServlet extends HttpServlet {

    private static final long serialVersionUID = -3601163473320027239L;
    private final Logger logger = LoggerFactory.getLogger(NukiApiServlet.class);

    private static final String PATH = "/nuki/bcb";
    private static final String CHARSET = "utf-8";
    private static final String APPLICATION_JSON = "application/json";

    private HttpService httpService;
    private ThingRegistry thingRegistry;

    public NukiApiServlet() {
        logger.trace("Instantiating NukiApiServlet()");
    }

    public HttpService getHttpService() {
        return httpService;
    }

    public void setHttpService(HttpService httpService) {
        this.httpService = httpService;
    }

    public ThingRegistry getThingRegistry() {
        return thingRegistry;
    }

    public void setThingRegistry(ThingRegistry thingRegistry) {
        this.thingRegistry = thingRegistry;
    }

    protected void activate(Map<String, Object> config) {
        logger.trace("NukiApiServlet:activate({})", config);
        Dictionary<String, String> servletParams = new Hashtable<String, String>();
        try {
            httpService.registerServlet(PATH, this, servletParams, httpService.createDefaultHttpContext());
            logger.debug("Started NukiApiServlet at path[{}]", PATH);
        } catch (ServletException | NamespaceException e) {
            logger.error("ERROR: {}", e.getMessage(), e);
        }
    }

    protected void modified(Map<String, Object> config) {
        logger.trace("NukiApiServlet:modified({})", config);
    }

    protected void deactivate() {
        logger.trace("NukiApiServlet:deactivate()");
        httpService.unregister(PATH);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.debug("NukiApiServlet:service URI[{}] request[{}]", request.getRequestURI(), request);

        StringBuffer requestContent = new StringBuffer();
        String line = null;
        try {
            BufferedReader bufferedReader = request.getReader();
            while ((line = bufferedReader.readLine()) != null) {
                requestContent.append(line);
            }
            logger.trace("requestContent[{}]", requestContent);
            BridgeApiLockStateRequestDto bridgeApiLockStateRequestDto = new Gson().fromJson(requestContent.toString(),
                    BridgeApiLockStateRequestDto.class);
            State state = bridgeApiLockStateRequestDto.getState() == NukiBindingConstants.LOCK_STATES_LOCKED
                    ? OnOffType.ON : OnOffType.OFF;
            String nukiId = Integer.toString(bridgeApiLockStateRequestDto.getNukiId());
            String nukiIdThing;
            for (Thing thing : thingRegistry.getAll()) {
                nukiIdThing = thing.getConfiguration().containsKey(NukiBindingConstants.CONFIG_NUKI_ID)
                        ? (String) thing.getConfiguration().get(NukiBindingConstants.CONFIG_NUKI_ID) : null;
                if (nukiIdThing != null && nukiIdThing.equals(nukiId)) {
                    logger.trace("Processing ThingUID[{}]", thing.getUID());
                    Channel channel = thing.getChannel(NukiBindingConstants.CHANNEL_SMARTLOCK_OPEN_CLOSE);
                    if (channel != null) {
                        thing.getHandler().handleUpdate(channel.getUID(), state);
                    }
                    channel = thing.getChannel(NukiBindingConstants.CHANNEL_SMARTLOCK_UNLATCH_CLOSE);
                    if (channel != null) {
                        thing.getHandler().handleUpdate(channel.getUID(), state);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Could not handle request! Message[{}]", e.getMessage(), e);
        }
        setHeaders(response);
        response.getWriter().println(new Gson().toJson(new NukiHttpServerStatusResponseDto("OK")));
    }

    private void setHeaders(HttpServletResponse response) {
        response.setCharacterEncoding(CHARSET);
        response.setContentType(APPLICATION_JSON);
    }

}
