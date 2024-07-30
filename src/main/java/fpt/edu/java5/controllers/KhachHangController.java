package fpt.edu.java5.controllers;

import fpt.edu.java5.entites.KhachHang;
import fpt.edu.java5.entites.KichThuoc;
import fpt.edu.java5.entites.NhanVien;
import fpt.edu.java5.repositories.KhachHangRepo;
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
@RequestMapping("/khach-hang")
public class KhachHangController {

    @Autowired
    KhachHangRepo khachHangRepo;



    @GetMapping("/hien-thi")
    public String khachHang(Model model, HttpSession session,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit) {
        NhanVien currentUser = (NhanVien) session.getAttribute("currentUSER");
        if(currentUser == null) {
            return "login/index.jsp";
        }
        model.addAttribute("currentUser", currentUser);
        Pageable p = PageRequest.of(page -1 , limit);
        Page<KhachHang> listKH = khachHangRepo.findAll(p);
        model.addAttribute("listKH", listKH.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", listKH.getTotalPages());
        return "khach_hang/index.jsp";
    }

    @GetMapping("/tim-theo-ten")
    public String findByName(@RequestParam("timKiem") String timKiem, Model model) {
        List<KhachHang> listKH;
        if (timKiem == null || timKiem.equals("")) {
            listKH = khachHangRepo.findAll();
        }else {
            listKH = khachHangRepo.findAllByTenContains(timKiem);
        }
        model.addAttribute("listKH", listKH);
        return "khach_hang/index.jsp";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("data") KhachHang khachHang) {
        return "khach_hang/create.jsp";
    }

    @PostMapping("/store")
    public String store(@Valid KhachHang khachHang, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<String, String>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            model.addAttribute("errors", errors);
            model.addAttribute("data", khachHang);
            return "khach_hang/create.jsp";
        }
        khachHangRepo.save(khachHang);
        return "redirect:/khach-hang/hien-thi";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        khachHangRepo.deleteById(id);
        return "redirect:/khach-hang/hien-thi";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        KhachHang khDetail = khachHangRepo.findById(id).get();
        model.addAttribute("khDetail",khDetail);
        return "/khach_hang/update.jsp";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid KhachHang khachHang, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<String, String>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            model.addAttribute("errors", errors);
            model.addAttribute("khDetail", khachHang);
            return "khach_hang/update.jsp";
        }
        khachHangRepo.save(khachHang);
        return "redirect:/khach-hang/hien-thi";
    }
}
