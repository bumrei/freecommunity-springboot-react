package org.commu.freecommunity.borad.controller;

import org.commu.freecommunity.borad.dto.BoardDto;
import org.commu.freecommunity.borad.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;

    /* 게시글 목록 */
    @GetMapping("/")
    public String list(Model model) {
        List<BoardDto> boardList = boardService.getBoardlist();

        model.addAttribute("boardList", boardList);
        return "board/list.html";
    }

    @GetMapping("/post")
    public String write() {
        return "board/write.html";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        boardService.savePost(boardDto);

        return "redirect:/";
    }

    @RequestMapping(value = "/post/edit/{no}" , method = RequestMethod.GET)
    public String edit(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "board/update.html";
    }

    @RequestMapping(value = "/post/edit/{no}" , method = {RequestMethod.POST, RequestMethod.PUT})
    public String update(BoardDto boardDTO) {
        boardService.savePost(boardDTO);

        return "redirect:/";
    }

    @RequestMapping(value = "/post/{no}" , method = RequestMethod.GET)
    public String detail(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "board/detail.html";
    }

    @RequestMapping(value = "/post/{no}" , method = {RequestMethod.POST, RequestMethod.DELETE})
    public String delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return "redirect:/";
    }

    // 로그인 페이지
    @RequestMapping(value = "/login")
    public String loginpage(){

        return "login/login.html";
    }

    // 회원가입 페이지
    @RequestMapping(value = "/join")
    public String join(){

        return "login/join.html";
    }
}
