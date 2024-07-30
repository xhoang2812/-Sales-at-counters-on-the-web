package fpt.edu.java5.controllers;

import fpt.edu.java5.entites.*;
import fpt.edu.java5.entites.custom.SanPhamChiTietCustom;
import fpt.edu.java5.repositories.KichThuocRepo;
import fpt.edu.java5.repositories.MauSacRepo;
import fpt.edu.java5.repositories.SanPhamChiTietRepo;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/san-pham-chi-tiet")
public class SanPhamChiTietController {

    @Autowired
    private SanPhamRepo sanPhamRepo;
    @Autowired
    private SanPhamChiTietRepo spctRepo;
    @Autowired
    private KichThuocRepo kichThuocRepo;
    @Autowired
    private MauSacRepo mauSacRepo;


    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session,
                          @RequestParam(defaultValue = "1") Integer page
            , @RequestParam(defaultValue = "50") Integer limit) {
        NhanVien currentUser = (NhanVien) session.getAttribute("currentUSER");
        if (currentUser == null) {
            return "login/index.jsp";
        }
        model.addAttribute("currentUser", currentUser);

        Pageable p = PageRequest.of(page - 1, limit);
        Page<SanPhamChiTietCustom> listSPCT = spctRepo.findAllByCustomPhanTrang(p);
        List<SanPham> listSP = sanPhamRepo.findAll();

        model.addAttribute("listSP", listSP);
        model.addAttribute("listSPCT", listSPCT.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", listSPCT.getTotalPages());
        return "spct/index.jsp";
    }

    @GetMapping("/create")
    public String create(Model model, @ModelAttribute("data") SanPhamChiTiet sanPhamChiTiet) {
        List<SanPham> listSP = sanPhamRepo.findAll();
        List<KichThuoc> listKT = kichThuocRepo.findAll();
        List<MauSac> listMS = mauSacRepo.findAll();
        model.addAttribute("listKT", listKT);
        model.addAttribute("listMS", listMS);
        model.addAttribute("listSP", listSP);
        return "spct/create.jsp";
    }

    @PostMapping("/store")
    public String store(@Valid SanPhamChiTiet sanPhamChiTiet, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            List<SanPham> listSP = sanPhamRepo.findAll();
            List<KichThuoc> listKT = kichThuocRepo.findAll();
            List<MauSac> listMS = mauSacRepo.findAll();
            model.addAttribute("listKT", listKT);
            model.addAttribute("listMS", listMS);
            model.addAttribute("listSP", listSP);
            model.addAttribute("errors", errors);
            model.addAttribute("data", sanPhamChiTiet);
            return "spct/create.jsp";
        }
        spctRepo.save(sanPhamChiTiet);
        return "redirect:/san-pham-chi-tiet/hien-thi";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        spctRepo.deleteById(id);
        return "redirect:/san-pham-chi-tiet/hien-thi";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        SanPhamChiTiet spctDetail = spctRepo.findById(id).get();
        List<SanPham> listSP = sanPhamRepo.findAll();
        List<KichThuoc> listKT = kichThuocRepo.findAll();
        List<MauSac> listMS = mauSacRepo.findAll();
        model.addAttribute("listKT", listKT);
        model.addAttribute("listMS", listMS);
        model.addAttribute("listSP", listSP);
        model.addAttribute("spctDetail", spctDetail);
        return "spct/update.jsp";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid SanPhamChiTiet sanPhamChiTiet, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            List<SanPham> listSP = sanPhamRepo.findAll();
            List<KichThuoc> listKT = kichThuocRepo.findAll();
            List<MauSac> listMS = mauSacRepo.findAll();
            model.addAttribute("listKT", listKT);
            model.addAttribute("listMS", listMS);
            model.addAttribute("listSP", listSP);
            model.addAttribute("errors", errors);
            model.addAttribute("spctDetail", sanPhamChiTiet);
            return "spct/update.jsp";
        }
        spctRepo.save(sanPhamChiTiet);
        return "redirect:/san-pham-chi-tiet/hien-thi";
    }

//    @GetMapping("/tim-kiem-theo-san-pham")
//    public String timTheoSP(Model model, @RequestParam("sanPham") Integer idSP) {
//        List<SanPhamChiTietCustom> listSPCT;
//        if (idSP <= 0) {
//            listSPCT = spctRepo.findAllByCustom(p);
//        } else {
//            listSPCT = spctRepo.findAllByIdSanPham(idSP);
//        }
//        List<SanPham> listSP = sanPhamRepo.findAll();
//        model.addAttribute("listSP", listSP);
//        model.addAttribute("listSPCT", listSPCT);
//        return "spct/index.jsp";
//    }
}
