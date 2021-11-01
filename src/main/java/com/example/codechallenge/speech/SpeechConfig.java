package com.example.codechallenge.speech;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SpeechConfig {


    /**
     * commandLineRunner - Sets initial value to DB
      * @param repo - JPA repository that handles SQL Commands
     * @return
     */
@Bean
CommandLineRunner commandLineRunner(SpeechRepository repo){
return args -> {
    Speech dream =new Speech("Martin Luther King",
            "I have a dream that one day this nation will rise up and live out the true meaning of its creed: \"We hold these truths to be self-evident, that all men are created equal.\"\n" +
                    "\n" +
                    "I have a dream that one day on the red hills of Georgia, the sons of former slaves and the sons of former slave owners will be able to sit down together at the table of brotherhood.\n" +
                    "\n" +
                    "I have a dream that one day even the state of Mississippi, a state sweltering with the heat of injustice, sweltering with the heat of oppression, will be transformed into an oasis of freedom and justice.\n" +
                    "\n" +
                    "I have a dream that my four little children will one day live in a nation where they will not be judged by the color of their skin but by the content of their character.\n" +
                    "\n" +
                    "I have a dream today!\n" +
                    "\n" +
                    "I have a dream that one day, down in Alabama, with its vicious racists, with its governor having his lips dripping with the words of \"interposition\" and \"nullification\" -- one day right there in Alabama little black boys and black girls will be able to join hands with little white boys and white girls as sisters and brothers.\n" +
                    "\n" +
                    "I have a dream today!\n" +
                    "\n" +
                    "I have a dream that one day every valley shall be exalted, and every hill and mountain shall be made low, the rough places will be made plain, and the crooked places will be made straight; \"and the glory of the Lord shall be revealed and all flesh shall see it together.\"2\n" +
                    "\n" +
                    "This is our hope, and this is the faith that I go back to the South with.\n" +
                    "\n" +
                    "With this faith, we will be able to hew out of the mountain of despair a stone of hope. With this faith, we will be able to transform the jangling discords of our nation into a beautiful symphony of brotherhood. With this faith, we will be able to work together, to pray together, to struggle together, to go to jail together, to stand up for freedom together, knowing that we will be free one day.\n" +
                    "\n" +
                    "And this will be the day -- this will be the day when all of God's children will be able to sing with new meaning:",
                    Arrays.asList("freedom", "dream"),

            LocalDate.of(1963, Month.AUGUST,28),Short.valueOf("0"),null);

    Speech getty =new Speech("Abraham Lincoln",
            "Four score and seven years ago our fathers brought forth on this continent, a new nation, conceived in Liberty, and dedicated to the proposition that all men are created equal.\n" +
                    "\n" +
                    "Now we are engaged in a great civil war, testing whether that nation, or any nation so conceived and so dedicated, can long endure. We are met on a great battle-field of that war. We have come to dedicate a portion of that field, as a final resting place for those who here gave their lives that that nation might live. It is altogether fitting and proper that we should do this.\n" +
                    "\n" +
                    "But, in a larger sense, we can not dedicate -- we can not consecrate -- we can not hallow -- this ground. " +
                    "\n"+
                    "The brave men, living and dead, who struggled here, have consecrated it, far above our poor power to add or detract. " +
                    "\n"+
                    "The world will little note, nor long remember what we say here, but it can never forget what they did here. " +
                    "It is for us the living, rather, to be dedicated here to the unfinished work which they who fought here have thus far so nobly advanced. " +
                    "It is rather for us to be here dedicated to the great task remaining before us -- that from these honored dead we take increased devotion to that cause for which they gave the last full measure of devotion " +
                    "-- that we here highly resolve that these dead shall not have died in vain -- that this nation, under God, shall have a new birth of freedom -- and that government of the people, by the people, for the people, shall not perish from the earth.",
            Arrays.asList("freedom","motivational")
            , LocalDate.of(1863, Month.NOVEMBER, 19),Short.valueOf("0"),null);

            Speech preparedDie =new Speech("Nelson Mandela",
            "During my lifetime I have dedicated myself to this struggle of the African people. " +
                    "I have fought against white domination, and I have fought against black domination. " +
                    "I have cherished the ideal of a democratic and free society in which all persons live together in harmony and with equal opportunities. " +
                    "It is an ideal which I hope to live for and to achieve. " +
                    "But if needs be, it is an ideal for which I am prepared to die.",
                    Arrays.asList("motivational","death")
                  , LocalDate.of(1863, Month.NOVEMBER, 19), Short.valueOf("0"), null);

            repo.saveAll(List.of(dream, getty, preparedDie));




};
}
}
