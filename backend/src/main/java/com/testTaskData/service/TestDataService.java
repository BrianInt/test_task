package com.testTaskData.service;

import com.testTaskData.domain.json.DataJson;
import com.testTaskData.domain.model.TestData;
import com.testTaskData.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestDataService {
    @Autowired
    private DataRepository repository;

    public TestData save(DataJson data) {
        return repository.save(new TestData.Builder()
                .setDate(data.getDate())
                .setDescription(data.getDescription())
                .setCategory(data.getCategory())
                .setAmount(data.getAmount())
                .build());
    }

    public List<TestData> getData() {
        return repository.findAll();
    }

}
