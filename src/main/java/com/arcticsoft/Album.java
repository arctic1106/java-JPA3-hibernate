package com.arcticsoft;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Album 
{
	private Long id;
	private String title;
	private LocalDate releaseDate;

	public Album() {
	}

	public Album(String title, LocalDate releaseDate) {
		this.title = title;
		this.releaseDate = releaseDate;
	}

	@Id()
	@GeneratedValue(generator = "aincrement", strategy = GenerationType.SEQUENCE)
	@GenericGenerator(name = "aincrement", strategy = "increment")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		return "Album [id=" + id + ", title=" + title + ", releaseDate=" + releaseDate + "]";
	}
}
