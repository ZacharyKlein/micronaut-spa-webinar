package com.objectcomputing.auth;

import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import org.reactivestreams.Publisher;

import javax.validation.Valid;

@Retryable
@Client(id ="auth", path = "/login")
interface AuthClient {

    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    @Post
    @SingleResult
    HttpResponse<AccessRefreshToken> login(@Valid @Body UsernamePasswordCredentials usernamePasswordCredentials, HttpRequest<?> request);
}
