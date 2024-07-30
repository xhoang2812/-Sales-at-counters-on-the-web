package fpt.edu.java5.controllers;

import fpt.edu.java5.entites.HoaDon;
import fpt.edu.java5.entites.HoaDonChiTiet;
import fpt.edu.java5.entites.KhachHang;
import fpt.edu.java5.entites.NhanVien;
import fpt.edu.java5.entites.custom.HoaDonCustom;
import fpt.edu.java5.repositories.HoaDonChiTietRepo;
import fpt.edu.java5.repositories.HoaDonRepo;
import fpt.edu.java5.repositories.KhachHangRepo;
import fpt.edu.java5.repositories.NhanVienRepo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/hoa-don")
@RequiredArgsConstructor
public class HoaDonController {

    private final HoaDonRepo hoaDonRepo;
    private final NhanVienRepo nhanVienRepo;
    private final KhachHangRepo khachHangRepo;
    private final HoaDonChiTietRepo hoaDonChiTietRepo;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @GetMapping("/hien-thi")
    public String hoaDon(Model model, HttpSession session,
                         @RequestParam(defaultValue = "1") int page,
                         @RequestParam(defaultValue = "5") int limit) {
        NhanVien currentUser = (NhanVien) session.getAttribute("currentUSER");
        if (currentUser == null) {
            return "login/index.jsp";
        }
        if (currentUser != null && currentUser.getTrangThai() == 1) {
            Pageable pageable = PageRequest.of(page-1, limit);
            Page<HoaDonCustom> listHD = hoaDonRepo.findAllByCustomPhanTrang(pageable);
            model.addAttribute("listHD", listHD.getContent());
            model.addAttribute("totalPages", listHD.getTotalPages());
            model.addAttribute("currentPage", page);
            model.addAttribute("currentUser", currentUser);
            return "hoa_don/index.jsp";
        } else {
            return "kich_thuoc/index.jsp";
        }

    }
//    @GetMapping("/hien-thi")
//    public String hoaDon(Model model, HttpSession session) {
//            List<HoaDon> listHD = hoaDonRepo.findAll();
//            model.addAttribute("listHD", listHD);
//            return "hoa_don/index.jsp";
//
//
//    }

    @GetMapping("/edit/{id}")
    public String hoaDonEdit(@PathVariable Integer id, Model model) {
        HoaDon hdDetail = hoaDonRepo.findById(id).get();
        List<KhachHang> listKH = khachHangRepo.findAll();
        List<NhanVien> listNV = nhanVienRepo.findAll();
        model.addAttribute("hdDetail", hdDetail);
        model.addAttribute("listKH", listKH);
        model.addAttribute("listNV", listNV);
        return "hoa_don/update.jsp";
    }

    @PostMapping("/update/{id}")
    public String hoaDonUpdate(HoaDon hoaDon) {
        hoaDonRepo.save(hoaDon);
        return "redirect:/hoa-don/hien-thi";
    }

    @GetMapping("/detail/{id}")
    public String hoaDonDetail(@PathVariable Integer id, Model model) {
        model.addAttribute("listHD", hoaDonRepo.findAllByCustomByTrangThai1());
        model.addAttribute("listHDCT", hoaDonChiTietRepo.findAllByCustomAndIdHoaDon(id));
        return "hoa_don/detail.jsp";
    }

}
