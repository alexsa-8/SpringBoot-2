package ru.hogwarts.school.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.repository.InfoPortService;

@RestController
public class InfoController {
    @Autowired
    private InfoPortService portService;
//    private final InfoPortService portService;
//
//    public InfoController(InfoPortService portService) {
//        this.portService = portService;
//    }


    @GetMapping("/getPort")
    public Integer getPort() {
        return portService.getPort();
    }
}
