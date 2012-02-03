/**
 * Copyright (C) 2011 Citrix Systems, Inc.  All rights reserved
 * 
 * This software is licensed under the GNU General Public License v3 or later.
 * 
 * It is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package com.cloud.exception;

import com.cloud.utils.IdentityProxy;

/**
 * CloudException is a generic exception class that has an IdentityProxy
 * object in it to enable on the fly conversion of database ids to uuids
 * by the API response serializer. Any exceptions that are thrown by
 * command invocations must extend this class, or the RuntimeCloudException
 * class, which extends RuntimeException instead of Exception like this
 * class does. 
 */

public class RuntimeCloudException extends RuntimeException {
    
	IdentityProxy id;
	
	public RuntimeCloudException(String table_name, Long id) {
		this.id = new IdentityProxy();
        this.id.setTableName(table_name);
        this.id.setValue(id);
    }

	public RuntimeCloudException(String message) {
		super(message);		
	}
	
	public RuntimeCloudException() {
		//this.id = new IdentityProxy(); ??
		//this.id = NULL; ??
	}
	
}
