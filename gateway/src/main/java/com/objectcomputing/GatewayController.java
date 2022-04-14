package com.objectcomputing;

import io.micronaut.http.annotation.*;

@Controller("/gateway")
public class GatewayController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}