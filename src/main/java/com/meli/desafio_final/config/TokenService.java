package com.meli.desafio_final.config;

import com.meli.desafio_final.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

	@Value("${jwt.expiration}")
	private String expiration;
	
	@Value("${jwt.secret}")
	private String secret;
	
	
	public String geraToken(Authentication authentication) {
		Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
		Date hoje = new Date();
		Long exp = new Long(expiration);
		Date expiracao = new Date(hoje.getTime()+exp);
		
		return Jwts.builder()
		.setIssuer("Nossa APP")
		.setSubject(usuarioLogado.getUser())
		.setIssuedAt(hoje)
		.setExpiration(expiracao)
		.signWith(SignatureAlgorithm.HS256, secret)
		.compact();
	}


	public boolean tokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	public String getUsername(String token) {
		Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
		Claims body = jwsClaims.getBody();
		return body.getSubject();
	}

}
