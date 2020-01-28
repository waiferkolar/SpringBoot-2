package coder.TM65.daos;

import coder.TM65.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookDao extends JpaRepository<Book, Long> {

}