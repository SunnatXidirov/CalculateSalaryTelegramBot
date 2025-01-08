//package uz.dev.salarybot.security;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.lang.NonNull;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//import uz.dev.internxidi1ov.repository.UserRepository;
//import uz.dev.internxidi1ov.service.UserDetailsService;
//
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class JwtFilter extends OncePerRequestFilter {
//
//    private JwtProvider jwtProvider;
//    private UserRepository userRepository;
//    private UserDetailsService userDetailsService;
//    private HandlerExceptionResolver handlerExceptionResolver;
//
//    @Override
//    protected void doFilterInternal(
//            @NonNull HttpServletRequest request,
//            @NonNull HttpServletResponse response,
//            @NonNull FilterChain filterChain
//    ) throws ServletException, IOException {
//
//        final String authHeader = request.getHeader("Authorization");
//        if (authHeader == null || !authHeader.startsWith("Bearer")) {
//            filterChain.doFilter(request, response);
//            return;
//        } else {
//
//
//            try {
//                String token = authHeader.substring(7);
//                String userName = jwtProvider.extractUsername(token);
//
//                if (userName != null) {
//                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
//                    if (jwtProvider.isTokenValid(token, userDetails)) {
//                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                                userDetails,
//                                null,
//                                userDetails.getAuthorities()
//                        );
//                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                        SecurityContextHolder.getContext().setAuthentication(authToken);
//                    }
//
//                }
//                filterChain.doFilter(request, response);
//            } catch (Exception exception) {
//                handlerExceptionResolver.resolveException(request, response, null, exception);
//            }
//        }
//
//
//    }
//}
