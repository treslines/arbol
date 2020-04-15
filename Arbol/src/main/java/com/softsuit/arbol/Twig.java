package com.softsuit.arbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <pre>
 * A natural tree implementation. A twig can be:
 * 
 * - a ramification only (where there is no twig info but branches to another twigs),
 * - a leaf, where the is no more ramification from it and it does contain a twig info,
 * - a removable (dead) twig, which does not contain a twig info and any ramifications.
 * 
 * </pre>
 * 
 * @author Ricardo Ferreira
 *
 * @param <T> Any Object of your choice used to describe or store information about this twig.
 */
public class Twig<T> implements Twigable<T> {

	private String id;
	private T twigInfo;
	private List<Twig<T>> ramifications;
	private Growth growth;
	private static final long serialVersionUID = -2274874211524488445L;

	public Twig(final String id) {
		this(id, null, new ArrayList<Twig<T>>());
		this.growth = null;
	}
	
	public Twig(final String id, final Growth growth) {
		this(id, null, new ArrayList<Twig<T>>());
		this.growth = growth;
	}

	public Twig(final String id, final T info, final List<Twig<T>> twigs) {
		if (id == null) throw new IllegalArgumentException("Id can't be null!");
		this.id = id.trim();
		this.twigInfo = info;
		this.ramifications = twigs;
	}

	public String getId() {
		return id;
	}

	public boolean isTwigWithLeaf() {
		return this.twigInfo != null && !ramifications.isEmpty();
	}

	public boolean isTwigLeaf() {
		return this.twigInfo != null && ramifications.isEmpty();
	}

	public boolean isTwigDead() {
		return this.twigInfo == null && ramifications.isEmpty();
	}

	public boolean isTwigRamification() {
		return this.twigInfo == null && !ramifications.isEmpty();
	}

	public T getTwigInfo() {
		return twigInfo;
	}

	public void setTwigInfo(T twigInfo) {
		this.twigInfo = twigInfo;
	}

	public List<Twig<T>> getRamifications() {
		return ramifications;
	}

	public void addRamification(final Twig<T> twig) {

		if(this.growth != null) {
			this.growth.addSeed(twig.getId());
		}
		
		final int nextIdIndex = ((this.id.length() + 1) > twig.getId().length()) ? twig.getId().length() : (this.id.length() + 1);
		final String nextTwigId = twig.getId().substring(0, nextIdIndex).trim();

		if (getRamifications().stream().anyMatch(t -> t.getId().contentEquals(nextTwigId))) {
			Optional<Twig<T>> target = getRamificationById(nextTwigId);
			if (target.isPresent() && twig.getId().length() > target.get().getId().length()) {
				target.get().addRamification(twig);
			} else {
				// already exists, no need to re-add!
			}
		} else {
			// if not found and new twigId is only one sign greater than current, add
			if (twig.getId().length() - nextTwigId.length() == 0) {
				ramifications.add(twig);
			} else {
				// otherwise, create and add to the newly created twig
				if(this.growth != null) {
					Twig<T> newTwig = new Twig<T>(nextTwigId, this.growth);
					this.ramifications.add(newTwig);
					newTwig.getRamifications().add(twig);					
				}else {
					Twig<T> newTwig = new Twig<T>(nextTwigId);
					this.ramifications.add(newTwig);
					newTwig.getRamifications().add(twig);	
				}
			}
		}
	}

	public Optional<Twig<T>> getRamificationById(final String id) {
		return this.ramifications.stream().filter(t -> t.getId().contentEquals(id)).findFirst();
	}

	public Optional<Twig<T>> getTwigById(final String id) {

		final int nextIdIndex = ((this.id.length() + 1) > id.length()) ? id.length() : (this.id.length() + 1);
		final String nextTwigId = id.substring(0, nextIdIndex).trim();

		Optional<Twig<T>> target = this.ramifications.stream().filter(t -> t.getId().contentEquals(nextTwigId)).findFirst();

		final int twigIdSigns = nextTwigId.length();
		if (target.isPresent() && id.length() == twigIdSigns) {
			// we are at twig level, just return
			return target;
		} else if (target.isPresent() && id.length() > twigIdSigns) {
			// forward to the next level
			return target.get().getTwigById(id);
		} else {
			// no match i.e. twig does not exist yet!
			return Optional.empty();
		}
	}

	@Override
	public void removeRamification(String id) {
		
		if(this.growth != null) {
			this.growth.removeSeed(id);
		}
		
		Optional<Twig<T>> target = this.ramifications.stream().filter(t -> t.getId().contentEquals(id)).findFirst();
		if(target.isPresent()) {
			this.ramifications.remove(target.get());
		}
	}
	
	public void addGrowth(final Growth growth) {
		this.growth = growth;
	}

}
