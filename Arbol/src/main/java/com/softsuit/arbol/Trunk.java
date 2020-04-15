package com.softsuit.arbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Is the root of this natural tree.
 * @author Ricardo Ferreira
 *
 * @param <T>  Any Object of your choice to describe / store information about this trunk and its twigs and leafs.
 */
public class Trunk<T> {

	private String id;
	private T trunkInfo;
	private List<Twig<T>> ramifications = new ArrayList<>();
	
	public Trunk(final String id) {
		this(id, null, new ArrayList<Twig<T>>());
	}

	public Trunk(final String id, final T info, final List<Twig<T>> twigs) {
		if (id == null) throw new IllegalArgumentException("Id can't be null!");
		this.id = id.trim();
		this.trunkInfo = info;
		this.ramifications = twigs;
	}
	
	public String getId() {
		return id;
	}
	
	public T getTrunkInfo() {
		return trunkInfo;
	}

	public void setTruniInfo(T trunkInfo) {
		this.trunkInfo = trunkInfo;
	}
	
	public boolean isTrunkWithLeaf() {
		return this.trunkInfo != null && !ramifications.isEmpty();
	}

	public boolean isTrunkLeaf() {
		return this.trunkInfo != null && ramifications.isEmpty();
	}

	public boolean isTrunkDead() {
		return this.trunkInfo == null && ramifications.isEmpty();
	}

	public boolean isTrunkRamification() {
		return this.trunkInfo == null && !ramifications.isEmpty();
	}
	
	public List<Twig<T>> getRamifications() {
		return ramifications;
	}

	public void addTwig(final Twig<T> twig) {
		final String trunkId = twig.getId().substring(0, 1).trim();
		if (ramifications.stream().anyMatch(t -> t.getId().contentEquals(trunkId))) {
			Optional<Twig<T>> twigFoundWithId = getTwigById(trunkId);
			if (twigFoundWithId.isPresent() && twig.getId().length() > 1) {
				twigFoundWithId.get().addRamification(twig);
			}
		} else {
			ramifications.add(twig);
		}
	}

	public Optional<Twig<T>> getTwigById(String id) {
		// be aware: id can be greater than only one sign
		
		final String firstIdChar = id.substring(0, 1).trim();
		Optional<Twig<T>> target = ramifications.stream().filter(t -> t.getId().contentEquals(firstIdChar)).findFirst();

		final int oneSign = 1;
		if (target.isPresent() && id.length() == oneSign) {
			// we are at trunk level, just return
			return target; 
		} else if (target.isPresent() && id.length() > oneSign) {
			// forward to the next level
			return target.get().getTwigById(id);
		} else {
			// no match i.e. twig does not exist yet!
			return Optional.empty();
		}
	}


}
