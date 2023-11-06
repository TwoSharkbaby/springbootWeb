package org.web.springboot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web.springboot.dto.GuestbookDTO;
import org.web.springboot.dto.PageRequestDTO;
import org.web.springboot.dto.PageResultDTO;
import org.web.springboot.entity.Guestbook;
import org.web.springboot.repository.GuestbookRepository;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GuestbookServiceImplTest {

    @Autowired
    private GuestbookService service;

    @Autowired
    private GuestbookRepository guestbookRepository;

//    @BeforeEach
//    public void setup(){
//        IntStream.rangeClosed(1, 10).forEach(i -> {
//            Guestbook guestbook = Guestbook.builder()
//                    .title("Title..." + i)
//                    .content("Content..." + i)
//                    .writer("user" + (i % 10))
//                    .build();
//            System.out.println(guestbookRepository.save(guestbook));
//        });
//    }

    @Test
    public void testRegister(){
        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("Sample")
                .content("Sample")
                .writer("user")
                .build();
        System.out.println(service.register(guestbookDTO));
    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);
        for (GuestbookDTO guestbookDTO : resultDTO.getDtoList()){
            System.out.println(guestbookDTO);
        }
        resultDTO.getPageList().forEach(System.out::println);
    }

    @Test
    public void testSearch(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("tcw")
                .keyword("3")
                .build();
        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

        for (GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
            System.out.println("guestbookDTO = " + guestbookDTO);
        }
        resultDTO.getDtoList().forEach(System.out::println);
    }
}