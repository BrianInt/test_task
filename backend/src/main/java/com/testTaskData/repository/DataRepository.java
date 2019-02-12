package com.testTaskData.repository;

import com.testTaskData.domain.model.TestData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<TestData, Long> {

}
