package com.waveaccess.condominiumapi.repositories;

import com.waveaccess.condominiumapi.models.Resource;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends PagingAndSortingRepository<Resource, Long> {



}
