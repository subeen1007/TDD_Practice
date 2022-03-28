package com.example.tdd_practice.iloveyouboss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfileTest {
    private Profile profile;
    private BooleanQuestion question;
    private Criteria criteria;

    @BeforeEach
    public void create(){
        profile = new Profile("Bull Hockey");
        question = new BooleanQuestion(1, "Got bonuses?");
        criteria = new Criteria();
    }
    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet(){
        //준비
        profile.add(new Answer(question, Bool.FALSE));
        criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch));
        //실행
        boolean matches = profile.matches(criteria);
        //단언
        assertFalse(matches);
    }

    @Test
    public void matchAnswersTrueForAnyDontCareCriteria(){
        Answer profileAnswer = new Answer(question, Bool.FALSE);
        profile.add(profileAnswer);
        Answer criteriaAnswer = new Answer(question, Bool.TRUE);
        Criterion criterion = new Criterion(criteriaAnswer, Weight.DontCare);

        criteria.add(criterion);

        boolean matches = profile.matches(criteria);

        assertTrue(matches);

    }
}
