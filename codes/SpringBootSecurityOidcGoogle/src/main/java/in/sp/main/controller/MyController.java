package in.sp.main.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/")
    public String openIndexPage(){
        return "index";
    }
    @GetMapping("/login")
    public String openLoginPage(){
        return "login";
    }

    @GetMapping("/profile")
    public String openProfilePage(@AuthenticationPrincipal OidcUser principal, Model model){

        if(principal!=null){
            String fullName = principal.getFullName();
            String email = principal.getEmail();

            // Retrieve the address from claims if available
            String address = (String) principal.getClaims().get("address");

            model.addAttribute("fullName", fullName);
            model.addAttribute("email", email);
            model.addAttribute("address", address != null ? address : "Address not available");

        }

        return "profile";
    }

}
