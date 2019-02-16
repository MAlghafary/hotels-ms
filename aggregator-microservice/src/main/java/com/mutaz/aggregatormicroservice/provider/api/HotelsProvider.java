package com.mutaz.aggregatormicroservice.provider.api;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

/**
 * A General interface to be implemented by different Hotels Providers. each provider 
 * must implement one method {@link HotelsProvider#search(SearchCriteria)} where each provider 
 * will do the mapping between the general  API and the provider specific API.  
 * 
 * Each provider must also provide a name that should be unique.
 * 
 * Each provider can optionally implement the {@link HotelsProvider#fallback()} method, this method will 
 * be used as a fallback strategy in case in provider didn't respond back in a timely manner. the default 
 * implementation  will simple return an empty result. 
 * 
 * @author Mutaz.Alghafary
 *
 */
public interface HotelsProvider {

	/**
	 * Provides a unique name for this provider.  
	 * @return
	 */
	public String getName();
	

	/**
	 * 
	 * @param server         : The server (host:Port) where the provider needs to execute the request
	 * @param searchCriteria : Search Criteria used to filter the results 
	 * @return
	 * @throws IOException   
	 */
	public Set<SearchResult> search(String server,SearchCriteria searchCriteria) throws IOException;

	/**
	 * A fallback method used if IO exceptions is thrown from the {@link HotelsProvider#search(String, SearchCriteria)}
	 * method. 
	 * @return
	 */
	public default Set<SearchResult> fallback() {
		return Collections.emptySet();
	}

}
