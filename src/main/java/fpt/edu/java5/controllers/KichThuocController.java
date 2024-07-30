package fpt.edu.java5.controllers;

import fpt.edu.java5.entites.KichThuoc;
import fpt.edu.java5.entites.NhanVien;
import fpt.edu.java5.repositories.KichThuocRepo;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/kich-thuoc")
public class KichThuocController {

    @Autowired
    KichThuocRepo kichThuocRepo;


    @GetMapping("/hien-thi")
    public String kichThuoc(Model model, HttpSession session,
                            @RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "5") int limit) {
        NhanVien currentUser = (NhanVien) session.getAttribute("currentUSER");
        if(currentUser == null) {
            return "login/index.jsp";
        }
        model.addAttribute("currentUser", currentUser);
        Pageable p = PageRequest.of(page - 1, limit);
        Page<KichThuoc> listKT  = kichThuocRepo.findAll(p);
        model.addAttribute("listKT", listKT.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", listKT.getTotalPages());
        return "kich_thuoc/index.jsp";
    }

    @GetMapping("/tim-theo-ten")
    public String findByName(@RequestParam("ten") String timKiem, Model model) {
        List<KichThuoc> listKT;
        if (timKiem.isEmpty() || timKiem.equals("")) {
            listKT = kichThuocRepo.findAll();
        }else {
            listKT = kichThuocRepo.findAllByTenContains(timKiem);
        }
        model.addAttribute("listKT", listKT);
        return "kich_thuoc/index.jsp";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("data") KichThuoc kichThuoc) {
        return "kich_thuoc/create.jsp";
    }



    @PostMapping("/store")
    public String store(@Valid  KichThuoc kichThuoc, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            Map<String,String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            model.addAttribute("errors", errors);
            model.addAttribute("data", kichThuoc);
            return "kich_thuoc/create.jsp";
        }
        kichThuocRepo.save(kichThuoc);
        return "redirect:/kich-thuoc/hien-thi";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        kichThuocRepo.deleteById(id);
        return "redirect:/kich-thuoc/hien-thi";
    }

    @GetMapping("/edit/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        KichThuoc ktDetail = kichThuocRepo.findById(id).get();
        model.addAttribute("ktDetail",ktDetail);
        return "/kich_thuoc/update.jsp";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid KichThuoc kichThuoc, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            Map<String,String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            model.addAttribute("errors", errors);
            model.addAttribute("ktDetail", kichThuoc);
            return "kich_thuoc/update.jsp";
        }
        kichThuocRepo.save(kichThuoc);
        return "redirect:/kich-thuoc/hien-thi";
    }
}
