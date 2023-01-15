package com.school.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.school.entity.Branch;

@Repository
public interface BranchRepository extends MongoRepository<Branch,String>{

}
