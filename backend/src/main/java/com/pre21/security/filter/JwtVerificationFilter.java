package com.pre21.security.filter;

import com.pre21.security.jwt.JwtTokenizer;
import com.pre21.security.utils.CustomAuthorityUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class JwtVerificationFilter extends OncePerRequestFilter {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            Map<String, Object> claims = verifyJws(req);
            setAuthenticationToContext(claims);
        } catch (SignatureException se) {
            req.setAttribute("exception", se);
        } catch (ExpiredJwtException ee) {
            req.setAttribute("exception", ee);
        } catch (Exception e) {
            req.setAttribute("exception", e);
        }

        filterChain.doFilter(req, res);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest req) throws ServletException {
        String authentication = req.getHeader("Authorization");
        return authentication == null || !authentication.startsWith("Bearer");
    }


    private Map<String, Object> verifyJws(HttpServletRequest req) {
        String jws = req.getHeader("Authorization").replace("Bearer ", "");
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        Map<String, Object> claims = jwtTokenizer.getClaims(jws, base64EncodedSecretKey).getBody();

        return claims;
    }


    private void setAuthenticationToContext(Map<String, Object> claims) {
        String email = (String) claims.get("username");
        List<GrantedAuthority> authorities = authorityUtils.createAuthorities((List) claims.get("roles"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
