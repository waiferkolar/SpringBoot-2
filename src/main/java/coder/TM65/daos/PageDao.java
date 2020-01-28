package coder.TM65.daos;

import coder.TM65.models.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface PageDao extends JpaRepository<Page, Long> {
}