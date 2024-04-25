package com.paul.project.services;

import com.paul.project.dto.ContactDto;
import com.paul.project.entity.Contact;
import com.paul.project.repository.ContactRepository;
import com.paul.project.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService{

    private final ContactRepository contactRepository;
    private final ObjectsValidator<ContactDto> validator;

    @Override
    public Integer save(ContactDto dto) {
        validator.validate(dto);
        Contact contact = ContactDto.fromEntity(dto);
        return contactRepository.save(contact).getId();
    }

    @Override
    public List<ContactDto> findAll() {
        return contactRepository.findAll()
                .stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ContactDto findById(Integer id) {
        return contactRepository.findById(id)
                .map(ContactDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException(" Not address found with the ID"+ id));
    }

    @Override
    public void delete(Integer id) {
        contactRepository.deleteById(id);

    }
}
