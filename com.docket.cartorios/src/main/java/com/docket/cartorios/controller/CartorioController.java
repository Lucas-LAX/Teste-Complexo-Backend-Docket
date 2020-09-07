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
import com.docket.cartorios.repository.CartorioRepository;

@Controller
@RequestMapping("/cartorio")
public class CartorioController {
	
	
	@Autowired
	private CartorioRepository repository;
	

	@GetMapping("/list")
	private String getAll(Model model) {
		model.addAttribute("cartorios", repository.findAll());
		return "cartorio_list";
	}
	
	@GetMapping(path = {"/add", "edit/{id}"})
    private String addForm(@PathVariable("id") Optional<Long> id, Model model){
        if(id.isPresent()){
            model.addAttribute("cartorio", repository.findById(id.get()));
        }else{
            model.addAttribute("cartorio", new Cartorio());
        }
        return "add_edit_cartorio";
    }
	
	@PostMapping("/addEdit")
    private String insertOrUpdate(Cartorio cartorio){
        if(cartorio.getId() == null){
            repository.save(cartorio);
        }else{
            Optional<Cartorio> cartorioOptional = repository.findById(cartorio.getId());
            if(cartorioOptional.isPresent()){
                Cartorio editCartorio = cartorioOptional.get();
                editCartorio.setNome(cartorio.getNome());
                editCartorio.setEndereco(cartorio.getEndereco());
                repository.save(editCartorio);
            }
        }
        return "redirect:/cartorio/list";
    }
	
	@GetMapping("/delete/{id}")
    private String deleteCartorio(@PathVariable("id") Long id){
        Optional<Cartorio> cartorio = repository.findById(id);
        if(cartorio.isPresent()){
            repository.delete(cartorio.get());
        }else{
            System.err.println("not found");
        }
        return "redirect:/cartorio/list";
    }
	
	
	
	
	
}
