/**
 *  Copyright (C) 2010 Cloud.com, Inc.  All rights reserved.
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

package com.cloud.api.commands;

import org.apache.log4j.Logger;

import com.cloud.api.ApiConstants;
import com.cloud.api.ApiResponseHelper;
import com.cloud.api.BaseCmd;
import com.cloud.api.Implementation;
import com.cloud.api.Parameter;
import com.cloud.api.ServerApiException;
import com.cloud.api.response.ConfigurationResponse;
import com.cloud.configuration.ConfigurationManager;
import com.cloud.configuration.ConfigurationVO;

@Implementation(method="updateConfiguration", manager=ConfigurationManager.class, description="Updates a configuration.")
public class UpdateCfgCmd extends BaseCmd {
    public static final Logger s_logger = Logger.getLogger(UpdateCfgCmd.class.getName());
    private static final String s_name = "updateconfigurationresponse";

    /////////////////////////////////////////////////////
    //////////////// API parameters /////////////////////
    /////////////////////////////////////////////////////

    @Parameter(name=ApiConstants.NAME, type=CommandType.STRING, required=true, description="the name of the configuration")
    private String cfgName;

    @Parameter(name=ApiConstants.VALUE, type=CommandType.STRING, description="the value of the configuration")
    private String value;

    /////////////////////////////////////////////////////
    /////////////////// Accessors ///////////////////////
    /////////////////////////////////////////////////////

    public String getCfgName() {
        return cfgName;
    }

    public String getValue() {
        return value;
    }

    /////////////////////////////////////////////////////
    /////////////// API Implementation///////////////////
    /////////////////////////////////////////////////////

    public String getName() {
        return s_name;
    }
    
    @Override @SuppressWarnings("unchecked")
    public ConfigurationResponse getResponse() {
        ConfigurationVO cfg = (ConfigurationVO)getResponseObject();
      
        if (cfg != null) {
            ConfigurationResponse response = ApiResponseHelper.createConfigurationResponse(cfg);
            response.setResponseName(getName());
            return response;
        } else {
            throw new ServerApiException(BaseCmd.INTERNAL_ERROR, "Failed to update config");
        }
    }
}
