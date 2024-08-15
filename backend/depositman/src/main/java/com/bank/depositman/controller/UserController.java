package com.bank.depositman.controller;

import com.bank.depositman.service.UserService;
import com.bank.depositman.utils.dto.UserDTO;
import com.bank.depositman.utils.response.Response;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?>create(@RequestBody UserDTO req){
        return Response.renderJSON(
                userService.create(req),"userc created",
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable Integer id){
        return Response.renderJSON(userService.getById(id));
    }


}
