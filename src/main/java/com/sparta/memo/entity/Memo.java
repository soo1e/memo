package com.sparta.memo.entity;

import com.sparta.memo.dto.MemoRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Memo {
    private Long id;
    private String username;
    private String contents;

    public Memo(MemoRequestDTO requestDTO) {
        this.username = requestDTO.getUsername();
        this.contents = requestDTO.getContents();
    }
}