package com.example.verma.bootapp.controller;

import com.example.verma.bootapp.dto.Book;
import com.example.verma.bootapp.exception.EmptyBookListForUserException;
import com.example.verma.bootapp.property.AmazonProperties;
import com.example.verma.bootapp.repository.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


    @RequestMapping(value = "/website/{reader}", method = RequestMethod.GET)
    public String readersBook(@PathVariable("reader") String reader, Model model){
        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList!=null){
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonID", amazonProperties.getAssociateId());
        }
        return "readingList";
    }

    @RequestMapping(value = "/website/{reader}", method = RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader, Book book){
        book.setReader(reader);
        readingListRepository.save(book);
        counterService.increment("books.save");
        gaugeService.submit("books.last.saved",System.currentTimeMillis());
        return "redirect:/website/{reader}";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{reader}", method = RequestMethod.GET)
    public List<Book> getBook(@PathVariable("reader") String reader, Model model){
        List<Book> readingList = readingListRepository.findByReader(reader);
        if(readingList.isEmpty()){
            throw new EmptyBookListForUserException("Book Not found for User");
        }
        return readingList;
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
    public void addBook(@PathVariable("reader") String reader, @RequestBody Book book){
        book.setReader(reader);
        readingListRepository.save(book);
    }
}
