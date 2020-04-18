package com.softsuit.arbol;

import java.io.Serializable;
import java.util.Optional;

public interface TrunkTraceable<T> extends Serializable {

	/**
	 * Every twig has the ability to search (look up) for another twig. With this
	 * method a twig is able to dispatch a trace action to its trunk, which looks up
	 * for ramifications (twigs) outgoing from the base trunk. Trunk is the listener
	 * that implements interface also. 
	 * 
	 * @param id twig id to trace (look up)
	 * @return an Optional containing the found twig or an empty optional
	 */
	Optional<Twig<T>> traceFromTrunk(final String id);

}
