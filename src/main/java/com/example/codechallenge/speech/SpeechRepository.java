package com.example.codechallenge.speech;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SpeechRepository extends JpaRepository<Speech, Long>  {


    @Query("SELECT s FROM Speech s WHERE s.deleteFlag = 0")
    List<Speech> retrieveAllActiveSpeeches();
    //Optional<Speech> findSpeechBySpeechAuthor(String SpeechAuthor);

    @Query("SELECT s FROM Speech s JOIN s.tags t WHERE t = LOWER(:tag) AND s.deleteFlag= 0")
    List<Speech> retrieveByTag(@Param("tag") String tag);
    //Optional<Speech> findSpeechBySpeechAuthor(String SpeechAuthor);

    @Query("Select s FROM Speech s WHERE LOWER(s.speechAuthor) = LOWER(:author) AND s.deleteFlag= 0")
    List<Speech> retrieveByAuthor(@Param("author")String author);


    @Query("SELECT s FROM Speech s WHERE s.speechDate BETWEEN ?1 AND ?2 AND s.deleteFlag= 0")
    List<Speech> retrieveSpeechBetweenDates(@Param("before")LocalDate before,
                                            @Param("after")LocalDate after);

    /*
    @Query( nativeQuery=true, value="SELECT s FROM Speech s WHERE to_tsvector(s.speechBody) @@ to_tsquery(:snip)")
    List<Speech> retrieveSpeechSnip(@Param("snip") String snippet);
*/

    @Query( "SELECT s FROM Speech s WHERE LOWER(s.speechBody) LIKE LOWER(?1) AND s.deleteFlag= 0")
    List<Speech> retrieveSpeechSnip(@Param("snip") String snippet);

}
