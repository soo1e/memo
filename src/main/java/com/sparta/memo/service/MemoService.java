package com.sparta.memo.service;

import com.sparta.memo.dto.MemoRequestDTO;
import com.sparta.memo.dto.MemoResponseDTO;
import com.sparta.memo.entity.Memo;
import com.sparta.memo.repository.MemoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        // 최신 수정 시간 순으로 DB 조회
        List<Memo> memos = memoRepository.findAllByOrderByModifiedAtDesc();
        List<MemoResponseDTO> memoResponseDTOs = new ArrayList<>();

        for (Memo memo : memos) {
            memoResponseDTOs.add(new MemoResponseDTO(memo));
        }

        return memoResponseDTOs;
    }


    @Transactional
    public Long updateMemo(Long id, MemoRequestDTO requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = findMemo(id);

        // memo 내용 수정
        memo.update(requestDto);

        return id;
    }

    public Long deleteMemo(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = findMemo(id);

        // memo 삭제
        memoRepository.delete(memo);

        return id;
    }

    private Memo findMemo(Long id) {
        return memoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }
}
