package com.tangtang.polingo.wordset.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "단어장 API", description = "단어 조회를 통해 얻은 데이터를 단어장에 저장하고 관리할 수 있습니다.")
@RestController
@RequestMapping("/api/wordset")
public class WordSetController {
}
