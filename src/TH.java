import javax.crypto.*;
import java.io.*;
import java.security.InvalidKeyException;

public class TH {
    Cipher cipher;

    SecretKey key;

    String name = "DES";

    public TH() {
        try {
            cipher = Cipher.getInstance(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setKey(SecretKey key) {
        this.key = key;
    }

    public SecretKey createKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(name);
            keyGenerator.init(56);
            setKey(keyGenerator.generateKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    public void readKey(String path) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
            setKey((SecretKey) objectInputStream.readObject());
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeKey(String path) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
            objectOutputStream.writeObject(key);
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void encryptFile(String pathIn, String pathOut) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(pathIn));
            CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(pathOut), cipher);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = bis.read(buffer)) != -1) {
                cos.write(buffer, 0, read);
            }
            bis.close();
            cos.flush();
            cos.close();
            System.out.println("Encrypt File Success!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void decryptFile(String pathIn, String pathOut) {
        try {
            if (key == null) throw new FileNotFoundException("Key not found");
            cipher.init(Cipher.DECRYPT_MODE, key);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(pathOut));
            CipherInputStream cis = new CipherInputStream(new FileInputStream(pathIn), cipher);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = cis.read(buffer)) != -1) {
                bos.write(buffer, 0, read);
            }
            cis.close();
            bos.flush();
            bos.close();
            System.out.println("Decrypt File Success!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TH test = new TH();
//        test.createKey();
//        test.writeKey("E:\\ATBM\\Key.txt");
//        test.encryptFile("E:\\ATBM\\PlainText.txt", "E:\\ATBM\\OutEncrypt.txt");
//
//        test.readKey("E:\\ATBM\\Key.txt");
//        test.decryptFile("E:\\ATBM\\OutEncrypt.txt", "E:\\ATBM\\OutDecrypt.txt");


//        test.createKey();
//        test.writeKey("E:\\ATBM\\Key.txt");
//        test.encryptFile("E:\\ATBM\\cv.png", "E:\\ATBM\\outEncrypt.png");

        test.readKey("E:\\ATBM\\Key.txt");
        test.decryptFile("E:\\ATBM\\outEncrypt.png", "E:\\ATBM\\OutDecrypt.png");
    }
}
