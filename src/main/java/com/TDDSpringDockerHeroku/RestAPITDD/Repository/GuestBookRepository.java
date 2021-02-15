package com.TDDSpringDockerHeroku.RestAPITDD.Repository;

import com.TDDSpringDockerHeroku.RestAPITDD.Model.GuestBook;
import com.TDDSpringDockerHeroku.RestAPITDD.Service.GuestbookService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestBookRepository extends JpaRepository<GuestBook , Long> {
}
