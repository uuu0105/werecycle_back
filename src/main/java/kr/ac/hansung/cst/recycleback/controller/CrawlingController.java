package kr.ac.hansung.cst.recycleback.controller;

import kr.ac.hansung.cst.recycleback.model.Article;
import kr.ac.hansung.cst.recycleback.service.CrawlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/weRecycle/issues")
public class CrawlingController {
    @Autowired
    private CrawlingService offerService;

    @RequestMapping(path = "/{pagenum}", method = RequestMethod.GET)
    public ResponseEntity<?> showOffers(@PathVariable("pagenum") int pagenum) {
        List<Article> articles=offerService.getIssuePages(pagenum);
        return ResponseEntity.ok(articles);
    }

    @RequestMapping(path = "/word/{word}", method = RequestMethod.GET)
    public ResponseEntity<?> retrieveOffers(@PathVariable("word") String word) {
        List<Article> articles=offerService.getSearchCrawling(word);
        if(articles.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("검색결과가 없습니다. 첫페이지로 이동합니다.");
        return ResponseEntity.ok(articles);
    }

}
