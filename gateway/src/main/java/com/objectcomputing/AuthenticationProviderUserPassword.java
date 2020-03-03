package com.objectcomputing;

import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.util.Collections;

@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

    @Override
    public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
        if ((authenticationRequest.getIdentity().equals("sherlock") || authenticationRequest.getIdentity().equals("watson")) &&
                authenticationRequest.getSecret().equals("password")) {
            return Flowable.just(new UserDetails((String) authenticationRequest.getIdentity(), Collections.emptyList()));
        }
        return Flowable.just(new AuthenticationFailed());
    }
}