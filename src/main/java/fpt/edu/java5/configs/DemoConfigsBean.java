package fpt.edu.java5.configs;

import fpt.edu.java5.entites.SanPham;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration // can @Configuration va @Bean
public class DemoConfigsBean {
    //Toa configs de xu ly nhung logic phuc tap hon
    @Bean(name = "sp1") // @qualifier("sp1") de goi
    @Primary // neu co 2 bean cung kieu du lieu, thi phai gan priamry
    public SanPham sanPham1(){
        SanPham sanPham = new SanPham();
        return sanPham;
    }


    public SanPham sanPham2(){
        SanPham sanPham = new SanPham();
        return sanPham;
    }
}
