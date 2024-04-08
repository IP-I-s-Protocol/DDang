package com.ip.ddangddangddang.domain.auction.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ip.ddangddangddang.domain.auction.dto.request.AuctionRequestDto;
import com.ip.ddangddangddang.domain.auction.dto.response.AuctionResponseDto;
import com.ip.ddangddangddang.domain.auction.entity.Auction;
import com.ip.ddangddangddang.domain.auction.repository.AuctionRepository;
import com.ip.ddangddangddang.domain.file.entity.File;
import com.ip.ddangddangddang.domain.file.service.FileService;
import com.ip.ddangddangddang.domain.user.entity.User;
import com.ip.ddangddangddang.domain.user.service.UserService;
import com.ip.ddangddangddang.global.exception.custom.CustomAuctionException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final UserService userService;
    private final FileService fileService;

    @Transactional
    public void createAuction(AuctionRequestDto requestDto, Long userId) {
        User user = userService.getUser(userId);
        File file = fileService.findFileOrElseThrow(requestDto.getFileId());
        auctionRepository.save(new Auction(requestDto, user, file));
    }

    @Transactional
    public void deleteAuction(Long auctionId, Long userId) {
        Auction auction = findAuctionOrElseThrow(auctionId);


        if (!userId.equals(auction.getUser().getId())) {
            throw new IllegalArgumentException("작성자가 아닙니다.");
        }

        auctionRepository.delete(auction);
    }

    public List<AuctionResponseDto> getAuctionList(Long userId) {
        User user = userService.getUser(userId);
        String townList = user.getTown().getNeighborIdList();

        ObjectMapper mapper = new ObjectMapper();
        List<Long> neighbor = new ArrayList<>();
        try {
            neighbor = mapper.readValue(townList, new TypeReference<List<Long>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        List<AuctionResponseDto> response = new ArrayList<>();
        for (Long id : neighbor) {
            List<Auction> auctionList = auctionRepository.findAllByTownId(id);
            for (Auction auction : auctionList) {
                response.add(new AuctionResponseDto(auction));
            }
        }
        return response;
    }

    public Page<AuctionResponseDto> getAuctionsByTitle(String title, Pageable pageable) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("제목을 찾을 수 없습니다.");
        }
        Page<Auction> auctionList = auctionRepository.findAllByTitle(title, pageable);
        return auctionList.map(AuctionResponseDto::new);
    }

    public AuctionResponseDto getAuction(Long auctionId) {
        Auction auction = findAuctionOrElseThrow(auctionId);

        return new AuctionResponseDto(auction);
    }

    public Auction findAuctionOrElseThrow(Long auctionId) {
        return auctionRepository.findById(auctionId).orElseThrow(
            () -> new CustomAuctionException("게시글이 존재하지 않습니다.")
        );
    }

    public void isExistAuction(Long auctionId) {
        auctionRepository.findById(auctionId);
    }

}
