package com.softsuit.arbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Growth<T> {

	private int longstSeed = 0;
	private Optional<TrunkTraceable<T>> traceable;
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

	public void addTraceable(TrunkTraceable<T> trunk) {
		this.traceable = Optional.of(trunk);
	}

	public Optional<TrunkTraceable<T>> getTraceable() {
		return traceable.isPresent() ? traceable : Optional.empty();
	}
}
