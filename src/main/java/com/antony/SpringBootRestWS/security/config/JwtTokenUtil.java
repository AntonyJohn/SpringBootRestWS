package com.antony.SpringBootRestWS.security.config;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.antony.SpringBootRestWS.dataobject.Users;
import com.antony.SpringBootRestWS.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 45000;
	//public static final String SIGNING_KEY = "antony";
	
	@Value("${jwt.secret}")
	private String SIGNING_KEY;

	@Autowired
    private UserRepository userRepository;

	//retrieve username from jwt token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	//retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {		
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	//for retrieveing any information from token we will need the SIGNING_KEY
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody();
	}

	//check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
		
		// Added below lines for handling invalidate JWT Token
		/*final String username = getUsernameFromToken(token);
		final Date expiration = getExpirationDateFromToken(token);
		Boolean isExpired = null;
		Users users = null;
		
		 if (null != (users = userRepository.findByUserName(username))) {
			 if(users.getLoginStatus().equals("0")) {
				 isExpired = expiration.after(new Date());
			 }else {
				 isExpired = expiration.before(new Date());
			 }
		 }else  {
			 isExpired = expiration.before(new Date()); 
		 }
		 return isExpired;*/
	}

//	public String generateToken(AgentDto customer) {
//		return doGenerateToken(customer.getAgentid());
//	}

	public String generateToken(String userId) {
		return doGenerateToken(userId);
	}
	

	//while creating the token -
	//1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
	//2. Sign the JWT using the HS512 algorithm and secret key.
	//3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	//   compaction of the JWT to a URL-safe string 
	private String doGenerateToken(String subject) {
		Claims claims = Jwts.claims().setSubject(subject);
		claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
		return Jwts.builder().setClaims(claims).setIssuer("antony").setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
				.signWith(SignatureAlgorithm.HS256, SIGNING_KEY).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		System.out.println(username);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public Boolean inValidateToken(String token) {
		return (isTokenExpired(token));
	}
}