package fpt.edu.java5.controllers;

import fpt.edu.java5.entites.HoaDon;
import fpt.edu.java5.entites.HoaDonChiTiet;
import fpt.edu.java5.entites.NhanVien;
import fpt.edu.java5.entites.custom.HoaDonChiTietCustom;
import fpt.edu.java5.entites.custom.SanPhamChiTietCustom;
import fpt.edu.java5.repositories.HoaDonChiTietRepo;
import fpt.edu.java5.repositories.HoaDonRepo;
import fpt.edu.java5.repositories.SanPhamChiTietRepo;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/hoa-don-chi-tiet")
@RequiredArgsConstructor
public class HoaDonChiTietController {

    final HoaDonChiTietRepo hoaDonChiTietRepo;
    final HoaDonRepo hoaDonRepo;
    final SanPhamChiTietRepo spctRepo;


    @GetMapping("/hien-thi")
    public String hoaDonChiTiet(Model model, HttpSession session,
                                @RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "10") int limit
                                ) {
        NhanVien currentUser = (NhanVien) session.getAttribute("currentUSER");
        if(currentUser == null) {
            return "login/index.jsp";
        }
        if (currentUser != null &&  currentUser.getTrangThai() == 1) {
            Pageable pageable = PageRequest.of(page-1, limit);
            Page<HoaDonChiTietCustom> listHDCT = hoaDonChiTietRepo.findAllByCustomPhanTrang(pageable);
            model.addAttribute("listHDCT", listHDCT.getContent());
            model.addAttribute("totalPages", listHDCT.getTotalPages());
            model.addAttribute("currentPage", page);
            model.addAttribute("currentUser", currentUser);
            return "hoa_don_chi_tiet/index.jsp";
        }else {
            return "kich_thuoc/index.jsp";
        }

    }
//    @GetMapping("/hien-thi")
//    public String hoaDonChiTiet(Model model, HttpSession session) {
//            List<HoaDonChiTietCustom> listHDCT = hoaDonChiTietRepo.findAllByCustom();
//            model.addAttribute("listHDCT", listHDCT);
//            return "hoa_don_chi_tiet/index.jsp";
//    }

    @GetMapping("/edit/{id}")
    public String hoaDonChiTiet(@PathVariable Integer id, Model model) {
        HoaDonChiTiet hdctDetail = hoaDonChiTietRepo.findById(id).get();
        List<HoaDon> listHD = hoaDonRepo.findAll();
        List<SanPhamChiTietCustom> listSPCT = spctRepo.findAllByCustom();
        model.addAttribute("hdctDetail", hdctDetail);
        model.addAttribute("listHD", listHD);
        model.addAttribute("listSPCT", listSPCT);
        return "hoa_don_chi_tiet/update.jsp";
    }

    @PostMapping("/update/{id}")
    public String updateHoaDonChiTiet(@Valid HoaDonChiTiet hoaDonChiTiet, BindingResult bindingResult, Model model) {
        List<HoaDon> listHD = hoaDonRepo.findAll();
        List<SanPhamChiTietCustom> listSPCT = spctRepo.findAllByCustom();
        if (bindingResult.hasErrors()){
            Map<String,String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            model.addAttribute("errors", errors);
            model.addAttribute("hdctDetail", hoaDonChiTiet);
            model.addAttribute("listHD", listHD);
            model.addAttribute("listSPCT", listSPCT);
            return "hoa_don_chi_tiet/update.jsp";
        }
        hoaDonChiTietRepo.save(hoaDonChiTiet);
        return "redirect:/hoa-don-chi-tiet/hien-thi";
    }
}
