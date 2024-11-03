package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDTO;
import com.sparta.memo.dto.MemoResponseDTO;
import com.sparta.memo.service.MemoService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemoController {

//    private final JdbcTemplate jdbcTemplate;
    private final MemoService memoService;

    public MemoController(JdbcTemplate jdbcTemplate) {
        this.memoService = new MemoService(jdbcTemplate);
    }

    @PostMapping("/memos")
    public MemoResponseDTO createMemo(@RequestBody MemoRequestDTO requestDTO) {
        return memoService.createMemo(requestDTO);
    }

    @GetMapping("/memos")
    public List<MemoResponseDTO> getMemos() {
        return memoService.getMemos();
    }

    @PutMapping("/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDTO requestDTO) {
        return memoService.updateMemo(id, requestDTO);
    }

    @DeleteMapping("/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        return memoService.deleteMemo(id);
    }
}