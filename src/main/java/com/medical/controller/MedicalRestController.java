package com.medical.controller;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Patient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicalRestController {

    @RequestMapping(method = RequestMethod.GET, path = "/patient")
    public Bundle patient() {

        //TODO: Make this whole block singleton instance.
        FhirContext fhirContext = FhirContext.forDstu3();
        String fhirServerBase = "http://hapi.fhir.org/baseDstu3"; //TODO: Move this to properties file.
        IGenericClient iGenericClient = fhirContext.newRestfulGenericClient(fhirServerBase);

        Bundle results = iGenericClient
                .search()
                .forResource(Patient.class)
                .where(Patient.FAMILY.matches().value("duck"))
                .returnBundle(Bundle.class)
                .execute();

        return results;

    }
}
