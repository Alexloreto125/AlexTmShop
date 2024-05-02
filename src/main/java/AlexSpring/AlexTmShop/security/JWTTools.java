//package AlexSpring.AlexTmShop.security;
//
//import BuildWeekTeam7.EpicEnergyServices.entities.User;
//import BuildWeekTeam7.EpicEnergyServices.exceptions.UnauthorizedException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JWTTools {
//    @Value("${jwt.secret}")
//    private String secret;
//
//    public String createToken(User user) {
//        return Jwts.builder()
//                .issuedAt(new Date(System.currentTimeMillis()))
//                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
//                .subject(String.valueOf(user.getId()))
//                .signWith(Keys.hmacShaKeyFor(this.secret.getBytes()))
//                .compact();
//    }
//
//    public void verifyToken(String token) {
//        try {
//            Jwts.parser()
//                    .verifyWith(Keys.hmacShaKeyFor(this.secret.getBytes()))
//                    .build().parse(token);
//        } catch (Exception ex) {
//            throw new UnauthorizedException("Token issues, verify if token is still active or it has been modified");
//        }
//    }
//
//    public String idFromToken(String token) {
//        return Jwts.parser()
//                .verifyWith(Keys.hmacShaKeyFor(this.secret.getBytes()))
//                .build().parseSignedClaims(token).getPayload().getSubject();
//    }
//}
