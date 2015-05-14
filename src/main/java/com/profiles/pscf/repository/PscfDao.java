package com.profiles.pscf.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.profiles.pscf.entity.Pscf;

public interface PscfDao extends PagingAndSortingRepository<Pscf, Long>, PscfDaoCustom {

}
