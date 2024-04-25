package com.paul.project.repository;

import com.paul.project.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository  extends JpaRepository<Contact, Integer> {
}
