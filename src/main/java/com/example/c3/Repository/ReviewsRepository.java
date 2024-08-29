package com.example.c3.Repository;

import com.example.c3.Model.Orders;
import com.example.c3.Model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Integer> {
    Reviews findReviewsById(Integer revId);
    List<Reviews> findAllByOrders(Orders orders);

    @Query("SELECT r FROM Reviews r WHERE r.rating = :rating")
    List<Reviews> findAllByRating(@Param("rating") Integer rating);

}
