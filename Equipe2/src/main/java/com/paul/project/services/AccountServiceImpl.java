package com.paul.project.services;

import com.paul.project.dto.AccountDto;
import com.paul.project.entity.Account;
import com.paul.project.exceptions.OperationNonPermittedException;
import com.paul.project.repository.AccountRepository;
import com.paul.project.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    private  final ObjectsValidator<AccountDto> validator;

    @Override
    public Integer save(AccountDto accountDto) {
        //block account update ->iban cannot be changed
       /* if(accountDto.getId()!= null){
            throw new OperationNonPermittedException(
                    "Account cannot be updated",
                    "save account",
                    "Account",
                    "Update not permitted");
        }*/
        validator.validate(accountDto);
        Account account = AccountDto.toEntity(accountDto);
        //generate random iban when creating new else do not update the IBAN
        if (accountDto.getId()==null){
        account.setIban(generateRandomIban());
        }
        return accountRepository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return accountRepository.findById(id)
                .map(AccountDto::fromEntity).orElseThrow(
                        ()->new EntityNotFoundException("Not account not found :"+ id ));
    }

    @Override
    public void delete(Integer id) {
        accountRepository.deleteById(id);
    }

    public String generateRandomIban(){
        //todo generate an iban
        String iban = Iban.random().toFormattedString();

        //check if the iban already exists
        boolean ibanExist = accountRepository.findByIban(iban).isPresent();

        //if exist -> generate random iban
        if (ibanExist){
            generateRandomIban();
        }
        // if not exist -> return generated
        return iban;
    }
}
