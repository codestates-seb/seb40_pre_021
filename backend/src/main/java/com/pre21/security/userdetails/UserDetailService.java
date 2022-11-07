package com.pre21.security.userdetails;

import com.pre21.entity.User;
import com.pre21.exception.BusinessLogicException;
import com.pre21.exception.ExceptionCode;
import com.pre21.security.utils.CustomAuthorityUtils;
import com.pre21.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

/**
 * 데이터베이스에서 유저 정보를 가져오는 서비스 클래스
 * @author mozzi327
 */
@Component
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final AuthRepository authRepository;
    private final CustomAuthorityUtils authorityUtils;

    /**
     * 데이터베이스에서 유저 정보를 조회해 UserDetails 클래스를 리턴해주는 오버라이딩 메서드
     * @param email 사용자 이메일
     * @return UserDetails
     * @throws UsernameNotFoundException notFound 예외
     * @author mozzi327
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = authRepository.findByEmail(email);
        User findUser = optionalUser.orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

        return new UsersDetails(findUser);
    }

    /**
     * 유저 테이블에서 사용할 Details 정보를 설정하는 클래스
     * @author mozzi327
     */
    private class UsersDetails extends User implements UserDetails {
        UsersDetails(User user) {
            setId(user.getId());
            setEmail(user.getEmail());
            setPassword(user.getPassword());
            setRoles(user.getRoles());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorityUtils.createAuthorities(this.getRoles());
        }

        @Override
        public String getUsername() {
            return getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
