package de.pingpong.vsoteamroom.components;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class VsoTeamroomCommonBean {
	
	public static final String HIDE_PASSWORD = "****";

	/**
	 * Create SHA-256 from input String
	 * @param input
	 * @return Base64 encoded SHA-256
	 */
	public String hashPassword(String input){
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
