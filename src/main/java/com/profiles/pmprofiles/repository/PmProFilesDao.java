package com.profiles.pmprofiles.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.profiles.pmprofiles.entity.PmProFiles;

public interface PmProFilesDao extends PagingAndSortingRepository<PmProFiles, Long>, PmProFilesDaoCustom {

}
