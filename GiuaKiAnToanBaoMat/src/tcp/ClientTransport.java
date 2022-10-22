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

	public static void main(String[] args) throws Exception {
		int errorCode = -1;
		Socket sk = new Socket("127.0.0.1", ServerTransport.PORT);
		DataInputStream dis = new DataInputStream(sk.getInputStream());
		DataOutputStream dos = new DataOutputStream(sk.getOutputStream());
		System.out.println(dis.readUTF());
		String publicKeyInString = dis.readUTF();
		byte[] data = Base64.getDecoder().decode((publicKeyInString.getBytes()));
		X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
		KeyFactory fact = KeyFactory.getInstance("RSA");
		PublicKey publicKey = fact.generatePublic(spec);
		System.out.println(publicKey);
		DESCipher des = new DESCipher();
		des.createKey();
		SecretKey keyOfDes = des.getKey();
		System.out.println(keyOfDes);
		RSA rsa = new RSA();
		String keyOfDesToString = Base64.getEncoder()
				.encodeToString(rsa.encryptByteArr(keyOfDes.getEncoded(), publicKey));
		dos.writeUTF(keyOfDesToString);
		des.encryptFile("E:\\ATBM\\Client\\LeQuocThinh_CV.pdf", "E:\\ATBM\\Client\\LeQuocThinh_CV_Encrypt.pdf", keyOfDes);
		File fileSource = new File("E:\\ATBM\\Client\\LeQuocThinh_CV_Encrypt.pdf");
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileSource));
		dos.writeLong(fileSource.length());
		dos.flush();
		int dataFile;
		while ((dataFile = bis.read()) != -1) {
			dos.write(dataFile);
			dos.flush();
		}
		bis.close();
		while (true) {
//			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//			String command = bf.readLine();
//			dos.writeUTF(command);
//			dos.flush();
//
//			if (command.equalsIgnoreCase("QUIT")) {
//			}

//			StringTokenizer st = new StringTokenizer(command, " ");
//			String key = st.nextToken().toUpperCase();
//			switch (key) {
//			case "SEND":
//				String pathDirectoryUploadAtClient = dis.readUTF();
//				if (pathDirectoryUploadAtClient.equals("-11")) {
//					System.out.println("Too many param!!!");
//					break;
//				}
//				String sf = st.nextToken();
//				File fileSource = new File(pathDirectoryUploadAtClient + File.separator + sf);
//				if (!fileSource.exists()) {
//					System.out.println("Source file name not exists!!!");
//					break;
//				}
//				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileSource));
//				dos.writeLong(fileSource.length());
//				dos.flush();
//				errorCode = dis.readInt();
//				if (errorCode == 0) {
//					int data;
//					while ((data = bis.read()) != -1) {
//						dos.write(data);
//						dos.flush();
//					}
//					bis.close();
//				}
//				break;
//			case "GET":
//				String pathDirectorySaveAtClient = dis.readUTF();
//				if (pathDirectorySaveAtClient.equals("-1")) {
//					System.out.println("Source file name not exists!!!");
//					break;
//				} else if (pathDirectorySaveAtClient.equals("-11")) {
//					System.out.println("Too many param!!!");
//					break;
//				}
//				String sourceFile = st.nextToken();
//				String saveFileWithName = st.nextToken();
//				File df = new File(pathDirectorySaveAtClient + File.separator + saveFileWithName);
//				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(df));
//				long fileSize = dis.readLong();
//				for (int i = 0; i < fileSize; i++) {
//					bos.write(dis.read());
//					bos.flush();
//				}
//				bos.close();
//				break;
//			default:
//				break;
//			}

			System.out.println(dis.readUTF());
		}
	}
}
