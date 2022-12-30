package com.tanpn.rest.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanpn.entities.UserEntity;
import com.tanpn.repositories.UserRepository;

import com.tanpn.generate.model.LoginReq;
import com.tanpn.generate.model.LoginResp;
import com.tanpn.interfaces.ICache;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    UserRepository userRepository;
    ICache<String, String> simpleCaching;

    @Autowired
    public UserController(UserRepository userRepository,
            @Qualifier("distributedCacheTemplate") ICache<String, String> simpleCaching) {
        this.userRepository = userRepository;
        this.simpleCaching = simpleCaching;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResp> login(@RequestBody LoginReq pLoginReq) {
        LoginResp lvLoginResp = new LoginResp();

        List<UserEntity> lvListUser = userRepository.findByUsername(pLoginReq.getUsername());
        if (lvListUser != null && lvListUser.size() > 0) {
            if (pLoginReq.getPassword().equals(lvListUser.get(0).getPassword())) {
                lvLoginResp.setSuccess(true);
                final String lvToken = "token123";
                lvLoginResp.setToken(lvToken);
                this.simpleCaching.set(pLoginReq.getUsername(), lvToken);
            }
        }
        else {
            lvLoginResp.setSuccess(false);
        }
        return new ResponseEntity<>(lvLoginResp, lvLoginResp.isSuccess() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/is-logged")
    public ResponseEntity<LoginResp> isLogged(@RequestBody LoginReq pLoginReq) {
        final String lvCachedData = this.simpleCaching.getIfPresent(pLoginReq.getUsername());
        LoginResp lvLoginResp = new LoginResp();

        if (lvCachedData != null) {
            lvLoginResp.setSuccess(true);
            lvLoginResp.setToken(lvCachedData);
        } else {
            lvLoginResp.setSuccess(false);
        }
        return new ResponseEntity<>(lvLoginResp, lvLoginResp.isSuccess() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
    }
}