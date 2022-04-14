package com.objectcomputing.auth;

import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.*;
import io.reactivex.rxjava3.core.Flowable;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;

@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

    @Override
    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
         if ((authenticationRequest.getIdentity().equals("ACME")
                || authenticationRequest.getIdentity().equals("Makers")
                || authenticationRequest.getIdentity().equals("Admin")) &&
                authenticationRequest.getSecret().equals("password")) {
            return Flowable.just(AuthenticationResponse.success((String) authenticationRequest.getIdentity()));
        }
        return Flowable.just(new AuthenticationFailed());
    }

}
