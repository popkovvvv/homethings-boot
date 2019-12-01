package com.homethings.homethingsboot.repository;

import com.homethings.homethingsboot.models.Note;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.homethings.homethingsboot.models.User;


import java.util.List;

@Repository
public interface NoteRepository extends PagingAndSortingRepository<Note, Long> {

    Note findById(long id);

    Note findByAuthor(User user);

    Note findByTitle(String title);

    Note findByTitleIsLike(String title);

    List<Note> getAllByHomeId(long homeId);

}
