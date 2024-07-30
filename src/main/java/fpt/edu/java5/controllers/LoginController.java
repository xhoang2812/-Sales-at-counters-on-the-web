package fpt.edu.java5.controllers;

import fpt.edu.java5.entites.KichThuoc;
import fpt.edu.java5.entites.NhanVien;
import fpt.edu.java5.repositories.KichThuocRepo;
import fpt.edu.java5.repositories.NhanVienRepo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    final NhanVienRepo nhanVienRepo;
    final KichThuocRepo kichThuocRepo;


    @GetMapping("/hien-thi")
    public String hienThi() {
        return "login/index.jsp";
    }

    @PostMapping("/dang-nhap")
    public String dangNhap(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpSession session, Model model) {
        NhanVien nhanVien = nhanVienRepo.findAllByTenDangNhap(userName);
        if (nhanVien != null){
            if(nhanVien.getMatKhau().equals(password)){
                List<KichThuoc> listKT = kichThuocRepo.findAll();
                model.addAttribute("listKT", listKT);
                session.setAttribute("currentUSER", nhanVien);
                model.addAttribute("currentUser", nhanVien);
                return "kich_thuoc/index.jsp";
            }
        }
        return "login/index.jsp";
    }



}
