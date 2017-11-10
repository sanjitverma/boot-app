package com.example.verma.bootapp.controller;

import com.example.verma.bootapp.dto.Book;
import com.example.verma.bootapp.property.AmazonProperties;
import com.example.verma.bootapp.repository.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by SANJIT on 31/10/17.
 * A ‘gauge’ records a single value; and a ‘counter’ records a delta (an increment or decrement)
 */


@Controller
@RequestMapping(value = "/")
public class ReadingListController {

    @Autowired
    ReadingListRepository readingListRepository;
    @Autowired
    AmazonProperties amazonProperties;
    @Autowired
    CounterService counterService;
    @Autowired
    GaugeService gaugeService;


    @RequestMapping(value = "/{reader}", method = RequestMethod.GET)
    public String readersBook(@PathVariable("reader") String reader, Model model){
        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList!=null){
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonID", amazonProperties.getAssociateId());
        }
        return "readingList";
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader, Book book){
        book.setReader(reader);
        readingListRepository.save(book);
        counterService.increment("books.save");
        gaugeService.submit("books.last.saved",System.currentTimeMillis());
        return "redirect:/{reader}";
    }
}
