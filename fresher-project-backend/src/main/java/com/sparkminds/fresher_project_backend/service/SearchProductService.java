package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.dto.request.SearchProductByNameRequest;
import com.sparkminds.fresher_project_backend.dto.request.SearchProductByPriceRangeRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;

public interface SearchProductService {
    ResponsePayload searchProductsByName(SearchProductByNameRequest request);
    ResponsePayload searchProductByPrice(SearchProductByPriceRangeRequest request);
}
