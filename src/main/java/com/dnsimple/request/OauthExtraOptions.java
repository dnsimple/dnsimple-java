package com.dnsimple.request;

import java.net.URI;
import java.util.Optional;
import java.util.function.Consumer;

public class OauthExtraOptions {
    private final Optional<String> state;
    private final Optional<URI> redirectUri;

    public OauthExtraOptions(Optional<String> state, Optional<URI> redirectUri) {
        this.state = state;
        this.redirectUri = redirectUri;
    }

    public static OauthExtraOptions empty() {
        return new OauthExtraOptions(Optional.empty(), Optional.empty());
    }

    public void ifState(Consumer<String> consumer) {
        state.ifPresent(consumer);
    }

    public void ifRedirectUri(Consumer<URI> consumer) {
        redirectUri.ifPresent(consumer);
    }

    public static class Builder {
        private String state;
        private URI redirectUri;

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder redirectUri(String url) {
            this.redirectUri = URI.create(url);
            return this;
        }

        public Builder redirectUri(URI url) {
            this.redirectUri = url;
            return this;
        }

        public OauthExtraOptions build() {
            return new OauthExtraOptions(Optional.ofNullable(state), Optional.ofNullable(redirectUri));
        }
    }
}
