package com.softsuit.arbol;

import java.util.Optional;

public interface TwigTraceable<T> extends TrunkTraceable<T>{
	
	/**
	 * Every ramification (twig) has a parent twig as its starting point.
	 * @param parent the ramification (twig) which originates this one.
	 */
	void addParentTwig(final Twig<T> parent);
	
	/**
	 * Every ramification (twig) has a parent twig as its starting point.
	 * @return the parent twig
	 */
	Twig<T> getParentTwig();

	/**
	 * Every twig has the ability to search (look up) for another twig. With this method
	 * a twig is able to dispatch a trace action to its parent, which looks up
	 * for ramifications (twigs) outgoing from it.
	 * 
	 * @param id from twig to trace (look up) but starting from this twig's parent
	 * @return an Optional containing the found twig or an empty optional
	 */
	Optional<Twig<T>> traceBackwords(final String id);
	
	/**
	 * Every twig has the ability to search (look up) for another twig. With this method
	 * a twig is able to dispatch a trace action for itself, looking up
	 * for the next ramification(twig) outgoing from itself.
	 * 
	 * @param id from twig to trace (look up) forwards
	 * @return an Optional containing the found twig or an empty optional
	 */
	Optional<Twig<T>> traceForwords(final String id);
	
	
	

}
