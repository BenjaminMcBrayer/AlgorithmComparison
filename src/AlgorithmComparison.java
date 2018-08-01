import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * adapted from
 * http://javandthings.com/2017/09/09/hashmap-iteration-comparision/
 * 
 * @author BenjaminMcBrayer
 * @version 1.0
 * 
 */
public class AlgorithmComparison {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		String masterKey;
		String key;
		String value = null;

		// Begin filling map with random strings.
		for (int i = 0; i < 25000; ++i) {
			key = getRandomString();
			map.put(key, key);
		}

		// Put master key in map.
		masterKey = getRandomString();
		System.out.println("Master Key: " + masterKey);
		map.put(masterKey, masterKey);

		// Finish filling map with random strings.
		for (int i = 0; i < 25000; ++i) {
			key = getRandomString();
			map.put(key, key);
		}

		// Print size of map.
		System.out.println("Size of map: " + map.size());

		// Calculate time to get key using getValue() method.
		long start = System.currentTimeMillis();
		value = getValue(map, masterKey);
		long end = System.currentTimeMillis();
		System.out.println("Time(ms) to find " + value + " via getValue(): " + (end - start));

		// Calculate time to get key using getValue() method.
		start = System.currentTimeMillis();
		value = map.get(masterKey);
		end = System.currentTimeMillis();
		System.out.println("Time(ms) to find " + value + " via get(): " + (end - start));

	}

	public static String getValue(Map<?, ?> metaData, String key) {
		String value = null;

		Iterator<?> it = metaData.keySet().iterator();

		while (it.hasNext()) {
			String nextKey = (String) it.next();

			if (nextKey.equals(key)) {
				String nextValue = (String) metaData.get(nextKey);
				value = nextValue;
			}
		}
		return value;
	}

	private static Set<String> collectedString = new HashSet<>();

	private static String getRandomString() {
		String randomString = Double.toString(Math.random() * 10000);
		while (collectedString.contains(randomString)) {
			randomString = Double.toString(Math.random() * 10000);
		}
		collectedString.add(randomString);
		return randomString;
	}
}
