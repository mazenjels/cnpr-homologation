package com.cnpr.homologation.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String errorMessage;

        if (exception instanceof LockedException) {
            errorMessage = "Votre compte est verrouillé. Veuillez contacter l'administrateur.";
        } else if (exception instanceof DisabledException) {
            errorMessage = "Votre compte est desactivé. Veuillez contacter le support.";
        } else if (exception instanceof CredentialsExpiredException) {
            errorMessage = "Votre mot de passe a expireé. Veuillez le réinitialiser.";
        } else if (exception instanceof AccountExpiredException) {
            errorMessage = "Votre compte a expiré. Veuillez contacter le support.";
        } else {
            errorMessage = "Identifiant ou mot de passe incorrect.";
        }

        request.setAttribute("error", errorMessage);
        request.getRequestDispatcher("/login?error=true").forward(request, response);
		
	}

}
