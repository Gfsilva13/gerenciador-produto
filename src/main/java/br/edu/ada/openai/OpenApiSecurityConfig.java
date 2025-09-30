package br.edu.ada.openai;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

@SecurityScheme(
        securitySchemeName = "BearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiSecurityConfig extends Application {
}
