package com.objectcomputing.auth;

import com.objectcomputing.auth.AuthClient;
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
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;

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
    HttpResponse<BearerAccessRefreshToken> login(@Valid @Body UsernamePasswordCredentials credentials) {

        HttpResponse<BearerAccessRefreshToken> response = authClient.login(credentials);

        if (response.getStatus().equals(HttpStatus.OK)) {
            return HttpResponse.ok(response.body());
        } else {
            return HttpResponse.unauthorized();
        }
    }
}
