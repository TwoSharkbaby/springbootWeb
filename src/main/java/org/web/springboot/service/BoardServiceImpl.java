package org.web.springboot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web.springboot.dto.BoardDTO;
import org.web.springboot.dto.PageRequestDTO;
import org.web.springboot.dto.PageResultDTO;
import org.web.springboot.entity.Board;
import org.web.springboot.entity.Member;
import org.web.springboot.repository.BoardRepository;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService{

    private final BoardRepository repository;
    @Override
    public Long register(BoardDTO dto) {
        Board board = dtoToEntity(dto);
        repository.save(board);
        return board.getBno();
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        Function<Object[], BoardDTO> fn = en ->
                entityToDTO((Board) en[0], (Member) en[1], (Long) en[2]);

        Page<Object[]> result =
                repository.getBoardWithReplyCount(pageRequestDTO
                        .getPageable(Sort.by("bno").descending()));

        return new PageResultDTO<>(result, fn);
    }
}
