package com.testTaskData.controller;

import com.testTaskData.domain.json.DataJson;
import com.testTaskData.response.Response;
import com.testTaskData.service.TestDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TestDataController {
    private final Logger logger = LoggerFactory.getLogger(TestDataController.class);

    @Autowired
    private TestDataService dataService;

    @PostMapping("saveData")
    @ResponseBody
    public Response saveData(@RequestBody @Valid DataJson data, Errors errors) {
        logger.error("Send data:", data);
        if (errors.hasErrors())
            return Response.error(errors.getAllErrors()
                    .stream().map(item -> item.getDefaultMessage())
                    .collect(Collectors.joining(", ")));

        try {
            return Response.ok(dataService.save(data));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Response.error("Server error");
        }
    }

    @GetMapping(value = "getData", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Response getData(HttpServletRequest request) {
        return Response.ok(dataService.getData());
    }

}
