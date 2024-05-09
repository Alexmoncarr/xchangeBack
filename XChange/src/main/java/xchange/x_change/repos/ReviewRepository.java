package xchange.x_change.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import xchange.x_change.domain.Review;
import xchange.x_change.domain.User;


public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Review findFirstByReviewer(User user);

    Review findFirstByReviewee(User user);
}
