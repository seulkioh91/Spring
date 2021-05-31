package com.austin.home.controller;


import com.austin.home.model.Board;
import com.austin.home.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/list")
    public String list(Model model) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards",boards);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model) {
        return "board/form";
    }

//    @PostMapping("/form")
//    public String postForm(@Valid Board board, BindingResult bindingResult, Authentication authentication) {
//        boardValidator.validate(board, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return "board/form";
//        }
//        String username = authentication.getName();
//        boardService.save(username, board);
////        boardRepository.save(board);
//        return "redirect:/board/list";
//    }


}
