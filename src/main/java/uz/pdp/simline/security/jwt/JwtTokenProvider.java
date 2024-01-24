package uz.pdp.simline.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uz.pdp.simline.entity.Customer;
import uz.pdp.simline.entity.Employee;
import uz.pdp.simline.entity.Role;
import uz.pdp.simline.entity.User;
import uz.pdp.simline.exception.NotFoundException;
import uz.pdp.simline.repository.RoleRepository;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    @Value("${jwt.token.secret.key}")
    private String key;
    @Value("${jwt.token.secret.expiry}")
    private String expiry;
    private final RoleRepository roleRepository;

    public String generateToken(User user,String... roles) {
        List<Role> role = Arrays.stream(roles).map(roleRepository::findByRole).map(r -> r.orElseThrow(() -> new NotFoundException("Role"))).toList();
        return generateToken(user,role);
    }
    public String generateToken(User user,List<Role> roles){
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .claim("roles", roles)
                .expiration(new Date(System.currentTimeMillis() + Long.parseLong(expiry)))
                .signWith(key())
                .compact();
    }
    public boolean isValid(String token) {
        Claims claims = parseAllClaims(token);
        Date date = extractExpiryDate(claims);
        return date.after(new Date());
    }

    public Claims parseAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Date extractExpiryDate(Claims claims) {
        return claims.getExpiration();
    }

    public SecretKey key() {
        return Keys.hmacShaKeyFor(key.getBytes());
    }
}
