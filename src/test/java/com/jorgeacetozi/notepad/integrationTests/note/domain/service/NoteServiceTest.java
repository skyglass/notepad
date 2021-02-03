package com.jorgeacetozi.notepad.integrationTests.note.domain.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jorgeacetozi.notepad.note.domain.model.Note;
import com.jorgeacetozi.notepad.note.domain.service.NoteService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class NoteServiceTest {

	@Autowired
	private NoteService noteService;

	private Note note;

	@BeforeEach
	public void setUp() {
		note = new Note("Kubernetes", "Best container orchestration tool ever");
	}

	@AfterEach
	public void destroy() {
		noteService.delete(note);
	}

	@Test
	public void shouldCreateNoteWithTitleAndContent() {
		Note createdNote = noteService.create(note);
		assertThat(createdNote.getId()).isNotNull();
		assertThat(createdNote.getWordCount()).isEqualTo(5);
	}
}
