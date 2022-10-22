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
	public String saveServerDirDefault = "upload";
	public String saveClientDirDefault = "download";
	String message = "";
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
			rsa = new RSA();
			rsa.getKey();
			PublicKey publicKey = rsa.publicKey;
			PrivateKey privateKey = rsa.privateKey;
			String keyToString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
			dos.writeUTF(keyToString);
			dos.flush();
			String keyDesFromString = dis.readUTF();
			byte[] dataBeforeDecypt = Base64.getDecoder().decode((keyDesFromString.getBytes()));
			SecretKey keyOfDes = rsa.decryptKey(dataBeforeDecypt, privateKey);
			long fileSize = dis.readLong();
			File f = new File("E:\\ATBM\\Server\\Decrypt.pdf");
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
			des.decryptFile("E:\\ATBM\\Server\\Decrypt.pdf", "E:\\ATBM\\Server\\Decrypt_Done.pdf", keyOfDes);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		while (true) {
//			try {
//				String request = dis.readUTF();
//				if ("QUIT".equalsIgnoreCase(request)) {
//					message = "Thanks! Goodbye and see you again...";
//					dos.writeUTF(message);
//					dos.flush();
//					break;
//				} else {
//					System.out.println(request);
//				}

//				StringTokenizer st = new StringTokenizer(request, " ");
//				String keyCommand = st.nextToken().toUpperCase();
//				switch (keyCommand) {
//				case "SEND":
//					String sourceFile = st.nextToken();
//					String saveFileWithName = st.nextToken();
//					if (st.hasMoreTokens()) {
//						dos.writeUTF("-11");
//						dos.flush();
//						break;
//					}
//					dos.writeUTF(saveClientDirDefault);
//					dos.flush();
//					long fileSize = dis.readLong();
//					fileReceive(saveFileWithName, fileSize);
//					message = "Receive success!";
//					break;
//				case "GET":
//					String sfName = st.nextToken();
//					String dfName = st.nextToken();
//					if (st.hasMoreTokens()) {
//						dos.writeUTF("-11");
//						dos.flush();
//						break;
//					}
//					File sf = new File(saveServerDirDefault + File.separator + sfName);
//					if (!sf.exists()) {
//						dos.writeUTF("-1");
//						dos.flush();
//						break;
//					}
//					dos.writeUTF(saveClientDirDefault);
//					dos.flush();
//					fileSend(sf);
//					message = "Send success!";
//					break;
//				case "SET_SERVER_DIR":
//					saveServerDirDefault = st.nextToken();
//					if (st.hasMoreTokens()) {
//						dos.writeUTF("-11");
//						dos.flush();
//						break;
//					}
//					message = "Changed save directory from C://dest to " + saveServerDirDefault + " success!";
//					break;
//				case "SET_CLIENT_DIR":
//					saveClientDirDefault = st.nextToken();
////					if(st.hasMoreTokens()){
////						dos.writeUTF("-11");dos.flush();break;
////					}
//					message = "Changed save directory from C://source to " + saveClientDirDefault + " success!";
//					break;
//				default:
//					message = "Key word command not exactly";
//					break;
//				}

//				dos.writeUTF(message);
//				dos.flush();

//			} catch (IOException e) {
//				e.printStackTrace();
//				System.out.println("Loi");
//				try {
//					Thread.sleep(4000);
//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}

		}
	}

	public void writeFile(String fileName, long fileSize) throws IOException {
		File f = new File(saveServerDirDefault + File.separator + fileName);
		BufferedOutputStream bos;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(f));
			dos.writeInt(0);
			dos.flush();
			for (int i = 0; i < fileSize; i++) {
				bos.write(dis.read());
				bos.flush();
			}
			bos.close();
		} catch (IOException e) {
		}
	}

	public void fileReceive(String fileName, long fileSize) throws IOException {
		File f = new File(saveServerDirDefault + File.separator + fileName);
		BufferedOutputStream bos;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(f));
			dos.writeInt(0);
			dos.flush();
			for (int i = 0; i < fileSize; i++) {
				bos.write(dis.read());
				bos.flush();
			}
			bos.close();
		} catch (IOException e) {
		}
	}

	public void fileSend(File sf) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sf));
		dos.writeLong(sf.length());
		dos.flush();
		int data;
		while ((data = bis.read()) != -1) {
			dos.write(data);
			dos.flush();
		}
		bis.close();
	}
}
