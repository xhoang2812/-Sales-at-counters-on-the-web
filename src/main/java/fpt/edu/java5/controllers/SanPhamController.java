package fpt.edu.java5.controllers;


import fpt.edu.java5.entites.NhanVien;
import fpt.edu.java5.entites.SanPham;
import fpt.edu.java5.repositories.SanPhamRepo;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/san-pham")
public class SanPhamController {

    @Autowired
    private SanPhamRepo sanPhamRepo;

//    @GetMapping("/hien-thi")
//    public String hienThi(Model model, @RequestParam(name="limit", defaultValue="10") int pageSize,
//                          @RequestParam(name="page", defaultValue="0") int pageNo) {
//        Pageable p = PageRequest.of(pageNo, pageSize);
//        Page<SanPham> page = sanPhamRepo.findAll(p);
//        model.addAttribute("data", page);
////        model.addAttribute("listSP", sanPhamRepo.findAll());
//        return "san_pham/hienThi.jsp";
//    }
    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session,@RequestParam(name = "limit", defaultValue = "10") int limit,
                          @RequestParam(name = "page", defaultValue = "1") int page) {
        NhanVien currentUser = (NhanVien) session.getAttribute("currentUSER");
        if(currentUser == null) {
            return "login/index.jsp";
        }

        Pageable p = PageRequest.of(page-1, limit);
        Page<SanPham> listSP = sanPhamRepo.findAll(p);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("listSP", listSP.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", listSP.getTotalPages());
        return "san_pham/hienThi.jsp";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("data") SanPham sanPham) {
        return "san_pham/create.jsp";
    }

    @GetMapping("/edit/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        SanPham sanPhamDetail = sanPhamRepo.findById(id).get();
        model.addAttribute("sanPhamDetail", sanPhamDetail);
        return "san_pham/update.jsp";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid SanPham sanPham, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            Map<String,String> errors = new HashMap<>();
            for (FieldError e : bindingResult.getFieldErrors()){
                errors.put(e.getField() , e.getDefaultMessage());
            }
            model.addAttribute("sanPhamDetail", sanPham);
            model.addAttribute("errors", errors);
            return "san_pham/update.jsp";
        }
        sanPhamRepo.save(sanPham);
        return "redirect:/san-pham/hien-thi";
    }
    @PostMapping("/store")
    public String store(Model model,@Valid  SanPham sanPham, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            Map<String,String> errors = new HashMap<>();
            for (FieldError e : bindingResult.getFieldErrors()){
                errors.put(e.getField() , e.getDefaultMessage());
            }
            model.addAttribute("data", sanPham);
            model.addAttribute("errors", errors);
            return "san_pham/create.jsp";
        }
        sanPhamRepo.save(sanPham);
        return "redirect:/san-pham/hien-thi";
    }
    @GetMapping("/delete/{id}")
    public String xoa(@PathVariable("id") Integer id) {
        sanPhamRepo.deleteById(id);
        return "redirect:/san-pham/hien-thi";
    }

    @GetMapping("/tim-theo-ten")
    public String findByName(@RequestParam("timKiem") String timKiem, Model model) {
        List<SanPham> listSP;
        if (timKiem == null || timKiem.equals("")) {
            listSP = sanPhamRepo.findAll();
        }else {
            listSP = sanPhamRepo.findAllByTenContains(timKiem);
        }
        model.addAttribute("listSP", listSP);
        return "san_pham/hienThi.jsp";
    }

}
