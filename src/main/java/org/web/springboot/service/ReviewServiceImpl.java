package org.web.springboot.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.web.springboot.dto.ReviewDTO;
import org.web.springboot.entity.Movie;
import org.web.springboot.entity.Review;
import org.web.springboot.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;


    @Override
    public List<ReviewDTO> getListOfMovie(Long mno){

        Movie movie = Movie.builder().mno(mno).build();

        List<Review> result = reviewRepository.findByMovie(movie);

        return result.stream().map(movieReview -> entityToDto(movieReview)).collect(Collectors.toList());
    }

    @Override
    public Long register(ReviewDTO movieReviewDTO) {

        Review movieReview = dtoToEntity(movieReviewDTO);

        reviewRepository.save(movieReview);

        return movieReview.getReviewnum();
    }

    @Override
    public void modify(ReviewDTO movieReviewDTO) {

        Optional<Review> result =
                reviewRepository.findById(movieReviewDTO.getReviewnum());

        if(result.isPresent()){

            Review movieReview = result.get();
//            movieReview.changeGrade(movieReviewDTO.getGrade());
//            movieReview.changeText(movieReviewDTO.getText());

            reviewRepository.save(movieReview);
        }

    }

    @Override
    public void remove(Long reviewnum) {

        reviewRepository.deleteById(reviewnum);

    }
}

