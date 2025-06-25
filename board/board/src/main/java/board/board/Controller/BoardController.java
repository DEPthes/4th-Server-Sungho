package board.board.Controller;

import board.board.dto.BoardDTO;
import board.board.dto.CommentDTO;
import board.board.service.BoardService;
import board.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor // 의존성 주입 중 생성자 주입을 해주는 어노테이션
@RequestMapping("/board") // /board
public class BoardController {
    private final BoardService boardService; // 생성자 주입을 하기 위한 필드 변수
    private final CommentService commentService;

    @GetMapping("/save") // 글 작성 페이지
    public String save() {
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) throws IOException { //@ModelAttribute를 통해 입력한 데이터가 BoardDTO 객체에 자동으로 바인딩
        boardService.save(boardDTO);
        return "index";
    }

    @GetMapping("/")
    public String findAll(Model model) { // 데이터를 가져오는 객체
        // DB에서 전체 게시글 데이터를 가져와 list.html에 보여줌.
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model,
                           @PageableDefault(page=1) Pageable pageable) {
        /*
        해당 게시글의 조회수 하나 올리고
        게시글 데이터를 가져와서 detail.html 출력
         */
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        // 목록 가져오기
        List< CommentDTO> commentDTOList = commentService.findAll(id);
        model.addAttribute("commentList", commentDTOList);

        model.addAttribute("board", boardDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "detail";
    }

    @GetMapping("/update/{id}") // 특성 게시글 삭제
    public String updateForm(@PathVariable Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board", board);
        return "detail";
    }

    @GetMapping("delete/{id}") // 글 삭제
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/board/";
    }

    // /board/paging?page=1
    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        pageable.getPageNumber();
        Page<BoardDTO> boardList = boardService.paging(pageable);
        int blockLimit = 3; // 보여지는 페이지 번호 갯수
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();

        // page 갯수 20개
        // 현재 사용자가 3페이지를 보는 중
        // 1 2 3
        // 현재 사용자 7페이지
        // 7 8 9
        // 보여지는 페이지 3개
        // 총 페이지 8개라면?
        // 7 8

        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "paging";
    }
}
