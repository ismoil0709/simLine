package uz.pdp.simline.security.jwt;

import io.jsonwebtoken.Claims;
import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.simline.entity.Role;

import java.io.IOException;
import java.util.*;

@NonNullApi
@RequiredArgsConstructor
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @SuppressWarnings("unchecked")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(AUTHORIZATION);
        if (token != null && token.startsWith(BEARER)) {
            token = token.split(" ")[1];
            if (jwtTokenProvider.isValid(token)) {
                Claims claims = jwtTokenProvider.parseAllClaims(token);
                List<LinkedHashMap<String, String>> rolesMapList = (List<LinkedHashMap<String, String>>) claims.get("roles");
                List<Role> roles = rolesMapList.stream()
                        .map(role -> {
                            Role employeeRoles = new Role();
                            employeeRoles.setRole(role.get("role"));
                            employeeRoles.setDescription(role.get("description"));
                            return employeeRoles;
                        }).toList();
                List<SimpleGrantedAuthority> grantedAuthority = roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole())).toList();
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                        claims.getSubject(),
                        null,
                        grantedAuthority
                ));

            }
        }
        filterChain.doFilter(request, response);
    }
}
