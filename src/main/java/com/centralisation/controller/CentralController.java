package com.centralisation.controller;

import com.centralisation.service.CentralService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CentralController {

    private final CentralService centralService;
}
