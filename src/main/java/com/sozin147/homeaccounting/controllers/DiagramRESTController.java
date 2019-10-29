package com.sozin147.homeaccounting.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Указывает что принимает и возвращает только JSON, без vue части
@RestController
public class DiagramRESTController {

    @RequestMapping("/dataFromDiagram")
    public String testGet() {
        String test = "{\"cols\": [{\"id\":\"\",\"label\":\"Topping\",\"pattern\":\"\",\"type\":\"string\"}, " +
                "{\"id\":\"\",\"label\":\"Slices\",\"pattern\":\"\",\"type\":\"number\"}], \"rows\": " +
                "[{\"c\":[{\"v\":\"Грибы\",\"f\":null},{\"v\":30,\"f\":null}]}, {\"c\":[{\"v\":\"Лук\",\"f\":null}," +
                "{\"v\":10,\"f\":null}]}, {\"c\":[{\"v\":\"Olives\",\"f\":null},{\"v\":5,\"f\":null}]}, " +
                "{\"c\":[{\"v\":\"Цукини\",\"f\":null},{\"v\":7,\"f\":null}]}, {\"c\":[{\"v\":\"Паперони\",\"f\":null}," +
                "{\"v\":20,\"f\":null}]}]}";
        return test;
    }
}
