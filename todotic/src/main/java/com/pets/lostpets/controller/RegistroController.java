package com.pets.lostpets.controller;

import com.pets.lostpets.entity.Admin;
import com.pets.lostpets.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistroController {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registro")
    public String index(Model model){

        model.addAttribute("admin", new Admin());
    return "registro";
    }

    @PostMapping("/registro")
    public String crear(@Validated Admin admin, BindingResult bindingResult,RedirectAttributes ra, Model model){

        if(bindingResult.hasErrors()){
        model.addAttribute("admin", admin);
            return "registro";
        }

        // validar la unicidad del correo electrónico
        String email = admin.getEmail();
        boolean usuarioYaExiste = adminRepository.existsByEmail(email);

        if (usuarioYaExiste) {
            bindingResult.rejectValue("email", "EmailAlreadyExists");
        }

        // validar la coincidencia de las contraseñas
        if (!admin.getPassword1().equals(admin.getPassword2())) {
            bindingResult.rejectValue("password1", "PasswordNotEquals");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("usuario", admin);
            return "registro";
        }

        admin.setPassword(passwordEncoder.encode(admin.getPassword1()));
        admin.setRol(Admin.Rol.valueOf("ADMIN"));
        adminRepository.save(admin);

        ra.addAttribute("registroExitoso", "");

        return "redirect:/registradook";
    }



    @GetMapping("/contacto")
    public String contacto(Model model) {
        return "contacto";
    }

    @GetMapping("/registradook")
    public String contactoRegistrado(Model model){
        return "registradook";
    }

}
