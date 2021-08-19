package com.bobo.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bobo
 * @date 2021/8/19
 * @apiNote
 */
@RestController
@Slf4j
public class DockerController {

    @RequestMapping("/")
    public String index() {
        log.info("Hello Docker!");
        return "Hello Docker!";
    }
}
