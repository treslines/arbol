package com.softsuit.arbol;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Twigable<T> extends Serializable{
	
	/**
	 * @return this twig id.
	 */
	String getId();
	
	/**
	 * A twig is considered a leaf, if it has a TwigInfo but no further ramifications.
	 * @return true if it contains a TwigInfo and no ramification, false otherwise.
	 */
	boolean isTwigLeaf();
	
	/**
	 * Use this method whenever you want to safely remove a twig.
	 * A twig is considered dead, if no TwigInfo and ramifications are present.
	 * @return true if does not contain TwigInfo and ramifications, false otherwise.
	 */
	boolean isTwigDead();
	
	/**
	 * A twig is considered a ramification, if it does NOT contain any TwigInfo,
	 * but has branches to other twigs.
	 * @return true it has branches to other twigs but no TwigInfo, false otherwise.
	 */
	boolean isTwigRamification();
	
	/**
	 * A twig info is the information (object) associated to this twig.
	 * @return Any type of object that describes this twig.
	 */
	T getTwigInfo();
	
	/**
	 * A twig info is the content that describes this twig. It contains its information.
	 * @param twigInfo any object you define as useful.
	 */
	void setTwigInfo(final T twigInfo);
	
	/**
	 * Returns all ramifications (branches) going out of this twig.
	 * @return a list of twigs from its type.
	 */
	List<Twig<T>> getRamifications();
	
	/**
	 * Adds a new ramification (branch) to this tree.
	 * @param twig a new ramification (branch) to be added to this twig.
	 */
	void addRamification(final Twig<T> twig);
	
	/**
	 * Call isTwigDead() first to be sure that this twig is empty before deleting it.
	 * Removes the specified ramification (branch) to this twig. 
	 * @param id a twig id to remove.
	 */
	void removeRamification(final String id);
	
	/**
	 * This method runs forcursively over all existing twigs (starting from the tree trunk) 
	 * looking for the matching id.
	 * @param id a twig id to look for.
	 * @return an Optional describing if any match, or an empty Optional. 
	 */
	Optional<Twig<T>> getTwigById(final String id);
	
	/**
	 * Returns the ramification twig (branch) by its id.
	 * @param id a twig id to look for.
	 * @return an Optional describing if any match, or an empty Optional.
	 */
	Optional<Twig<T>> getRamificationById(final String id);
}
