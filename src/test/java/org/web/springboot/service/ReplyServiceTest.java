package org.web.springboot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web.springboot.dto.ReplyDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReplyServiceTest {

    @Autowired
    private ReplyService service;

    // 테스트 필요
    @Test
    public void testGetList(){
        Long bno = 100L;
        List<ReplyDTO> replyDTOList = service.getList(bno);
        replyDTOList.forEach(System.out::println);
    }

}