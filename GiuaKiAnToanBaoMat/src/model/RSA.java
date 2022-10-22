package model;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class RSA {
	public KeyPair keyPair;
	public PublicKey publicKey;
	public PrivateKey privateKey;

	public RSA() {
		// TODO Auto-generated constructor stub
		getKey();
	}

	public void getKey() {
		KeyPairGenerator keyGenerator = null;
		try {
			keyGenerator = KeyPairGenerator.getInstance("RSA");
			keyGenerator.initialize(2048);
			keyPair = keyGenerator.generateKeyPair();
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public byte[] encrypt(String text) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE,
				KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKey.getEncoded())));
		byte[] plaintText = text.getBytes(StandardCharsets.UTF_8);
		return cipher.doFinal(plaintText);
	}

	public byte[] encryptByteArr(byte[] text, Key key) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(text);
	}

	public SecretKey decryptKey(byte[] text, Key key) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] plaintText = cipher.doFinal(text);
		return new SecretKeySpec(plaintText, 0, plaintText.length, "DES");
	}

	public String decrypt(byte[] text, Key key) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] plaintText = cipher.doFinal(text);
		return new String(plaintText, StandardCharsets.UTF_8);
	}

	public KeyPair getKeyPair() {
		return keyPair;
	}

	public void setKeyPair(KeyPair keyPair) {
		this.keyPair = keyPair;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		RSA rsa = new RSA();
		Key key = rsa.getPrivateKey();
		System.out.println(rsa.decrypt(rsa.encrypt("Lê Quốc Thịnh"), key));
	}

}