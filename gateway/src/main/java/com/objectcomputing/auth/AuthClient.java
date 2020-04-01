package com.objectcomputing.auth;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;

import javax.validation.Valid;

@Retryable
@Client(id ="auth")
interface AuthClient {

    @Post(uri = "/login", consumes = {MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    HttpResponse<BearerAccessRefreshToken> login(@Valid @Body UsernamePasswordCredentials usernamePasswordCredentials);
}
