import java.util.Arrays;
import java.util.Random;

public class GenericShuffleMethod {
	public static void main(String[] arghs) {
		Random random = new Random(42);
		int size = 10;
		Integer[] array = new Integer[size]; // wrapper Integer lets us treat the index like objects
		for (int i = 0; i < array.length; i++)
			array[i] = i;
		System.out.println(Arrays.toString(shuffle(array, random)));
	}

	public static <E> E[] shuffle(E[] array, Random random) {
		for (int i = array.length - 1; i > 0; --i) {
			int j = random.nextInt(i + 1);
			E tmp = array[i];
			array[i] = array[j];
			array[j] = tmp;
		}
		return array;
	}
}
