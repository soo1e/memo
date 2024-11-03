package com.sparta.memo.service;

import com.sparta.memo.dto.MemoRequestDTO;
import com.sparta.memo.dto.MemoResponseDTO;
import com.sparta.memo.entity.Memo;
import com.sparta.memo.repository.MemoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoService {

//    private final JdbcTemplate jdbcTemplate;
    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public MemoResponseDTO createMemo(MemoRequestDTO requestDTO) {
        // RequestDto -> Entity
        Memo memo = new Memo(requestDTO);

        // DB 저장
        Memo saveMemo = memoRepository.save(memo);

        // Entity -> ResponseDto
        MemoResponseDTO memoResponseDTO = new MemoResponseDTO(saveMemo);

        return memoResponseDTO;
    }

    public List<MemoResponseDTO> getMemos() {
        // DB 조회
        return memoRepository.findAll();
    }

    public Long updateMemo(Long id, MemoRequestDTO requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = memoRepository.findById(id);
        if (memo != null) {
            // memo 내용 수정
            memoRepository.update(id, requestDto);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    public Long deleteMemo(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = memoRepository.findById(id);
        if (memo != null) {
            // memo 삭제
            memoRepository.delete(id);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }
}
