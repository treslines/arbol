package com.softsuit.arbol;

import java.util.ArrayList;
import java.util.List;

public class Growth {

	private int longstSeed = 0;
	private List<String> seeds = new ArrayList<>();

	public void addSeed(String seed) {
		
		if(seed.length() > longstSeed) {
			longstSeed = seed.length();
		}
		
		for (int i = seed.length() - 1; i >= 0; i--) {
			final String in = seed.substring(0, i + 1);
			if (!seeds.stream().anyMatch(s -> s.contentEquals(in))) {
				seeds.add(in);
			}
		}
	}
	
	public void removeSeed(String seed) {
		if (seeds.stream().anyMatch(s -> s.contentEquals(seed))) {
			seeds.remove(seed);
		}
	}

	public List<String> getSeeds() {
		return seeds;
	}
	
	public int getLongstSeed() {
		return this.longstSeed;
	}

}
