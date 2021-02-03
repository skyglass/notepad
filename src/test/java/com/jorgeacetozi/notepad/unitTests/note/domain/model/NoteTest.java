package com.jorgeacetozi.notepad.unitTests.note.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.jorgeacetozi.notepad.note.domain.model.Note;

public class NoteTest {

	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void shouldNotRaiseViolationWhenTitleAndContentAreFilled() {
		Note note = new Note("Unit Tests", "Unit tests provide fast feedback");
		Set<ConstraintViolation<Note>> constraintViolations = validator.validate(note);
		assertThat(constraintViolations.size()).isEqualTo(0);
	}

	@Test
	public void shouldRaiseViolationWhenTitleIsEmpty() {
		Note note = new Note("", "Unit tests provide fast feedback");
		Set<ConstraintViolation<Note>> constraintViolations = validator.validate(note);
		assertThat(constraintViolations.size()).isEqualTo(1);
	}

	@Test
	public void shouldRaiseViolationWhenContentIsEmpty() {
		Note note = new Note("Unit Tests", "");
		Set<ConstraintViolation<Note>> constraintViolations = validator.validate(note);
		assertThat(constraintViolations.size()).isEqualTo(1);
	}

	@Test
	public void shouldCountOneForContentWithSingleWord() {
		Note note = new Note("Unit Tests", "Xuxa");
		assertThat(note.getWordCount()).isEqualTo(1);
	}

	@Test
	public void shouldCountWordsFromNoteContent() {
		Note note = new Note("Unit Tests",
				"Unit tests provide fast feedback, but they test only an isolated unit of code");
		assertThat(note.getWordCount()).isEqualTo(14);
	}
}
