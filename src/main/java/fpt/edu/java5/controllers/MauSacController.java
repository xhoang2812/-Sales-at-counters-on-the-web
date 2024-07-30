package fpt.edu.java5.controllers;


import fpt.edu.java5.entites.MauSac;
import fpt.edu.java5.entites.NhanVien;
import fpt.edu.java5.repositories.MauSacRepo;
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
@RequestMapping("/mau-sac")
public class MauSacController {

    @Autowired private MauSacRepo mauSacRepo ;


//    @GetMapping("/hien-thi")
//    public String hienThi(Model model, HttpSession session) {
//
//        NhanVien currentUser = (NhanVien) session.getAttribute("currentUSER");
//        if(currentUser == null) {
//            return "login/index.jsp";
//        }
//        model.addAttribute("currentUser", currentUser);
//        List<MauSac> listMS  = mauSacRepo.findAll();
//        model.addAttribute("listMS", listMS);
//        return "mau_sac/index.jsp";
//    }

    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session, @RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "5") Integer limit) {
        NhanVien currentUser = (NhanVien) session.getAttribute("currentUSER");
        if(currentUser == null) {
            return "login/index.jsp";
        }
        model.addAttribute("currentUser", currentUser);

        Pageable p = PageRequest.of(page-1, limit);
        Page<MauSac> listMSPage = mauSacRepo.findAll(p);
        model.addAttribute("listMS", listMSPage.getContent());
        model.addAttribute("totalPages", listMSPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "mau_sac/index.jsp";
    }

    @GetMapping("/tim-theo-ten")
    public String findByName(@RequestParam("timKiem") String timKiem, Model model) {
        List<MauSac> listMS;
        if (timKiem == null || timKiem.equals("")) {
            listMS = mauSacRepo.findAll();
        }else {
            listMS = mauSacRepo.findAllByTenContains(timKiem);
        }
        model.addAttribute("listMS", listMS);
        return "mau_sac/index.jsp";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("data") MauSac mauSac) {
        return "mau_sac/create.jsp";
    }

    @PostMapping("/store")
    public String store( Model model, @Valid MauSac mauSac, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<String, String>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            model.addAttribute("errors", errors);
            model.addAttribute("data", mauSac);
            return "mau_sac/create.jsp";
        }
        mauSacRepo.save(mauSac);
        return "redirect:/mau-sac/hien-thi";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        mauSacRepo.deleteById(id);
        return "redirect:/mau-sac/hien-thi";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        MauSac msDetail = mauSacRepo.findById(id).get();
        model.addAttribute("msDetail",msDetail);
        return "/mau_sac/update.jsp";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid MauSac mauSac, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<String, String>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            model.addAttribute("errors", errors);
            model.addAttribute("msDetail", mauSac);
            return "mau_sac/update.jsp";
        }
        mauSacRepo.save(mauSac);
        return "redirect:/mau-sac/hien-thi";
    }

}
