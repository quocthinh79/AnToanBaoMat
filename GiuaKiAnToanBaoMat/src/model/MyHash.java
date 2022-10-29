package model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyHash {

	public static String MD5 = "MD5";

	String name;
	MessageDigest md;

	public MyHash(String name) throws NoSuchAlgorithmException {
		super();
		this.name = name;
		md = MessageDigest.getInstance(this.name);
	}

	public static MyHash getInstance(String name) {
		MyHash install = null;
		try {
			install = new MyHash(name);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return install;
	}

	public String hash(String data) {
		byte[] out = this.md.digest(data.getBytes());
		BigInteger num = new BigInteger(1, out);
		String hs = num.toString(16);
		return hs;
	}

	public String hashFile(String path) throws FileNotFoundException {
		try {
			File file = new File(path);
			if (file.exists()) {
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
				DigestInputStream dis = new DigestInputStream(bis, this.md);
				byte[] buff = new byte[1024];
				int i = -1;
				do {
					i = dis.read(buff);
				} while (i != -1);
				BigInteger num = new BigInteger(1, dis.getMessageDigest().digest());
				return num.toString();
			} else {

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "Dai hoc Nong Lam";
		MyHash mh = MyHash.getInstance(MyHash.MD5);
		System.out.println(mh.hash(text));
	}

}
