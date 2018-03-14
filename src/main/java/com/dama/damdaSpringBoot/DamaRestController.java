package com.dama.damdaSpringBoot;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Patient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DamaRestController {

    @RequestMapping(method = RequestMethod.GET, path = "/patient")
    public Bundle patient() {

        //TODO: Make this whole block single instance.
        FhirContext ctx = FhirContext.forDstu3();
        String serverBase = "http://hapi.fhir.org/baseDstu3"; //TODO: Move this to properties file.
        IGenericClient client = ctx.newRestfulGenericClient(serverBase);

        Bundle results = client
                .search()
                .forResource(Patient.class)
                .where(Patient.FAMILY.matches().value("duck"))
                .returnBundle(Bundle.class)
                .execute();

        return results;

    }
}
