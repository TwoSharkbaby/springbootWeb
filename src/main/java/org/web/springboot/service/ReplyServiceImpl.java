package org.web.springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web.springboot.dto.ReplyDTO;
import org.web.springboot.entity.Board;
import org.web.springboot.entity.Reply;
import org.web.springboot.repository.ReplyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;

    @Transactional
    @Override
    public Long register(ReplyDTO replyDTO) {
        Reply reply = dtoToEntity(replyDTO);
        replyRepository.save(reply);
        return reply.getRno();
    }

    @Override
    public List<ReplyDTO> getList(Long bno) {
        List<Reply> result = replyRepository.getRepliesByBoardOrderByRno(Board.builder().bno(bno).build());
        return result.stream().map(reply -> entityToDto(reply)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void modify(ReplyDTO replyDTO) {
        Reply reply = dtoToEntity(replyDTO);
        replyRepository.save(reply);
    }

    @Transactional
    @Override
    public void remove(Long rno) {
        replyRepository.deleteById(rno);
    }
}
