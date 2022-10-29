package com.pre21.security.filter;

import com.pre21.security.jwt.JwtTokenizer;
import com.pre21.security.utils.CustomAuthorityUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import static com.pre21.util.RestConstants.AUTHORIZATION;
import static com.pre21.util.RestConstants.BEARER;

@Slf4j
@RequiredArgsConstructor
public class JwtVerificationFilter extends OncePerRequestFilter {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String refreshToken = req.getHeader("RefreshToken");
            jwtTokenizer.verifiedExistRefresh(refreshToken);
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
        String authentication = req.getHeader(AUTHORIZATION);
        return authentication == null || !authentication.startsWith(BEARER);
    }


    private Map<String, Object> verifyJws(HttpServletRequest req) throws Exception {
        String jws = req.getHeader(AUTHORIZATION).replace(BEARER, "");
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
