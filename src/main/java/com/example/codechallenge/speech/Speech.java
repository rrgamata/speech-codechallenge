package com.example.codechallenge.speech;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Speech Class for codeChallenge
 *
 */
@Table
@Entity(name = "Speech")
public class Speech {

    /**
     * Sets id as PK
     */
    @Id
    @SequenceGenerator(
            name="speech_sequence",
            sequenceName = "speech_sequence",
            allocationSize = 1
    )
    @GeneratedValue(

    )
    private Long id;
    /**
     * used simple string for author.
     * For further enhancement should be able to add another table for authors and have n:n
     * relationships
     */
    private String speechAuthor;
    /**
     * Sets speechBody to TEXT for larger capacity
     */
    @Column(columnDefinition = "TEXT")
    private String speechBody;
    /**
     * Uses 1:n or one to many implementation.
     * n:n implementation is beyond my capabilities as of now due to time constraint.
     * Still have not though of idea for seamless input of new tag for n:n.
     */
    @ElementCollection
    private List<String> tags = new ArrayList<>();
   // private String speechKeywords;
    private LocalDate speechDate;
    @Column(columnDefinition = "SMALLINT default 0")
    private Short deleteFlag;
    private LocalDate deleteDate;



    public Speech() {
    }


    public Speech(String speechAuthor, String speechBody, List<String> tags,  LocalDate speechDate,Short  deleteFlag, LocalDate deleteDate) {
        this.speechAuthor=speechAuthor;
        this.speechBody = speechBody;
        this.tags = tags;
        this.speechDate = speechDate;
        this.deleteFlag = deleteFlag;
        this.deleteDate = deleteDate;
    }

    public Speech(String speechAuthor, String speechBody, List<String> tags,  LocalDate speechDate) {
        this.speechAuthor=speechAuthor;
        this.speechBody = speechBody;
        this.tags = tags;
        this.speechDate = speechDate;
        this.deleteFlag = Short.valueOf("0");
    }


    public Speech(Long id, String speechAuthor, String speechBody, List<String> tags, LocalDate speechDate, Short  deleteFlag, LocalDate deleteDate) {
        this.speechAuthor=speechAuthor;
        this.speechBody = speechBody;
        this.tags = tags;
        this.speechDate = speechDate;
        this.deleteFlag = deleteFlag;
        this.deleteDate = deleteDate;
    }

    public Speech(Long id, String speechAuthor, String speechBody, List<String> tags, LocalDate speechDate) {
        this.speechAuthor=speechAuthor;
        this.speechBody = speechBody;
        this.tags = tags;
        this.speechDate = speechDate;
        this.deleteFlag = Short.valueOf("0");

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpeechBody() {
        return speechBody;
    }

    public void setSpeechBody(String speechBody) {
        this.speechBody = speechBody;
    }


    public LocalDate getSpeechDate() {
        return speechDate;
    }

    public void setSpeechDate(LocalDate speechDate) {
        this.speechDate = speechDate;
    }

    public String getSpeechAuthor() {
        return speechAuthor;
    }

    public void setSpeechAuthor(String speechAuthor) {
        this.speechAuthor = speechAuthor;
    }



    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Short getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Short deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public LocalDate getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(LocalDate deleteDate) {
        this.deleteDate = deleteDate;
    }

    @Override
    public String toString() {
        return "Speech{" +
                "id=" + id +
                ", speechAuthor='" + speechAuthor + '\'' +
                ", speechBody='" + speechBody + '\'' +
                ", tags=" + tags +
                ", speechDate=" + speechDate +
                ", deleteFlag=" + deleteFlag +
                ", deleteDate=" + deleteDate +
                '}';
    }
}
