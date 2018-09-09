package com.vn.camthanh.spring.security.oauth2.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

import lombok.Getter;

public class CustomOAuth2Authentication extends AbstractAuthenticationToken {
    //private static final long serialVersionUID = 7527734076633525176L;

	private final OAuth2Request storedRequest;

	private final Authentication userAuthentication;

	/**
	 * Construct an OAuth 2 authentication. Since some grant types don't require user authentication, the user
	 * authentication may be null.
	 * 
	 * @param storedRequest The authorization request (must not be null).
	 * @param userAuthentication The user authentication (possibly null).
	 */
	public CustomOAuth2Authentication(OAuth2Authentication authentication) {
        super(authentication.isClientOnly() ? authentication.getAuthorities() : filter(authentication.getAuthorities(), authentication.getOAuth2Request().getScope()));
        this.storedRequest = authentication.getOAuth2Request();
        this.userAuthentication = authentication.getUserAuthentication();
        setDetails(authentication.getDetails());
        setAuthenticated(authentication.isAuthenticated());
    }
	
	/**
     * Retains only the authorities from the set of approved scopes.
     */
    private static Collection<GrantedAuthority> filter(Collection<? extends GrantedAuthority> authorities, Set<String> scope) {
        Collection<GrantedAuthority> result = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            if (scope.contains(authority.getAuthority())) {
                result.add(authority);
            }
        }
        return result;
    }

	public Object getCredentials() {
		return "";
	}

	public Object getPrincipal() {
		return this.userAuthentication == null ? this.storedRequest.getClientId() : this.userAuthentication
				.getPrincipal();
	}

	/**
	 * Convenience method to check if there is a user associated with this token, or just a client application.
	 * 
	 * @return true if this token represents a client app not acting on behalf of a user
	 */
	public boolean isClientOnly() {
		return userAuthentication == null;
	}

	/**
	 * The authorization request containing details of the client application.
	 * 
	 * @return The client authentication.
	 */
	public OAuth2Request getOAuth2Request() {
		return storedRequest;
	}

	/**
	 * The user authentication.
	 * 
	 * @return The user authentication.
	 */
	public Authentication getUserAuthentication() {
		return userAuthentication;
	}

	@Override
	public boolean isAuthenticated() {
		return this.storedRequest.isApproved()
				&& (this.userAuthentication == null || this.userAuthentication.isAuthenticated());
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		if (this.userAuthentication != null && CredentialsContainer.class.isAssignableFrom(this.userAuthentication.getClass())) {
			CredentialsContainer.class.cast(this.userAuthentication).eraseCredentials();
		}
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + storedRequest.hashCode();
		result = 31 * result + (userAuthentication != null ? userAuthentication.hashCode() : 0);
		return result;
}
}
