import java.util.ArrayList;
import java.util.Random;

public class Seed {

	private static int seed;

	Seed() {
		seed = 816785647;
	}

	Seed(ArrayList<Long> aggregates) {
		Random rand;
		int tempSeed = 0;
		for (int i = 0; i < aggregates.size(); i++) {
			// System.out.println(aggregates.get(i));
			rand = new Random(aggregates.get(i));
			tempSeed += rand.nextInt();
			System.out.println(tempSeed);
		}
		seed = tempSeed;
	}

	public int getSeed() {

		return seed;

	}

}
