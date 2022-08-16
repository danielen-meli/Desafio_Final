package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.SellerHistoryByProductsResponseDto;
import com.meli.desafio_final.dto.SellerHistoryDto;
import com.meli.desafio_final.dto.SellerHistoryTotalSoldResponseDto;
import com.meli.desafio_final.exception.BadRequestException;
import com.meli.desafio_final.model.*;

import static org.assertj.core.api.Assertions.assertThat;

import com.meli.desafio_final.repository.IBuyerRepository;
import com.meli.desafio_final.repository.ISellerHistoryRepository;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SellerBuyerHistoryServiceTest {

    @InjectMocks
    SellerBuyerHistoryService sellerBuyerHistoryService;

    @Mock
    private IBuyerRepository buyerRepository;

    @Mock
    private ISellerHistoryRepository sellerHistoryRepository;

    @BeforeEach
    public void setup() {
        BDDMockito.when(buyerRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(generateBuyer());

        BDDMockito.when(buyerRepository.findByOrderByQuantityPurchasedAsc())
                .thenReturn(generateListBuyer());

        BDDMockito.when(buyerRepository.findByOrderByQuantityPurchasedDesc())
                .thenReturn(generateListBuyer());

        BDDMockito.when(buyerRepository.findBuyerBetweenPurchaseValueAsc(ArgumentMatchers.any(Double.class), ArgumentMatchers.any(Double.class)))
                .thenReturn(generateListBuyer());

        BDDMockito.when(buyerRepository.findBuyerBetweenPurchaseValueDesc(ArgumentMatchers.any(Double.class), ArgumentMatchers.any(Double.class)))
                .thenReturn(generateListBuyer());

        BDDMockito.when(sellerHistoryRepository.findAll())
                .thenReturn(generateSellersHistoryList());

        BDDMockito.when(sellerHistoryRepository.sellerHistoryTotalSold())
                .thenReturn(generateSellersOrderedTotalSold());

        BDDMockito.when(sellerHistoryRepository.getSellersHistoryByProduct(ArgumentMatchers.any(Long.class)))
                .thenReturn(generateSellersByProduct());
    }

    @Test
    void getABuyer() {
        Buyer buyer = sellerBuyerHistoryService.getABuyer(1L);

        assertThat(buyer).isNotNull();
        assertThat(buyer.getBuyerId()).isEqualTo(1L);
    }

    @Test
    void getBuyersOrderedBuyPurchaseQuantity() {
        List<Buyer> buyersAsc = sellerBuyerHistoryService.getBuyersOrderedBuyPurchaseQuantity("asc");

        assertThat(buyersAsc).isNotNull();
        assertThat(buyersAsc).isNotEmpty();
        assertThat(buyersAsc.size()).isEqualTo(3);

        List<Buyer> buyersDesc = sellerBuyerHistoryService.getBuyersOrderedBuyPurchaseQuantity("desc");

        assertThat(buyersDesc).isNotNull();
        assertThat(buyersDesc).isNotEmpty();
        assertThat(buyersDesc.size()).isEqualTo(3);
    }

    @Test
    void getBuyersOrderedBuyPurchaseQuantity_WhenOrderWrong() {
        assertThrows(BadRequestException.class, () -> {
            sellerBuyerHistoryService.getBuyersOrderedBuyPurchaseQuantity("wrong");
        });
    }

    @Test
    void getBuyersBetweenValuesPurchaseOrderedBuyPurchaseQuantity() {
        List<Buyer> buyersBetweenAsc = sellerBuyerHistoryService.getBuyersBetweenValuesPurchaseOrderedBuyPurchaseQuantity(20, 40, "asc");
        List<Buyer> buyersBetweenDesc = sellerBuyerHistoryService.getBuyersBetweenValuesPurchaseOrderedBuyPurchaseQuantity(20, 40, "desc");

        assertThat(buyersBetweenAsc).isNotNull();
        assertThat(buyersBetweenAsc).isNotEmpty();
        assertThat(buyersBetweenAsc.size()).isEqualTo(3);
        assertThat(buyersBetweenDesc).isNotNull();
        assertThat(buyersBetweenDesc).isNotEmpty();
        assertThat(buyersBetweenDesc.size()).isEqualTo(3);
    }

    @Test
    void getBuyersBetweenValuesPurchaseOrderedBuyPurchaseQuantity_WrongOrderBy() {
        assertThrows(BadRequestException.class, () -> {
            sellerBuyerHistoryService.getBuyersBetweenValuesPurchaseOrderedBuyPurchaseQuantity(20, 40, "wrong");
        });
    }

    @Test
    void getAllSellersHistory() {
        List<SellerHistoryDto> sellerHistories = sellerBuyerHistoryService.getAllSellersHistory();

        assertThat(sellerHistories).isNotNull();
        assertThat(sellerHistories).isNotEmpty();
        assertThat(sellerHistories.size()).isEqualTo(3);
    }

    @Test
    void getSellersOrderedByTotalSold() {
        List<SellerHistoryTotalSoldResponseDto> slhtdto = sellerBuyerHistoryService.getSellersOrderedByTotalSold();
        assertThat(slhtdto).isNotEmpty();
        assertThat(slhtdto).isNotNull();
        assertThat(slhtdto.size()).isEqualTo(3);
    }

    @Test
    void getSellersHistoryByProduct() {
        List<SellerHistoryByProductsResponseDto> shbprdto = sellerBuyerHistoryService.getSellersHistoryByProduct(1L);
        assertThat(shbprdto).isNotEmpty();
        assertThat(shbprdto).isNotNull();
        assertThat(shbprdto.size()).isEqualTo(3);
    }

    public Optional<Buyer> generateBuyer() {
        return Optional.of(Buyer.builder().buyerId(1).build());
    }

    public List<Buyer> generateListBuyer() {
        List<Buyer> buyersOrdered = new ArrayList<>();

        buyersOrdered.add(Buyer.builder().buyerId(1L).build());
        buyersOrdered.add(Buyer.builder().buyerId(2L).build());
        buyersOrdered.add(Buyer.builder().buyerId(3L).build());

        return buyersOrdered;
    }

    public Seller generateSeller(long id) {
        return Seller
                .builder()
                .sellerId(id)
                .sellerName("name")
                .user(User.builder().email("email@email.com").build())
                .build();
    }

    public SellerAd generateSellerAd(long id, long productId, String productName) {
        return SellerAd.builder()
                .sellerAdId(id)
                .product(Product.builder().productName(productName).productId(productId).build())
                .price(15)
                .build();
    }

    public SellerHistory generateSellerHistory(long id, long sellerId, long sellerAdId, long productId, String productName) {
        return SellerHistory
                .builder()
                .id(id)
                .seller(generateSeller(sellerId))
                .sellerAd(generateSellerAd(sellerAdId, productId, productName))
                .quantity(30)
                .build();
    }

    public List<SellerHistory> generateSellersHistoryList() {
        List<SellerHistory> sellerHistoryDtos = new ArrayList<>();

        sellerHistoryDtos.add(generateSellerHistory(1, 1, 1, 1, "banana"));
        sellerHistoryDtos.add(generateSellerHistory(2, 2, 2, 2, "apple"));
        sellerHistoryDtos.add(generateSellerHistory(3, 3, 3, 3, "papaya"));


        return sellerHistoryDtos;
    }

    public List<ISellerHistoryTotalSold> generateSellersOrderedTotalSold() {
        List<ISellerHistoryTotalSold> sellerHistoryTotalSolds = new ArrayList<>();

        sellerHistoryTotalSolds.add(SellerHistoryTSTest.builder().build());
        sellerHistoryTotalSolds.add(SellerHistoryTSTest.builder().build());
        sellerHistoryTotalSolds.add(SellerHistoryTSTest.builder().build());

        return sellerHistoryTotalSolds;
    }

    public List<ISellerHistoryByProduct> generateSellersByProduct() {
        List<ISellerHistoryByProduct> shbp = new ArrayList<>();

        shbp.add(SellerHistoryByProduct.builder().build());
        shbp.add(SellerHistoryByProduct.builder().build());
        shbp.add(SellerHistoryByProduct.builder().build());

        return shbp;
    }
}

@NoArgsConstructor
@Builder
class SellerHistoryTSTest implements ISellerHistoryTotalSold {
    @Override
    public Double getTotal_sold() {
        return 50.0;
    }

    @Override
    public Long getSeller_id() {
        return 1L;
    }

    @Override
    public String getSeller_name() {
        return "name";
    }

    @Override
    public String getSeller_email() {
        return "email@email.com";
    }
}

@NoArgsConstructor
@Builder
class SellerHistoryByProduct implements ISellerHistoryByProduct {
    @Override
    public Long getId() {
        return 1L;
    }

    @Override
    public Double getPrice() {
        return 4.0;
    }

    @Override
    public Double getTotal_quantity_sold() {
        return 50.0;
    }

    @Override
    public Long getSeller_seller_id() {
        return 1L;
    }

    @Override
    public String getSeller_name() {
        return "name";
    }

    @Override
    public String getEmail() { return "email@email.com"; }

    @Override
    public String getProduct_name() { return "prod name"; }

}



