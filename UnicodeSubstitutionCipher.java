import java.util.Random;

public class UnicodeSubstitutionCipher implements Cipher {
	char[] encryptMap = new char[0x10000];
	char[] decryptMap = new char[0x10000];
	
	public UnicodeSubstitutionCipher(long seed) {
		Random random = new Random(seed);
		for (int i = 0; i < encryptMap.length; i++)
			encryptMap[i] =(char) i;
		for (int i = encryptMap.length - 1; i > 0; i--) {
			int j = random.nextInt(i + 1);
			char ch = encryptMap[i];
			encryptMap[i] = encryptMap[j];
			encryptMap[j] = ch;
		}
		for (int i = 0; i < decryptMap.length; i++)
			decryptMap[encryptMap[i]] = (char) i;
	}

	@Override
	public String encrypt(String message) {
		char[] m = message.toCharArray();
		for (int i = 0; i < m.length; i++)
			m[i] = encryptMap[m[i]];
		return new String(m);
	}

	@Override
	public String decrypt(String message) {
		char[] m = message.toCharArray();
		for (int i = 0; i < m.length; i++)
			m[i] = decryptMap[m[i]];
		return new String(m);
	}

}
