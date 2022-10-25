package tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.StringTokenizer;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import model.DESCipher;
import model.RSA;

public class Transport extends Thread {
	Socket s;
	DataInputStream dis;
	DataOutputStream dos;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	RSA rsa;

	public Transport(Socket s) throws IOException {
		this.s = s;
		dis = new DataInputStream(s.getInputStream());
		dos = new DataOutputStream(s.getOutputStream());
	}

	@Override
	public void run() {
		try {
			dos.writeUTF("Welcome! \n");
			String command = dis.readUTF();
			switch (command) {
			case "UPLOAD": {
				rsa = new RSA();
				rsa.getKey();
				PublicKey publicKey = rsa.publicKey;
				PrivateKey privateKey = rsa.privateKey;
				String keyToString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
				dos.writeUTF(keyToString);
				dos.flush();
				String keyDesFromString = dis.readUTF();
				String fileName = dis.readUTF();
				String fileExtension = dis.readUTF();
				byte[] dataBeforeDecypt = Base64.getDecoder().decode((keyDesFromString.getBytes()));
				SecretKey keyOfDes = rsa.decryptKey(dataBeforeDecypt, privateKey);
				long fileSize = dis.readLong();
				String pathFileDecrypt = "./" + fileName + "_Decrypt" + fileExtension;
				File f = new File(pathFileDecrypt);
				DESCipher des = new DESCipher();
				BufferedOutputStream bos;
				try {
					bos = new BufferedOutputStream(new FileOutputStream(f));
					for (int i = 0; i < fileSize; i++) {
						bos.write(dis.read());
						bos.flush();
					}
					bos.close();
				} catch (IOException e) {
				}
				des.decryptFile(pathFileDecrypt, "./" + fileName + fileExtension, keyOfDes);
				f.delete();
				break;
			}
			case "DOWNLOAD": {

				break;
			}
			default:
				break;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
