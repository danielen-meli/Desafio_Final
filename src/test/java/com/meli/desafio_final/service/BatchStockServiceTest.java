package com.meli.desafio_final.service;

import com.meli.desafio_final.repository.IBatchStockRepository;
import com.meli.desafio_final.util.TestUtilsGen_BatchStock;
import com.meli.desafio_final.util.TestUtilsGen_SellerAd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BatchStockServiceTest {

    @InjectMocks
    BatchStockService batchStockService;

    @Mock
    IBatchStockRepository batchStockRepository;

    @BeforeEach
    public void setup(){
        BDDMockito.when(batchStockRepository.findAll())
                .thenReturn(TestUtilsGen_BatchStock.getNewListBatchStock());
    }


}
