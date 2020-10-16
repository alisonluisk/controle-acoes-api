package com.ibolsa.api.resources.v1;

import com.ibolsa.api.config.UserSS;
import com.ibolsa.api.dto.login.LoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/v1")
public class SecurityResource {

    @GetMapping(value = "/user-auth")
    @ResponseBody
    public ResponseEntity<LoginDTO> user() {
        UserSS user = (UserSS) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        LoginDTO loginDTO = new LoginDTO(user.getUsuario());
        return ResponseEntity.ok().body(loginDTO);
    }

}
