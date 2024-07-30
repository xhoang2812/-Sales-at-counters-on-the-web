package fpt.edu.java5.controllers;

import com.oracle.wls.shaded.org.apache.xpath.operations.Mod;
import fpt.edu.java5.entites.MauSac;
import fpt.edu.java5.entites.NhanVien;
import fpt.edu.java5.repositories.NhanVienRepo;
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
@RequestMapping("/nhan-vien")
public class NhanVienController {
    @Autowired
    NhanVienRepo nhanVienRepo ;


    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session,
                          @RequestParam(defaultValue = "1") int page,
                          @RequestParam(defaultValue = "10") int limit) {
        NhanVien currentUser = (NhanVien) session.getAttribute("currentUSER");
        if(currentUser == null) {
            return "login/index.jsp";
        }
        model.addAttribute("currentUser", currentUser);
        Pageable p = PageRequest.of(page-1,limit);
        Page<NhanVien> listNV = nhanVienRepo.findAll(p);
        model.addAttribute("listNV", listNV.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", listNV.getTotalPages());
        return "nhan_vien/index.jsp";
    }

    @GetMapping("/tim-theo-ten")
    public String findByName(@RequestParam("timKiem") String timKiem, Model model) {
        List<NhanVien> listNV;
        if (timKiem == null || timKiem.equals("")) {
            listNV = nhanVienRepo.findAll();
        }else {
            listNV = nhanVienRepo.findAllByTenContains(timKiem);
        }
        model.addAttribute("listNV", listNV);
        return "nhan_vien/index.jsp";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("data") NhanVien nhanVien) {
        return "nhan_vien/create.jsp";
    }

    @PostMapping("/store")
    public String store(Model model, @Valid NhanVien nhanVien, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<String, String>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            model.addAttribute("errors", errors);
            model.addAttribute("data", nhanVien);
            return "nhan_vien/create.jsp";
        }
        nhanVienRepo.save(nhanVien);
        return "redirect:/nhan-vien/hien-thi";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        nhanVienRepo.deleteById(id);
        return "redirect:/nhan-vien/hien-thi";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        NhanVien nvDetail = nhanVienRepo.findById(id).get();
        model.addAttribute("nvDetail", nvDetail);
        return "nhan_vien/update.jsp";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid  NhanVien nhanVien, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<String, String>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            model.addAttribute("errors", errors);
            model.addAttribute("nvDetail", nhanVien);
            return "nhan_vien/update.jsp";
        }
        nhanVienRepo.save(nhanVien);
        return "redirect:/nhan-vien/hien-thi";
    }
}
