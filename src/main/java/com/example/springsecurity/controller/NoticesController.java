package com.example.springsecurity.controller;

import com.example.springsecurity.model.Notice;
import com.example.springsecurity.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.List;


@RestController
@RequiredArgsConstructor
class NoticesController {
    private final NoticeRepository noticeRepository;

    @GetMapping("/notices")
    public ResponseEntity<List<Notice>> getNotices() {
        List<Notice> notices = noticeRepository.findAllActiveNotices();
        if (notices != null) {
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(Duration.ofSeconds(60)))
                    .body(notices);
        } else {
            return null;
        }
    }
}
