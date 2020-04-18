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
public class Trunk<T> implements TrunkTraceable<T> {

	private String id;
	private T trunkInfo;
	private Growth<T> growth;
	private List<Twig<T>> ramifications = new ArrayList<>();
	private static final long serialVersionUID = 6163745529150493833L;
	
	public Trunk(final String id) {
		this(id, null, new ArrayList<Twig<T>>(), new Growth<T>());
	}
	
	public Trunk(final String id, final Growth<T> growth) {
		this(id, null, new ArrayList<Twig<T>>(), growth);
	}

	public Trunk(final String id, final T info, final List<Twig<T>> twigs, final Growth<T> growth) {
		if (id == null) throw new IllegalArgumentException("Id can't be null!");
		this.id = id.trim();
		this.trunkInfo = info;
		this.ramifications = twigs;
		this.growth = growth;
		this.growth.addTraceable(this);
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
	
	public Growth<T> getGrowth() {
		return this.growth;
	}

	public void addTwig(final Twig<T> twig) {
		
		if(this.growth != null) {
			this.growth.addSeed(twig.getId());
			twig.addGrowth(this.growth);
		}
		
		final String trunkId = twig.getId().substring(0, 1).trim();
		if (ramifications.stream().anyMatch(t -> t.getId().contentEquals(trunkId))) {
			Optional<Twig<T>> targetTwig = getTwigById(trunkId);
			if (targetTwig.isPresent() && twig.getId().length() > 1) {
				targetTwig.get().addRamification(twig);
			}
		} else {
			// if not found and new twigId is equals current, add
			if (twig.getId().length() - trunkId.length() == 0) {
				ramifications.add(twig);
			} else {
				// otherwise, create & add to the newly created twig
				if(this.growth != null) {
					Twig<T> newTwig = new Twig<T>(trunkId, this.growth);
					ramifications.add(newTwig);
					newTwig.addParentTwig(null);
					newTwig.addRamification(twig);	
				}else {
					Twig<T> newTwig = new Twig<T>(trunkId);
					ramifications.add(newTwig);
					newTwig.addParentTwig(null);
					newTwig.addRamification(twig);	
				}
			}
		}
	}

	public Optional<Twig<T>> getTwigById(String id) {
		// be aware: id can be greater than only one sign
		
		final String firstIdChar = id.substring(0, 1).trim();
		Optional<Twig<T>> target = ramifications.stream().filter(t -> t.getId().startsWith(firstIdChar)).findFirst();

		final int oneSign = 1;
		if (target.isPresent() && id.length() == oneSign) {
			// we are at trunk level, just return
			return target; 
		} 
		else if (target.isPresent() && id.length() > oneSign) {
			// forward to the next level (ramification - twig)
			return target.get().getTwigById(id);
		} else {
			// no match i.e. twig does not exist yet!
			return Optional.empty();
		}
	}

	// Whenever a twig decides to search from the trunk, it calls this method.
	@Override
	public Optional<Twig<T>> traceFromTrunk(final String id) {
		return getTwigById(id);
	}


}
