package tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.StringTokenizer;

import javax.crypto.SecretKey;

import model.DESCipher;
import model.RSA;

public class ClientTransport {
	static String publicKey;
	static String secretKey;
	

	public static String getSecretKey() {
		return secretKey;
	}

	public static void setSecretKey(String secretKey) {
		ClientTransport.secretKey = secretKey;
	}

	public static String getPublicKey() {
		return publicKey;
	}

	public static void setPublicKey(String publicKey) {
		ClientTransport.publicKey = publicKey;
	}

	public static void main(String[] args) throws Exception {
		handleInClient("E:\\ATBM\\Client\\LeQuocThinh_CV.pdf", "UPLOAD");
	}

	public static void handleInClient(String pathSourcefile, String options) throws Exception {
		// TODO Auto-generated method stub
		Socket sk = new Socket("127.0.0.1", ServerTransport.PORT);
		DataInputStream dis = new DataInputStream(sk.getInputStream());
		DataOutputStream dos = new DataOutputStream(sk.getOutputStream());
		System.out.println(dis.readUTF());
		switch (options) {
		case "UPLOAD": {
			dos.writeUTF("UPLOAD");
			String publicKeyInString = dis.readUTF();
			setPublicKey(publicKeyInString);
			byte[] data = Base64.getDecoder().decode((publicKeyInString.getBytes()));
			X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
			KeyFactory fact = KeyFactory.getInstance("RSA");
			PublicKey publicKey = fact.generatePublic(spec);
			DESCipher des = new DESCipher();
			des.createKey();
			SecretKey keyOfDes = des.getKey();
			RSA rsa = new RSA();
			String keyOfDesToString = Base64.getEncoder()
					.encodeToString(rsa.encryptByteArr(keyOfDes.getEncoded(), publicKey));
			dos.writeUTF(keyOfDesToString);
			setSecretKey(keyOfDesToString);
			File file = new File(pathSourcefile);
			String fileName = file.getName();
			String name = fileName.substring(0, fileName.lastIndexOf("."));
			String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
			dos.writeUTF(name);
			dos.writeUTF(extension);
			String locationFileEncrypt = "./" + name + "_Encrypt" + extension;
			des.encryptFile(pathSourcefile, locationFileEncrypt, keyOfDes);
			File fileSource = new File(locationFileEncrypt);
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileSource));
			dos.writeLong(fileSource.length());
			dos.flush();
			int dataFile;
			while ((dataFile = bis.read()) != -1) {
				dos.write(dataFile);
				dos.flush();
			}
			bis.close();
			fileSource.delete();
			break;
		}
		case "DOWNLOAD": {
			dos.writeUTF("DOWNLOAD");
			break;
		}
		default:
			break;
		}
	}
}
