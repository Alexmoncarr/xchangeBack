package xchange.x_change.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import xchange.x_change.domain.Review;
import xchange.x_change.domain.User;
import xchange.x_change.model.ReviewDTO;
import xchange.x_change.repos.ReviewRepository;
import xchange.x_change.repos.UserRepository;
import xchange.x_change.util.NotFoundException;


@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public ReviewService(final ReviewRepository reviewRepository,
            final UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    public List<ReviewDTO> findAll() {
        final List<Review> reviews = reviewRepository.findAll(Sort.by("id"));
        return reviews.stream()
                .map(review -> mapToDTO(review, new ReviewDTO()))
                .toList();
    }

    public ReviewDTO get(final Integer id) {
        return reviewRepository.findById(id)
                .map(review -> mapToDTO(review, new ReviewDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final ReviewDTO reviewDTO) {
        final Review review = new Review();
        mapToEntity(reviewDTO, review);
        return reviewRepository.save(review).getId();
    }

    public void update(final Integer id, final ReviewDTO reviewDTO) {
        final Review review = reviewRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(reviewDTO, review);
        reviewRepository.save(review);
    }

    public void delete(final Integer id) {
        reviewRepository.deleteById(id);
    }

    private ReviewDTO mapToDTO(final Review review, final ReviewDTO reviewDTO) {
        reviewDTO.setId(review.getId());
        reviewDTO.setContent(review.getContent());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setReviewer(review.getReviewer() == null ? null : review.getReviewer().getId());
        reviewDTO.setReviewee(review.getReviewee() == null ? null : review.getReviewee().getId());
        return reviewDTO;
    }

    private Review mapToEntity(final ReviewDTO reviewDTO, final Review review) {
        review.setContent(reviewDTO.getContent());
        review.setRating(reviewDTO.getRating());
        final User reviewer = reviewDTO.getReviewer() == null ? null : userRepository.findById(reviewDTO.getReviewer())
                .orElseThrow(() -> new NotFoundException("reviewer not found"));
        review.setReviewer(reviewer);
        final User reviewee = reviewDTO.getReviewee() == null ? null : userRepository.findById(reviewDTO.getReviewee())
                .orElseThrow(() -> new NotFoundException("reviewee not found"));
        review.setReviewee(reviewee);
        return review;
    }

}