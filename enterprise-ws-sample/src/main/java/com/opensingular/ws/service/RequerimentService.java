package com.opensingular.ws.service;

import com.opensingular.ws.requirement.Requirementsample;
import com.opensingular.ws.requirement.RequirementsamplePortType;
import com.opensingular.ws.requirement.RequirementsampleRequest;
import com.opensingular.ws.requirement.RequirementsampleResponse;
import com.opensingular.ws.requirement.RequirementsampleService;

public class RequerimentService {

    private static final RequirementsampleService SERVICE = new RequirementsampleService();

    public RequirementsampleResponse send(Requirementsample sample){
        RequirementsamplePortType requirementsampleServiceSoap = SERVICE.getRequirementsampleServiceSoap();
        RequirementsampleRequest request = new RequirementsampleRequest();
        request.setRequirementsample(sample);
        return requirementsampleServiceSoap.sendRequirementsample(request);
    }
}
