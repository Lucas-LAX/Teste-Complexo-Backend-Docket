package com.docket.cartorios.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docket.cartorios.model.Cartorio;
import com.docket.cartorios.model.Certidao;
import com.docket.cartorios.repository.CartorioRepository;
import com.docket.cartorios.repository.CertidaoRepository;

@Controller
@RequestMapping("/certidao")
public class CertidaoController {
	
	@Autowired
	private CertidaoRepository repository;
	
	@Autowired
	private CartorioRepository repository2;
	
	
	@GetMapping(path = {"list/{id}"})
    private String addForm(@PathVariable("id") Optional<Long> id, Model model,Model model2){
            model.addAttribute("certidaos", repository.findByCartorio(id.get()));
            model2.addAttribute("cartorios", repository2.findByExtraId(id.get()));  
        return "certidao_list";
    }
	
	@GetMapping(path = {"/add/{id2}"})
    private String addForm(@PathVariable("id2") Long id2, Model model){
        
        	Cartorio car = repository2.findByExtraIdCar(id2);
        	System.out.println(car);
            model.addAttribute("certidao", new Certidao(null,null,car));
        
        return "add_edit_certidao";
    }
	
	@GetMapping(path = {"edit/{id}"})
    private String addForm(@PathVariable("id") Optional<Long> id, Model model){
        if(id.isPresent()){
            model.addAttribute("certidao", repository.findById(id.get()));
            
        }else{
        	
        }
        return "add_edit_certidao";
    }
	
	@PostMapping("/addEdit")
    private String insertOrUpdate(Certidao certidao){
        if(certidao.getId() == null){
            repository.save(certidao);
        }else{
            Optional<Certidao> certidaoOptional = repository.findById(certidao.getId());
            if(certidaoOptional.isPresent()){
                Certidao editCertidao = certidaoOptional.get();
                editCertidao.setNome(certidao.getNome());
                repository.save(editCertidao);
            }
        }
        return "redirect:/cartorio/list";
    }
	
	@GetMapping("/delete/{id}")
    private String deleteCertidao(@PathVariable("id") Long id){
        Optional<Certidao> certidao = repository.findById(id);
        if(certidao.isPresent()){
            repository.delete(certidao.get());
        }else{
            System.err.println("not found");
        }
        return "redirect:/cartorio/list";
    }

}
