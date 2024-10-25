package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDTO;
import com.sparta.memo.dto.MemoResponseDTO;
import com.sparta.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class MemoController {

    private final Map<Long, Memo> memoList = new HashMap<>();

    @PostMapping("/memos")
    public MemoResponseDTO createMemo(@RequestBody MemoRequestDTO requestDTO) {
        // RequestDTO -> Entity
        Memo memo = new Memo(requestDTO);

        // Memo Max ID Check
        Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet()) + 1 : 1;
        memo.setId(maxId);

        // DB 저장
        memoList.put(memo.getId(), memo);

        // Entity -> ResponseDTO
        MemoResponseDTO memoResponseDTO = new MemoResponseDTO(memo);

        return memoResponseDTO;
    }

    @GetMapping("/memos")
    public List<MemoResponseDTO> getMemos() {
        // Map to List
        List<MemoResponseDTO> responseList = memoList.values().stream()
                .map(MemoResponseDTO::new).toList();

        return responseList;
    }
}
