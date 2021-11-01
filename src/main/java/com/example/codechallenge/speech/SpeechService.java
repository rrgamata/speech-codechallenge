package com.example.codechallenge.speech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SpeechService {

    private final SpeechRepository speechRepository;

    @Autowired
    public SpeechService(SpeechRepository speechRepository) {
        this.speechRepository = speechRepository;
    }

    public List<Speech> getAllSpeeches(){
        return speechRepository.findAll();
    }

    public List<Speech> getAllActiveSpeeches(){
        return speechRepository.retrieveAllActiveSpeeches();
    }

    public void addNewSpeech(Speech speech) {
        //Optional<Speech> studentOptional = speechRepository.findStudentByEmail(student.getEmail());

        //if(studentOptional.isPresent()){
        //    throw new IllegalStateException("email taken");
        //}
        speechRepository.save(speech);

    }

    public void removeSpeech(Long speechId) {
        boolean exists = speechRepository.existsById(speechId);

        if(!exists){
            throw new IllegalStateException("speech ID "+ speechId+" does not exists");

        }else{
            speechRepository.deleteById(speechId);

        }
    }

    @Transactional
    public void updateSpeech(Long speechId, String author, String body, List<String> tags, String date) {
        Speech speech = speechRepository.findById(speechId).orElseThrow(
                ()->  new IllegalStateException("speech id "+ speechId+" does not exists"));


        if(author!= null && author.length()>0 &&
                !Objects.equals(speech.getSpeechAuthor(), author)){
            speech.setSpeechAuthor(author);
        }

        if(body!= null && body.length()>0 &&
                !Objects.equals(speech.getSpeechBody(), body)){
            speech.setSpeechBody(body);
        }

        if(tags!=null && !Objects.equals(speech.getTags(),tags)){
            speech.setTags(tags);
        }
//        if(keywords!= null && keywords.length()>0 &&
//                !Objects.equals(speech.getSpeechKeywords(), keywords)){
//            speech.setSpeechKeywords(keywords);
//        }
        if(date!= null && date.length()>0 &&
                !Objects.equals(speech.getSpeechDate(), LocalDate.parse(date))){
            speech.setSpeechDate(LocalDate.parse(date));
        }


    }

    public List<Speech> getSpeechWithTag(String tag) {
        List<Speech> speeches = speechRepository.retrieveByTag(tag);
        if(speeches.size()<1){
            new IllegalStateException("speech with tag "+ tag+" does not exist.");
        }
        return speeches;

    }

    public List<Speech> getSpeechWithAuthor(String author){
        List<Speech> speeches = speechRepository.retrieveByAuthor(author);
        if(speeches.size()<1){
            new IllegalStateException("speech with author "+author+" does not exist.");
        }
        return speeches;
    }


    public List<Speech> getSpeechBetweenDates(String before, String after){
        List<Speech> speeches = speechRepository.retrieveSpeechBetweenDates(LocalDate.parse(before), LocalDate.parse(after));
        if(speeches.size()<1){
            new IllegalStateException("There are no speeches between "+before+" and "+after+".");
        }
        return speeches;
    }


    public List<Speech> getSpeechWithSnippet(String snippet){
        String likesnip = "%"+snippet+"%";
        List<Speech> speeches = speechRepository.retrieveSpeechSnip(likesnip);
        if(speeches.size()<1){
            new IllegalStateException("There are no speeches with snippet"+snippet+".");
        }
        return speeches;
    }

    @Transactional
    public void deleteSpeech(Long speechId){
        Speech speech = speechRepository.findById(speechId).orElseThrow(
                ()->  new IllegalStateException("speech id "+ speechId+" does not exists"));

        if(speech.getDeleteFlag()!= Short.valueOf("1")){
            speech.setDeleteFlag(Short.valueOf("1"));
            speech.setDeleteDate(LocalDate.now());
        }


}
}
