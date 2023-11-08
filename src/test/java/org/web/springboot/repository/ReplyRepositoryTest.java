package org.web.springboot.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web.springboot.entity.Board;
import org.web.springboot.entity.Reply;

import javax.persistence.criteria.CriteriaBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertReply(){
        IntStream.rangeClosed(1, 100).forEach(i -> {
            long bno = (long) (Math.random() * 100) + 1;

            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .text("Reply...." + i)
                    .board(board)
                    .replyer("guest")
                    .build();

            replyRepository.save(reply);
        });
    }
    
    @Test
    public void readReply1(){
        Optional<Reply> result = replyRepository.findById(1L);
        Reply reply = result.get();
        System.out.println("reply = " + reply);
        System.out.println("reply.getBoard() = " + reply.getBoard());
    }

    // 테스트 필요
    @Test
    public void testListByBoard(){
        List<Reply> replyList = replyRepository.getRepliesByBoardOrderByRno(Board.builder().bno(97L).build());
        replyList.forEach(System.out::println);
    }
}