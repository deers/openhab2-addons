/**
 * Copyright (c) 2014-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.nuki.dataexchange;

/**
 * The {@link NukiBaseResponse} class is the base class for API Responses.
 *
 * @author Markus Katter - Initial contribution
 */
public abstract class NukiBaseResponse {

<<<<<<< HEAD
<<<<<<< HEAD
    private int status;
    private String message;

    public NukiBaseResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
=======
    private int statusCode;
=======
    private int status;
>>>>>>> a3d389951... Implemented NukiSmartLockHandlerHandler initialize
    private String message;

    public NukiBaseResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

<<<<<<< HEAD
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
>>>>>>> 0a5308483... Implemented NukiBridgeHandler initialize
=======
    public void setStatus(int status) {
        this.status = status;
>>>>>>> a3d389951... Implemented NukiSmartLockHandlerHandler initialize
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

>>>>>>> 0a5308483... Implemented NukiBridgeHandler initialize
=======
>>>>>>> a3d389951... Implemented NukiSmartLockHandlerHandler initialize
}
