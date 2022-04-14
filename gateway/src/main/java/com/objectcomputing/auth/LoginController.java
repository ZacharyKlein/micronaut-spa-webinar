package com.objectcomputing.auth;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;

import javax.validation.Valid;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/login")
class LoginController {

    private final AuthClient authClient;

    LoginController(AuthClient authClient) {
        this.authClient = authClient;
    }

    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    @Post("/")
    HttpResponse<AccessRefreshToken> login(@Valid @Body UsernamePasswordCredentials credentials, HttpRequest<?> request) {

        HttpResponse<AccessRefreshToken> response = authClient.login(credentials, request);

        if (response.getStatus().equals(HttpStatus.OK)) {
            return HttpResponse.ok(response.body());
        } else {
            return HttpResponse.unauthorized();
        }
    }
}
