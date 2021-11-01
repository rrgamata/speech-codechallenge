package com.example.codechallenge.speech;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class SpeechRepositoryTest {

    @Autowired
    private SpeechRepository unTest;

    @AfterEach
    void tearDown(){

    }

    @Test
    void canRetrieveSpeechByTagExists() {
        //given
        Speech speech = new Speech("John Doe", "I have a speech and it is very good",  Arrays.asList("motivational","speech"),
                LocalDate.of(2021, Month.OCTOBER, 29));

        List<Speech> mainData = new ArrayList<Speech>();
        mainData.add(speech);
        unTest.save(speech);

        //when
        List<Speech> exist = unTest.retrieveByTag("motivational");

        //then
        assertThat(exist).isEqualTo(mainData);

    }

    @Test
    void cannotRetrieveSpeechByTagNotExists() {
        //given
        Speech speech = new Speech("John Doe", "I have a speech and it is very good",  Arrays.asList("motivational","speech"),
                LocalDate.of(2021, Month.OCTOBER, 29));

        List<Speech> mainData = new ArrayList<Speech>();
        mainData.add(speech);
        unTest.save(speech);

        //when
        List<Speech> exist = unTest.retrieveByTag("motivs");

        //then
        assertThat(exist).isNotEqualTo(mainData);

    }

    @Test
    void canRetrieveByAuthorExists() {
        //given
        Speech speech = new Speech("John Doe", "I have a speech and it is very good",  Arrays.asList("motivational","speech"),
                LocalDate.of(2021, Month.OCTOBER, 29));

        List<Speech> mainData = new ArrayList<Speech>();
        mainData.add(speech);
        unTest.save(speech);

        //when
        List<Speech> exist = unTest.retrieveByAuthor("John Doe");

        //then
        assertThat(exist).isEqualTo(mainData);

    }

    @Test
    void cannotRetrieveByAuthorNotExists() {
        //given
        Speech speech = new Speech("John Doe", "I have a speech and it is very good",  Arrays.asList("motivational","speech"),
                LocalDate.of(2021, Month.OCTOBER, 29));

        List<Speech> mainData = new ArrayList<Speech>();
        mainData.add(speech);
        unTest.save(speech);

        //when
        List<Speech> exist = unTest.retrieveByAuthor("John Wick");

        //then
        assertThat(exist).isNotEqualTo(mainData);

    }

    @Test
    void canRetrieveSpeechBetweenDatesExists() {
        //given
        Speech speech = new Speech("John Doe", "I have a speech and it is very good",  Arrays.asList("motivational","speech"),
                LocalDate.of(2021, Month.OCTOBER, 29));

        List<Speech> mainData = new ArrayList<Speech>();
        mainData.add(speech);
        unTest.save(speech);

        //when
        List<Speech> exist = unTest.retrieveSpeechBetweenDates(LocalDate.of(2021,Month.AUGUST,31),LocalDate.of(2021, Month.NOVEMBER, 29));

        //then
        assertThat(exist).isEqualTo(mainData);
    }


    @Test
    void cannotRetrieveSpeechBetweenDatesNotExists() {
        //given
        Speech speech = new Speech("John Doe", "I have a speech and it is very good",  Arrays.asList("motivational","speech"),
                LocalDate.of(2021, Month.OCTOBER, 29));

        List<Speech> mainData = new ArrayList<Speech>();
        mainData.add(speech);
        unTest.save(speech);

        //when
        List<Speech> exist = unTest.retrieveSpeechBetweenDates(LocalDate.of(2021,Month.AUGUST,31),LocalDate.of(2021, Month.SEPTEMBER, 29));

        //then
        assertThat(exist).isNotEqualTo(mainData);
    }

    @Test
    void canRetrieveSpeechSnipExists() {

        //given
        Speech speech = new Speech("John Doe", "I have a speech and it is very good",  Arrays.asList("motivational","speech"),
                LocalDate.of(2021, Month.OCTOBER, 29));

        List<Speech> mainData = new ArrayList<Speech>();
        mainData.add(speech);
        unTest.save(speech);
        String snip = "%speech%";
        //when
        List<Speech> exist = unTest.retrieveSpeechSnip(snip);

        //then
        assertThat(exist).isEqualTo(mainData);
    }

    @Test
    void cannotRetrieveSpeechSnipNotExists() {
        //given
        Speech speech = new Speech("John Doe", "I have a speech and it is very good",  Arrays.asList("motivational","speech"),
                LocalDate.of(2021, Month.OCTOBER, 29));

        List<Speech> mainData = new ArrayList<Speech>();
        mainData.add(speech);
        unTest.save(speech);
        String snip = "%when%";
        //when
        List<Speech> exist = unTest.retrieveSpeechSnip(snip);

        //then
        assertThat(exist).isNotEqualTo(mainData);
    }

    @Test
    void canRetrieveActiveSpeech(){
        //given
        Speech speech = new Speech("John Doe", "I have a speech and it is very good",  Arrays.asList("motivational","speech"),
                LocalDate.of(2021, Month.OCTOBER, 29),Short.valueOf("0"),null);

        List<Speech> mainData = new ArrayList<Speech>();
        mainData.add(speech);
        unTest.save(speech);
        //when
        List<Speech> exist = unTest.retrieveAllActiveSpeeches();

        //then
        assertThat(exist).isEqualTo(mainData);

    }

    @Test
    void cannotRetrieveInactiveSpeech(){
        //given
        Speech speech = new Speech("John Doe", "I have a speech and it is very good",  Arrays.asList("motivational","speech"),
                LocalDate.of(2021, Month.OCTOBER, 29),Short.valueOf("1"),LocalDate.now());

        List<Speech> mainData = new ArrayList<Speech>();
        mainData.add(speech);
        unTest.save(speech);
        //when
        List<Speech> exist = unTest.retrieveAllActiveSpeeches();

        //then
        assertThat(exist).isNotEqualTo(mainData);

    }
}