//package AlexSpring.AlexTmShop.security;
//
//import BuildWeekTeam7.EpicEnergyServices.entities.User;
//import BuildWeekTeam7.EpicEnergyServices.exceptions.UnauthorizedException;
//import BuildWeekTeam7.EpicEnergyServices.services.UserService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class JWTFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JWTTools jwtTools;
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader == null || !authHeader.startsWith("Bearer "))
//            throw new UnauthorizedException("There is no Authentication Header in the request");
//        String accessToken = authHeader.substring(7);
//        this.jwtTools.verifyToken(accessToken);
//        String id = jwtTools.idFromToken(accessToken);
//        User currentUser = this.userService.findById(Long.parseLong(id));
//        Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser, null, currentUser.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        filterChain.doFilter(request, response);
//    }
//
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) {
//        return new AntPathMatcher().match("/auth/**", request.getServletPath());
//    }
//}
