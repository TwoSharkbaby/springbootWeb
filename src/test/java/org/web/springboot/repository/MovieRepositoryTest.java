package org.web.springboot.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.web.springboot.entity.Movie;
import org.web.springboot.entity.MovieImage;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository movieImageRepository;

    @Commit
    @Transactional
    @Test
    @DisplayName("테스트 필요")
    public void insertMovies(){
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Movie movie = Movie.builder().title("Movie..." + i).build();
            System.out.println(" ========================== ");
            movieRepository.save(movie);

            int count = (int) (Math.random() * 5) + 1;

            for (int j = 0; j < count; j++){
                MovieImage movieImage = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .movie(movie)
                        .imgName("test" + j + ".jpg")
                        .build();
                movieImageRepository.save(movieImage);
            }
        });
    }

    @Test
    @DisplayName("테스트 필요")
    public void testListPage(){
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno"));

        Page<Object[]> result = movieRepository.getListPage(pageRequest);

        for (Object[] objects : result.getContent()) {
            System.out.println("Arrays.toString(objects) = " + Arrays.toString(objects));
        }
    }

    @Test
    @DisplayName("테스트 필요")
    public void testGetMovieWithAll(){
        List<Object[]> result = movieRepository.getMovieWithAll(94L);
        System.out.println("result = " + result);
        for (Object[] objects : result) {
            System.out.println("Arrays.toString(objects) = " + Arrays.toString(objects));
        }
    }

}