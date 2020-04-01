package com.objectcomputing.auth;

import io.micronaut.security.authentication.*;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.util.Collections;

@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

    @Override
    public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
        if ((authenticationRequest.getIdentity().equals("ACME")
                || authenticationRequest.getIdentity().equals("Makers")
                || authenticationRequest.getIdentity().equals("Admin")) &&
                authenticationRequest.getSecret().equals("password")) {
            return Flowable.just(new UserDetails((String) authenticationRequest.getIdentity(), Collections.emptyList()));
        }
        return Flowable.just(new AuthenticationFailed());
    }
}
