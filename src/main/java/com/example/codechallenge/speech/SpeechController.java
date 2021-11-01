package com.example.codechallenge.speech;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * SpeechController: this is where I put paths(URL-paths)
 * used to set what action should be taken
 */
@RestController
@RequestMapping(path="/api/v1/codechallenge")
public class SpeechController {

    private final SpeechService speechService;

    @Autowired
    public SpeechController(SpeechService speechService) {
        this.speechService = speechService;
    }

    /**
     * getSpeeches
     * @return -all Speeches in DB
     */
    @GetMapping
    public List<Speech> getSpeeches(){
        return speechService.getAllSpeeches();
    }

    @GetMapping(path = "active")
    public List<Speech> getActiveSpeeches(){
        return speechService.getAllActiveSpeeches();
    }

    /**
     * Adds a new Speech
     * @param speech -JSON format of speech
     */
    @PostMapping
    public void registerNewSpeech(@RequestBody Speech speech){
        speechService.addNewSpeech(speech);
    }

    /**
     * Deletes speech from DB
     * @param speechId - Id of speech to be deleted
     */
    @DeleteMapping(path="remove/{speechId}")
    public void removeSpeech(@PathVariable("speechId")Long speechId){
        speechService.removeSpeech(speechId);
    }

    @PutMapping(path="delete/{speechId}")
    public void deleteSpeech(@PathVariable("speechId")Long speechId){
        speechService.deleteSpeech(speechId);
    }


    /**
     * Edits Speech
     * @param speechId - PK Id of Speech to be edited must use edit/<pk id> followed by?<column keyword>
     *                 (see below for keywords to be used)if more than 1 edit use & for next keyword
     * @param author - optional must use "author=<value>"in URL
     * @param body -- optional must use "body=<value>"in URL
     * @param tags - optional must use "tags=<value>"in URL
     * @param date - optional must use "date=<value>"in URL
     */
    @PutMapping(path="edit/{speechId}",produces = "application/json")
    private void updateSpeech(@PathVariable("speechId")Long speechId,
                              @RequestParam(required = false) String author,
                              @RequestParam(required = false)String body,
                              @RequestParam(required = false)List<String> tags,
                              @RequestParam(required = false)String date){
        speechService.updateSpeech(speechId, author, body, tags, date);
    }

    /**
     * Search by tag
     * @param tag -  use /tag?value=<tag to be searched>
     * @return list of all
     */
   @GetMapping(path="tag")
    public List<Speech> searchTag(@RequestParam("value") String tag){
        return speechService.getSpeechWithTag(tag);
    }

    @GetMapping(path="author")
    public List<Speech> searchAuthor(@RequestParam("value")String author){
        return speechService.getSpeechWithAuthor(author);
    }

    @GetMapping(path="between")
    public List<Speech> searchBetween(@RequestParam("before")String before, @RequestParam("after") String after){
        return speechService.getSpeechBetweenDates(before, after);
    }


    @GetMapping(path="search")
    public List<Speech> searchSnippet(@RequestParam("snip")String snippet){
        return speechService.getSpeechWithSnippet(snippet);
    }

}
