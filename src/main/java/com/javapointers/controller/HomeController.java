package com.javapointers.controller;

import com.javapointers.model.Genre;
import com.javapointers.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Controller
public class HomeController {
    @Autowired
    private GenreRepository genreRepository;

    @RequestMapping(value = "/start",method = RequestMethod.GET)
    public String select(Model model) throws IOException, MessagingException {
        List<Genre> genreList = genreRepository.findAll();
        model.addAttribute("genreList",genreList);
        return "start";
    }

    @RequestMapping(value = "/startE",method = RequestMethod.POST)
    public String mail(Model model,@RequestParam String mail,@RequestParam String subject,@RequestParam String content) throws IOException, MessagingException {
        List<Genre> genreList = genreRepository.findAll();
        model.addAttribute("genreList",genreList);
        sendmail(mail,subject,content);
        return "start";
    }
    @RequestMapping (value = "/delete",method = RequestMethod.POST)
    public String delete(@RequestParam int hiden_Ganre){
        genreRepository.deleteById(hiden_Ganre);
        return "redirect:/start";
        }
        @RequestMapping(value ="/update",method=RequestMethod.POST)
    public String update(@RequestParam int hiden_Ganre,@RequestParam String title){
        Genre genre = genreRepository.getOne(hiden_Ganre);
        genre.setTitle(title);
        genreRepository.save(genre);
        return "redirect:/start";
        }
        @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public String save(@RequestParam String title){
        genreRepository.save(new Genre(title));
        return "redirect:/start";
        }
        @GetMapping("/findOne")
        @ResponseBody
    public Genre findOne(Integer id) {
            return genreRepository.getOne(id);
        }

    private void sendmail(String mail,String subject,String content) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("....enter_you_mail", "....enter_you_pass");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("..enter_email", false));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
        msg.setSubject(subject);
        msg.setContent(content, "text/html;charset=utf-8");
        msg.setSentDate(new Date());

        Transport.send(msg);
    }
    }

