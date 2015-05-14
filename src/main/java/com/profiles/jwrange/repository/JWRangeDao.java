package com.profiles.jwrange.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.profiles.jwrange.entity.JWRange;

public interface JWRangeDao extends PagingAndSortingRepository<JWRange, Long>, JWRangeDaoCustom {

}
