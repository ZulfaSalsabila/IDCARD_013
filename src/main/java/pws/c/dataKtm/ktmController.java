/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pws.c.dataKtm;

import java.io.IOException;              
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ROG
 */
@Controller
public class ktmController {
    @RequestMapping("/getData")
    public String getData(@RequestParam("name") String text,
                          @RequestParam("image") MultipartFile file,
                          @RequestParam("tanggal")@DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                          Model model) throws IOException{
        
        SimpleDateFormat tanggal = new SimpleDateFormat("EEEE,dd-MMMM-yyyy");
        String newTanggal = tanggal.format(date);
        
        String blob = Base64.encodeBase64String(file.getBytes());
        String gambar = "data:image/jpeg;base64,".concat(blob);
        
        model.addAttribute("nm", text);
        model.addAttribute("tgl", newTanggal);
        model.addAttribute("img", gambar);
        
        return "ktm";
    }
}
