package encrypt;
public class KeyEncrypt{

	public native String encrypt(String s);
	public native String decrypt(String s);
	static {
		System.loadLibrary("keyEncryption");
	}
}
