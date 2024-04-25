package com.paul.project.services;

import com.paul.project.dto.AccountDto;
import com.paul.project.dto.TransactionDto;
import com.paul.project.dto.UserDto;
import com.paul.project.entity.Transaction;
import com.paul.project.entity.TransactionType;
import com.paul.project.entity.User;
import com.paul.project.repository.TransactionRepository;
import com.paul.project.repository.UserRepository;
import com.paul.project.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/*
@auteur paul Damien
 */

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private  final AccountService accountService;
    private final ObjectsValidator<TransactionDto> validator;


    @Override
    public Integer save(TransactionDto dto) {
        validator.validate(dto);
        Transaction transaction = TransactionDto.toEntity(dto);
        // transaction calculation
        BigDecimal transactionMultiplier = BigDecimal.valueOf(getTransactionMultiplier(transaction.getType()));
        BigDecimal amount = transaction.getAmount().multiply(transactionMultiplier);
        transaction.setAmount(amount);
        return transactionRepository.save(transaction).getId();
    }

    @Override
    public List<TransactionDto> findAll() {
        return transactionRepository.findAll()
                .stream().map(TransactionDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(Integer id) {

        return transactionRepository.findById(id).map(TransactionDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException(""+id));
    }

    @Override
    public void delete(Integer id) {
        transactionRepository.deleteById(id);

    }

    private int getTransactionMultiplier(TransactionType transactionType){
        return TransactionType.TRANSFERT == transactionType ? 1 : -1;
    }

    @Override
    public Integer validateAccount(Integer id) {
        //research the user
        User user = transactionRepository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException(" no account user was found " )).getUser();

        //user.setAccount(true);

        //create user account
        AccountDto accountDto = AccountDto.builder()
                .user(UserDto.fromEntity(user))
                .build();
        accountService.save(accountDto);
        return user.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {
        return null;
    }
}
