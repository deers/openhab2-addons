/**
 * Copyright (c) 2014-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.nuki.internal.dto;

/**
 * The {@link BridgeApiInfoIdDto} class defines the Data Transfer Object (POJO) for the Nuki Bridge API /info endpoint.
 * It is a nested JSON object of {@link BridgeApiInfoDto}.
 *
 * @author Markus Katter - Initial contribution
 */
public class BridgeApiInfoIdDto {

    private int hardwareId;
    private int serverId;

    public int getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(int hardwareId) {
        this.hardwareId = hardwareId;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

}