package com.example.codechallenge.speech;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class SpeechServiceTest {


    @Mock
    private SpeechRepository speechRepository;
    @InjectMocks
    private SpeechService unTest;

    @BeforeEach
    void setUp(){
       unTest =  new SpeechService(speechRepository);
    }



    @Test
    void canGetAllSpeeches() {
        //when
        unTest.getAllSpeeches();

        //then
        verify(speechRepository).findAll();
    }

    @Test
    void canAddNewSpeech() {
      //given
     Speech speech = new Speech("John Doe", "I have a speech and it is very good",  Arrays.asList("motivational","speech"),
             LocalDate.of(2021, Month.OCTOBER, 29));

     //when
     unTest.addNewSpeech(speech);

     //then
     ArgumentCaptor<Speech> speechArgumentCaptor = ArgumentCaptor.forClass(Speech.class);

     verify((speechRepository)).save(speechArgumentCaptor.capture());

     Speech capturedSpeech = speechArgumentCaptor.getValue();

     assertThat(capturedSpeech).isEqualTo(speech);
    }

    @Test
    @Disabled
    void removeSpeech() {
//        //when
//        unTest.removeSpeech(1L);
//        //then
//        verify(speechRepository).deleteById(1L);

    }

    @Test
    @Disabled
    void canUpdateSpeech() {
    }


    @Test
    void getSpeechWithTag() {


        Speech speech = new Speech("John Doe", "I have a speech and it is very good",  Arrays.asList("motivational","speech"),
                LocalDate.of(2021, Month.OCTOBER, 29));

        List<Speech> mainData = new ArrayList<Speech>();
        mainData.add(speech);
        when(unTest.getSpeechWithTag("motivational")).thenReturn(mainData);
        assertEquals(mainData, unTest.getSpeechWithTag("motivational"));
        verify(speechRepository).retrieveByTag("motivational");
    }

    @Test
    void getSpeechWithAuthor() {


        Speech speech = new Speech("John Doe", "I have a speech and it is very good",  Arrays.asList("motivational","speech"),
                LocalDate.of(2021, Month.OCTOBER, 29));

        List<Speech> mainData = new ArrayList<Speech>();
        mainData.add(speech);
        when(unTest.getSpeechWithAuthor("John Doe")).thenReturn(mainData);
        assertEquals(mainData, unTest.getSpeechWithAuthor("John Doe"));
        verify(speechRepository).retrieveByAuthor("John Doe");

    }

    @Test
    void getSpeechBetweenDates() {
        Speech speech = new Speech("John Doe", "I have a speech and it is very good",  Arrays.asList("motivational","speech"),
                LocalDate.of(2021, Month.OCTOBER, 29));

        List<Speech> mainData = new ArrayList<Speech>();
        mainData.add(speech);
        when(unTest.getSpeechBetweenDates("2021-10-01","2021-10-31")).thenReturn(mainData);
        assertEquals(mainData, unTest.getSpeechBetweenDates("2021-10-01","2021-10-31"));
        verify(speechRepository).retrieveSpeechBetweenDates(LocalDate.of(2021, Month.OCTOBER,01),LocalDate.of(2021, Month.OCTOBER, 31));

    }

    @Test
      void getSpeechWithSnippet() {
        Speech speech = new Speech("John Doe", "I have a speech and it is very good",  Arrays.asList("motivational","speech"),
                LocalDate.of(2021, Month.OCTOBER, 29));

        List<Speech> mainData = new ArrayList<Speech>();
        mainData.add(speech);
        when(unTest.getSpeechWithSnippet("speech")).thenReturn(mainData);
        assertEquals(mainData, unTest.getSpeechWithSnippet("speech"));
        verify(speechRepository).retrieveSpeechSnip("%speech%");

    }

    @Test
    void getAllActiveSpeeches() {
        Speech speech = new Speech("John Doe", "I have a speech and it is very good",  Arrays.asList("motivational","speech"),
                LocalDate.of(2021, Month.OCTOBER, 29),Short.valueOf("0"),null);

        List<Speech> mainData = new ArrayList<Speech>();
        mainData.add(speech);
        when(unTest.getAllActiveSpeeches()).thenReturn(mainData);
        assertEquals(mainData, unTest.getAllActiveSpeeches());
        verify(speechRepository).retrieveAllActiveSpeeches();

    }

    @Test
    @Disabled
    void deleteSpeech() {
    }
}