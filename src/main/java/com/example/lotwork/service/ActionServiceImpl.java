package com.example.lotwork.service;

import com.example.lotwork.DTO.BetDto;
import com.example.lotwork.DTO.CreateLot;
import com.example.lotwork.DTO.FullInfoLot;
import com.example.lotwork.DTO.LotDto;
import com.example.lotwork.exception.BetNotFoundException;
import com.example.lotwork.exception.LotNotFoundException;
import com.example.lotwork.model.Bet;
import com.example.lotwork.model.Lot;
import com.example.lotwork.model.Status;
import com.example.lotwork.repository.BetRepository;
import com.example.lotwork.repository.LotRepository;
import com.example.lotwork.repository.PaginLotRepository;
import lombok.Data;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


@Data
@Service
public abstract class ActionServiceImpl implements ActionService {
    private final LotRepository lotRepository;
    private final BetRepository betRepository;
    private final PaginLotRepository paginLotRepository;
    private static final Logger logger = LoggerFactory.getLogger(ActionServiceImpl.class);
    private Iterable<?> list;
    private Object writer;
    private Lot[] lot;

    @Override
    public FullInfoLot getEmployeeFullLotById(int id) {
        Lot lot = lotRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error(id + " не найден");
                    return new LotNotFoundException(id);
                });

        return FullInfoLot.fromLot(lot);
    }

    @Override
    public void startLot(int id) {
        Lot lot = lotRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error(id + " не найден");
                    return new LotNotFoundException(id);
                });
        logger.info("Торги по лоту " + id + " начаты");
        lot.setStatus(Status.STARTED);
        lotRepository.save(lot);
    }

    @Override
    public void placeABet(int id, String bidderName) {
        Lot lot = lotRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Лот с ID = " + id + " не найден");
                    return new LotNotFoundException(id);
                });
        if (lot.getStatus() == Status.STARTED) {
            Bet bet = new Bet(bidderName);
            List<Bet> bets = new LinkedList<>(lot.getBets());
            bets.add(bet);
            lot.setId(id);
            logger.info("Ставка по лоту " + id + " сделана");
            betRepository.save(bet);
            lotRepository.save(lot);
        } else {
            logger.error("Статус лота не позволяет сделать ставку");
        }
    }

    @Override
    public BetDto getFirstBidder(int id) {
        Lot lot = lotRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Лот с ID = " + id + " не найден");
                    return new LotNotFoundException(id);
                });
        Bet bet = lot.getBets().stream()
                .findFirst()
                .orElseThrow(() -> {
                    logger.error("Ставки нет");
                    return new BetNotFoundException();
                });
        return BetDto.fromBet(bet);
    }

    @Override
    public void stopLot(int id) {
        Lot lot = lotRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Лот с ID = " + id + " не найден");
                    return new LotNotFoundException(id);
                });
        logger.info("Лот " + id + " остановлен");
        lot.setStatus(Status.STOPPED);
        lotRepository.save(lot);
    }

    @Override
    public LotDto createLot(String title, String description, int startPrice, int bidPrice) {
        CreateLot createLot = new CreateLot(title, description, startPrice, bidPrice);
        Lot lot = createLot.toLot(createLot);
        lotRepository.save(lot);
        logger.info("Лот  создан");
        return LotDto.fromLot(lot);
    }

    @Override
    public List<LotDto> getLotsByStatusAndPage(Integer status, int page) {
        int unitPerPage = 10;
        PageRequest lotOfThePage = PageRequest.of(page, unitPerPage);
        Page<LotDto> pageLotDto = paginLotRepository.findAll(lotOfThePage);
        logger.info("Вызван метод getLotsByStatusAndPage");
        return pageLotDto.stream().filter(l -> l.getStatusId() == status).toList();
    }

    @Override
    public String getFrequentBidder(int id) {
        Lot lot = lotRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Лот с ID = " + id + " не найден");
                    return new LotNotFoundException(id);
                });
        String[] bidders = lot.getBets().stream()
                .map(Bet::getNameBidder)
                .toList().toArray(new String[0]);
        logger.info("Вызван метод getFrequentBidder");
        return mostPopular(bidders);
    }

    public static String mostPopular(String[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        Arrays.sort(array);

        String prev = array[0];
        String popular = array[0];
        int count = 1;
        int maxCount = 1;

        for (int i = 1; i < array.length; i++) {
            if (array[i].equals(prev)) {
                count++;
            } else {
                if (count > maxCount) {
                    popular = array[i - 1];
                    maxCount = count;
                }
                prev = array[i];
                count = 1;
            }
        }
        return count > maxCount ? array[array.length - 1] : popular;
    }

    @Override
    public byte[] exportLots() throws IOException {

        String fileName = "src/test/resources/lots.csv";
        String[] HEADERS = {"ID", "Status", "Title", "Description", "Start Price", "Bid Price"};

        FileWriter out = new FileWriter(fileName);
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
                .withHeader(HEADERS))) {

            lotRepository.findAll().forEach(l -> {
                try {
                    printer.printRecord(l);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        return File(byte[]);

    }
}

    
