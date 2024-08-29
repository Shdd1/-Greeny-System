package com.example.c3.Service;

import com.example.c3.Api.ApiException;
import com.example.c3.Model.Consultant;
import com.example.c3.Model.Orders;
import com.example.c3.Model.Reviews;
import com.example.c3.Repository.ConsultantRepository;
import com.example.c3.Repository.OrdersRepository;
import com.example.c3.Repository.ReviewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewsService {

    private final ReviewsRepository reviewsRepository;
    private final ConsultantRepository consultantRepository;
    private final OrdersRepository ordersRepository;


    public List<Reviews> getAllReviews(){
        return reviewsRepository.findAll();
    }
    public void addReviews(Reviews reviews){
        reviewsRepository.save(reviews);
    }
    public void updateReviews(Integer id,Reviews reviews){
        Reviews reviews1=reviewsRepository.findReviewsById(id);
        if (reviews1==null) {
            throw new ApiException("not found");
        }
        reviews1.setRating(reviews.getRating());
        reviews1.setComment(reviews.getComment());
        reviewsRepository.save(reviews1);

    }

    public void deleteReviews(Integer id){
        Reviews reviews=reviewsRepository.findReviewsById(id);
        if(reviews==null){
            throw new ApiException("user not found");
        }
        reviewsRepository.delete(reviews);
    }

    public void assignReviewsToConsultant(Integer review_id, Integer consultant_id){
        Reviews reviews = reviewsRepository.findReviewsById(review_id);
        Consultant consultant = consultantRepository.findConsultantById(consultant_id);

        if ( reviews==null || consultant==null) {
            throw new ApiException("Cannot assign reviews to consultant");
        }
//        reviews.setConsultant(consultant);
        reviewsRepository.save(reviews);
    }

    //assign Order ToReview
    public void assignOrderToReview(Integer orderId,Integer revId) {
        Orders orders=ordersRepository.findOrdersById(orderId);
        Reviews reviews=reviewsRepository.findReviewsById(revId);
        if(orders==null){
            throw new ApiException("orders not found");
        }
        if(reviews==null){
            throw new ApiException("reviews not found");
        }
        reviews.setOrders(orders);
        reviewsRepository.save(reviews);
    }



    public List<Reviews> getAllReviewsForOrder(Integer orderId) {
        Orders order = ordersRepository.findOrdersById(orderId);
        if (order == null) {
            throw new ApiException("Order not found");
        }
        return reviewsRepository.findAllByOrders(order);
    }



    public List<Reviews> getAllReviewsByRating(Integer rating) {
        return reviewsRepository.findAllByRating(rating);
    }
}
