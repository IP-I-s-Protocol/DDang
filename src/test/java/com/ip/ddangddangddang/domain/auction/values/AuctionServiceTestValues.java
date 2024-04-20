package com.ip.ddangddangddang.domain.auction.values;

import com.ip.ddangddangddang.domain.auction.dto.request.AuctionRequestDto;
import com.ip.ddangddangddang.domain.auction.entity.Auction;
import com.ip.ddangddangddang.domain.auction.entity.StatusEnum;
import com.ip.ddangddangddang.domain.file.entity.File;
import com.ip.ddangddangddang.domain.town.entity.Town;
import com.ip.ddangddangddang.domain.user.entity.User;
import java.time.LocalDateTime;
import java.util.List;

public interface AuctionServiceTestValues {

    //town
    Long TEST_TOWN1_ID = 1L;
    Long TEST_TOWN2_ID = 2L;
    Long TEST_ANOTHER_TOWN1_ID = 3L;
    Long TEST_ANOTHER_TOWN2_ID = 4L;
    String TEST_TOWN1_NAME = "testTownName1";
    String TEST_TOWN2_NAME = "testTownName2";
    String TEST_ANOTHER_TOWN1_NAME = "testAnotherTownName1";
    String TEST_ANOTHER_TOWN2_NAME = "testAnotherTownName2";

    Town TEST_TOWN1 = new Town(TEST_TOWN1_ID, TEST_TOWN1_NAME,
        List.of(TEST_TOWN1_ID, TEST_TOWN2_ID));
    Town TEST_TOWN2 = new Town(TEST_TOWN2_ID, TEST_TOWN2_NAME,
        List.of(TEST_TOWN1_ID, TEST_TOWN2_ID));
    Town TEST_ANOTHER_TOWN1 = new Town(TEST_ANOTHER_TOWN1_ID, TEST_ANOTHER_TOWN1_NAME,
        List.of(TEST_ANOTHER_TOWN1_ID, TEST_ANOTHER_TOWN2_ID));
    Town TEST_ANOTHER_TOWN2 = new Town(TEST_ANOTHER_TOWN2_ID, TEST_ANOTHER_TOWN2_NAME,
        List.of(TEST_ANOTHER_TOWN1_ID, TEST_ANOTHER_TOWN2_ID));

    //user
    Long TEST_USER1_ID = 1L;
    Long TEST_USER2_ID = 2L;
    Long TEST_USER3_ID = 3L;
    Long TEST_USER4_ID = 4L;

    Long TEST_ANOTHER_USER1_ID = 5L; // 원래 통일하게 맞춰야하는데 바꿔야할 게 많아서 추가 유저 아이디입니다.

    String TEST_USER_EMAIL = "testUserEmail";
    String TEST_USER_NICKNAME = "testUserNickname";
    String TEST_BUYER_USER_NICKNAME = "testBuyerNickname";
    String TEST_USER_PASSWORD = "testPassword";
    String TEST_USER_ENCRYPTED_PASSWORD = "testEncryptedPassword";

    User TEST_USER1 = User.builder()
        .id(TEST_USER1_ID)
        .email(TEST_USER_EMAIL)
        .nickname(TEST_USER_NICKNAME)
        .password(TEST_USER_ENCRYPTED_PASSWORD)
        .town(TEST_TOWN1)
        .build();

    User TEST_USER2 = User.builder()
        .id(TEST_USER2_ID)
        .email(TEST_USER_EMAIL)
        .nickname(TEST_USER_NICKNAME)
        .password(TEST_USER_ENCRYPTED_PASSWORD)
        .town(TEST_TOWN2)
        .build();

    User TEST_USER3 = User.builder()
        .id(TEST_USER3_ID)
        .email(TEST_USER_EMAIL)
        .nickname(TEST_USER_NICKNAME)
        .password(TEST_USER_ENCRYPTED_PASSWORD)
        .town(TEST_ANOTHER_TOWN1)
        .build();

    User TEST_USER4 = User.builder()
        .id(TEST_USER4_ID)
        .email(TEST_USER_EMAIL)
        .nickname(TEST_USER_NICKNAME)
        .password(TEST_USER_ENCRYPTED_PASSWORD)
        .town(TEST_ANOTHER_TOWN2)
        .build();

    User TEST_BUYER_USER1 = User.builder()
        .id(TEST_ANOTHER_USER1_ID)
        .email(TEST_USER_EMAIL)
        .nickname(TEST_BUYER_USER_NICKNAME)
        .password(TEST_USER_ENCRYPTED_PASSWORD)
        .town(TEST_TOWN1)
        .build();

    //file
    Long TEST_FILE1_ID = 1L;
    Long TEST_FILE2_ID = 2L;
    String TEST_OBJECT_NAME = "testObjectName";
    String TEST_KEY_NAME = "testKeyName";
    String TEST_FILE_PATH = "testFilePath";

    File TEST_FILE1 = File.builder()
        .id(TEST_FILE1_ID)
        .objectName(TEST_OBJECT_NAME)
        .keyName(TEST_KEY_NAME)
        .filePath(TEST_FILE_PATH)
        .user(TEST_USER1)
        .build();

    File TEST_FILE2 = File.builder()
        .id(TEST_FILE2_ID)
        .objectName(TEST_OBJECT_NAME)
        .keyName(TEST_KEY_NAME)
        .filePath(TEST_FILE_PATH)
        .user(TEST_USER3)
        .build();

    //auction
    Long TEST_TOWN1_AUCTION1_ID = 1L;
    Long TEST_TOWN2_AUCTION2_ID = 2L;
    Long TEST_TOWN3_AUCTION1_ID = 3L;
    Long TEST_TOWN4_AUCTION2_ID = 4L;
    String TEST_AUCTION_TITLE = "testTitle";
    String TEST_AUCTION_CONTENT = "testContent";
    Long TEST_AUCTION_PRICE = 100L;
    Long TEST_AUCTION_UPDATE_PRICE = 200L;
    Long TEST_AUCTION_BUYER_ID = 2L;

    LocalDateTime TEST_LOCAL_DATETIME_TODAY = LocalDateTime.parse("2024-03-01T00:00:00");
    LocalDateTime TEST_LOCAL_DATETIME_TOMORROW = LocalDateTime.parse("2024-03-02T00:00:00");

    Auction TEST_AUCTION1 = Auction.builder()
        .id(TEST_TOWN1_AUCTION1_ID)
        .townId(TEST_TOWN1_ID)
        .title(TEST_AUCTION_TITLE)
        .content(TEST_AUCTION_CONTENT)
        .price(TEST_AUCTION_PRICE)
        .buyerId(TEST_ANOTHER_USER1_ID)
        .statusEnum(StatusEnum.ON_SALE)
        .finishedAt(TEST_LOCAL_DATETIME_TOMORROW)
        .user(TEST_USER1)
        .file(TEST_FILE1)
        .build();

    Auction TEST_AUCTION2 = Auction.builder()
        .id(TEST_TOWN1_AUCTION1_ID)
        .townId(TEST_TOWN1_ID)
        .title(TEST_AUCTION_TITLE)
        .content(TEST_AUCTION_CONTENT)
        .price(TEST_AUCTION_PRICE)
        .buyerId(TEST_ANOTHER_USER1_ID)
        .statusEnum(StatusEnum.ON_SALE)
        .finishedAt(TEST_LOCAL_DATETIME_TOMORROW)
        .user(TEST_USER1)
        .file(TEST_FILE1)
        .build();


    Auction TEST_ANOTHER_TOWN_AUCTION1 = Auction.builder()
        .id(TEST_TOWN3_AUCTION1_ID)
        .townId(TEST_ANOTHER_TOWN1_ID)
        .title(TEST_AUCTION_TITLE)
        .content(TEST_AUCTION_CONTENT)
        .price(TEST_AUCTION_PRICE)
        .buyerId(TEST_USER4_ID)
        .statusEnum(StatusEnum.ON_SALE)
        .finishedAt(TEST_LOCAL_DATETIME_TOMORROW)
        .user(TEST_USER3)
        .file(TEST_FILE2)
        .build();

    Auction TEST_ANOTHER_TOWN_AUCTION2 = Auction.builder()
        .id(TEST_TOWN3_AUCTION1_ID)
        .townId(TEST_ANOTHER_TOWN1_ID)
        .title(TEST_AUCTION_TITLE)
        .content(TEST_AUCTION_CONTENT)
        .price(TEST_AUCTION_PRICE)
        .buyerId(TEST_USER4_ID)
        .statusEnum(StatusEnum.ON_SALE)
        .finishedAt(TEST_LOCAL_DATETIME_TOMORROW)
        .user(TEST_USER3)
        .file(TEST_FILE2)
        .build();

    //dto
    AuctionRequestDto TEST_AUCTION_REQUEST_DTO1 = new AuctionRequestDto(TEST_AUCTION_TITLE,
        TEST_AUCTION_CONTENT, TEST_FILE1_ID);

    //message
    String TEST_MESSAGE_STARTS_WITH_AUCTION_ID = "auctionId:";
    String TEST_MESSAGE_NOT_STARTS_WITH_AUCTION_ID = "wrongAuctionId:";


}
