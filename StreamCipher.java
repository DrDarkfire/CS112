import java.util.Random;

public class StreamCipher implements Cipher {

	//private long seed;
	private Random random;
	private char random1;
	//private StreamCipher object;
	
	public StreamCipher(long seed) {
		this.random = new Random(seed);
		this.random1 = (char) random.nextInt(0x10000);
	}
	
	@Override
	public String encrypt(String message) {
		char[] m = message.toCharArray();
		for (int i = 0; i < m.length; i++) {
			//char mes = (char) (m[i]^random1);
			//System.out.println(random1);
			//char random1 = (char) random.nextInt(0x10000);
			int mes = (int) (m[i]^random1);
			//System.out.println(mes);
			char j = (char) mes;
			//m[i] = mes;
			m[i] = j;
		}
		//System.out.println(m);
		return new String(m);
	}

	@Override
	public String decrypt(String message) {
		char[] m = message.toCharArray();
		for (int i = 0; i < m.length; i++) {
			//char mes = (char) (m[i]^random1);
			//System.out.println(random1);
			//char random1 = (char) random.nextInt(0x10000);
			int mes = (int) (m[i]^random1);
			//System.out.println(mes);
			char j = (char) mes;
			//m[i] = mes;
			m[i] = j;
		}
		//System.out.println(m);
		return new String(m);
	}

}
