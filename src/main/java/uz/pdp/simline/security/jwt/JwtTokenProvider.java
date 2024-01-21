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
import uz.pdp.simline.exception.NotFoundException;
import uz.pdp.simline.repository.RoleRepository;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    @Value("${jwt.token.secret.key}")
    private String key;
    @Value("${jwt.token.secret.expiry}")
    private String expiry;
    private final RoleRepository roleRepository;

    public String generateForCustomer(Customer customer) {
        return Jwts.builder()
                .subject(customer.getUsername())
                .id(customer.getId().toString())
                .issuedAt(new Date())
                .claim("roles", List.of(roleRepository.findByRole("ROLE_CUSTOMER")
                        .orElseThrow(() -> new NotFoundException("Role"))))
                .expiration(new Date(System.currentTimeMillis() + Long.parseLong(expiry)))
                .signWith(key())
                .compact();
    }

    public String generateForEmployee(Employee employee) {
        return Jwts.builder()
                .subject(employee.getEmail())
                .id(employee.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + Long.parseLong(expiry)))
                .claim("roles", employee.getEmployeeRoles())
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
