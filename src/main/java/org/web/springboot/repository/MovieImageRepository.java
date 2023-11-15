package org.web.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.springboot.entity.MovieImage;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {
}
