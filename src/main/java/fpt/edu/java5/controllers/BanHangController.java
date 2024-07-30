package fpt.edu.java5.controllers;

import fpt.edu.java5.entites.*;
import fpt.edu.java5.repositories.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Controller
@RequestMapping("/ban-hang")
@RequiredArgsConstructor
public class BanHangController {

    private final SanPhamChiTietRepo sanPhamChiTietRepo;
    private final KhachHangRepo khachHangRepo;
    private final NhanVienRepo nhanVienRepo;
    private final HoaDonChiTietRepo hoaDonChiTietRepo;
    private final HoaDonRepo hoaDonRepo;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session) {
        NhanVien currentUser = (NhanVien) session.getAttribute("currentUSER");
        if (currentUser == null) {
            return "login/index.jsp";
        }
        model.addAttribute("currentUser", currentUser);
//        model.addAttribute("listHDCT", hoaDonChiTietRepo.findAllByCustom());
        model.addAttribute("listHD", hoaDonRepo.findAllByCustomByTrangThai());
        model.addAttribute("listSPCT", sanPhamChiTietRepo.findAllByCustomAndSPCT());
        return "ban_hang_tai_quay/index.jsp";
    }

    @GetMapping("/tim-theo-sdt")
    public String timKhachHang(Model model, HttpSession session, @RequestParam("timKiem") String timKiem) {
        KhachHang khDetailByPhone = khachHangRepo.findBySdt(timKiem);
        session.setAttribute("khDetailByPhone", khDetailByPhone);
        model.addAttribute("khDetailByPhone", khDetailByPhone);
        NhanVien currentUser = (NhanVien) session.getAttribute("currentUSER");
        model.addAttribute("currentUser", currentUser);
//        model.addAttribute("listHDCT", hoaDonChiTietRepo.findAllByCustom());
        model.addAttribute("listHD", hoaDonRepo.findAllByCustomByTrangThai());
        model.addAttribute("listSPCT", sanPhamChiTietRepo.findAllByCustomAndSPCT());
        return "ban_hang_tai_quay/index.jsp";
    }


    @GetMapping("/tao-hoa-don")
    public String taoHoaDon(Model model, HttpSession session) {
        NhanVien currentUser = (NhanVien) session.getAttribute("currentUSER");
        KhachHang kh = (KhachHang) session.getAttribute("khDetailByPhone");
        if (currentUser == null) {
            return "login/index.jsp";
        }
        HoaDon hd = new HoaDon();
        hd.setIdKH(kh.getId());
        hd.setIdNV(currentUser.getId());
        hd.setNgayMuaHang(LocalDate.now());
        hd.setTrangThai(0);
        session.setAttribute("hdSession", hoaDonRepo.save(hd));
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("listHD", hoaDonRepo.findAllByCustomByTrangThai());
        model.addAttribute("listSPCT", sanPhamChiTietRepo.findAllByCustomAndSPCT());
        return "redirect:/ban-hang/hien-thi";
    }

    @GetMapping("/xoa-hoa-don/{id}")
    public String deleteHoaDon(@PathVariable int id) {
        hoaDonChiTietRepo.deleteByIdHoaDon(id);
        hoaDonRepo.deleteById(id);
        return "redirect:/ban-hang/hien-thi";
    }

    @GetMapping("/hoa-don-detail/{id}")
    public String hoaDonDetail(@PathVariable Integer id, Model model) {
        model.addAttribute("hoaDonDetail", hoaDonRepo.findById(id).get());
        model.addAttribute("listHD", hoaDonRepo.findAllByCustomByTrangThai());
        model.addAttribute("listHDCT", hoaDonChiTietRepo.findAllByCustomAndIdHoaDon(id));
        model.addAttribute("listSPCT", sanPhamChiTietRepo.findAllByCustomAndSPCT());
        return "ban_hang_tai_quay/index.jsp";
    }

    @GetMapping("/xoa-hoa-don-chi-tiet/{id}")
    public String deleteHoaDonChiTiet(@PathVariable int id, @RequestParam("idSP") int idSP, HttpSession session) {
        HoaDonChiTiet hdct = hoaDonChiTietRepo.findById(id).get();
        HoaDon hd = (HoaDon) session.getAttribute("hdSession");
        SanPhamChiTiet spct = sanPhamChiTietRepo.findById(idSP).get();
        int soLuongTang = hdct.getSoLuong() + spct.getSoLuong();
        spct.setSoLuong(soLuongTang);
        sanPhamChiTietRepo.save(spct);
        hoaDonChiTietRepo.delete(hdct);
        return "redirect:/ban-hang/hoa-don-detail" + "/" + hd.getId();
    }


    @GetMapping("/them-vao-hoa-don-chi-tiet/{id}")
    public String themSPCTvaoHDCT(@PathVariable int id, Model model, HttpSession session) {
        SanPhamChiTiet spct = sanPhamChiTietRepo.findById(id).get();
        HoaDon hd = (HoaDon) session.getAttribute("hdSession");

        int soLuongGiam = spct.getSoLuong() - 1;
        spct.setSoLuong(soLuongGiam);
        sanPhamChiTietRepo.save(spct);

        int count = 0;
        for (HoaDonChiTiet hdct : hoaDonChiTietRepo.findAllByIdHoaDon(hd.getId())) {
            if (hdct.getIdSPCT() == id) {
                count++;
            }
        }
        if (count == 0) {
            HoaDonChiTiet hdct = new HoaDonChiTiet();
            hdct.setIdSPCT(spct.getId());
            hdct.setIdHoaDon(hd.getId());
            hdct.setThoiGian(LocalDate.now());
            hdct.setSoLuong(1);
            hdct.setDonGia(spct.getDonGia());
            hdct.setTrangThai(1);
            hoaDonChiTietRepo.save(hdct);
        } else {
            HoaDonChiTiet hdct = hoaDonChiTietRepo.findByIdHoaDonAndIdSPCT(hd.getId(), id);
            int soLuongTang = hdct.getSoLuong() + 1;
            hdct.setThoiGian(LocalDate.now());
            hdct.setSoLuong(soLuongTang);
            hoaDonChiTietRepo.save(hdct);
        }

        return "redirect:/ban-hang/hoa-don-detail" + "/" + hd.getId();
    }

    @PostMapping("/thanh-toan-hoa-don")
    public String thanhToanHoaDon(@RequestParam int idHoaDon, HttpSession session) {
        HoaDon hd = hoaDonRepo.findById(idHoaDon).get();
        hd.setTrangThai(1);
        hoaDonRepo.save(hd);
        session.removeAttribute("khDetailByPhone");
        return "redirect:/ban-hang/hien-thi";
    }
}
