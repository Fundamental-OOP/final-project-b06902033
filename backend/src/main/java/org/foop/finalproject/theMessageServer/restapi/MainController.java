package org.foop.finalproject.theMessageServer.restapi;

import org.foop.finalproject.theMessageServer.Main;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@CrossOrigin(origins = "*")
public class MainController {
    // 用來開始執行程式 （用PostConstruct標記的方法會在一開始就執行）
    @PostConstruct
    public void run() {
        try {
            Main main = new Main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
