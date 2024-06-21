package com.ip.ddangddangddang.domain.town.service;

import com.ip.ddangddangddang.domain.town.entity.Town;
import com.ip.ddangddangddang.domain.town.repository.TownRepository;
import com.ip.ddangddangddang.domain.town.townlist.TownList;
import com.ip.ddangddangddang.domain.town.townlist.TownListRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TownService {

    private final Double DEFAULT_X = 111.35;
    private final Double DEFAULT_Y = 88.80;

    //기준 km
    private final Double DEFAULT_DISTANCE = 5.0;

    private final TownRepository townRepository;
    private final TownListRepository townListRepository;

    @Transactional
    public void createTown() {
        List<TownList> townList = townListRepository.findAll();
        List<Long> idList = new ArrayList<>();

        for (TownList tl : townList) {
            String name = getTownName(tl);

            //neighbor
            for (TownList tl2 : townList) {
                double x = Math.pow((tl.getX() - tl2.getX()) * DEFAULT_X, 2.0);
                double y = Math.pow((tl.getY() - tl2.getY()) * DEFAULT_Y, 2.0);

                double distance = Math.sqrt(x + y);

                if (distance < DEFAULT_DISTANCE) {
                    idList.add(tl2.getId());
                }
            }

            Town town = new Town(name, idList);
            townRepository.save(town);
            idList.clear();
        }
    }

    @Transactional
    public Town findTownByNameOrElseThrow(String address) {
        return townRepository.findByName(address).orElseThrow(
            () -> new IllegalArgumentException("해당 동네가 없습니다.")
        );
    }

    public Optional<Town> findTownByName(String address) {
        return townRepository.findByName(address);
    }

    public String getTownName(TownList t) {
        String name = t.getCity1();

        if (!t.getCity2().isEmpty()) {
            name += " " + t.getCity2();
        }
        if (!t.getCity3().isEmpty()) {
            name += " " + t.getCity3();
        }
        if (!t.getCity4().isEmpty()) {
            name += " " + t.getCity4();
        }
        if (!t.getCity5().isEmpty()) {
            name += " " + t.getCity5();
        }
        return name;
    }

    public String findNameByIdOrElseThrow(Long townId) {
        Town town = townRepository.findById(townId).orElseThrow(
            () -> new NoSuchElementException("해당 동네가 없습니다.")
        );

        return town.getName();
    }
}
