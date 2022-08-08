package rca.risbo.gestiondestock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rca.risbo.gestiondestock.dto.auth.AuthenticationRequest;
import rca.risbo.gestiondestock.dto.auth.AuthenticationResponse;
import rca.risbo.gestiondestock.model.auth.ExtendedUser;
import rca.risbo.gestiondestock.services.auth.ApplicationUserDetailService;
import rca.risbo.gestiondestock.utils.JwtUtil;

import static rca.risbo.gestiondestock.utils.Constants.AUTHENTICATION_ENDPOINT;

@RestController
@RequestMapping(AUTHENTICATION_ENDPOINT)
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApplicationUserDetailService userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        // Vérifie si l'utilisateur avec le mdp et le login existe dans la BDD ou non
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getLogin(),
                request.getPassword()
            )
        );

        // Recherche de l'utilisateur
        final UserDetails userDetails = userDetailService.loadUserByUsername(request.getLogin());

        // Géneration du token
        final String jwt = jwtUtil.generateToken((ExtendedUser) userDetails);

        return ResponseEntity.ok(AuthenticationResponse.builder().accessToken(jwt).build());
    }
}
