package com.mutaz.aggregatormicroservice.provider.impl;

import java.io.IOException;
import java.util.Set;

import com.mutaz.aggregatormicroservice.provider.api.HotelsProvider;
import com.mutaz.aggregatormicroservice.provider.api.SearchCriteria;
import com.mutaz.aggregatormicroservice.provider.api.SearchResult;

public class CrazyHotelsProvider implements HotelsProvider {

	@Override
	public String getName() {
		return null;
	}

	@Override
	public Set<SearchResult> search(String url,SearchCriteria searchCriteria) throws IOException {
		return null;
	}

}
