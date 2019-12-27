package com.homethings.repository;

import com.homethings.models.Account;
import com.homethings.models.Note;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends PagingAndSortingRepository<Note, Long> {

    Note findById(long id);

    Note findByAuthor(Account account);

    Note findByTitle(String title);

    Note findByTitleIsLike(String title);

    List<Note> getAllByHomeId(long homeId);

}
