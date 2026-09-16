package com.vlad.project.controller;

import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.LoanOfferDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/application")
public class ApplicationController {

    @PostMapping()
    public ResponseEntity<List<LoanOfferDto>> apllication(@RequestBody
                                                          LoanApplicationRequestDto requestDto) {
        return ResponseEntity.ok(null);
    }

    @PutMapping("/offer")
    public ResponseEntity<Void> offer(@RequestBody
                                      LoanOfferDto requestDto) {
        return ResponseEntity.ok().build();
    }
}
