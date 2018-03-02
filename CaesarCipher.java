
public class CaesarCipher implements Cipher {
	
	private int rotation;
	
	/**
	 * CeaserCipher rotates letters by a certain # of characters(rotation)
	 * to encrypt/decrypt a certain message.
	 * @constructor
	 * @param rotation
	 */
	public CaesarCipher(int rotation) {
		super();
		this.rotation = rotation;
	}
	/**
	 * encrypt - scrambles a message
	 * @param message
	 */
	@Override
	public String encrypt(String message) {
		char[] m = message.toCharArray();
		for (int i = 0; i < m.length; i++)
			if (Character.isUpperCase(m[i])){
				m[i] += rotation;
				if (m[i] > 'Z')
					m[i] -= 26;
			}
			else if (Character.isLowerCase(m[i])){
				m[i] += rotation;
				if (m[i] > 'z')
					m[i] -= 26;
			}
		return new String(m);
	}
	/**
	 * decrypt - descrambles a message
	 * @param message
	 */
	@Override
	public String decrypt(String message) {
		char[] m = message.toCharArray();
		for (int i = 0; i < m.length; i++)
			if (Character.isUpperCase(m[i])){
				m[i] -= rotation;
				if (m[i] < 'A')
					m[i] += 26;
			}
			else if (Character.isLowerCase(m[i])){
				m[i] -= rotation;
				if (m[i] < 'a')
					m[i] += 26;
			}
		return new String(m);
	}

}
