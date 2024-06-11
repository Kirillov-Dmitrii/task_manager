package org.grogu.task_manager.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestController {

    @GetMapping("/unsecured")
    public ResponseEntity<String> getUnsecured() {
        return ResponseEntity.ok("unsecured");
    }

    @GetMapping("/secured")
    public ResponseEntity<String> getSecured() {
        return ResponseEntity.ok("secured");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAdmin() {
        return ResponseEntity.ok("admin");
    }

    @GetMapping("/info")
    public ResponseEntity<String> getInfo(Principal principal) {
        return ResponseEntity.ok(principal.getName());
    }

}
