package com.paul.project.services;

import com.paul.project.dto.TransactionDto;

public interface TransactionService extends  AbstractService<TransactionDto>{

    Integer validateAccount(Integer id);


    Integer invalidateAccount(Integer id);
}
