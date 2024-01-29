package uz.pdp.simline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.stylesheets.LinkStyle;
import uz.pdp.simline.service.SimCardService;
import uz.pdp.simline.util.Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@RestController
@RequiredArgsConstructor
public class GenerateController {
    private final Generator generator;
    @GetMapping("/generate")
    public ResponseEntity<?> generate(){
        generator.generator();
        return ResponseEntity.ok(Map.of("message","Success"));
    }
}
