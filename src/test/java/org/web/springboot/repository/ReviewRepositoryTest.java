package org.web.springboot.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web.springboot.entity.Member;
import org.web.springboot.entity.Movie;
import org.web.springboot.entity.Review;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMovieReviews(){
        IntStream.rangeClosed(1, 200).forEach(i -> {
            Long mno = (long)(Math.random()*100)+1;
            Long mid = ((long)(Math.random()*100) +1);
            Member member = Member.builder().mid(mid).build();

            Review movieReview = Review.builder()
                    .member(member)
                    .movie(Movie.builder().mno(mno).build())
                    .grade((int) (Math.random()*5) +1)
                    .text("이 영화에 대한 느낌..." + i)
                    .build();
            reviewRepository.save(movieReview);
        });
    }

    @Test
    public void insertGetMovieReview(){
        Movie movie = Movie.builder().mno(92L).build();
        List<Review> result = reviewRepository.findByMovie(movie);
        result.forEach(review -> {
            System.out.println(review.getReviewnum());
            System.out.println(review.getGrade());
            System.out.println(review.getText());
            System.out.println(review.getMember().getEmail());
        });
    }

}