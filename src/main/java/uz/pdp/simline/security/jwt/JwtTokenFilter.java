package uz.pdp.simline.security.jwt;

import io.jsonwebtoken.Claims;
import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.simline.entity.Role;
import uz.pdp.simline.entity.User;
import uz.pdp.simline.repository.UserRepository;

import java.io.IOException;
import java.util.*;

@NonNullApi
@RequiredArgsConstructor
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(AUTHORIZATION);
        if (token != null && token.startsWith(BEARER)) {
            token = token.split(" ")[1];
            if (jwtTokenProvider.isValid(token)) {
                Claims claims = jwtTokenProvider.parseAllClaims(token);
                Optional<User> user = userRepository.findByUsername(claims.getSubject());
                if (user.isPresent()) {
                    List<SimpleGrantedAuthority> grantedAuthority = user.get().getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getRole())).toList();
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                            claims.getSubject(),
                            null,
                            grantedAuthority
                    ));
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
