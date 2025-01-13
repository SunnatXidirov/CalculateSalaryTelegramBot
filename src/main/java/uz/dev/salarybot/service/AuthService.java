package uz.dev.salarybot.service;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.dev.salarybot.repsitory.RoleRepository;
import uz.dev.salarybot.repsitory.UserRepository;
import uz.dev.salarybot.utils.RefreshTokenRequest;
import uz.dev.salarybot.utils.ResponseToken;
import uz.dev.salarybot.utils.jwt.TokenService;


@Service
public class AuthService implements UserDetailsService {

    private final TokenService accessTokenService;
    private final TokenService refreshTokenService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AuthService(@Qualifier("accessTokenService") TokenService accessTokenService,
                       @Qualifier("refreshTokenService") TokenService refreshTokenService,
                       UserRepository userRepository,
                       RoleRepository roleRepository) {
        this.accessTokenService = accessTokenService;
        this.refreshTokenService = refreshTokenService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    public ResponseToken login(String login) {


        return null;
    }


    public ResponseToken refreshToken(RefreshTokenRequest tokenRequest) {

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        return null;
    }
}



