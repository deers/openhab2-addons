/**
 * Copyright (c) 2014-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.nuki.internal.dataexchange;

import org.openhab.binding.nuki.internal.dto.BridgeApiInfoDto;

/**
 * The {@link BridgeInfoResponse} class wraps {@link BridgeApiInfoDto} class.
 *
 * @author Markus Katter - Initial contribution
 */
public class BridgeInfoResponse extends NukiBaseResponse {

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> a3d389951... Implemented NukiSmartLockHandlerHandler initialize
=======
    private BridgeApiInfoDto bridgeInfo;

>>>>>>> d79dc40ae... Incorporated various pull request review comments (#2019).
    public BridgeInfoResponse(int status, String message) {
        super(status, message);
    }

<<<<<<< HEAD:addons/binding/org.openhab.binding.nuki/src/main/java/org/openhab/binding/nuki/dataexchange/BridgeInfoResponse.java
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 0a5308483... Implemented NukiBridgeHandler initialize
=======
>>>>>>> a3d389951... Implemented NukiSmartLockHandlerHandler initialize
    private BridgeApiInfoDto bridgeInfo;

=======
>>>>>>> d79dc40ae... Incorporated various pull request review comments (#2019).
=======
    public BridgeInfoResponse(NukiBaseResponse nukiBaseResponse) {
        super(nukiBaseResponse.getStatus(), nukiBaseResponse.getMessage());
    }

>>>>>>> 330cf6474... Incorporated various pull request review comments - Number 5 (#2019).:addons/binding/org.openhab.binding.nuki/src/main/java/org/openhab/binding/nuki/internal/dataexchange/BridgeInfoResponse.java
    public BridgeApiInfoDto getBridgeInfo() {
        return bridgeInfo;
    }

    public void setBridgeInfo(BridgeApiInfoDto bridgeInfo) {
        this.bridgeInfo = bridgeInfo;
    }

}