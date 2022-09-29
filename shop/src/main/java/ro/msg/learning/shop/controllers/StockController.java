package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dtos.StockDTO;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.mappers.StockMapper;
import ro.msg.learning.shop.services.StockService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/csv-export")
public class StockController {
    private final StockService stockService;

    @GetMapping(value = "/{locationId}", produces = {"text/csv"})
    public List<StockDTO> exportStock(@PathVariable Integer locationId, HttpServletResponse response) {
        response.setHeader("Content-Disposition", "attachment; filename=exportedStocks.csv");
        List<Stock> stocksToExport = stockService.exportStocks(locationId);
        return StockMapper.stockListToStockListDTO(stocksToExport);
    }
}
